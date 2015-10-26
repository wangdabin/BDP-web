package com.bdp.service;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * 集群状态监控服务层通用接口,负责cpu的监控处理
 * @author xuend
 *
 */
public interface CpuService {

	JSONObject statistic(HttpServletRequest request) throws JSONException;

	JSONObject monitor(HttpServletRequest request);

}
