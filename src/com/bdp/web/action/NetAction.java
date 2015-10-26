package com.bdp.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

import com.bdp.service.NetService;
import com.bdp.util.WebUtil;

/**
 * 集群网络监控类,
 * @author xuend
 *
 */
public class NetAction extends MultiAction{
	
	//注入service对象
	private NetService netService;

	public NetService getNetService() {
		return netService;
	}

	public void setNetService(NetService netService) {
		this.netService = netService;
	}


	/*
	 * 处理请求,查看集群的网络监控状态:
	 */
	public void statistic() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		JSONObject jsonObject = netService.statistic(request);
		response.getWriter().print(jsonObject.toString());
	}
	
	/*
	 * 处理请求,查看集群的网络监控详细信息:
	 */
	public void monitor() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		JSONObject jsonObject = netService.monitor(request);
		response.getWriter().print(jsonObject.toString());
	}
}
