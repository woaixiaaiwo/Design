package com.boge.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class SelectorLearn {

	public static void main(String[] args) {
	}
	
	private static void testSelector() throws Exception {
		Selector selector = Selector.open();
		SocketChannel channel = SocketChannel.open();
		//为了将Channel和Selector配合使用，必须将channel注册到selector上。
		//通过SelectableChannel.register()方法来实现，如下：
		channel.configureBlocking(false);
		//将通道注册到选择器上
		SelectionKey key = channel.register(selector,SelectionKey.OP_READ);
		/**
		与Selector一起使用时，Channel必须处于非阻塞模式下。
		这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以。
		注意register()方法的第二个参数。这是一个“interest集合”，意思是在通过Selector监听Channel时对什么事件感兴趣。可以监听四种不同类型的事件：
		1.Connect
		2.Accept
		3.Read
		4.Write
		通道触发了一个事件意思是该事件已经就绪。所以，某个channel成功连接到另一个服务器称为“连接就绪”。一个server socket channel准备好接收新进入的连接称为“接收就绪”。
		一个有数据可读的通道可以说是“读就绪”。等待写数据的通道可以说是“写就绪”。这四种事件用SelectionKey的四个常量来表示：
		1.SelectionKey.OP_CONNECT
		2.SelectionKey.OP_ACCEPT
		3.SelectionKey.OP_READ
		4.SelectionKey.OP_WRITE
		如果你对不止一种事件感兴趣，那么可以用“位或”操作符将常量连接起来，如下：
		int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
		*/
		while(true) {
		  //select方法会阻塞，如果有某个事件完成了，就放行
		/**
		 一旦向Selector注册了一或多个通道，就可以调用几个重载的select()方法。这些方法返回你所感兴趣的事件（如连接、接受、读或写）已经准备就绪的那些通道。换句话说，如果你对“读就绪”的通道感兴趣，select()方法会返回读事件已经就绪的那些通道。
		 下面是select()方法：
		 int select()
		 int select(long timeout)
		 int selectNow()
		 select()阻塞到至少有一个通道在你注册的事件上就绪了。
		 select(long timeout)和select()一样，除了最长会阻塞timeout毫秒(参数)。
		 selectNow()不会阻塞，不管什么通道就绪都立刻返回（译者注：此方法执行非阻塞的选择操作。如果自从前一次选择操作后，没有通道变成可选择的，则此方法直接返回零。）。
		 select()方法返回的int值表示有多少通道已经就绪。亦即，自上次调用select()方法后有多少通道变成就绪状态。如果调用select()方法，因为有一个通道变成就绪状态，返回了1，若再次调用select()方法，如果另一个通道就绪了，它会再次返回1。如果对第一个就绪的channel没有做任何操作，现在就有两个就绪的通道，但在每次select()方法调用之间，只有一个通道就绪了。 
		 */
		  int readyChannels = selector.select();
		  //readyChannels不为0，说明已经有某个事件就绪了。此时遍历选择器上的通道，执行下一步操作
		  if(readyChannels == 0) continue;
		  //获取一旦调用了select()方法，并且返回值表明有一个或更多个通道就绪了，然后可以通过调用selector的selectedKeys()方法，访问“已选择键集（selected key set）”中的就绪通道。
		  //当向Selector注册Channel时，Channel.register()方法会返回一个SelectionKey 对象。这个对象代表了注册到该Selector的通道。可以通过SelectionKey的selectedKeySet()方法访问这些对象。
		  Set<SelectionKey> selectedKeys = selector.selectedKeys();
		  Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
		  while(keyIterator.hasNext()) {
		    key = keyIterator.next();
		    if(key.isAcceptable()) {
		        // a connection was accepted by a ServerSocketChannel.
		    } else if (key.isConnectable()) {
		        // a connection was established with a remote server.
		    } else if (key.isReadable()) {
		        // a channel is ready for reading
		    } else if (key.isWritable()) {
		        // a channel is ready for writing
		    }
		    keyIterator.remove();
		  }
		}

	}
	
	private static void pailie(char[] arr,int num) {
		
		if(num == arr.length) {
			System.out.println(Arrays.toString(arr));
		}
		
		for(int i=num;i<arr.length;i++) {
			swap(arr, num, i);
			pailie(arr, num+1);
			swap(arr, num, i);
		}
		
	}
	
	private static void swap(char[] arr,int i,int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
}
