package com.bdp.service.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.bdp.rest.cpu.CpuClient;
import com.bdp.service.CpuService;
import com.joe.core.utils.JsonUtils;
import com.joe.monitor.cpu.CpuCluster;
import com.sky.monitor.MonitorUtils;

public class CpuServiceImpl implements CpuService {

	//注入rest对象
	private CpuClient cpuClient;
	
	public CpuClient getCpuClient() {
		return cpuClient;
	}

	public void setCpuClient(CpuClient cpuClient) {
		this.cpuClient = cpuClient;
	}


	/**
	 * 监控处理,返回集群Cpu指标监控状况,
	 * @throws JSONException 
	 * 
	 */
	public JSONObject statistic(HttpServletRequest request) throws JSONException {
		JSONObject jsonObject = new JSONObject() ;
		CpuCluster cpuCluster = cpuClient.getStatInfo();
		//System.out.println(cpuCluster);
		String idle =cpuCluster.getIdle();
		jsonObject.put("idle",idle);
		return jsonObject;
		
	}
	
	public static void main(String[] args) throws IOException {
		CpuClient cpuClient = new CpuClient();
		CpuCluster cpuCluster = cpuClient.getStatInfo();
	}

	public JSONObject monitor(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject() ;
		CpuCluster cpuCluster = cpuClient.getStatInfo();
		jsonObject = JsonUtils.objectToJson(cpuCluster);
		return jsonObject;
	}
}
