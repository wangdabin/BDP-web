package com.bdp.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.bdp.common.BdpClient;
import com.bdp.rest.cpu.CpuClient;
import com.bdp.rest.net.NetClient;
import com.bdp.service.NetService;
import com.bdp.util.UriUtil;
import com.joe.core.utils.JsonUtils;
import com.joe.monitor.net.NetInterfaceCluster;

public class NetServiceImpl implements NetService {

	//注入rest对象
	private NetClient netClient;

	public NetClient getNetClient() {
		return netClient;
	}

	public void setNetClient(NetClient netClient) {
		this.netClient = netClient;
	}



	/**
	 * 监控处理,返回集群网络指标监控状况,
	 * @throws JSONException 
	 * 
	 */
	public JSONObject statistic(HttpServletRequest request) throws JSONException {
		JSONObject jsonObject = new JSONObject() ;
		NetInterfaceCluster netInterfaceCluster = netClient.getStatInfo();
		jsonObject.put("rxBytes",netInterfaceCluster.getRxBytes());
		jsonObject.put("speed",netInterfaceCluster.getSpeed());
		jsonObject.put("txBytes",netInterfaceCluster.getTxBytes());
		return jsonObject;
	}

	public JSONObject monitor(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject() ;
		NetInterfaceCluster netInterfaceCluster = netClient.getStatInfo();
		jsonObject = JsonUtils.objectToJson(netInterfaceCluster);
		return jsonObject;
	}

}
