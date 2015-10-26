package com.bdp.rest.hive;

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
 *@version 2015-01-05 13:53:32
 * Automatic generation
 *
 */
public   class  HiveClient extends AbstractClient {

	public     HiveClient () throws java.io.IOException{
		super(ClientConfigUtils.create());
	}
	//POST
	public   com.joe.core.vo.ReCode  deployHive (com.joe.service.vo.ServiceVo param0) {
		String resource = getConf().getString("server.ws.resource.hive.deploy");
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		Map<String, String> headerParams = new HashMap<String, String>();
		List<Cookie> cookies = new ArrayList<Cookie>();
		return this.doPost(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param0);
	}

}