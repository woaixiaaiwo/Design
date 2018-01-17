package com.boge.utils.distributedlock;

import java.time.Duration;
import java.time.Instant;

import org.apache.commons.lang.StringUtils;

import redis.clients.jedis.JedisCluster;

/**
 * 分布式锁的redis实现
 * 1.所需技术
 * redis的几个命令：
 * SETNX命令：将 key 的值设为 value ，当且仅当 key 不存在。
 * 若给定的 key 已经存在，则 SETNX 不做任何动作，且返回0
 * 若给定的 key 不存在，则设置当前的key值为value，且返回1
 * 
 * GET命令：获取指定key的值
 * 
 * GETSET命令：返回旧的key值，并设置新的value值
 * 
 * DELETE命令：删除指定key的值
 * 
 * 2.实现原理：
 * 假设有三个客户端:A,B,C，锁到期时间为10s(此Redis锁不使用轮询，即如果没获取到锁，直接返回false。所以不用设置获取锁的超时时间)
 * A先使用SETNX命令，创建一条记录，键为key，值为当前时间的毫秒数
 * 1)正常情况
 * 正常情况下，A没有宕机，如果此时B和C来请求锁，会先使用SETNX判断是否有锁，如果返回0，说明此时有锁，所以B和C都返回false
 * A的逻辑处理完成后，根据保存在redis中的时间和开始调用的时间判断是否超时，超时不处理，不超时则使用Delete命令删除键，释放锁
 * 2)A宕机的情况
 * 如果A因为宕机导致锁没有释放，此时B和C都来请求锁，B和C先用SETNX判断有锁，然后再使用get命令获取对应的值，再根据当前时间，判断
 * 锁超时了，此时B和C不能删除锁，原因是，如果B先删除了锁，然后SETNX命令，成功，紧接着C也删除锁，然后SETNX命令成功，就会导致B和C
 * 都获取到了锁。
 * 最终解决办法：如果发现锁超时了，那么B先使用GETSET命令，获取锁的时间戳，再判断是否超时，如果还是超时，说明B确实获取到了锁。如果在B
 * 之前，C先做了这部操作，改写了时间，那么B此时获取到的锁就不是超时的了，B就要再次等待。尽管B没拿到锁，但是它改写了C此前设置的时间戳，不过
 * 这点微小的误差可以忽略不计
 */
public class RedisLock{
	
	//锁超时时间
	private static final Integer LOCK_TIME_OUT = 10;
	
	private static final String LOCK_KEY = "wubotest";
	
	//这里的初始化要配置redis，否则jc会报空指针
	private JedisCluster jc;

	/**
	 * @param 
	 * 操作执行的开始时间，因为是单例模式，而lock和unlock都要获取开始时间，所以只能用局部变量处理
	 */
	public boolean lock(Instant now) {
		//获取本地时间戳
		//System.out.println("123***");
		//Instant now = Instant.now();
		String localTime = String.valueOf(now.toEpochMilli());
		//先使用SETNX尝试上锁
		if(jc.setnx(LOCK_KEY,localTime) == 1){
			return true;
		}else{
			//获取当前key的值
			String lockTime = jc.get(LOCK_KEY);
			Instant lockInstant = null;
			//如果在当前客户端获取时，其他客户端正好解锁，那么当前客户端也无法获取锁
			if(StringUtils.isEmpty(lockTime)){
				lockInstant = now;
			}
			else lockInstant = Instant.ofEpochMilli(Long.valueOf(lockTime));
			//判断是否超时
			if(Duration.between(lockInstant,now).getSeconds() <= LOCK_TIME_OUT){
				return false;
			}
			//超时，使用GETSET设置key的值
			lockTime = jc.getSet(LOCK_KEY,localTime);
			lockInstant = Instant.ofEpochMilli(Long.valueOf(lockTime));
			//再判断是否超时
			if(Duration.between(lockInstant,now).getSeconds() > LOCK_TIME_OUT){
				//超时，说明获取到的是旧key，获取锁成功
				return true;
			}
			//未超时，说明获取到的时间是另一个客户端的，不处理
		}
		return false;
	}

	/**
	 * @param 
	 * 操作执行的开始时间 ，因为是单例模式，而lock和unlock都要获取开始时间，所以只能用局部变量处理
	 */
	public void unlock(Instant now) {
		// TODO Auto-generated method stub
		//获取redis锁的时间
		String lockTime = jc.get(LOCK_KEY);
		if(!StringUtils.isEmpty(lockTime)){
			Instant end = Instant.ofEpochMilli(Long.valueOf(lockTime));
			//如果锁的时间和开始时间小于等于锁超时时间，解锁
			//判断是否超时
			if(Duration.between(now,end).getSeconds() <= LOCK_TIME_OUT){
				jc.del(LOCK_KEY);
			}
			/*System.out.println("开始时间为:"+now);
			System.out.println("结束时间为:"+end);
			System.out.println("差值为:"+Duration.between(now,end).getSeconds());*/
		}
		//获取不到，说明key已经被删除
	}
}
