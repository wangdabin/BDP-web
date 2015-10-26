package com.bdp.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

import com.bdp.service.HostsService;
import com.bdp.util.WebUtil;
import com.bdp.vo.ClientHost;
import com.joe.host.vo.FindResult;

/**
 * 主机处理类,完成主机的各种处理请求
 * @author xuend
 *
 */
public class HostsAction extends MultiAction{
	
	
	//注入service对象
	private HostsService hostsService;

	private ClientHost clientHost = new ClientHost();
	
	public HostsService getHostsService() {
		return hostsService;
	}

	public void setHostsService(HostsService hostsService) {
		this.hostsService = hostsService;
	}

	/*
	 * 处理请求,列出所有主机信息
	 */
	public void list() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		List<ClientHost> hosts = new ArrayList<ClientHost>() ;
				
		hosts = hostsService.list();
		
		request.setAttribute("hosts", hosts);
		request.getRequestDispatcher("/hosts.jsp").forward(request, response);
	}
	
	/*
	 * 处理请求,单个添加主机
	 */
	public void addSingle() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		boolean flag = hostsService.addSingle(request);
		if(flag){
			request.getRequestDispatcher("../../hostProcess.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("../../hostError.jsp").forward(request, response);
		}
	}
	
	/*
	 * 处理请求,根据文本输入批量新增主机
	 */
	public void addByText() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		//将请求参数封装到指定的数据模型对象中
		//WebUtil.setParamToObject(request,cHost);
		
		boolean flag = hostsService.addByText(request);
//		String path = request.getContextPath();
//		String basePath = request.getScheme() + "://" + request.getServerName()
//				+ ":" + request.getServerPort() + path + "/";
		if(flag){
			request.getRequestDispatcher("../../hostProcess.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("../../hostError.jsp").forward(request, response);
		}
	}
	
	/*
	 * 处理请求,根据文件导入批量新增主机
	 */
	public void addByFile() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		//将请求参数封装到指定的数据模型对象中
		//WebUtil.setParamToObject(request,cHost);
		
		boolean flag = hostsService.addByFile(request);
		
		if(flag){
			request.getRequestDispatcher("../../hostProcess.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("../../hostError.jsp").forward(request, response);
		}
	}
	
	/*
	 * 处理请求,根据根据开始ip和结束ip查询主机
	 */
	public void checkIps() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		//将请求参数封装到指定的数据模型对象中
		//WebUtil.setParamToObject(request,cHost);
		JSONObject jsonObject = new JSONObject();
		jsonObject = hostsService.checkIps(request);
		
		System.out.println(jsonObject.toString());
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(jsonObject.toString());
	}
	
	/*
	 * 处理请求,根据文件导入批量新增主机
	 */
	public void addByIps() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		//将请求参数封装到指定的数据模型对象中
		//WebUtil.setParamToObject(request,cHost);
		
		hostsService.addByIps(request);
		
		request.getRequestDispatcher("../../hostProcess.jsp").forward(request, response);
	}
	
	/*
	 * 处理请求,检查安装进度
	 */
	public void monitor() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		JSONObject jsonObject = hostsService.monitor(request);
		
		response.getWriter().print(jsonObject.toString());;
	}
}
