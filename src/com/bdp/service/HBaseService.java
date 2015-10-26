package com.bdp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

/**
 * hbase服务等服务层接口
 * @author xs
 *
 */

public interface HBaseService {

	void install(HttpServletRequest request);
	JSONObject getStatus(HttpServletRequest request); 

}
