package com.bdp.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONObject;

import com.bdp.service.impl.HadoopServiceImpl;
import com.bdp.vo.Configuration;
import com.bdp.vo.ConfigurationList;
import com.joe.core.utils.JsonUtils;
import com.joe.service.vo.ConfigProperty;
import com.joe.service.vo.ConfigurePropertySet;

public class FileConfigUtil {

	/**
	 * 将相应的配置文件封装成某一对象
	 * @author xs
	 *
	 */
	
	/***********   将相应的配置文件封装成ConfigurePropertySet对象 *********/
	public static ConfigurePropertySet packageConfig(HttpServletRequest request,String pathName,String fileName,String type) {
		
		ConfigurePropertySet propertySet_core_site=new ConfigurePropertySet();
		HashSet<ConfigProperty> properties = new HashSet<ConfigProperty>();
		
		String path=FileConfigUtil.class.getClassLoader().getResource(pathName).getPath();
		List<Object> name=XMLUtil.read(path, "name");
		List<Object> isDir=XMLUtil.read(path, "isDir");

		for (int i = 0; i < name.size(); i++) {
			ConfigProperty configProperty=new ConfigProperty();
			configProperty.setName((String)name.get(i));
			String value=request.getParameter((String)name.get(i));
			configProperty.setValue(value);
			configProperty.setDir(Boolean.parseBoolean((isDir.get(i).toString())));
			properties.add(configProperty);
		}
		propertySet_core_site.setProperties(properties);
		propertySet_core_site.setFileName(fileName);
		propertySet_core_site.setFileType(type);
		
		return propertySet_core_site;	
	}
	
	/***********   将相应的配置文件封装成ConfigurationList对象 *********/
	public static ConfigurationList packageConfig(String pathName,String fileName) {
		
		List<Configuration> configs = new ArrayList<Configuration>();
		ConfigurationList configList = new ConfigurationList();
		
		String path=FileConfigUtil.class.getClassLoader().getResource(pathName).getPath();
		List<Object> name=XMLUtil.read(path, "name");
		List<Object> value=XMLUtil.read(path, "value");
		List<Object> description=XMLUtil.read(path, "description");
		
		for (int i = 0; i < name.size(); i++) {
			Configuration config = new Configuration();
			config.setValue((String) value.get(i));
			config.setName((String) name.get(i));
			config.setDescription((String) description.get(i));
			configs.add(config);
		}
		configList.setFileName(fileName);
		configList.setConfigs(configs);
		return configList;	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurationList configList = new ConfigurationList();
	
		configList=FileConfigUtil.packageConfig("cdh5.1.0/core-site_hide.xml", "core-site.xml");
		JSONObject jsonObject=JsonUtils.objectToJson(configList);
		System.out.println(jsonObject);
		
	}

}
