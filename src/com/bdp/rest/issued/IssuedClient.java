package com.bdp.rest.issued;

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
public   class  IssuedClient extends AbstractClient {

public     IssuedClient () throws java.io.IOException{
super(ClientConfigUtils.create());
}
//GET
public   java.util.List<com.sky.task.vo.Task>  getAll () {
String resource = getConf().getString("server.ws.resource.issueds");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doGet(new GenericType<java.util.List<com.sky.task.vo.Task>>(){},resource, queryParams, headerParams, cookies);
}
//POST
public   com.joe.core.vo.ReCode  scheduleTask (com.sky.task.vo.Task param0) {
String resource = getConf().getString("server.ws.resource.issueds.add");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doPost(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param0);
}
//GET
public   com.sky.task.vo.Task  getSingleTask (long param0) {
String resource = getConf().getString("server.ws.resource.issueds.tid");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{tid\\}",""+param0);
return this.doGet(com.sky.task.vo.Task.class,resource, queryParams, headerParams, cookies);
}
//GET
public   java.util.List<com.sky.task.vo.TranOrder>  getOrders (long param0) {
String resource = getConf().getString("server.ws.resource.issueds.task.tid");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{tid\\}",""+param0);
return this.doGet(new GenericType<java.util.List<com.sky.task.vo.TranOrder>>(){},resource, queryParams, headerParams, cookies);
}
//GET
public   com.sky.task.vo.TranOrder  getOrder (long param0) {
String resource = getConf().getString("server.ws.resource.issueds.orders.tid");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{tid\\}",""+param0);
return this.doGet(com.sky.task.vo.TranOrder.class,resource, queryParams, headerParams, cookies);
}
//GET
public   java.util.List<com.sky.task.vo.Task>  getTaskInfo () {
String resource = getConf().getString("server.ws.resource.issueds.list");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doGet(new GenericType<java.util.List<com.sky.task.vo.Task>>(){},resource, queryParams, headerParams, cookies);
}
//DELETE
public   com.joe.core.vo.ReCode  kill (long param0) {
String resource = getConf().getString("server.ws.resource.issueds.kill.tid");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{tid\\}",""+param0);
return this.doDelete(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,null);
}
//GET
public   java.util.List<com.joe.agent.vo.LogFile>  getLogs (int param0,int param1) {
String resource = getConf().getString("server.ws.resource.issueds.logs");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
queryParams.add("pageNo",""+param0);
queryParams.add("pageSize",""+param1);
return this.doGet(new GenericType<java.util.List<com.joe.agent.vo.LogFile>>(){},resource, queryParams, headerParams, cookies);
}
//GET
public   com.joe.agent.vo.LogFile  getSingleLog (long param0) {
String resource = getConf().getString("server.ws.resource.issueds.logs.tid");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{tid\\}",""+param0);
return this.doGet(com.joe.agent.vo.LogFile.class,resource, queryParams, headerParams, cookies);
}
//PUT
public   com.joe.core.vo.ReCode  writeBackTask (com.sky.task.vo.Task param0) {
String resource = getConf().getString("server.ws.resource.issueds.task.status");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doPut(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param0);
}
//PUT
public   com.joe.core.vo.ReCode  writeBackOrder (long param0,double param1) {
String resource = getConf().getString("server.ws.resource.issueds.order.oid");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{oid\\}",""+param0);
queryParams.add("completion",""+param1);
return this.doPut(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,null);
}

}