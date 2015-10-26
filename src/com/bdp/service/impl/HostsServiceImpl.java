package com.bdp.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.bdp.rest.host.HostClient;
import com.bdp.service.HostsService;
import com.bdp.vo.ClientHost;
import com.joe.core.utils.JsonUtils;
import com.joe.host.init.Status;
import com.joe.host.vo.FindResult;
import com.joe.host.vo.Host;
import com.joe.host.vo.HostRange;
import com.sky.monitor.MonitorUtils;

/**
 * 主机服务层实现类
 * 
 * @author xuend
 * 
 */
public class HostsServiceImpl implements HostsService {

	// 注入rest对象
	private HostClient hostClient;

	public HostClient getHostClient() {
		return hostClient;
	}

	public void setHostClient(HostClient hostClient) {
		this.hostClient = hostClient;
	}

	// 列出所有主机信息
	public List<ClientHost> list() throws ConfigurationException, JSONException {
		System.out.println("=============");
		List<Host> listHosts = hostClient.list();
		List<ClientHost> hosts = new ArrayList<ClientHost>();
		// JSONArray jsonArray = JsonUtils.objectToJsonArray(listHosts);
		// JSONObject jsonObejct = jsonArray.toJSONObject(jsonArray);
		// for (int i = 0; i < jsonArray.length(); i++) {
		// //jsonArray.
		// JSONObject object = (JSONObject) jsonArray.get(i);
		// int num=((int)(1+7*(Math.random())));
		// ClientHost clientHost=new ClientHost() ;
		// clientHost.setIp((String)object.get("ip"));
		// clientHost.setName("nimei");
		// clientHost.setHostnum(String.valueOf(num));
		// hosts.add(clientHost);
		// }
		// System.out.println(hosts);

		for (Host host : listHosts) {
			int num = ((int) (1 + 7 * (Math.random())));
			ClientHost clientHost = new ClientHost();
			clientHost.setHid(host.getId());
			clientHost.setIp(host.getIp());
			clientHost.setName(host.getName());
			clientHost.setHostnum(String.valueOf(num));
			hosts.add(clientHost);
		}
		return hosts;
	}

	// 单个添加主机信息
	public boolean addSingle(HttpServletRequest request) throws IOException,
			JSONException {
		String host_ip = request.getParameter("ip");
		String host_name = request.getParameter("host_name");
		String root_name = request.getParameter("root_name");
		String root_pswd = request.getParameter("root_pswd");

		// 首先根据ip进行查询,查询主机是否存在;如果存在则执行添加,不存在则提示用户无法添加

		Host findHost = new Host();
		findHost.setIp(host_ip);
		findHost.setRoot(root_name);
		findHost.setRootPass(root_pswd);
		findHost.setName(host_name);
		Host[] hosts = { findHost };
		FindResult findResult = hostClient.find(hosts);
		request.setAttribute("ips", host_ip);
		request.setAttribute("root_name", root_name);
		request.setAttribute("root_pswd", root_pswd);
		boolean flag = false;
		if (findResult.getExistHosts() != null) {
			flag = findResult.getExistHosts().size() > 0 ? true : false;
		}
		if (flag) {
			Host host = new Host();
			host.setIp(host_ip);
			host.setName(host_name);
			host.setRoot(root_name);
			host.setRootPass(root_pswd);
			hostClient.add(host);
		}
		return flag;
	}

	// 根据文本添加主机
	public boolean addByText(HttpServletRequest request) throws IOException {
		String ipArea = request.getParameter("ipArea");
		String root_name = request.getParameter("root_name");
		String root_pswd = request.getParameter("root_pswd");
		// System.out.println(ipArea);
		List<String> ips = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		String strs[] = ipArea.split("\r\n");
		for (String str : strs) {
			String s[] = str.split(" |\t");
			String ip = s[0];
			String name = s[1];
			ips.add(ip);
			names.add(name);
		}
		// 所有从前台取得的主机列表
		Host[] hostsOfText = new Host[ips.size()];
		for (int i = 0; i < ips.size(); i++) {
			Host host = new Host();
			host.setIp(ips.get(i));
			host.setName(names.get(i));
			host.setRoot(root_name);
			host.setRootPass(root_pswd);
			hostsOfText[i] = host;

		}
		// 查找主机是否存在
		FindResult findResult = hostClient.find(hostsOfText);
		request.setAttribute("root_name", root_name);
		request.setAttribute("root_pswd", root_pswd);
		boolean flag = false;
		if (findResult.getExistHosts() != null) {
			flag = findResult.getExistHosts().size() > 0 ? true : false;
		}
		StringBuilder sb = new StringBuilder();

		if (!flag) {// 如果查询的主机全都不存在,返回错误页面提示所有主机均不存在
			int count = 0;
			List<Host> noExistHosts = findResult.getNoExistHosts();
			for (Host host : noExistHosts) {
				sb.append(host.getIp());
				count++;
				if (count < noExistHosts.size()) {
					sb.append(",");
				}
			}
			request.setAttribute("ips", sb.toString());

		} else {// 如果查询的主机部分存在,则将存在的主机进行添加操作
			int count = 0;
			List<Host> existHosts = findResult.getExistHosts();
			for (Host host : existHosts) {
				sb.append(host.getIp());
				count++;
				if (count < existHosts.size()) {
					sb.append(",");
				}
				// 挨个添加主机
				hostClient.add(host);
			}
			request.setAttribute("ips", sb.toString());
		}
		return flag;
	}

