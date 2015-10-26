package com.bdp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bdp.rest.host.HostClient;
import com.bdp.service.ServicesService;
import com.bdp.util.FileConfigUtil;
import com.bdp.util.XMLUtil;
import com.bdp.vo.ClientHost;
import com.bdp.vo.ClientService;
import com.bdp.vo.Configuration;
import com.bdp.vo.ConfigurationList;
import com.joe.host.vo.Host;

/**
 * hadoop服务等服务层实现类
 * 
 * @author xs
 * 
 */
public class ServicesServiceImpl implements ServicesService {

	// 注入rest对象
	private HostClient hostClient;

	public HostClient getHostClient() {
		return hostClient;
	}

	public void setHostClient(HostClient hostClient) {
		this.hostClient = hostClient;
	}

	public void add(HttpServletRequest request) {
		// 查询所有主机的信息
		List<Host> listHosts = hostClient.list();
		List<ClientHost> hosts = new ArrayList<ClientHost>();
		for (Host host : listHosts) {
			int num = ((int) (1 + 7 * (Math.random())));
			ClientHost clientHost = new ClientHost();
			clientHost.setHid(host.getId());
			clientHost.setIp(host.getIp());
			clientHost.setName(host.getName());
			clientHost.setHostnum(String.valueOf(num));
			hosts.add(clientHost);
		}
		request.setAttribute("hosts", hosts);

		// 查询服务的配置文件
		List<ConfigurationList> configurationLists_hadoop = new ArrayList<ConfigurationList>();
		List<ConfigurationList> configurationLists_hadoop_hide = new ArrayList<ConfigurationList>();
		List<ConfigurationList> configurationLists_hadoop_env = new ArrayList<ConfigurationList>();
		List<ConfigurationList> configurationLists_hbase = new ArrayList<ConfigurationList>();
		List<ConfigurationList> configurationLists_hbase_env = new ArrayList<ConfigurationList>();
		List<ConfigurationList> configurationLists_hive = new ArrayList<ConfigurationList>();
		List<ConfigurationList> configurationLists_hive_env = new ArrayList<ConfigurationList>();
		
		ConfigurationList configList_core_site = new ConfigurationList();
		ConfigurationList configList_hdfs_site = new ConfigurationList();
		ConfigurationList configList_mapred_site = new ConfigurationList();
		ConfigurationList configList_core_site_hide = new ConfigurationList();
		ConfigurationList configList_hdfs_site_hide = new ConfigurationList();
		ConfigurationList configList_mapred_site_hide = new ConfigurationList();
		ConfigurationList configList_hadoop_env = new ConfigurationList();
		ConfigurationList configList_hbase_env = new ConfigurationList();
		ConfigurationList configList_hbase_site = new ConfigurationList();
		ConfigurationList configList_hive_env = new ConfigurationList();
		ConfigurationList configList_hive_site = new ConfigurationList();
		
//		List<Configuration> configs_core_site = new ArrayList<Configuration>();
//		List<Configuration> configs_hdfs_site = new ArrayList<Configuration>();
//		List<Configuration> configs_mapred_site = new ArrayList<Configuration>();
//		List<Configuration> configs_core_site_hide = new ArrayList<Configuration>();
//		List<Configuration> configs_hdfs_site_hide = new ArrayList<Configuration>();
//		List<Configuration> configs_mapred_site_hide = new ArrayList<Configuration>();
//		List<Configuration> configs_hadoop_env = new ArrayList<Configuration>();
//		List<Configuration> configs_hbase_env = new ArrayList<Configuration>();
//		List<Configuration> configs_hbase_site = new ArrayList<Configuration>();
//		List<Configuration> configs_hive_env = new ArrayList<Configuration>();
//		List<Configuration> configs_hive_site = new ArrayList<Configuration>();
//		
//		String path_core_site = ServicesServiceImpl.class.getClassLoader()
//				.getResource("cdh5.1.0/core-site.xml").getPath();
//		String path_hdfs_site = ServicesServiceImpl.class.getClassLoader()
//				.getResource("cdh5.1.0/hdfs-site.xml").getPath();
//		String path_mapred_site = ServicesServiceImpl.class.getClassLoader()
//				.getResource("cdh5.1.0/mapred-site.xml").getPath();
//		String path_core_site_hide = ServicesServiceImpl.class.getClassLoader()
//				.getResource("cdh5.1.0/core-site_hide.xml").getPath();
//		String path_hdfs_site_hide = ServicesServiceImpl.class.getClassLoader()
//				.getResource("cdh5.1.0/hdfs-site_hide.xml").getPath();
//		String path_mapred_site_hide = ServicesServiceImpl.class.getClassLoader()
//				.getResource("cdh5.1.0/mapred-site_hide.xml").getPath();
//		String path_hadoop_env = ServicesServiceImpl.class.getClassLoader()
//				.getResource("cdh5.1.0/hadoop-env.sh.template.xml").getPath();
//		String path_hbase_env = ServicesServiceImpl.class.getClassLoader()
//				.getResource("cdh5.1.0/hbase-env.xml").getPath();
//		String path_hbase_site = ServicesServiceImpl.class.getClassLoader()
//				.getResource("cdh5.1.0/hbase-site.xml").getPath();
//		String path_hive_env = ServicesServiceImpl.class.getClassLoader()
//				.getResource("cdh5.1.0/hive-env.xml").getPath();
//		String path_hive_site = ServicesServiceImpl.class.getClassLoader()
//				.getResource("cdh5.1.0/hive-site.xml").getPath();

//		List<Object> core_site = XMLUtil.read(path_core_site, "name");
//		List<Object> hdfs_site = XMLUtil.read(path_hdfs_site, "name");
//		List<Object> mapred_site = XMLUtil.read(path_mapred_site, "name");
//		List<Object> hadoop_env = XMLUtil.read(path_hadoop_env, "name");
//		List<Object> hbase_env = XMLUtil.read(path_hbase_env, "name");
//		List<Object> hbase_site = XMLUtil.read(path_hbase_site, "name");
//		List<Object> core_site_hide = XMLUtil.read(path_core_site_hide, "name");
//		List<Object> hdfs_site_hide = XMLUtil.read(path_hdfs_site_hide, "name");
//		List<Object> mapred_site_hide = XMLUtil.read(path_mapred_site_hide,"name");
//		List<Object> hive_env = XMLUtil.read(path_hive_env, "name");
//		List<Object> hive_site = XMLUtil.read(path_hive_site, "name");
//		
//		List<Object> core_site_value = XMLUtil.read(path_core_site, "value");
//		List<Object> hdfs_site_value = XMLUtil.read(path_hdfs_site, "value");
//		List<Object> mapred_site_value = XMLUtil.read(path_mapred_site, "value");
//		List<Object> hadoop_env_value = XMLUtil.read(path_hadoop_env, "value");
//		List<Object> hbase_env_value = XMLUtil.read(path_hbase_env, "value");
//		List<Object> hbase_site_value = XMLUtil.read(path_hbase_site, "value");
//		List<Object> core_site_value_hide = XMLUtil.read(path_core_site_hide,"value");
//		List<Object> hdfs_site_value_hide = XMLUtil.read(path_hdfs_site_hide,"value");
//		List<Object> mapred_site_value_hide = XMLUtil.read(path_mapred_site_hide, "value");
//		List<Object> hive_env_value = XMLUtil.read(path_hive_env, "value");
//		List<Object> hive_site_value = XMLUtil.read(path_hive_site, "value");
//		
//		List<Object> core_site_description = XMLUtil.read(path_core_site,"description");
//		List<Object> hdfs_site_description = XMLUtil.read(path_hdfs_site,"description");
//		List<Object> mapred_site_description = XMLUtil.read(path_mapred_site,"description");
//		List<Object> hadoop_env_description = XMLUtil.read(path_hadoop_env,"description");
//		List<Object> hbase_env_description = XMLUtil.read(path_hbase_env,"description");
//		List<Object> hbase_site_description = XMLUtil.read(path_hbase_site,"description");
//		List<Object> core_site_description_hide = XMLUtil.read(path_core_site_hide, "description");
//		List<Object> hdfs_site_description_hide = XMLUtil.read(path_hdfs_site_hide, "description");
//		List<Object> mapred_site_description_hide = XMLUtil.read(path_mapred_site_hide, "description");
//		List<Object> hive_env_description = XMLUtil.read(path_hive_env,"description");
//		List<Object> hive_site_description = XMLUtil.read(path_hive_site,"description");
//		
//		for (int i = 0; i < hbase_env.size(); i++) {
//			Configuration config = new Configuration();
//			config.setValue((String) hbase_env_value.get(i));
//			config.setName((String) hbase_env.get(i));
//			config.setDescription((String) hbase_env_description.get(i));
//			configs_hbase_env.add(config);
//		}
//		for (int i = 0; i < hbase_site.size(); i++) {
//			Configuration config = new Configuration();
//			config.setValue((String) hbase_site_value.get(i));
//			config.setName((String) hbase_site.get(i));
//			config.setDescription((String) hbase_site_description.get(i));
//			configs_hbase_site.add(config);
//		}
//		for (int i = 0; i < hive_env.size(); i++) {
//			Configuration config = new Configuration();
//			config.setValue((String) hive_env_value.get(i));
//			config.setName((String) hive_env.get(i));
//			config.setDescription((String) hive_env_description.get(i));
//			configs_hive_env.add(config);
//		}
//		for (int i = 0; i < hive_site.size(); i++) {
//			Configuration config = new Configuration();
//			config.setValue((String) hive_site_value.get(i));
//			config.setName((String) hive_site.get(i));
//			config.setDescription((String) hive_site_description.get(i));
//			configs_hive_site.add(config);
//		}
//		for (int i = 0; i < core_site.size(); i++) {
//			Configuration config = new Configuration();
//			config.setValue((String) core_site_value.get(i));
//			config.setName((String) core_site.get(i));
//			config.setDescription((String) core_site_description.get(i));
//			configs_core_site.add(config);
//		}
//		for (int i = 0; i < hdfs_site.size(); i++) {
//			Configuration config = new Configuration();
//			config.setValue((String) hdfs_site_value.get(i));
//			config.setName((String) hdfs_site.get(i));
//			config.setDescription((String) hdfs_site_description.get(i));
//			configs_hdfs_site.add(config);
//		}
//		for (int i = 0; i < mapred_site.size(); i++) {
//			Configuration config = new Configuration();
//			config.setValue((String) mapred_site_value.get(i));
//			config.setName((String) mapred_site.get(i));
//			config.setDescription((String) mapred_site_description.get(i));
//			configs_mapred_site.add(config);
//		}
//		for (int i = 0; i < core_site_hide.size(); i++) {
//			Configuration config = new Configuration();
//			config.setValue((String) core_site_value_hide.get(i));
//			config.setName((String) core_site_hide.get(i));
//			config.setDescription((String) core_site_description_hide.get(i));
//			configs_core_site_hide.add(config);
//		}
//		for (int i = 0; i < hdfs_site_hide.size(); i++) {
//			Configuration config = new Configuration();
//			config.setValue((String) hdfs_site_value_hide.get(i));
//			config.setName((String) hdfs_site_hide.get(i));
//			config.setDescription((String) hdfs_site_description_hide.get(i));
//			configs_hdfs_site_hide.add(config);
//		}
//		for (int i = 0; i < mapred_site_hide.size(); i++) {
//			Configuration config = new Configuration();
//			config.setValue((String) mapred_site_value_hide.get(i));
//			config.setName((String) mapred_site_hide.get(i));
//			config.setDescription((String) mapred_site_description_hide.get(i));
//			configs_mapred_site_hide.add(config);
//		}
//		for (int i = 0; i < hadoop_env.size(); i++) {
//			Configuration config = new Configuration();
//			config.setValue((String) hadoop_env_value.get(i));
//			config.setName((String) hadoop_env.get(i));
//			config.setDescription((String) hadoop_env_description.get(i));
//			configs_hadoop_env.add(config);
//		}
//
//		configList_core_site.setFileName("core-site.xml");
//		configList_hdfs_site.setFileName("hdfs-site.xml");
//		configList_mapred_site.setFileName("mapred-site.xml");
//		configList_core_site_hide.setFileName("core-site.xml");
//		configList_hdfs_site_hide.setFileName("hdfs-site.xml");
//		configList_mapred_site_hide.setFileName("mapred-site.xml");
//		configList_hadoop_env.setFileName("hadoop-env.sh");
//		configList_hbase_env.setFileName("hbase-env.sh");
//		configList_hbase_site.setFileName("hbase-site.xml");
//		configList_hive_env.setFileName("hive-env.sh");
//		configList_hive_site.setFileName("hive-site.xml");
//		
//		configList_core_site.setConfigs(configs_core_site);
//		configList_hdfs_site.setConfigs(configs_hdfs_site);
//		configList_mapred_site.setConfigs(configs_mapred_site);
//		configList_core_site_hide.setConfigs(configs_core_site_hide);
//		configList_hdfs_site_hide.setConfigs(configs_hdfs_site_hide);
//		configList_mapred_site_hide.setConfigs(configs_mapred_site_hide);
//		configList_hadoop_env.setConfigs(configs_hadoop_env);
//		configList_hbase_env.setConfigs(configs_hbase_env);
//		configList_hbase_site.setConfigs(configs_hbase_site);
//		configList_hive_env.setConfigs(configs_hive_env);
//		configList_hive_site.setConfigs(configs_hive_site);

		configList_core_site=FileConfigUtil.packageConfig("cdh5.1.0/core-site.xml", "core-site.xml");
		configList_hdfs_site=FileConfigUtil.packageConfig("cdh5.1.0/hdfs-site.xml", "hdfs-site.xml");
		configList_mapred_site=FileConfigUtil.packageConfig("cdh5.1.0/mapred-site.xml", "mapred-site.xml");
		configList_core_site_hide=FileConfigUtil.packageConfig("cdh5.1.0/core-site_hide.xml", "core-site.xml");
		configList_hdfs_site_hide=FileConfigUtil.packageConfig("cdh5.1.0/hdfs-site_hide.xml", "hdfs-site.xml");
		configList_mapred_site_hide=FileConfigUtil.packageConfig("cdh5.1.0/mapred-site_hide.xml", "mapred-site.xml");
		configList_hadoop_env=FileConfigUtil.packageConfig("cdh5.1.0/hadoop-env.sh.template.xml", "hadoop-env.sh");
		configList_hbase_env=FileConfigUtil.packageConfig("cdh5.1.0/hbase-env.xml", "hbase-env.sh");
		configList_hbase_site=FileConfigUtil.packageConfig("cdh5.1.0/hbase-site.xml", "hbase-site.xml");
		configList_hive_env=FileConfigUtil.packageConfig("cdh5.1.0/hive-env.xml", "hive-env.sh");
		configList_hive_site=FileConfigUtil.packageConfig("cdh5.1.0/hive-site.xml", "hive-site.xml");
		
		
		configurationLists_hadoop.add(configList_core_site);
		configurationLists_hadoop.add(configList_hdfs_site);
		configurationLists_hadoop.add(configList_mapred_site);
		configurationLists_hadoop_hide.add(configList_core_site_hide);
		configurationLists_hadoop_hide.add(configList_hdfs_site_hide);
		configurationLists_hadoop_hide.add(configList_mapred_site_hide);
		configurationLists_hadoop_env.add(configList_hadoop_env);
		configurationLists_hbase.add(configList_hbase_site);
		configurationLists_hbase_env.add(configList_hbase_env);
		configurationLists_hive.add(configList_hive_site);
		configurationLists_hive_env.add(configList_hive_env);

		System.out.println("configs_hadoop"+configurationLists_hadoop.toString());
		System.out.println("configs_hadoop_hide"+ configurationLists_hadoop_hide.toString());
		System.out.println("configs_hadoop_env"+ configurationLists_hadoop_env.toString());
		request.setAttribute("configs_hadoop", configurationLists_hadoop);
		request.setAttribute("configs_hadoop_hide",configurationLists_hadoop_hide);
		request.setAttribute("configs_hadoop_env",configurationLists_hadoop_env);

		System.out.println("configs_hbase"+ configurationLists_hbase.toString());
		System.out.println("configs_hbase_env"+ configurationLists_hbase_env.toString());
		request.setAttribute("configs_hbase", configurationLists_hbase);
		request.setAttribute("configs_hbase_env", configurationLists_hbase_env);
		
		System.out.println("configs_hive"+ configurationLists_hive.toString());
		System.out.println("configs_hive_env"+ configurationLists_hive_env.toString());
		request.setAttribute("configs_hive", configurationLists_hive);
		request.setAttribute("configs_hive_env", configurationLists_hive_env);
	}

