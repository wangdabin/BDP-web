package com.bdp.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

import com.bdp.service.FsService;
import com.bdp.util.WebUtil;

/**
 * 集群文件系统监控类,
 * @author xuend
 *
 */
public class FsAction extends MultiAction{
	
	//注入service对象
	private FsService fsService;

	public FsService getFsService() {
		return fsService;
	}

	public void setFsService(FsService fsService) {
		this.fsService = fsService;
	}


	/*
	 * 处理请求,查看集群的文件系统监控状态:
	 */
	public void statistic() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		JSONObject jsonObject = fsService.statistic(request);
		response.getWriter().print(jsonObject.toString());
	}
	
	/*
	 * 处理请求,查看集群的文件系统监控详细信息:
	 */
	public void monitor() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		JSONObject jsonObject = fsService.monitor(request);
		response.getWriter().print(jsonObject.toString());
	}
}
