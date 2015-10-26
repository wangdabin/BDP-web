package com.bdp.vo;

import java.util.Arrays;
import java.util.List;

public class ServiceFu {
	String name_fu;
	List<ServiceZi> zis;
	public String getName_fu() {
		return name_fu;
	}
	public void setName_fu(String name_fu) {
		this.name_fu = name_fu;
	}
	public List<ServiceZi> getZis() {
		return zis;
	}
	public void setZis(List<ServiceZi> zis) {
		this.zis = zis;
	}
	public ServiceFu() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ServiceFu [name_fu=" + name_fu + ", zis=" + zis + "]";
	}
	 
	
}
