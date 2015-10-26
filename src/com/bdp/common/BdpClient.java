package com.bdp.common;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * BDP项目客户端
 * 
 * @author Administrator
 * 
 */
public class BdpClient {

	public static void main(String[] args) throws JSONException {
		String resource = "http://127.0.0.1:8098/ws/v1/hosts/433";
		JSONObject obj = delete(resource);
		System.out.println(obj.toString());
	}

	/**
	 * 请求资源
	 * 
	 * @param resource
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject get(String resource) throws JSONException {
		
		Client client = Client.create();
		WebResource webResource = client.resource(resource);
		// 添加请求头信息,包含token用户服务端验证合法性
		// webResource.header("token", "...");
		return webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(
				JSONObject.class);
	}
	/**
	 * 请求资源
	 * 
	 * @param resource
	 * @return
	 * @throws JSONException
	 */
	public static Object get(String resource,Class clazz) throws JSONException {
		// resource = "http://127.0.0.1:8080/test/rest/user/1"
		
		Client client = Client.create();
		WebResource webResource = client.resource(resource);
		// 添加请求头信息,包含token用户服务端验证合法性
		// webResource.header("token", "...");
		return webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(clazz);
	}

	/**
	 * 删除资源
	 * 
	 * @param resource
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject delete(String resource) throws JSONException {
		Client client = Client.create();
		WebResource webResource = client.resource(resource);
		// 添加请求头信息,包含token用户服务端验证合法性
		webResource.header("token", "...");
		JSONObject json = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
				.delete(JSONObject.class);
		return json;
	}

	/**
	 * 新增资源
	 * 
	 * @param resource
	 * @param object
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject post(String resource, Object object)
			throws JSONException {
		Client client = Client.create();
		WebResource webResource = client.resource(resource);
		// 添加请求头信息,包含token用户服务端验证合法性
		webResource.header("token", "...");
		JSONObject json = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
				.post(JSONObject.class, object);
		return json;
	}
	/**
	 * 新增资源
	 * 
	 * @param resource
	 * @param object
	 * @return
	 * @throws JSONException
	 */
	public static <T> T post(Class<T> clazz, String resource, Object object)
			throws JSONException {
		Client client = Client.create();
		WebResource webResource = client.resource(resource);
		// 添加请求头信息,包含token用户服务端验证合法性
		//webResource.header("token", "...");
		return webResource.accept(MediaType.APPLICATION_JSON_TYPE).post(clazz, object);
	}

	/**
	 * 更新资源
	 * 
	 * @param resource
	 * @param object
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject put(String resource, Object object)
			throws JSONException {
		Client client = Client.create();
		WebResource webResource = client.resource(resource);
		// 添加请求头信息,包含token用户服务端验证合法性
		webResource.header("token", "...");
		JSONObject json = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
				.put(JSONObject.class, object);
		return json;
	}
}
