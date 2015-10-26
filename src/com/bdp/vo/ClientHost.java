package com.bdp.vo;

public class ClientHost {
	private int hid;
	private String ip;
	private String name;
	private String hostnum;
	
	
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHostnum() {
		return hostnum;
	}
	public void setHostnum(String hostnum) {
		this.hostnum = hostnum;
	}
	
	public ClientHost(){
		
	}
	public ClientHost(String ip, String name, String hostnum) {
		super();
		this.ip = ip;
		this.name = name;
		this.hostnum = hostnum;
	}
	@Override
	public String toString() {
		return "ClientHost [ip=" + ip + ", name=" + name + ", hostnum="
				+ hostnum + "]";
	}

	
	
}
