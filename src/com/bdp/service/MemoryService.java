package com.bdp.service;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * 集群状态监控服务层通用接口,负责内存的监控处理
 * @author xuend
 *
 */
public interface MemoryService {

	JSONObject statistic(HttpServletRequest request) throws JSONException;

	JSONObject monitor(HttpServletRequest request) throws JSONException;

}
