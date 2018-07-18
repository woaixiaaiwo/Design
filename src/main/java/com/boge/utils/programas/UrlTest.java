package com.boge.utils.programas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.boge.concurrency.threadpools.ExcutorServiceTest;

public class UrlTest {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
		sb.append((char) cp);
		}
		return sb.toString();
	}
	
	public static String getRequestFromUrl(String url) throws Exception {
		URL realUrl = new URL(url);
		Long start = System.currentTimeMillis();
		URLConnection conn = realUrl.openConnection();
		InputStream instream = conn.getInputStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
			String res = readAll(rd);
			Long end = System.currentTimeMillis();
			System.out.println("耗时:"+(end-start)/1000);
			return res;
		} finally {
			instream.close();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						getRequestFromUrl("http://www.uamazed.com");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		});
	}
}
