package com.bdp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONObject;

import com.bdp.rest.hbase.HbaseClient;
import com.bdp.service.HBaseService;
import com.bdp.util.FileConfigUtil;
import com.bdp.util.XMLUtil;
import com.joe.core.utils.JsonUtils;
import com.joe.service.vo.ConfigProperty;
import com.joe.service.vo.ConfigurePropertySet;
import com.joe.service.vo.ServiceHost;
import com.joe.service.vo.ServiceVo;

public class HBaseServiceImpl implements HBaseService{
	
	private HbaseClient hbaseClient;

	public HbaseClient getHbaseClient() {
		return hbaseClient;
	}

	public void setHbaseClient(HbaseClient hbaseClient) {
		this.hbaseClient = hbaseClient;
	}

	public void install(HttpServletRequest request) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject() ;

		HashSet<ConfigProperty> properties_hbase_env = new HashSet<ConfigProperty>();
		HashSet<ConfigProperty> properties_hbase_site = new HashSet<ConfigProperty>();
		HashSet<ConfigProperty> properties_Regionservers = new HashSet<ConfigProperty>();
		
		ConfigurePropertySet propertySet_hbase_env=new ConfigurePropertySet();
		ConfigurePropertySet propertySet_hbase_site=new ConfigurePropertySet();
		ConfigurePropertySet propertySet_Regionservers=new ConfigurePropertySet();
		
		List<ConfigurePropertySet> props = new ArrayList<ConfigurePropertySet>();
		Set<ServiceHost> hosts = new HashSet<ServiceHost>();
		ServiceVo serviceVo=new ServiceVo();

		String versionHBase=request.getParameter("versionHBase");
		String installHBase=request.getParameter("installHBase");
		String[] hbase_envs = request.getParameterValues("hbase-env.sh");	
		String[] hbase_sites = request.getParameterValues("hbase-site.xml");	
		
		String regionserversstr=request.getParameter("regionservers");
		String[] regionservers=regionserversstr.split(",");
		
		//查询服务的配置文件Name 
//		String path_hbase_env=HBaseServiceImpl.class.getClassLoader().getResource("cdh5.1.0/hbase-env.xml").getPath();
//		String path_hbase_site=HBaseServiceImpl.class.getClassLoader().getResource("cdh5.1.0/hbase-site.xml").getPath();
//			
//		List<Object> hbase_envNames=XMLUtil.read(path_hbase_env, "name");
//		List<Object> hbase_siteNames=XMLUtil.read(path_hbase_site, "name");
//		List<Object> hbase_env_isDir=XMLUtil.read(path_hbase_env, "isDir");
//		List<Object> hbase_site_isDir=XMLUtil.read(path_hbase_site, "isDir");
//	
//		for (int i = 0; i < hbase_envNames.size(); i++) {
//			ConfigProperty configProperty_hbase_envs=new ConfigProperty();
//			configProperty_hbase_envs.setName((String)hbase_envNames.get(i));
//			configProperty_hbase_envs.setValue(hbase_envs[i]);
//			configProperty_hbase_envs.setDir(Boolean.parseBoolean((hbase_env_isDir.get(i).toString())));
//			properties_hbase_env.add(configProperty_hbase_envs);
//		}
//		propertySet_hbase_env.setProperties(properties_hbase_env);
//		propertySet_hbase_env.setFileName("hbase-env.sh");
//		propertySet_hbase_env.setFileType("sh");
//
//		for (int i = 0; i < hbase_siteNames.size(); i++) {
//			ConfigProperty configProperty_hbase_sites=new ConfigProperty();
//			configProperty_hbase_sites.setName((String)hbase_siteNames.get(i));
//			configProperty_hbase_sites.setValue(hbase_sites[i]);
//			configProperty_hbase_sites.setDir(Boolean.parseBoolean((hbase_site_isDir.get(i).toString())));
//			properties_hbase_site.add(configProperty_hbase_sites);
//		}
//		propertySet_hbase_site.setProperties(properties_hbase_site);
//		propertySet_hbase_site.setFileName("hbase-site.xml");
//		propertySet_hbase_site.setFileType("xml");
//	
		for (int i = 0; i < regionservers.length; i++) {
			ConfigProperty configProperty_regionservers=new ConfigProperty();
			configProperty_regionservers.setName("node"+i);
			configProperty_regionservers.setValue(regionservers[i]);
			properties_Regionservers.add(configProperty_regionservers);
		}
		propertySet_Regionservers.setProperties(properties_Regionservers);
		propertySet_Regionservers.setFileName("regionservers");
		propertySet_Regionservers.setFileType("txt");

		propertySet_hbase_env=FileConfigUtil.packageConfig(request, "cdh5.1.0/hbase-env.xml", "hbase-env.sh","sh");
		propertySet_hbase_site=FileConfigUtil.packageConfig(request, "cdh5.1.0/hbase-site.xml", "hbase-site.xml","xml");
		
		props.add(propertySet_hbase_env);
		props.add(propertySet_hbase_site);
		props.add(propertySet_Regionservers);
		
		serviceVo.setHosts(hosts);
		serviceVo.setProps(props);
		serviceVo.setInstallDir(installHBase);
		serviceVo.setVersion(versionHBase);
		serviceVo.setName("hbase");
		serviceVo.setUserName("hbase");

		System.out.println("serviceVo="+JsonUtils.objectToJson(serviceVo));

		hbaseClient.deployHadoop(serviceVo);
//		try {
//			jsonObject = BdpClient.post("http://127.0.0.1:8098/ws/v1/hadoop/deploy", serviceVo);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		request.setAttribute("flag", "web/hbase/getStatus");
		request.setAttribute("servicesName", "HBase");
		//response.sendRedirect("../servlet/listHosts");

	}

	public JSONObject getStatus(HttpServletRequest request) {
		// TODO Auto-generated method stub
/*		JSONObject jsonObject = new JSONObject() ;
		try {
			jsonObject = BdpClient.get("");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		System.out.println(jsonObject.toString());*/
		
		JSONObject jsonObject = new JSONObject() ;
		
		System.out.println("getStatus...");
		try {
			jsonObject.put("data", "100");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonObject.toString());
		return jsonObject;
	}


}
