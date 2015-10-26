package com.bdp.common;


import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdp.util.WebUtil;

/**
 * 前端控制器
 * 请求转发Action
 * 		解析URI,执行Action的业务方法。
 * 
 * 例子：
 * 		URI : /bdp/web/services/add
 * 
 * 		hosts+"Action"	表示Action的Bean的id名称
 * 		add     		表示Aciton对象的业务方法名称
 * 		/web    		表示前缀名称。当请求的uri是以/web开头，则前端控制器执行请求转发
 * 
 * @author xuend
 */
public class DispatcherServlet extends HttpServlet {
	
	private static String actionId = "";
	private static String actionMethodName="";
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		WebUtil.setHttpObjectToThreadLocal(request,response);
		//获取请求的URI
		String uri = request.getRequestURI();
		
		if(uri.endsWith(".jsp")){
			request.getRequestDispatcher(uri).forward(request, response);
		}else{
			//解析URI - 获取Action的id已经Action的方法名
			String action = uri.substring(9);
			int index = action.indexOf("/");
			if(index == -1){
				actionId = action + "Action";
				actionMethodName = "list";
			}else{
				actionId = action.substring(0, action.lastIndexOf("/")) + "Action";
				//String str = action.substring(action.lastIndexOf("/")+1);
				if("".equals(action.substring(action.lastIndexOf("/")+1))||action.substring(action.lastIndexOf("/")+1)==""){
					actionMethodName = "list";
				}else{
					actionMethodName = action.substring(action.lastIndexOf("/")+1);
				}
			}
			
			try {
				
				//根据id获取Action对象
				Object actionObject = BeanFactory.getActionObject(actionId);
				System.out.println("actionObject="+actionObject);
				
				Class actionClazz = actionObject.getClass();
				Method actionMethodObject = actionClazz.getMethod(actionMethodName);
				actionMethodObject.invoke(actionObject);
			} catch (Exception e) {
				e.printStackTrace();
				//response.sendRedirect("/404-page.html");
			} 
		}
	}
	
}
