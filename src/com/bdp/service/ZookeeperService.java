package com.bdp.service;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONObject;

/**
 * hadoop服务等服务层接口
 * @author xs
 *
 */
public interface ZookeeperService {
	int install(HttpServletRequest request);
	JSONObject getStatus(HttpServletRequest request); 
}
