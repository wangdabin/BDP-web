package com.bdp.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.bdp.common.BdpClient;
import com.bdp.config.ClientConfigUtils;
import com.bdp.service.ClusterService;
import com.bdp.util.UriUtil;
import com.sky.config.Configed;
import com.sun.jndi.toolkit.url.UrlUtil;

public class ClusterServiceImpl extends Configed implements ClusterService {
	
	public ClusterServiceImpl(){
		this(ClientConfigUtils.create());
	}

	public ClusterServiceImpl(Configuration conf) {
		super(conf);
	}
	
	/*
	 * 检查集群ip和端口号是否为空
	 */
	public JSONObject check() {
		JSONObject jsonObject = new JSONObject() ;
		String ip = getConf().getString("bdp.server.ip");
		String port = getConf().getString("bdp.server.port");
		try {
			if(ip==null||"".equals(ip)){
				jsonObject.put("success", false);
			}else{
				jsonObject.put("success", true);
				jsonObject.put("ip", ip);
				jsonObject.put("port", port);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/*
	 * 保存集群ip和端口号
	 */
	public JSONObject add(HttpServletRequest request) throws Exception {
		String ip=request.getParameter("ip");
		String port=request.getParameter("port");
		JSONObject jsonObject = new JSONObject() ;
		
		getConf().setProperty("bdp.server.ip", ip);
		getConf().setProperty("bdp.server.port", port);
		ClientConfigUtils.updateConfig(getConf());
		jsonObject.put("success",true);
		return jsonObject;
	}
	
	/*
	 * 获取集群监控指标状态
	 */
	public JSONObject statistic() {
		JSONObject jsonObject = new JSONObject() ;
		try {
			jsonObject.put("host_totoal", "38");
			jsonObject.put("memory_total", "5800");
			jsonObject.put("disk_num", "38");
			jsonObject.put("disk_total", "4900");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = ClientConfigUtils.create();
		conf.setProperty("kkey", "test");
		conf.setProperty("kkey222", "test");
		ClientConfigUtils.updateConfig(conf);
	}

}
