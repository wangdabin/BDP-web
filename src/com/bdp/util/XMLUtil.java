package com.bdp.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLUtil {

	public static List<Object> read(String filesString,String ele) {
		File file=new File(filesString);
		List<Object> lists=new ArrayList<Object>();
		try {
			SAXReader sr=new SAXReader();
			Document doc=sr.read(file);
			Element eleroot=doc.getRootElement();
			List<Element> list=eleroot.elements();
			lists=parse(list,ele);//解析	 
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
	private static List<Object> parse(List<Element> list,String ele) {
		List<Object> lists=new ArrayList<Object>();
		Iterator<Element> it=list.iterator();
		while (it.hasNext()) {
			Element element =it.next();
			String str=element.elementText(ele);
			lists.add(str);
		}
		return lists;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 List<Object> list=XMLUtil.read("/F:/eclipse/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/BDP-web/WEB-INF/classes/cdh5.1.0/core-site.xml", "name");
		 System.out.println(list.toString());
	}

}
