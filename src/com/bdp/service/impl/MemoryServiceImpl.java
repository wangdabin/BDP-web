package com.bdp.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.bdp.rest.memory.MemoryClient;
import com.bdp.service.MemoryService;
import com.joe.core.utils.JsonUtils;
import com.joe.monitor.memory.MemoryCluster;

public class MemoryServiceImpl implements MemoryService {

	//注入rest对象
	private MemoryClient memoryClient;

	public MemoryClient getMemoryClient() {
		return memoryClient;
	}

	public void setMemoryClient(MemoryClient memoryClient) {
		this.memoryClient = memoryClient;
	}

	/**
	 * 监控处理,返回集群内存指标监控状况,
	 * @throws JSONException 
	 * 
	 */
	public JSONObject statistic(HttpServletRequest request) throws JSONException {
		JSONObject jsonObject = new JSONObject() ;
		MemoryCluster memoryCluster = memoryClient.getMemoryStatistic();
		//String s = memoryCluster.getUsedPercent();
		jsonObject.put("usedPercent", memoryCluster.getUsedPercent());
		return jsonObject;
	}

	/**
	 * 监控处理,返回集群内存指标监控详细状况
	 * @throws JSONException 
	 * 
	 */
	public JSONObject monitor(HttpServletRequest request) throws JSONException {
		JSONObject jsonObject = new JSONObject() ;
		MemoryCluster memoryCluster = memoryClient.getMemoryStatistic();
		jsonObject = JsonUtils.objectToJson(memoryCluster);
		return jsonObject;
	}

}
