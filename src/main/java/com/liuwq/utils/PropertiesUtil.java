package com.liuwq.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * <br>
 * 标题: <br>
 * 描述: 获取配置文件信息<br>
 * 公司: www.tydic.com<br>
 * @autho daiyuanyuan
 * @time 2018年4月13日 下午6:32:01
 */
public class PropertiesUtil {

	private static Properties props;

	static {
		loadProps();
	}

	synchronized static private void loadProps() {
		props = new Properties();
		InputStream in = null;
		try {
			in = PropertiesUtil.class.getClassLoader().getResourceAsStream("dev.properties");
			if(null==in){
				in = PropertiesUtil.class.getClassLoader().getResourceAsStream("test.properties");
				if(in==null){
					in = PropertiesUtil.class.getClassLoader().getResourceAsStream("prod.properties");
				}
			}
			props.load(in);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (null != in) {
					in.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getProperty(String key) {
		if (null == props) {
			loadProps();
		}
		return props.getProperty(key);
	}

	public static int getIntProperty(String key) {
		if (null == props) {
			loadProps();
		}
		return Integer.parseInt(props.getProperty(key));
	}

	public static boolean getBooleanProperty(String key) {
		if (null == props) {
			loadProps();
		}
		return Boolean.getBoolean(props.getProperty(key));
	}

	public static String getProperty(String key, String defaultValue) {
		if (null == props) {
			loadProps();
		}
		return props.getProperty(key, defaultValue);
	}

}
