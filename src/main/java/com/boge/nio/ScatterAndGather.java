package com.boge.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 *  Scatter和Gather方法学习
 *  Java NIO开始支持scatter/gather，scatter/gather用于描述从Channel中读取或者写入到Channel的操作。
 *  分散（scatter）从Channel中读取是指在读操作时将读取的数据写入多个buffer中。因此，scatter将从Channel中读取的数据“分散（scatter）”到多个Buffer中。
 *  聚集（gather）写入Channel是指在写操作时将多个buffer的数据写入同一个Channel，因此，gather将多个Buffer中的数据“聚集（gather）”后发送到Channel。
 */
public class ScatterAndGather {

	public static void main(String[] args) throws Exception {
		 /*FileInputStream input = new FileInputStream("D://test.txt");
		 FileChannel source = input.getChannel();
         scatter(source);
         source.close();*/
         
         FileOutputStream output = new FileOutputStream("D://test2.txt");
		 FileChannel source = output.getChannel();
         gather(source);
	}
	
	private static void scatter(FileChannel channel) throws Exception {
		ByteBuffer buffer1 = ByteBuffer.allocateDirect(15);
		ByteBuffer buffer2 = ByteBuffer.allocateDirect(15);
		byte[] dst = new byte[15];
		ByteBuffer[] bufferArray = { buffer1, buffer2 };
		channel.read(bufferArray);
		buffer1.flip();
		buffer2.flip();
		buffer1.get(dst,0,buffer1.limit());
		System.out.println(new String(dst));
		buffer2.get(dst,0,buffer1.limit());
		System.out.println(new String(dst));
	}
	
	private static void gather(FileChannel channel) throws Exception {
		ByteBuffer buffer1 = ByteBuffer.allocateDirect(15);
		ByteBuffer buffer2 = ByteBuffer.allocateDirect(15);
		
		String s1="第一个Buffer";
		String s2="第二个Buffer";
		buffer1.put(s1.getBytes());
		buffer2.put(s2.getBytes());
		//每次写完Buffer之后，必须要flip，把position置为0，否则会读取不到数据
		buffer1.flip();
		buffer2.flip();
		ByteBuffer[] bufferArray = {buffer1,buffer2};
		
		channel.write(bufferArray);
		channel.close();
	}
}
