package com.bdp.config;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.sky.config.ConfigUtil;

/**
 * 配置选项
 * @author Joe
 *
 */
public class ClientConfigUtils extends ConfigUtil{

	private static final String RESOURCE_BASE= "cluster_config.properties";
	private static final String RESOURCE_REST = "rest_service.properties";
	
	private static Configuration conf;
	private static Configuration restConf;
	
	/**
	 * 基本配置文件
	 * @return
	 * @throws ConfigurationException 
	 */
	public static Configuration create(){
		if(conf == null){
			try {
				conf = create(null);
			} catch (ConfigurationException e) {
				throw new RuntimeException(e);
			}
		}
		return conf;
	}
	
	/**
	 * Rest 配置文件
	 * @return
	 * @throws IOException
	 * @throws ConfigurationException 
	 */
	public static Configuration createRestConf(){
		if(restConf == null){
			try {
				restConf = createConfig(RESOURCE_REST);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return restConf;
	}

	/**
	 * 
	 * @param addSkyResources
	 * @param skyProperties
	 * @return
	 * @throws IOException 
	 * @throws ConfigurationException 
	 */
	public static Configuration create(Properties skyProperties) throws ConfigurationException {
		PropertiesConfiguration conf = new PropertiesConfiguration(RESOURCE_BASE);
		if(skyProperties != null){
			for(Entry<Object, Object> entry : skyProperties.entrySet()){
				conf.addProperty(entry.getKey().toString(), entry.getValue());
			}
		}
		return conf;
	}

	
	public static void main(String[] args) throws IOException, ConfigurationException {
		Configuration config = createRestConf();  
		System.out.println(config.getString("server.ws.resource.hosts.hid"));
		System.out.println(config.getString("server.ws.resource.cpu.add"));
	}

}
