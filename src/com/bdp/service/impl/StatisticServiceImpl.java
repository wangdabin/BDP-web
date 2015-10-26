package com.bdp.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONObject;

import com.bdp.service.StatisticService;

public class StatisticServiceImpl implements StatisticService {

	/**
	 * 处理监控状态的跳转
	 */
	public void dispatch(HttpServletRequest request) {
		request.getSession().setAttribute("type", request.getParameter("type"));
	}

}
