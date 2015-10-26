package com.bdp.vo;

public class Services {
	String type;
	String name;
	String state;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Services(String type, String name, String state) {
		super();
		this.type = type;
		this.name = name;
		this.state = state;
	}
	public Services() {
		super();
		// TODO Auto-generated constructor stub
	}
}
