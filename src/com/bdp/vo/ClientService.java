package com.bdp.vo;

import java.util.List;

public class ClientService  {
	private String name;//服务名
	private String ip;//服务器地址
	private String timebg;//开始时间
	private String timecr;//最近发生时间
	private String state;//状态描述
	private String num;//启停次数
	private List<ClientService> childService;
	private ClientService parentService;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTimebg() {
		return timebg;
	}
	public void setTimebg(String timebg) {
		this.timebg = timebg;
	}
	public String getTimecr() {
		return timecr;
	}
	public void setTimecr(String timecr) {
		this.timecr = timecr;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	
	public List<ClientService> getChildService() {
		return childService;
	}
	public void setChildService(List<ClientService> childService) {
		this.childService = childService;
	}
	public ClientService getParentService() {
		return parentService;
	}
	public void setParentService(ClientService parentService) {
		this.parentService = parentService;
	}
	public ClientService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ClientService(String name, String ip, String timebg, String timecr,
			String state, String num) {
		super();
		this.name = name;
		this.ip = ip;
		this.timebg = timebg;
		this.timecr = timecr;
		this.state = state;
		this.num = num;
	}
	@Override
	public String toString() {
		return "Service [name=" + name + ", ip=" + ip + ", timebg=" + timebg
				+ ", timecr=" + timecr + ", state=" + state + ", num=" + num
				+ "]";
	}


}
