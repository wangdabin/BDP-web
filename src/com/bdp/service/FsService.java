package com.bdp.service;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * 集群状态监控服务层通用接口,负责磁盘的监控处理
 * @author xuend
 *
 */
public interface FsService {

	JSONObject statistic(HttpServletRequest request) throws JSONException;

	JSONObject monitor(HttpServletRequest request) throws JSONException;

}
