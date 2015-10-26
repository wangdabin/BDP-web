package com.bdp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONObject;

import com.bdp.rest.hadoop.HadoopClient;
import com.bdp.rest.issued.IssuedClient;
import com.bdp.service.HadoopService;
import com.bdp.util.FileConfigUtil;
import com.bdp.util.XMLUtil;
import com.joe.core.utils.JsonUtils;
import com.joe.core.vo.ReCode;
import com.joe.core.vo.ReCode.Data;
import com.joe.service.vo.ConfigProperty;
import com.joe.service.vo.ConfigurePropertySet;
import com.joe.service.vo.ServiceHost;
import com.joe.service.vo.ServiceHost.ServiceRole;
import com.joe.service.vo.ServiceVo;
import com.sky.task.vo.Task;




/**
 * hadoop服务等服务层实现类
 * @author xs
 *
 */

public class HadoopServiceImpl implements HadoopService {

	public static final int HADOOP_SUCCESS = 1;
	public static final int HADOOP_FAIL=-1;

	private HadoopClient hadoopClient;
	private IssuedClient issuedClient;

	public IssuedClient getIssuedClient() {
		return issuedClient;
	}

	public void setIssuedClient(IssuedClient issuedClient) {
		this.issuedClient = issuedClient;
	}

	public HadoopClient getHadoopClient() {
		return hadoopClient;
	}

	public void setHadoopClient(HadoopClient hadoopClient) {
		this.hadoopClient = hadoopClient;
	}

