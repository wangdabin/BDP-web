package com.bdp.service;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONObject;

/**
 * 集群监控跳转转服务层接口,
 * 根据参数type的类型跳转具体的监控项
 * @author xuend
 *
 */
public interface StatisticService {

	void dispatch(HttpServletRequest request);

}
