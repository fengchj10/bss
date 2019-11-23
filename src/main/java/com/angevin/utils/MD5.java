/**
 * Copyright (c) 2012 netqin.
 * All Rights Reserved
 */

package com.angevin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @auther Angevin
 * @date 2019年11月1日
 */
public class MD5 {
	private static  Logger log =  LoggerFactory.getLogger(MD5.class);

	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private static final ThreadLocal<MessageDigest> messageDigest = ThreadLocal.withInitial(() -> {
		try {
			MessageDigest messagedigest = MessageDigest.getInstance("MD5");
			return messagedigest;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	});
	
	/**
	 * md5算法
	 */
	public static String md5(String string) {
		return md5(string, "UTF-8");
	}
	
	/**
	 * md5算法
	 * @param string
	 * @param charset
	 * @return
	 */
	public static String md5(String string, String charset) {
		try {

			// MD5 的计算结果
			byte tmp[] = messageDigest.get().digest(
					string.getBytes(charset));
			//最后结果缓冲区
			StringBuilder buf = new StringBuilder(32);
			for (byte b : tmp) {
				buf.append(String.format("%02x", b & 0xff));
			}
			return buf.toString();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getFileMD5String(File file) throws IOException{
		FileInputStream in = new FileInputStream(file);
		FileChannel ch =in.getChannel();
		MappedByteBuffer byteBuffer =ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		messageDigest.get().update(byteBuffer);
		return bufferToHex(messageDigest.get().digest());
	}
	
	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}
	
	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}
	

	public static void main(String args[]) {
		log.info("MD5(\"admin\"): " + md5("admin"));
		log.info("MD5(\"admin123!@#\"): " + md5("admin123!@#"));
		log.info("MD5(\"auditor123!@#\"): " + md5("auditor123!@#"));
		log.info("MD5(\"NQ_auditor123!@#^Sky\"): " + md5("NQ_auditor123!@#^Sky"));
		log.info("MD5(\"123456\"): " + md5("123456"));
		log.info("MD5(\"NQ_123456^Sky\"): " + md5("NQ_123456^Sky"));
		log.info("MD5(\"NQ_Nqsky1130^Sky\"): " + md5("NQ_Nqsky1130^Sky"));
		
	}
	
 

}