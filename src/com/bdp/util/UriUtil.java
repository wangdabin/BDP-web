package com.bdp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 从配置文件中解析uri路径
 * @author xuend
 *
 */
public class UriUtil {

	/*多个集群时通过配置文件读取当前服务的ip和端口*/
	private static String ip = "localhost";
	private static String port = "8098";
	public static String webroot = "/ws/v1";
	public static String basePath ;
	
	/*
	static{
		try {
			InputStream ClusterIn = Thread.currentThread().getContextClassLoader().getResourceAsStream("ClusterConfig.properties");
			Properties ClusterProperties = new Properties();
			ClusterProperties.load(ClusterIn);
			ip = ClusterProperties.getProperty("ip");
			port = ClusterProperties.getProperty("port");
			webroot = ClusterProperties.getProperty("webroot");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	public static String getBasePath(){
		return "http://" + ip + ":" + port + webroot;
	}
	public static String getUri(String requestUri){
		String path = requestUri.substring(9);
		StringBuilder uri = new StringBuilder();
		uri.append("http://");
		uri.append(ip);
		uri.append(":");
		uri.append(port);
		uri.append("/ws/v1/");
		uri.append(path);
		return uri.toString() ;
	}
	public static void main(String[] args) {
		System.out.println("");
	}
}
