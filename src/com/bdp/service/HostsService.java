package com.bdp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.fileupload.FileUploadException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.joe.host.vo.FindResult;

/**
 * 主机服务层接口
 * @author xuend
 *
 */
public interface HostsService {

	List list() throws ConfigurationException, JSONException;
	void add(HttpServletRequest request);
	boolean addSingle(HttpServletRequest request) throws IOException, JSONException;
	boolean addByText(HttpServletRequest request) throws IOException;
	boolean addByFile(HttpServletRequest request) throws IOException, FileUploadException;
	JSONObject checkIps(HttpServletRequest request) throws JSONException;
	void addByIps(HttpServletRequest request) throws IOException;
	JSONObject monitor(HttpServletRequest request) throws JSONException;
}
