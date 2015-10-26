package com.bdp.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.bdp.rest.fs.FsClient;
import com.bdp.service.FsService;
import com.joe.core.utils.JsonUtils;
import com.joe.monitor.filesystem.DiskCluster;

public class FsServiceImpl implements FsService {

	//注入rest对象
	private FsClient fsClient;

	public FsClient getFsClient() {
		return fsClient;
	}

	public void setFsClient(FsClient fsClient) {
		this.fsClient = fsClient;
	}


	/**
	 * 监控处理,返回集群磁盘指标监控状况,
	 * @throws JSONException 
	 * 
	 */
	public JSONObject statistic(HttpServletRequest request) throws JSONException {
		JSONObject jsonObject = new JSONObject() ;
		DiskCluster diskCluster = fsClient.getStatInfo();
		jsonObject.put("usedPercent", diskCluster.getUsedPercent());
		return jsonObject;

	}
	public JSONObject monitor(HttpServletRequest request) throws JSONException {
		JSONObject jsonObject = new JSONObject() ;
		DiskCluster diskCluster = fsClient.getStatInfo();
		int size = diskCluster.getDiskUnits().size();
		jsonObject = JsonUtils.objectToJson(diskCluster);
		jsonObject.put("size", size);
		return jsonObject;
	}

}
