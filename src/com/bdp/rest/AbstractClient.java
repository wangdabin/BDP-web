package com.bdp.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;

import com.joe.core.exception.HttpClientErrorException;
import com.joe.core.rest.RestClient;
import com.joe.core.utils.HttpStatus;
import com.joe.core.vo.ErrorMessage;
import com.sky.config.ConfigAble;
import com.sky.config.Configed;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

/**
 * 
 * @author qiaolong
 * 
 */
public class AbstractClient extends Configed implements ConfigAble {

	private static final Logger LOG = Logger.getLogger(AbstractClient.class);
	public static final String TOKEN_KEY = "JSESSIONID";
	private String token; // 用于认证。。

	public AbstractClient(Configuration conf) {
		super(conf);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private List<Cookie> checkAuth(List<Cookie> cookies) {
		if (cookies == null) {
			cookies = new ArrayList<Cookie>();
			if (token != null) {
				cookies.add(new Cookie(TOKEN_KEY, token));
			}
		} else {
			boolean hasToken = false;
			for (Cookie cookie : cookies) {
				if (TOKEN_KEY.equals(cookie.getName())) {
					hasToken = true;
					break;
				}
			}
			if (!hasToken && token != null) {
				cookies.add(new Cookie(TOKEN_KEY, token));
			}
		}
		return cookies;
	}

	private <T> T parseResponse(ClientResponse resp, Class<T> clazz) {
		int code = resp.getStatus();
		HttpStatus httpStatus = HttpStatus.valueOf(code);
		if (httpStatus.is2xxSuccessful()) {
			T t = resp.getEntity(clazz);
			return t;
		} else if (httpStatus.is4xxClientError()) {
			throw new HttpClientErrorException(httpStatus);
		} else if (httpStatus.is5xxServerError()) {
			try {
				ErrorMessage error = resp.getEntity(ErrorMessage.class);
				httpStatus = HttpStatus.valueOf(error.getCode());
				throw new HttpClientErrorException(httpStatus,
						error.getMessage());
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
				LOG.error(getContent(resp));
				throw new HttpClientErrorException(httpStatus);
			}
		} else {
			throw new HttpClientErrorException(httpStatus);
		}
	}
	
	private <T> T parseResponse(ClientResponse resp, GenericType<T> gt) {
		int code = resp.getStatus();
		HttpStatus httpStatus = HttpStatus.valueOf(code);
		if (httpStatus.is2xxSuccessful()) {
			T t = resp.getEntity(gt);
			return t;
		} else if (httpStatus.is4xxClientError()) {
			throw new HttpClientErrorException(httpStatus);
		} else if (httpStatus.is5xxServerError()) {
			try {
				ErrorMessage error = resp.getEntity(ErrorMessage.class);
				httpStatus = HttpStatus.valueOf(error.getCode());
				throw new HttpClientErrorException(httpStatus,
						error.getMessage());
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
				LOG.error(getContent(resp));
				throw new HttpClientErrorException(httpStatus);
			}
		} else {
			throw new HttpClientErrorException(httpStatus);
		}
	}

	private String getContent(ClientResponse resp) {
		InputStream in = resp.getEntityInputStream();
		try {
			byte[] b = new byte[in.available()];
			in.read(b);
			return new String(b);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 
	 * @param clazz
	 * @param resource
	 * @param queryParams
	 * @param headers
	 * @param cookies
	 * @return
	 */
	protected <T> T doGet(Class<T> clazz, String resource,
			MultivaluedMap<String, String> queryParams,
			Map<String, String> headers, List<Cookie> cookies) {
		cookies = this.checkAuth(cookies);
		ClientResponse resp = RestClient.get(ClientResponse.class, resource,
				queryParams, headers, cookies);
		T t = parseResponse(resp, clazz);
		resp.close();
		return t;
	}
	
	/**
	 * 
	 * @param clazz
	 * @param resource
	 * @param queryParams
	 * @param headers
	 * @param cookies
	 * @return
	 */
	protected <T> T doGet(GenericType<T> clazz, String resource,
			MultivaluedMap<String, String> queryParams,
			Map<String, String> headers, List<Cookie> cookies) {
		cookies = this.checkAuth(cookies);
		ClientResponse resp = RestClient.get(ClientResponse.class, resource,
				queryParams, headers, cookies);
		T t = parseResponse(resp, clazz);
		resp.close();
		return t;
	}

	/**
	 * 
	 * @param clazz
	 * @param resource
	 * @param queryParams
	 * @param headers
	 * @param cookies
	 * @param value
	 * @return
	 */
	protected <T> T doPost(Class<T> clazz, String resource,
			MultivaluedMap<String, String> queryParams,
			Map<String, String> headers, List<Cookie> cookies, Object value) {
		cookies = this.checkAuth(cookies);
		ClientResponse resp = RestClient.post(ClientResponse.class, resource,
				queryParams, headers, cookies, value);
		T t = parseResponse(resp, clazz);
		resp.close();
		return t;
	}
	
	/**
	 * 
	 * @param clazz
	 * @param resource
	 * @param queryParams
	 * @param headers
	 * @param cookies
	 * @param value
	 * @return
	 */
	protected <T> T doPost(GenericType<T> clazz, String resource,
			MultivaluedMap<String, String> queryParams,
			Map<String, String> headers, List<Cookie> cookies, Object value) {
		cookies = this.checkAuth(cookies);
		ClientResponse resp = RestClient.post(ClientResponse.class, resource,
				queryParams, headers, cookies, value);
		T t = parseResponse(resp, clazz);
		resp.close();
		return t;
	}

	/**
	 * 
	 * @param clazz
	 * @param resource
	 * @param queryParams
	 * @param headers
	 * @param cookies
	 * @param value
	 * @return
	 */
	protected <T> T doPut(Class<T> clazz, String resource,
			MultivaluedMap<String, String> queryParams,
			Map<String, String> headers, List<Cookie> cookies, Object value) {
		cookies = this.checkAuth(cookies);
		ClientResponse resp = RestClient.put(ClientResponse.class, resource,
				queryParams, headers, cookies, value);
		T t = parseResponse(resp, clazz);
		resp.close();
		return t;
	}
	
	/**
	 * 
	 * @param clazz
	 * @param resource
	 * @param queryParams
	 * @param headers
	 * @param cookies
	 * @param value
	 * @return
	 */
	protected <T> T doPut(GenericType<T> clazz, String resource,
			MultivaluedMap<String, String> queryParams,
			Map<String, String> headers, List<Cookie> cookies, Object value) {
		cookies = this.checkAuth(cookies);
		ClientResponse resp = RestClient.put(ClientResponse.class, resource,
				queryParams, headers, cookies, value);
		T t = parseResponse(resp, clazz);
		resp.close();
		return t;
	}

	/**
	 * 
	 * @param clazz
	 * @param resource
	 * @param queryParams
	 * @param headers
	 * @param cookies
	 * @param value
	 * @return
	 */
	protected <T> T doDelete(Class<T> clazz, String resource,
			MultivaluedMap<String, String> queryParams,
			Map<String, String> headers, List<Cookie> cookies, Object value) {
		cookies = this.checkAuth(cookies);
		ClientResponse resp = RestClient.delete(ClientResponse.class, resource,
				queryParams, headers, cookies, value);
		T t = parseResponse(resp, clazz);
		resp.close();
		return t;
	}
	
	/**
	 * 
	 * @param clazz
	 * @param resource
	 * @param queryParams
	 * @param headers
	 * @param cookies
	 * @param value
	 * @return
	 */
	protected <T> T doDelete(GenericType<T> clazz, String resource,
			MultivaluedMap<String, String> queryParams,
			Map<String, String> headers, List<Cookie> cookies, Object value) {
		cookies = this.checkAuth(cookies);
		ClientResponse resp = RestClient.delete(ClientResponse.class, resource,
				queryParams, headers, cookies, value);
		T t = parseResponse(resp, clazz);
		resp.close();
		return t;
	}
}