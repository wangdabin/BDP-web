package com.bdp.rest.role;

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
public   class  RoleClient extends AbstractClient {

public     RoleClient () throws java.io.IOException{
super(ClientConfigUtils.create());
}
//POST
public   com.joe.core.vo.ReCode  add (com.joe.user.vo.Role param0) {
String resource = getConf().getString("server.ws.resource.roles.add");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doPost(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param0);
}
//GET
public   java.util.List<com.joe.user.vo.Role>  list () {
String resource = getConf().getString("server.ws.resource.roles.list");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doGet(new GenericType<java.util.List<com.joe.user.vo.Role>>(){},resource, queryParams, headerParams, cookies);
}
//PUT
public   com.joe.core.vo.ReCode  update (int param0,com.joe.user.vo.Role param1) {
String resource = getConf().getString("server.ws.resource.roles.hid");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{hid\\}",""+param0);
return this.doPut(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param1);
}
//GET
public   java.util.List<com.joe.user.vo.Role>  getAll () {
String resource = getConf().getString("server.ws.resource.roles");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doGet(new GenericType<java.util.List<com.joe.user.vo.Role>>(){},resource, queryParams, headerParams, cookies);
}
//GET
public   com.joe.user.vo.Role  getRole (int param0) {
String resource = getConf().getString("server.ws.resource.roles.id");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
resource = resource.replaceAll("\\{id\\}",""+param0);
return this.doGet(com.joe.user.vo.Role.class,resource, queryParams, headerParams, cookies);
}

}