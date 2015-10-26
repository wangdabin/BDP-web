package com.bdp.rest.host;

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
public   class  HostClient extends AbstractClient {

public     HostClient () throws java.io.IOException{
super(ClientConfigUtils.create());
}
//POST
public   com.joe.core.vo.ReCode  add (com.joe.host.vo.Host param0) {
String resource = getConf().getString("server.ws.resource.hosts.add");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doPost(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param0);
}
//POST
public   com.joe.host.vo.FindResult  find (com.joe.host.vo.Host[] param0) {
String resource = getConf().getString("server.ws.resource.hosts.findbatch");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doPost(com.joe.host.vo.FindResult.class,resource, queryParams, headerParams, cookies,param0);
}
//POST
public   com.joe.host.vo.FindResult  find (com.joe.host.vo.HostRange param0) {
String resource = getConf().getString("server.ws.resource.hosts.find");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doPost(com.joe.host.vo.FindResult.class,resource, queryParams, headerParams, cookies,param0);
}
//GET
public   java.util.List<com.joe.host.vo.Host>  list () {
String resource = getConf().getString("server.ws.resource.hosts.list");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doGet(new GenericType<java.util.List<com.joe.host.vo.Host>>(){},resource, queryParams, headerParams, cookies);
}
//DELETE
public   com.joe.core.vo.ReCode  delete (int param0) {
String resource = getConf().getString("server.ws.resource.hosts.hid");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{hid\\}",""+param0);
return this.doDelete(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,null);
}
//GET
public   com.joe.host.vo.Host  getHost (int param0) {
String resource = getConf().getString("server.ws.resource.hosts.hid");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{hid\\}",""+param0);
return this.doGet(com.joe.host.vo.Host.class,resource, queryParams, headerParams, cookies);
}
//GET
public   com.joe.core.vo.Total  getTotal () {
String resource = getConf().getString("server.ws.resource.hosts.total");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doGet(com.joe.core.vo.Total.class,resource, queryParams, headerParams, cookies);
}
//PUT
public   com.joe.core.vo.ReCode  update (int param0,com.joe.host.vo.Host param1) {
String resource = getConf().getString("server.ws.resource.hosts.hid");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{hid\\}",""+param0);
return this.doPut(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param1);
}
//GET
public   java.util.List<com.joe.host.vo.Host>  getAll () {
String resource = getConf().getString("server.ws.resource.hosts");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doGet(new GenericType<java.util.List<com.joe.host.vo.Host>>(){},resource, queryParams, headerParams, cookies);
}
//POST
public   com.joe.host.init.Status  monitor (com.joe.host.vo.Host param0) {
String resource = getConf().getString("server.ws.resource.hosts.monitor");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doPost(com.joe.host.init.Status.class,resource, queryParams, headerParams, cookies,param0);
}

}