package com.bdp.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdp.service.StatisticService;
import com.bdp.util.WebUtil;

/**
 * 集群监控跳转转类,
 * 根据参数type的类型跳转具体的监控项
 * @author xuend
 *
 */
public class StatisticAction extends MultiAction{
	
	//注入service对象
	private StatisticService statisticService;

	public StatisticService getStatisticService() {
		return statisticService;
	}

	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	/*
	 * 处理请求,根据集群监控的不同,跳转不同的页面:
	 */
	public void dispatch() throws Exception {
		
		HttpServletRequest request = WebUtil.getRequest();
		HttpServletResponse response = WebUtil.getResponse();
		
		statisticService.dispatch(request);
		response.sendRedirect("../../cluster-statistic.jsp");
	}
}
