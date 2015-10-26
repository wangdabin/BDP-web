package com.bdp.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

import com.bdp.service.MemoryService;
import com.bdp.util.WebUtil;

/**
 * 集群内存监控类,
 * @author xuend
 *
 */
public class MemoryAction extends MultiAction{
	
	//注入service对象
	private MemoryService memoryService;

	public MemoryService getMemoryService() {
		return memoryService;
	}

	public void setMemoryService(MemoryService memoryService) {
		this.memoryService = memoryService;
	}

	/*
	 * 处理请求,查看集群的内存监控状态:
	 */
	public void statistic() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		JSONObject jsonObject = memoryService.statistic(request);
		response.getWriter().print(jsonObject.toString());
	}
	
	/*
	 * 处理请求,查看集群的内存监控详细信息:
	 */
	public void monitor() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		JSONObject jsonObject = memoryService.monitor(request);
		response.getWriter().print(jsonObject.toString());
	}
}
