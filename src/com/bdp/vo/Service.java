package com.bdp.vo;

public class Service  {
	String name;//服务名
	String ip;//服务器地址
	String timebg;//开始时间
	String timecr;//最近发生时间
	String state;//状态描述
	String num;//启停次数
	
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
	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Service(String name, String ip, String timebg, String timecr,
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
