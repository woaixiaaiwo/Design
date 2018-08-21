package com.boge.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * TransferFrom和TransferTo方法
 */
public class TransferFromAndTo {

	public static void main(String[] args) throws Exception {
		File input = new File("D://test.txt");
		File output = new File("D://test2.txt");
		transferTo(input, output);
	}
	
	private static void transferFrom(File fromFile,File toFile) throws Exception {
		ByteBuffer buffer1 = ByteBuffer.allocateDirect(0);
		FileChannel fromChannel = new FileInputStream(fromFile).getChannel();
		FileChannel toChannel = new FileOutputStream(toFile).getChannel();
		toChannel.write(buffer1);
		toChannel.transferFrom(fromChannel, 0, fromChannel.size());
	}
	
	private static void transferTo(File fromFile,File toFile) throws Exception {
		ByteBuffer buffer1 = ByteBuffer.allocateDirect(0);
		FileChannel fromChannel = new FileInputStream(fromFile).getChannel();
		FileChannel toChannel = new FileOutputStream(toFile).getChannel();
		toChannel.write(buffer1);
		fromChannel.transferTo(0,fromChannel.size(),toChannel);
	}
}
