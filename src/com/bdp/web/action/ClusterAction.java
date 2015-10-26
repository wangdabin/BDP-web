package com.bdp.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

import com.bdp.service.ClusterService;
import com.bdp.service.ServicesService;
import com.bdp.util.WebUtil;

/**
 * 集群处理类,
 * 查询集群的ip和端口是否已经配置,如没有配置则认为是首次安装客户端,跳转客户端初始化
 * @author xuend
 *
 */
public class ClusterAction extends MultiAction{
	
	//注入service对象
	private ClusterService clusterService;


	public ClusterService getClusterService() {
		return clusterService;
	}

	public void setClusterService(ClusterService clusterService) {
		this.clusterService = clusterService;
	}

	/*
	 * 处理请求,配置集群的ip和端口号
	 */
	public void add() throws Exception {
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		JSONObject jsonObject = clusterService.add(request);
		response.getWriter().print(jsonObject.toString());
	}
	
	/*
	 * 处理请求,查看集群ip端口是否已经配置
	 */
	public void check() throws Exception {
		
//		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		JSONObject jsonObject = clusterService.check();
		
		response.getWriter().print(jsonObject.toString());
	}
	
	/*
	 * 处理请求,查看集群的的监控状态:
	 * 主机数量,总内存容量,总硬盘数,总存储容量
	 */
	public void statistic() throws Exception {
		
//		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		JSONObject jsonObject = clusterService.statistic();
		response.getWriter().print(jsonObject.toString());
	}
}
