package com.bdp.util;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Web层工具类,用于传递Http对象
 * @author xuend
 */
public class WebUtil {
	
	private static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<HttpServletResponse>();
	
	/**
	 * 将Http对象与当前线程进行绑定
	 * @param request
	 * @param response
	 */
	public static void setHttpObjectToThreadLocal(HttpServletRequest request,HttpServletResponse response){
		requestThreadLocal.set(request);
		responseThreadLocal.set(response);
	}
	
	/**
	 * 从ThreadLocal中获取请求对象
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		return requestThreadLocal.get();
	}
	
	/**
	 * 从ThreadLocal中获取响应对象
	 * @return
	 */
	public static HttpServletResponse getResponse(){
		return responseThreadLocal.get();
	}

}
