package com.boge.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * NIO通道 
 */
public class ChannelTest {

	public static void main(String args[]) throws IOException {
        FileInputStream input = new FileInputStream("D://123.txt");
        ReadableByteChannel source = input.getChannel();
        FileOutputStream output = new FileOutputStream("D://456.txt");
        WritableByteChannel destination = output.getChannel();
        copyData(source, destination);
        source.close();
        destination.close();
        System.out.println("Copy Data finished.");
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
	
}
