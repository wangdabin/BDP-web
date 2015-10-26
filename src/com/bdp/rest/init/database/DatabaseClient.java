package com.bdp.rest.init.database;

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
public   class  DatabaseClient extends AbstractClient {

public     DatabaseClient () throws java.io.IOException{
super(ClientConfigUtils.create());
}
//GET
public   com.joe.core.database.DataBase  getDataBase () {
String resource = getConf().getString("server.init.resource.database");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doGet(com.joe.core.database.DataBase.class,resource, queryParams, headerParams, cookies);
}
//GET
public   java.util.Collection<com.joe.core.database.Dialect>  getSupperts () {
String resource = getConf().getString("server.init.resource.databasesupport");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doGet(new GenericType<java.util.Collection<com.joe.core.database.Dialect>>(){},resource, queryParams, headerParams, cookies);
}
//POST
public   com.joe.core.vo.ReCode  setDatabase (com.joe.core.database.DataBase param0) {
String resource = getConf().getString("server.init.resource.database");
MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
Map<String, String> headerParams = new HashMap<String, String>();
List<Cookie> cookies = new ArrayList<Cookie>();
return this.doPost(com.joe.core.vo.ReCode.class,resource, queryParams, headerParams, cookies,param0);
}

}