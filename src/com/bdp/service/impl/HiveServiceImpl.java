package com.bdp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONObject;

import com.bdp.rest.hbase.HbaseClient;
import com.bdp.rest.hive.HiveClient;
import com.bdp.rest.issued.IssuedClient;
import com.bdp.service.HiveService;
import com.bdp.util.FileConfigUtil;
import com.bdp.util.XMLUtil;
import com.joe.core.utils.JsonUtils;
import com.joe.core.vo.ReCode;
import com.joe.core.vo.ReCode.Data;
import com.joe.service.vo.ConfigProperty;
import com.joe.service.vo.ConfigurePropertySet;
import com.joe.service.vo.ServiceHost;
import com.joe.service.vo.ServiceVo;
import com.joe.service.vo.ServiceHost.ServiceRole;
import com.sky.task.vo.Task;

public class HiveServiceImpl implements HiveService{
	
	public static final int Hive_SUCCESS = 1;
	public static final int Hive_FAIL=-1;
	
	private HiveClient hiveClient;
	private IssuedClient issuedClient;
	
	public IssuedClient getIssuedClient() {
		return issuedClient;
	}

	public void setIssuedClient(IssuedClient issuedClient) {
		this.issuedClient = issuedClient;
	}

	public HiveClient getHiveClient() {
		return hiveClient;
	}

	public void setHiveClient(HiveClient hiveClient) {
		this.hiveClient = hiveClient;
	}

	public int install(HttpServletRequest request) {
		// TODO Auto-generated method stub

		HashSet<ConfigProperty> properties_hive_env = new HashSet<ConfigProperty>();
		HashSet<ConfigProperty> properties_hive_site = new HashSet<ConfigProperty>();
	
		ConfigurePropertySet propertySet_hive_env=new ConfigurePropertySet();
		ConfigurePropertySet propertySet_hive_site=new ConfigurePropertySet();
			
		List<ConfigurePropertySet> props = new ArrayList<ConfigurePropertySet>();
		Set<ServiceHost> hosts = new HashSet<ServiceHost>();
		ServiceVo serviceVo=new ServiceVo();

		String versionHive=request.getParameter("versionHive");
		String installHive=request.getParameter("installHive");
		String[] hive_envs = request.getParameterValues("hive-env.sh");	
		String[] hive_sites = request.getParameterValues("hive-site.xml");
		String[] hostIPs = request.getParameterValues("subBox4");
		
		//查询服务的配置文件Name 
//		String path_hive_env=HiveServiceImpl.class.getClassLoader().getResource("cdh5.1.0/hive-env.xml").getPath();
//		String path_hive_site=HiveServiceImpl.class.getClassLoader().getResource("cdh5.1.0/hive-site.xml").getPath();
//			
//		List<Object> hive_envNames=XMLUtil.read(path_hive_env, "name");
//		List<Object> hive_siteNames=XMLUtil.read(path_hive_site, "name");
//		List<Object> hive_env_isDir=XMLUtil.read(path_hive_env, "isDir");
//		List<Object> hive_site_isDir=XMLUtil.read(path_hive_site, "isDir");
//	
//		for (int i = 0; i < hive_envNames.size(); i++) {
//			ConfigProperty configProperty_hive_envs=new ConfigProperty();
//			configProperty_hive_envs.setName((String)hive_envNames.get(i));
//			configProperty_hive_envs.setValue(hive_envs[i]);
//			configProperty_hive_envs.setDir(Boolean.parseBoolean((hive_env_isDir.get(i).toString())));
//			properties_hive_env.add(configProperty_hive_envs);
//		}
//		propertySet_hive_env.setProperties(properties_hive_env);
//		propertySet_hive_env.setFileName("hive-env.sh");
//		propertySet_hive_env.setFileType("sh");
//
//		for (int i = 0; i < hive_siteNames.size(); i++) {
//			ConfigProperty configProperty_hive_sites=new ConfigProperty();
//			configProperty_hive_sites.setName((String)hive_siteNames.get(i));
//			configProperty_hive_sites.setValue(hive_sites[i]);
//			configProperty_hive_sites.setDir(Boolean.parseBoolean((hive_site_isDir.get(i).toString())));
//			properties_hive_site.add(configProperty_hive_sites);
//		}
//		propertySet_hive_site.setProperties(properties_hive_site);
//		propertySet_hive_site.setFileName("hive-site.xml");
//		propertySet_hive_site.setFileType("xml");
	
		propertySet_hive_env=FileConfigUtil.packageConfig(request, "cdh5.1.0/hive-env.xml", "hive-env.sh","sh");
		propertySet_hive_site=FileConfigUtil.packageConfig(request, "cdh5.1.0/hive-site.xml", "hive-site.xml","xml");
		
		props.add(propertySet_hive_env);
		props.add(propertySet_hive_site);
		
		for (int i = 0; i < hostIPs.length; i++) {
			ServiceHost host=new ServiceHost();
			host.setHostIp(hostIPs[i]);
			hosts.add(host);
		}
		
		serviceVo.setHosts(hosts);
		serviceVo.setProps(props);
		serviceVo.setInstallDir(installHive);
		serviceVo.setVersion(versionHive);
		serviceVo.setName("hive");
		serviceVo.setUserName("hive");

//		try {
//			jsonObject = BdpClient.post("http://127.0.0.1:8098/ws/v1/hadoop/deploy", serviceVo);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("serviceVo="+JsonUtils.objectToJson(serviceVo));
		ReCode	code=hiveClient.deployHive(serviceVo);
		int ret=code.getRet();
		System.out.println("code.getRet()="+ret);
		if(ret==code.RET_FAIL)
		{
			request.setAttribute("error_msg", code.getMsg()+"提交任务失败！");
			return Hive_FAIL;
		}
		if(ret==code.RET_SUCCESS)
		{
			Data data=code.getData();
			Long taskID=data.getId();

			request.setAttribute("taskID", taskID);
			request.setAttribute("path", "web/hive/getStatus");
			request.setAttribute("servicesName", "Hive");
			return Hive_SUCCESS;
		}
		return ret;

	}

	public JSONObject getStatus(HttpServletRequest request) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject() ;
		String taskID=request.getParameter("taskID");
		System.out.println("taskID="+taskID);
		Task task =issuedClient.getSingleTask(Long.valueOf(taskID));
		System.out.println("task.getOrders().size()="+task.getOrders().size());
		System.out.println("task.getCompletion()="+task.getCompletion());
		jsonObject=JsonUtils.objectToJson(task);
		System.out.println(jsonObject.toString());
		return jsonObject;
	}


}
