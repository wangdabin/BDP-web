package com.bdp.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdp.service.ServicesService;
import com.bdp.util.WebUtil;

/**
 * 服务跳转处理类,完成服务添加的页面跳转,加载一些配置参数,并不执行真正的服务部署操作
 * @author xuend
 *
 */
public class ServicesAction extends MultiAction{
	
//	public ServicesAction(){
//		System.out.println("********ServicesAction="+this);
//	}
	
	//注入service对象
	private ServicesService servicesService;
	
	public ServicesService getServicesService() {
		return servicesService;
	}

	public void setServicesService(ServicesService servicesService) {
		this.servicesService = servicesService;
	}
	
	/*
	 * 处理请求,新增服务页面跳转加载配置模板
	 */
	public void add() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		servicesService.add(request);
		
		String path = WebUtil.getRequest().getContextPath();
		
		request.getRequestDispatcher("../../add-services.jsp").forward(request, response);
	}
	
	/*
	 * 处理请求,新增服务页面跳转加载配置模板
	 */
	public void list() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		servicesService.list(request);
		
		request.getRequestDispatcher("../../service-monitor2.jsp").forward(request, response);
	}



	
	
}
