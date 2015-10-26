package com.bdp.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

import com.bdp.service.HadoopService;
import com.bdp.service.ZookeeperService;
import com.bdp.util.WebUtil;

/**
 * 服务跳转处理类,完成服务添加的页面跳转,加载一些配置参数,并不执行真正的服务部署操作
 * @author xs
 *
 */
public class ZookeeperAction extends MultiAction{

	//注入service对象
	private ZookeeperService zookeeperService;

	
	public ZookeeperService getZookeeperService() {
		return zookeeperService;
	}
	public void setZookeeperService(ZookeeperService zookeeperService) {
		this.zookeeperService = zookeeperService;
	}
	/*
	 * 处理请求,新增服务页面跳转加载配置模板
	 */
	public void install() throws Exception {

		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();

		int ret=zookeeperService.install(request);

		String path = WebUtil.getRequest().getContextPath();;
		if(ret==-1)
		{
			request.getRequestDispatcher("../../404-page.jsp").forward(request, response);
		}
		if(ret==1)
		{
			request.getRequestDispatcher("../../process-bar.jsp").forward(request, response);
		}
	}
	public void getStatus() throws Exception {

		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();

		JSONObject jsonObject = zookeeperService.getStatus(request);

		response.getWriter().print(jsonObject.toString());
	}


}
