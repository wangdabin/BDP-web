package com.bdp.web.action;

/**
 * 通过克隆方式实现Action多例
 * @author xuend
 */
public class MultiAction implements Cloneable{
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
