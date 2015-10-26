package com.bdp.vo;

import java.util.List;

public class ConfigurationList {
	String fileName;
	List<Configuration> configs;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<Configuration> getConfigs() {
		return configs;
	}
	public void setConfigs(List<Configuration> configs) {
		this.configs = configs;
	}
	public ConfigurationList() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ConfigurationList [fileName=" + fileName + ", configs="
				+ configs + "]";
	}
	
}
