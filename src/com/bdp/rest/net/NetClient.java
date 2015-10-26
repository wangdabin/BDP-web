package com.bdp.rest.net;

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
public   class  NetClient extends AbstractClient {

public     NetClient () throws java.io.IOException{
super(ClientConfigUtils.create());
}
//POST
public   com.joe.core.vo.ReCode  add (com.joe.monitor.net.NetInterfaceUnit param0) {
String resource = getConf().getString("server.ws.resource.net.add");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doPost(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param0);
}
//DELETE
public   com.joe.core.vo.ReCode  delete (int param0) {
String resource = getConf().getString("server.ws.resource.net.delete.hid");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{hid\\}",""+param0);
return this.doDelete(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,null);
}
//GET
public   java.util.List<com.joe.monitor.net.NetInterfaceUnit>  getConsInfo () {
String resource = getConf().getString("server.ws.resource.net.list");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doGet(new GenericType<java.util.List<com.joe.monitor.net.NetInterfaceUnit>>(){},resource, queryParams, headerParams, cookies);
}
//GET
public   com.joe.monitor.net.NetInterfaceCluster  getStatInfo () {
String resource = getConf().getString("server.ws.resource.net.statistic");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doGet(com.joe.monitor.net.NetInterfaceCluster.class,resource, queryParams, headerParams, cookies);
}

}