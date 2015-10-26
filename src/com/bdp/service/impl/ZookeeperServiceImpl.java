package com.bdp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.bdp.common.BdpClient;
import com.bdp.rest.issued.IssuedClient;
import com.bdp.rest.zookeeper.ZookeeperClient;
import com.bdp.service.ZookeeperService;
import com.joe.core.utils.JsonUtils;
import com.joe.core.vo.ReCode;
import com.joe.core.vo.ReCode.Data;
import com.joe.service.utils.Component;
import com.joe.service.vo.*;
import com.joe.service.vo.ServiceVo.ServiceVoType;
import com.sky.task.vo.Task;


public class ZookeeperServiceImpl implements  ZookeeperService{

	public static final int ZooKeeper_SUCCESS = 1;
	public static final int ZooKeeper_FAIL=-1;

	private ZookeeperClient zookeeperClient ;
	private IssuedClient issuedClient;


	public IssuedClient getIssuedClient() {
		return issuedClient;
	}
	public void setIssuedClient(IssuedClient issuedClient) {
		this.issuedClient = issuedClient;
	}
	public ZookeeperClient getZookeeperClient() {
		return zookeeperClient;
	}
	public void setZookeeperClient(ZookeeperClient zookeeperClient) {
		this.zookeeperClient = zookeeperClient;
	}
	public int install(HttpServletRequest request) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject() ;
		ConfigProperty pro1 =new ConfigProperty();
		ConfigProperty pro2 =new ConfigProperty();
		HashSet<ConfigProperty> properties = new HashSet<ConfigProperty>();
		List<ConfigurePropertySet> props=new ArrayList<ConfigurePropertySet>();
		ConfigurePropertySet propertySet=new ConfigurePropertySet();
		Set<ServiceHost> hosts = new HashSet<ServiceHost>();
		ServiceVo serviceVo=new ServiceVo();

		String installZK=request.getParameter("installZK");
		String fileName=request.getParameter("fileName");
		String save=request.getParameter("save");
		String time=request.getParameter("time");
		String version=request.getParameter("versionZK");
		String[] subBox = request.getParameterValues("subBox");

		pro1.setName("dataDir");
		pro1.setValue(save);
		pro2.setName("tickTime");
		pro2.setValue(time);

		properties.add(pro1);
		properties.add(pro2);

		propertySet.setFileName(fileName);
		propertySet.setFileType("cfg");
		propertySet.setProperties(properties);

		props.add(propertySet);


		for (int i = 0; i < subBox.length; i++) {
			ServiceHost host=new ServiceHost();
			host.setHostIp(subBox[i]);
			hosts.add(host);
		}

		serviceVo.setProps(props);
		serviceVo.setHosts(hosts);
		serviceVo.setVersion(version);
		serviceVo.setInstallDir(installZK);
		serviceVo.setType(ServiceVoType.INSTALL.getValue());
		serviceVo.setName(Component.Zookeeper.getValue());
		//serviceVo.setType(ServiceVoType.CONFIG.getValue());

		System.out.println("serviceVo="+JsonUtils.objectToJson(serviceVo));
		ReCode	code=zookeeperClient.install(serviceVo);
		int ret=code.getRet();
		System.out.println("code.getRet()="+ret);
		if(ret==code.RET_FAIL)
		{
			request.setAttribute("error_msg", code.getMsg()+"提交任务失败！");
			return ZooKeeper_FAIL;
		}
		if(ret==code.RET_SUCCESS)
		{
			Data data=code.getData();
			Long taskID=data.getId();

			request.setAttribute("taskID", taskID);
			request.setAttribute("path", "web/zookeeper/getStatus");
			request.setAttribute("servicesName", "ZooKeeper");
			return ZooKeeper_SUCCESS;
		}
		return ret;
	}
	public JSONObject getStatus(HttpServletRequest request) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject() ;
		String taskID=request.getParameter("taskID");
		System.out.println("taskID="+taskID);
		Task task =issuedClient.getSingleTask(Long.valueOf(taskID));
		System.out.println("task.getOrders().size()="+task.getOrders().size());
		System.out.println("task.getCompletion()="+task.getCompletion());
		jsonObject=JsonUtils.objectToJson(task);
		System.out.println(jsonObject.toString());
		return jsonObject;
	}

}
