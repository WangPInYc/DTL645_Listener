package com.wp.DTL645_Listener.dataUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


/**
 * 资源类
 * 
 * @author luma
 */
public class ServerConfig {

	/** 配置文件 */
	private static final String PROPERTY_FILE = "src/main/resources/param.properties";

	/**
	 * 根据Key 读取Value
	 * 
	 * @param key
	 * @return
	 */
	public static String readData(String key) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					PROPERTY_FILE));
			props.load(in);
			in.close();
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 修改或添加键值对 如果key存在，修改; 反之，添加。
	 * 
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public static void writeProperty(String key, String value)
			throws IOException {
		Properties prop = new Properties();
		File file = new File(PROPERTY_FILE);
		if (!file.exists())
			file.createNewFile();
		InputStream fis = new FileInputStream(file);
		prop.load(fis);
		fis.close();// 一定要在修改值之前关闭fis
		OutputStream fos = new FileOutputStream(file);
		prop.setProperty(key, value);
		prop.store(fos, "Update '" + key + "' value");
		fos.flush();
		fos.close();

	}
}
