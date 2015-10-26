package com.bdp.rest.agent;

import com.bdp.config.ClientConfigUtils;
import com.bdp.rest.AbstractClient;
import com.joe.core.utils.JsonUtils;
import javax.ws.rs.core.MultivaluedMap;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sky.config.Configed;
import com.joe.core.vo.ErrorMessage;
import java.util.HashMap;
import com.joe.core.rest.RestClient;
import com.sun.jersey.api.client.GenericType;
import java.util.Map;
import java.util.Iterator;
import org.apache.commons.configuration.Configuration;
import com.joe.core.vo.ReCode;
import java.text.MessageFormat;
import java.io.IOException;
import com.sky.config.ConfigAble;
import org.codehaus.jettison.json.JSONArray;
import javax.ws.rs.core.Cookie;
import org.codehaus.jettison.json.JSONObject;
import java.util.ArrayList;
import com.sun.jersey.api.client.ClientResponse;
import java.util.List;

/**
 *@author computer
 *@version 2015-01-05 13:18:40
 * Automatic generation
 *
*/
public   class  AgentClient extends AbstractClient {

public     AgentClient () throws java.io.IOException{
super(ClientConfigUtils.create());
}
//POST
public   com.joe.core.vo.ReCode  add (com.joe.agent.vo.Agent param0) {
String resource = getConf().getString("server.ws.resource.agents.add");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doPost(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param0);
}
//DELETE
public   com.joe.core.vo.ReCode  delete (int param0) {
String resource = getConf().getString("server.ws.resource.agents.aid");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{aid\\}",""+param0);
return this.doDelete(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,null);
}
//POST
public   com.joe.core.vo.ReCode  update (com.joe.agent.vo.Agent param0) {
String resource = getConf().getString("server.ws.resource.agents.update");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doPost(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param0);
}
//GET
public   java.util.List<com.joe.agent.vo.Agent>  getAll () {
String resource = getConf().getString("server.ws.resource.agents");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doGet(new GenericType<java.util.List<com.joe.agent.vo.Agent>>(){},resource, queryParams, headerParams, cookies);
}
//GET
public   com.joe.agent.vo.Agent  getSingleInfo (int param0) {
String resource = getConf().getString("server.ws.resource.agents.aid");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{aid\\}",""+param0);
return this.doGet(com.joe.agent.vo.Agent.class,resource, queryParams, headerParams, cookies);
}
//GET
public   com.joe.agent.vo.Agent  getAgentByIp (java.lang.String param0) {
String resource = getConf().getString("server.ws.resource.agents.ip.ip");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{ip\\}",""+param0);
return this.doGet(com.joe.agent.vo.Agent.class,resource, queryParams, headerParams, cookies);
}
//GET
public   java.util.List<com.joe.agent.vo.Agent>  getAgentInfo () {
String resource = getConf().getString("server.ws.resource.agents.list");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doGet(new GenericType<java.util.List<com.joe.agent.vo.Agent>>(){},resource, queryParams, headerParams, cookies);
}
//PUT
public   com.joe.core.vo.ReCode  updateRunStatus (java.lang.String param0,java.lang.String param1) {
String resource = getConf().getString("server.ws.resource.agents.run.ip");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{ip\\}",""+param0);
queryParams.add("status",""+param1);
return this.doPut(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,null);
}
//PUT
public   com.joe.core.vo.ReCode  updateInstallStatus (java.lang.String param0,double param1) {
String resource = getConf().getString("server.ws.resource.agents.install.ip");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{ip\\}",""+param0);
queryParams.add("status",""+param1);
return this.doPut(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,null);
}

}