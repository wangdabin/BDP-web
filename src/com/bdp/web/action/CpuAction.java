package com.bdp.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

import com.bdp.service.CpuService;
import com.bdp.util.WebUtil;

/**
 * 集群CPU监控类,
 * @author xuend
 *
 */
public class CpuAction extends MultiAction{
	
	//注入service对象
	private CpuService cpuService;


	public CpuService getCpuService() {
		return cpuService;
	}

	public void setCpuService(CpuService cpuService) {
		this.cpuService = cpuService;
	}



	/*
	 * 处理请求,查看集群的CPU监控状态:
	 */
	public void statistic() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		JSONObject jsonObject = cpuService.statistic(request);
		response.getWriter().print(jsonObject.toString());
	}
	
	/*
	 * 处理请求,查看集群的内存监控详细信息:
	 */
	public void monitor() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		JSONObject jsonObject = cpuService.monitor(request);
		response.getWriter().print(jsonObject.toString());
	}
}