	// 根据文件导入批量添加主机
	public boolean addByFile(HttpServletRequest request) throws IOException,
			FileUploadException {
		String root_name = null;
		String root_pswd = null;
		List<String> ips = new ArrayList<String>();
		List<String> names = new ArrayList<String>();

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> itr = items.iterator();

		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			InputStream in = item.getInputStream();
			byte[] c = new byte[1024];
			int i = 0;
			String fileText = null;
			while ((i = in.read(c)) != -1) {
				fileText = new String(c, 0, i);
			}
			// System.out.println(v);
			if ("file".equals(item.getFieldName())) {
				String strs[] = fileText.split("\r\n");
				for (String str : strs) {
					String s[] = str.split(" |\t");
					String ip = s[0];
					String name = s[1];
					ips.add(ip);
					names.add(name);
				}
			} else if ("root_name".equals(item.getFieldName())) {
				root_name = fileText;
			} else if ("root_pswd".equals(item.getFieldName())) {
				root_pswd = fileText;
			}
		}
		// 所有从前台取得的主机列表
		Host[] hostsOfFile = new Host[ips.size()];
		for (int j = 0; j < ips.size(); j++) {
			Host host = new Host();
			host.setIp(ips.get(j));
			host.setName(names.get(j));
			host.setRoot(root_name);
			host.setRootPass(root_pswd);
			hostsOfFile[j] = host;
		}

		// 查找主机是否存在
		boolean flag = false;
		FindResult findResult = hostClient.find(hostsOfFile);
		request.setAttribute("root_name", root_name);
		request.setAttribute("root_pswd", root_pswd);
		if (findResult.getExistHosts() != null) {
			flag = findResult.getExistHosts().size() > 0 ? true : false;
		}
		StringBuilder sb = new StringBuilder();

		if (!flag) {// 如果查询的主机全都不存在,返回错误页面提示所有主机均不存在
			int count = 0;
			List<Host> noExistHosts = findResult.getNoExistHosts();
			for (Host host : noExistHosts) {
				sb.append(host.getIp());
				count++;
				if (count < noExistHosts.size()) {
					sb.append(",");
				}
			}
			request.setAttribute("ips", sb.toString());

		} else {// 如果查询的主机部分存在,则将存在的主机进行添加操作
			int count = 0;
			List<Host> existHosts = findResult.getExistHosts();
			for (Host host : existHosts) {
				sb.append(host.getIp());
				count++;
				if (count < existHosts.size()) {
					sb.append(",");
				}
				// 挨个添加主机
				hostClient.add(host);
			}
			request.setAttribute("ips", sb.toString());
		}
		return flag;
	}

	public void add(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	// 根据根据开始ip和结束ip查询主机
	public JSONObject checkIps(HttpServletRequest request) throws JSONException {
		String startHost = request.getParameter("startHost");
		String stopHost = request.getParameter("stopHost");
		String user = request.getParameter("user");
		String password = request.getParameter("password");

		HostRange hostRange = new HostRange();
		hostRange.setStartHost(startHost);
		hostRange.setStopHost(stopHost);
		hostRange.setUser(user);
		hostRange.setPassword(password);
		FindResult findResult = hostClient.find(hostRange);
		JSONObject jsonObject = JsonUtils.objectToJson(findResult);
		jsonObject.put("success", true);
		if (findResult.getExistHosts() == null) {
			jsonObject.put("success", false);
		}
		return jsonObject;
	}

	public void addByIps(HttpServletRequest request) throws IOException {
		String[] ips = request.getParameterValues("subBox");
		String host_name = request.getParameter("host_name");
		String root_name = request.getParameter("root_name");
		String root_pswd = request.getParameter("root_pswd");
		StringBuilder sb = new StringBuilder();
		int count = 0;
		Host host = new Host();
		for (String ip : ips) {
			sb.append(ip);
			count++;
			if (count < ips.length) {
				sb.append(",");
			}
			host.setIp(ip);
			host.setName(host_name);
			host.setRoot(root_name);
			host.setRootPass(root_pswd);
			hostClient.add(host);
		}
		request.setAttribute("ips", sb.toString());
	}

	public JSONObject monitor(HttpServletRequest request) throws JSONException {
		String ips = request.getParameter("ips");
		String root_name = request.getParameter("root_name");
		String root_pswd = request.getParameter("root_pswd");
		String[] hostsIp = { ips };
		int index = ips.indexOf(",");
		if (index != -1) {
			hostsIp = ips.split(",");
		}
		Host host = new Host();
		host.setRoot(root_name);
		host.setRootPass(root_pswd);
		// 总主机数
		int hosts = hostsIp.length;
		// 安装成功的主机数
		int success = 0;
		// 正在安装的主机数
		int install = 0;
		// 安装失败的主机数
		int fail = 0;

		// 总进度
		int percent = 0;
		for (String ip : hostsIp) {
			host.setIp(ip);
			Status status = hostClient.monitor(host);
			if (status.getProgress() == -1) {
				fail++;
			} else if (status.getProgress() == 100) {
				success++;
				percent += status.getProgress();
			} else {
				install++;
				percent += status.getProgress();
			}
		}
		// 安装的平均总进度
		double process = 0.0;
		if (fail < hosts) {
			process = percent / (hosts - fail);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("hosts", hosts);
		jsonObject.put("success", success);
		jsonObject.put("install", install);
		jsonObject.put("fail", fail);
		jsonObject.put("process", MonitorUtils.DFormat(process));
		return jsonObject;
	}
}
