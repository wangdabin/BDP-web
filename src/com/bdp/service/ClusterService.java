package com.bdp.service;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * 集群处理服务层接口
 * @author xuend
 *
 */
public interface ClusterService {

	JSONObject check();

	JSONObject add(HttpServletRequest request) throws Exception;

	JSONObject statistic();

}
