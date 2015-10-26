package com.bdp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter{
	

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		HttpSession session = request.getSession();
		String path = request.getServletPath();
		
		if(session.getAttribute("user")!=null||path.equals("/login.jsp")||path.equals("/servlet/login")){
			chain.doFilter(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			
		}
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