	public void list(HttpServletRequest request) {
		// TODO Auto-generated method stub

		ClientService s1 = new ClientService();
		ClientService s2 = new ClientService();
		ClientService s3 = new ClientService();
		ClientService s4 = new ClientService();
		ClientService s5 = new ClientService();

		ClientService s6 = new ClientService();
		ClientService s7 = new ClientService();
		ClientService s8 = new ClientService();

		ClientService s9 = new ClientService();
		ClientService s10 = new ClientService();

		s1.setIp("192.168.1.1");
		s1.setName("namenode1");
		s1.setNum("401");
		s1.setState("很好1");
		s1.setTimebg("2014/1/5 11:1");
		s1.setTimecr("2014/1/5 11:1");
		s1.setParentService(s6);

		s2.setIp("192.168.1.2");
		s2.setName("namenode2");
		s2.setNum("402");
		s2.setState("很好2");
		s2.setTimebg("2014/1/5 11:2");
		s2.setTimecr("2014/1/5 11:2");
		s2.setParentService(s6);

		s3.setIp("192.168.1.3");
		s3.setName("namenode3");
		s3.setNum("403");
		s3.setState("很好3");
		s3.setTimebg("2014/1/5 11:3");
		s3.setTimecr("2014/1/5 11:3");
		s3.setParentService(s7);

		s4.setIp("192.168.1.4");
		s4.setName("namenode4");
		s4.setNum("404");
		s4.setState("很好4");
		s4.setTimebg("2014/1/5 11:4");
		s4.setTimecr("2014/1/5 11:4");
		s4.setParentService(s7);

		s5.setIp("192.168.1.5");
		s5.setName("namenode5");
		s5.setNum("405");
		s5.setState("很好5");
		s5.setTimebg("2014/1/5 11:5");
		s5.setTimecr("2014/1/5 11:5");
		s5.setParentService(s8);

		List<ClientService> list1 = new ArrayList<ClientService>();
		List<ClientService> list2 = new ArrayList<ClientService>();
		List<ClientService> list3 = new ArrayList<ClientService>();

		list1.add(s1);
		list1.add(s2);

		list2.add(s3);
		list2.add(s4);

		list3.add(s5);

		s6.setChildService(list1);
		s7.setChildService(list2);
		s8.setChildService(list3);

		s6.setName("namenode");
		s7.setName("datanode");
		s6.setParentService(s9);
		s7.setParentService(s9);

		List<ClientService> list4 = new ArrayList<ClientService>();
		List<ClientService> list5 = new ArrayList<ClientService>();
		list4.add(s6);
		list4.add(s7);
		list5.add(s8);

		s9.setName("hadoop");
		s9.setChildService(list4);

		s10.setName("zookeeper");
		s10.setChildService(list5);

		List<ClientService> list6 = new ArrayList<ClientService>();

		list6.add(s9);
		list6.add(s10);

		// s11.setChildService(list6);

		request.setAttribute("services", list6);
		System.out.println(request.getAttribute("services"));
	}

}
