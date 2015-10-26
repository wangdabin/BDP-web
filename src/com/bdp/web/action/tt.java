package com.bdp.web.action;

import java.util.ArrayList;
import java.util.List;

public class tt {

	public static void main(String[] args) {
		List list1=new ArrayList();
		List list2=new ArrayList();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list2.add(4);
		list2.add(5);
		list2.add(6);
		System.out.println(list1.toString());
		System.out.println(list2.toString());
		list1.addAll(list2);
		System.out.println(list1.toString());
	}
}
