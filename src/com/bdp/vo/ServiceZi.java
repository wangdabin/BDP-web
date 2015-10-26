package com.bdp.vo;

import java.util.Arrays;
import java.util.List;

public class ServiceZi {
	String name_zi;//服务名namenode
	List<Service> services;
	public String getName_zi() {
		return name_zi;
	}
	public void setName_zi(String name_zi) {
		this.name_zi = name_zi;
	}
	public List<Service> getServices() {
		return services;
	}
	public void setServices(List<Service> services) {
		this.services = services;
	}
	public ServiceZi() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ServiceZi [name_zi=" + name_zi + ", services=" + services + "]";
	}
 
	

	
}