	public int install(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HashSet<ConfigProperty> properties_core_site = new HashSet<ConfigProperty>();
		HashSet<ConfigProperty> properties_hdfs_site = new HashSet<ConfigProperty>();
		HashSet<ConfigProperty> properties_mapred_site = new HashSet<ConfigProperty>();
		HashSet<ConfigProperty> properties_hadoop_env = new HashSet<ConfigProperty>();
		HashSet<ConfigProperty> properties_slave = new HashSet<ConfigProperty>();
		ConfigurePropertySet propertySet_core_site=new ConfigurePropertySet();
		ConfigurePropertySet propertySet_hdfs_site=new ConfigurePropertySet();
		ConfigurePropertySet propertySet_mapred_site=new ConfigurePropertySet();
		ConfigurePropertySet propertySet_hadoop_env=new ConfigurePropertySet();
		ConfigurePropertySet propertySet_slave=new ConfigurePropertySet();
		List<ConfigurePropertySet> props = new ArrayList<ConfigurePropertySet>();
		Set<ServiceHost> hosts = new HashSet<ServiceHost>();
		ServiceVo serviceVo=new ServiceVo();

		String versionHadoop=request.getParameter("versionHadoop");
		String installHadoop=request.getParameter("installHadoop");
		String slavestr=request.getParameter("slaves");
		String[] slaves=slavestr.split(",");
		String[] core_sites = request.getParameterValues("core-site.xml");	
		String[] hdfs_sites = request.getParameterValues("hdfs-site.xml");	
		String[] mapred_sites = request.getParameterValues("mapred-site.xml");
		String[] hadoop_envs = request.getParameterValues("hadoop-env.sh");	
		String[] DataNodes = request.getParameterValues("subBox1");
		String[] NameNodes = request.getParameterValues("subBox2");
		String[] JournalNodes = request.getParameterValues("subBox3");

		//查询服务的配置文件Name 
/*		String path_core_site=HadoopServiceImpl.class.getClassLoader().getResource("cdh5.1.0/core-site.xml").getPath();
		String path_hdfs_site=HadoopServiceImpl.class.getClassLoader().getResource("cdh5.1.0/hdfs-site.xml").getPath();
		String path_mapred_site=HadoopServiceImpl.class.getClassLoader().getResource("cdh5.1.0/mapred-site.xml").getPath();
		String path_core_site_hide=HadoopServiceImpl.class.getClassLoader().getResource("cdh5.1.0/core-site_hide.xml").getPath();
		String path_hdfs_site_hide=HadoopServiceImpl.class.getClassLoader().getResource("cdh5.1.0/hdfs-site_hide.xml").getPath();
		String path_mapred_site_hide=HadoopServiceImpl.class.getClassLoader().getResource("cdh5.1.0/mapred-site_hide.xml").getPath();
		String path_hadoop_env=HadoopServiceImpl.class.getClassLoader().getResource("cdh5.1.0/hadoop-env.sh.template.xml").getPath();

		List<Object> core_siteNames=XMLUtil.read(path_core_site, "name");
		List<Object> hdfs_siteNames=XMLUtil.read(path_hdfs_site, "name");
		List<Object> mapred_siteNames=XMLUtil.read(path_mapred_site, "name");
		List<Object> hadoop_envNames=XMLUtil.read(path_hadoop_env, "name");
		List<Object> core_site_hideNames=XMLUtil.read(path_core_site_hide, "name");
		List<Object> hdfs_site_hideNames=XMLUtil.read(path_hdfs_site_hide, "name");
		List<Object> mapred_site_hideNames=XMLUtil.read(path_mapred_site_hide, "name");
		List<Object> core_site_isDir=XMLUtil.read(path_core_site, "isDir");
		List<Object> hdfs_site_isDir=XMLUtil.read(path_hdfs_site, "isDir");
		List<Object> mapred_site_isDir=XMLUtil.read(path_mapred_site, "isDir");
		List<Object> core_site_hide_isDir=XMLUtil.read(path_core_site_hide, "isDir");
		List<Object> hdfs_site_hide_isDir=XMLUtil.read(path_hdfs_site_hide, "isDir");
		List<Object> mapred_site_hide_isDir=XMLUtil.read(path_mapred_site_hide, "isDir");
		List<Object> hadoop_env_isDir=XMLUtil.read(path_hadoop_env, "isDir");

		System.out.println("size_OLD="+core_siteNames.size()+" "+hdfs_siteNames.size()+" "+mapred_siteNames.size());

		Boolean boolean1=core_siteNames.addAll(core_site_hideNames);
		Boolean boolean2=hdfs_siteNames.addAll(hdfs_site_hideNames);
		Boolean boolean3=mapred_siteNames.addAll(mapred_site_hideNames);
		Boolean boolean4=core_site_isDir.addAll(core_site_hide_isDir);
		Boolean boolean5=hdfs_site_isDir.addAll(hdfs_site_hide_isDir);
		Boolean boolean6=mapred_site_isDir.addAll(mapred_site_hide_isDir);

		System.out.println("Boolean="+boolean1+boolean2+boolean3+boolean4+boolean5+boolean6);
		System.out.println("size_NEW="+core_siteNames.size()+" "+hdfs_siteNames.size()+" "+mapred_siteNames.size());

		for (int i = 0; i < core_siteNames.size(); i++) {
			ConfigProperty configProperty_core_sites=new ConfigProperty();
			configProperty_core_sites.setName((String)core_siteNames.get(i));
			configProperty_core_sites.setValue(core_sites[i]);
			configProperty_core_sites.setDir(Boolean.parseBoolean((core_site_isDir.get(i).toString())));
			properties_core_site.add(configProperty_core_sites);
		}
		propertySet_core_site.setProperties(properties_core_site);
		propertySet_core_site.setFileName("core-site.xml");
		propertySet_core_site.setFileType("xml");

		for (int i = 0; i < hdfs_siteNames.size(); i++) {
			ConfigProperty configProperty_hdfs_sites=new ConfigProperty();
			configProperty_hdfs_sites.setName((String)hdfs_siteNames.get(i));
			configProperty_hdfs_sites.setValue(hdfs_sites[i]);
			configProperty_hdfs_sites.setDir(Boolean.parseBoolean((hdfs_site_isDir.get(i).toString())));
			properties_hdfs_site.add(configProperty_hdfs_sites);
		}
		propertySet_hdfs_site.setProperties(properties_hdfs_site);
		propertySet_hdfs_site.setFileName("hdfs-site.xml");
		propertySet_hdfs_site.setFileType("xml");

		for (int i = 0; i < mapred_siteNames.size(); i++) {
			ConfigProperty configProperty_mapred_sites=new ConfigProperty();
			configProperty_mapred_sites.setName((String)mapred_siteNames.get(i));
			configProperty_mapred_sites.setValue(mapred_sites[i]);
			configProperty_mapred_sites.setDir(Boolean.parseBoolean((mapred_site_isDir.get(i).toString())));
			properties_mapred_site.add(configProperty_mapred_sites);
		}
		propertySet_mapred_site.setProperties(properties_mapred_site);
		propertySet_mapred_site.setFileName("mapred-site.xml");
		propertySet_mapred_site.setFileType("xml");

		for (int i = 0; i < hadoop_envNames.size(); i++) {
			ConfigProperty configProperty_hadoop_envs=new ConfigProperty();
			configProperty_hadoop_envs.setName((String)hadoop_envNames.get(i));
			configProperty_hadoop_envs.setValue(hadoop_envs[i]);
			configProperty_hadoop_envs.setDir(Boolean.parseBoolean((hadoop_env_isDir.get(i).toString())));
			properties_hadoop_env.add(configProperty_hadoop_envs);
		}
		propertySet_hadoop_env.setProperties(properties_hadoop_env);
		propertySet_hadoop_env.setFileName("hadoop-env.sh");
		propertySet_hadoop_env.setFileType("sh");
*/
		for (int i = 0; i < slaves.length; i++) {
			ConfigProperty configProperty_slaves=new ConfigProperty();
			configProperty_slaves.setName("node"+i);
			configProperty_slaves.setValue(slaves[i]);
			properties_slave.add(configProperty_slaves);
		}
		propertySet_slave.setProperties(properties_slave);
		propertySet_slave.setFileName("slaves");
		propertySet_slave.setFileType("txt");

		propertySet_core_site=FileConfigUtil.packageConfig(request, "cdh5.1.0/core-sites.xml", "core-site.xml","xml");
		propertySet_hdfs_site=FileConfigUtil.packageConfig(request, "cdh5.1.0/hdfs-sites.xml", "hdfs-site.xml","xml");
		propertySet_mapred_site=FileConfigUtil.packageConfig(request, "cdh5.1.0/mapred-sites.xml", "mapred-site.xml","xml");
		propertySet_hadoop_env=FileConfigUtil.packageConfig(request, "cdh5.1.0/hadoop-env.sh.template.xml", "hadoop-env.sh","sh");
		
		props.add(propertySet_core_site);
		props.add(propertySet_hdfs_site);
		props.add(propertySet_mapred_site);
		props.add(propertySet_hadoop_env);
		props.add(propertySet_slave);

		for (int i = 0; i < DataNodes.length; i++) {
			ServiceHost host=new ServiceHost();
			host.setHostIp(DataNodes[i]);
			host.setRole(ServiceRole.HDFS_DATANODE);
			hosts.add(host);

		}
		for (int i = 0; i < NameNodes.length; i++) {
			ServiceHost host=new ServiceHost();
			host.setHostIp(NameNodes[i]);
			host.setRole(ServiceRole.HDFS_NAMENODE);
			hosts.add(host);
		}
		for (int i = 0; i < JournalNodes.length; i++) {
			ServiceHost host=new ServiceHost();
			host.setHostIp(JournalNodes[i]);
			host.setRole(ServiceRole.HDFS_JOURNALNODE);
			hosts.add(host);
		}

		serviceVo.setHosts(hosts);
		serviceVo.setProps(props);
		serviceVo.setInstallDir(installHadoop);
		serviceVo.setVersion(versionHadoop);
		serviceVo.setName("hadoop");
		serviceVo.setUserName("hadoop");

		System.out.println("serviceVo="+JsonUtils.objectToJson(serviceVo));

		ReCode	code=hadoopClient.deployHadoop(serviceVo);
		int ret=code.getRet();
		System.out.println("code.getRet()="+ret);
		if(ret==code.RET_FAIL)
		{
			request.setAttribute("error_msg", code.getMsg()+"提交任务失败！");
			return HADOOP_FAIL;
		}
		if(ret==code.RET_SUCCESS)
		{
			Data data=code.getData();
			Long taskID=data.getId();
			//		try {
			//			jsonObject = BdpClient.post("http://127.0.0.1:8098/ws/v1/hadoop/deploy", serviceVo);
			//		} catch (JSONException e) {
			//			// TODO Auto-generated catch block
			//			e.printStackTrace();
			//		}
			System.out.println("taskID="+taskID);
			request.setAttribute("taskID", taskID);
			request.setAttribute("path", "web/hadoop/getStatus");
			request.setAttribute("servicesName", "Hadoop");
			return HADOOP_SUCCESS;
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
