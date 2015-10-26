package com.bdp.rest.zookeeper;

import com.bdp.config.ClientConfigUtils;
import com.bdp.rest.AbstractClient;
import com.joe.core.rest.RestClient;
import com.joe.core.utils.JsonUtils;
import java.text.MessageFormat;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONArray;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.ClientResponse;
import com.joe.core.vo.ErrorMessage;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.util.List;
import org.apache.commons.configuration.Configuration;
import javax.ws.rs.core.Cookie;
import java.util.HashMap;
import com.sky.config.Configed;
import com.joe.core.vo.ReCode;
import java.util.Iterator;
import java.io.IOException;
import java.util.Map;
import com.sky.config.ConfigAble;

/**
 *@author computer
 *@version 2015-01-05 18:22:18
 * Automatic generation
 *
 */
public   class  ZookeeperClient extends AbstractClient {

	public     ZookeeperClient () throws java.io.IOException{
		super(ClientConfigUtils.create());
	}
	//GET
	public   java.util.List<com.joe.service.vo.ServiceVo>  getAll () {
		String resource = getConf().getString("server.ws.resource.zk");
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		Map<String, String> headerParams = new HashMap<String, String>();
		List<Cookie> cookies = new ArrayList<Cookie>();
		return this.doGet(new GenericType<java.util.List<com.joe.service.vo.ServiceVo>>(){},resource, queryParams, headerParams, cookies);
	}
	//PUT
	public   com.joe.core.vo.ReCode  modifyConfig (com.joe.service.vo.ServiceVo param0) {
		String resource = getConf().getString("server.ws.resource.zk.config");
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		Map<String, String> headerParams = new HashMap<String, String>();
		List<Cookie> cookies = new ArrayList<Cookie>();
		return this.doPut(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param0);
	}
	//GET
	public   com.joe.service.vo.ServiceVo  getSingle (java.lang.String param0) {
		String resource = getConf().getString("server.ws.resource.zk.sid");
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		Map<String, String> headerParams = new HashMap<String, String>();
		List<Cookie> cookies = new ArrayList<Cookie>();
		resource = resource.replaceAll("\\{sid\\}",""+param0);
		return this.doGet(com.joe.service.vo.ServiceVo.class,resource, queryParams, headerParams, cookies);
	}
	//PUT
	public   com.joe.core.vo.ReCode  start (com.joe.service.vo.ServiceVo param0) {
		String resource = getConf().getString("server.ws.resource.zk.start.name");
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		Map<String, String> headerParams = new HashMap<String, String>();
		List<Cookie> cookies = new ArrayList<Cookie>();
		return this.doPut(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param0);
	}
	//PUT
	public   com.joe.core.vo.ReCode  stop (com.joe.service.vo.ServiceVo param0) {
		String resource = getConf().getString("server.ws.resource.zk.stop.name");
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		Map<String, String> headerParams = new HashMap<String, String>();
		List<Cookie> cookies = new ArrayList<Cookie>();
		return this.doPut(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param0);
	}
	//POST
	public   com.joe.core.vo.ReCode  install (com.joe.service.vo.ServiceVo param0) {
		String resource = getConf().getString("server.ws.resource.zk.deploy");
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		Map<String, String> headerParams = new HashMap<String, String>();
		List<Cookie> cookies = new ArrayList<Cookie>();
		return this.doPost(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param0);
	}

}