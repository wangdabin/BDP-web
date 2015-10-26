package com.bdp.vo;

import java.util.List;

public class Configuration {
	String name;
	String value;
	Boolean isDir;
	String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Configuration() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getIsDir() {
		return isDir;
	}
	public void setIsDir(Boolean isDir) {
		this.isDir = isDir;
	}
	@Override
	public String toString() {
		return "Configuration [name=" + name + ", value=" + value + ", isDir="
				+ isDir + ", description=" + description + "]";
	}
	
 
}
