package com.boge.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * NIO通道和Buffer学习 
 */
public class ChannelAndBuffer {

	public static void main(String args[]) throws IOException {
        FileInputStream input = new FileInputStream("D://123.txt");
        ReadableByteChannel source = input.getChannel();
        /*FileOutputStream output = new FileOutputStream("D://456.txt");
        WritableByteChannel destination = output.getChannel();*/
        printData(source);
        source.close();
        //destination.close();
        //System.out.println("Copy Data finished.");
    }

    private static void copyData(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(20 * 1024);
        //首先读取数据到Buffer
        while (src.read(buffer) != -1) {
            //然后将指针指向buffer的起始位置
            buffer.flip();
            //while循环确保buffer能被读取完
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
            buffer.clear(); //现在缓冲区已被读取完毕，清空以备再次使用
        }
    }
    
    private static void printData(ReadableByteChannel src) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(5);
        byte[] dst = new byte[5];
        List<Byte> list = new ArrayList<>();
        //首先读取数据到Buffer
        while (src.read(buffer) != -1) {
            //然后将指针指向buffer的起始位置
            buffer.flip();
            //while循环确保buffer能被读取完
            while (buffer.hasRemaining()) {
            	buffer.get(dst,0,buffer.limit());
            	for(int i=0;i<buffer.limit();i++) {
            		list.add(dst[i]);
            	}
            	
            }
            buffer.clear(); //现在缓冲区已被读取完毕，清空以备再次使用
        }
        Byte[] arr = list.toArray(new Byte[list.size()]);
        byte[] res = new byte[arr.length];
        for(int i=0;i<arr.length;i++) {
        	res[i] = arr[i];
        }
        System.out.println(list);
        System.out.println(new String(res));
    }
	
}
