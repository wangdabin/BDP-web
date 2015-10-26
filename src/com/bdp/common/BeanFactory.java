/**
 * 对象工厂类
 */
package com.bdp.common;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bdp.web.action.MultiAction;

/**
 * 管理对象的生命周期（Action,Service）
 * @author xuend
 */
public class BeanFactory {
	
	/**
	 * 存储Rest对象
	 */
	private static Map<String,Object> restObjectMap = new HashMap<String,Object>();
	
	/**
	 * 存储Service对象
	 */
	private static Map<String,Object> serviceObjectMap = new HashMap<String,Object>();

	/**
	 * 存储Action对象
	 */
	private static Map<String,Object> actionObjectMap = new HashMap<String,Object>();
	
	/**
	 * 根据id获取Rest对象
	 * @param id
	 * @return
	 */
	private static Object getRestObject(String id) {
		return restObjectMap.get(id);
	}
	
	/**
	 * 根据id获取Service对象
	 * @param id
	 * @return
	 */
	public static Object getServiceObject(String id){
		return serviceObjectMap.get(id);
	}
	
	/**
	 * 根据id获取Action对象,为了解决单实例线程安全问题,用克隆的方式实现多例
	 * @param id
	 * @return
	 * @throws CloneNotSupportedException 
	 * @throws ClassNotFoundException 
	 */
	public static Object getActionObject(String id) throws CloneNotSupportedException, ClassNotFoundException{
		Object actionObject = actionObjectMap.get(id);
		if(actionObject instanceof MultiAction){
			MultiAction action = (MultiAction) actionObject ;			
			return action.clone();
		}
		return null;
	}
	
	
	
	/**
	 * 创建系统的Bean对象
	 * @throws Exception
	 */
	public static void createBean() throws Exception{
		
		SAXReader reader = new SAXReader();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("bean-cfg.xml");
		Document doc = reader.read(in);

		/*
		 * 获取所有的Rest对象
		 */
		String xpath = "//bean[@type='rest']";
		List<Element> restElements = doc.selectNodes(xpath);
		for(Element restElement : restElements){
			String id = restElement.attributeValue("id");
			String restClassName = restElement.attributeValue("class");
			Class restClazz = Class.forName(restClassName);
			Object restObject = restClazz.newInstance();
			//存放到临时存储空间中			
			restObjectMap.put(id, restObject);
		}
		
		/*
		 * 获取所有的Service对象
		 */
		xpath = "//bean[@type='service']";
		List<Element> serviceElements = doc.selectNodes(xpath);
		for(Element serviceElement : serviceElements){
			String id = serviceElement.attributeValue("id");
			String serviceClassName = serviceElement.attributeValue("class");
			Class serviceClazz = Class.forName(serviceClassName);
			Object serviceObject = serviceClazz.newInstance();
			//存放到临时存储空间中			
			serviceObjectMap.put(id, serviceObject);
			
			//组合Service和Rest之间的关系
			
			List<Element> propertyElements = serviceElement.elements("property");
			
			for(Element propertyElement : propertyElements){
				String refid = propertyElement.attributeValue("ref");
				String restId = refid ;
				//获取Rest对象
				Object restObject = getRestObject(restId);
				
				String propertyName = propertyElement.attributeValue("name"); 
				String setPropertyName = "set"+propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
				
				//获取属性的类型，属性类型也是set方法的参数对象类型
				Field propertyField = serviceClazz.getDeclaredField(propertyName);
				Class propertyType = propertyField.getType();
				//通过方法的名称，反射获取方法对象
				Method setPropertyMethodObject = serviceClazz.getMethod(setPropertyName, propertyType);
				
				//反射调用setUserService方法，将Rest对象关联到Service对象中。
				setPropertyMethodObject.invoke(serviceObject, restObject);
				
			}
		}
		
		/*
		 * 获取所有的Action对象
		 */
		xpath = "//bean[@type='action']";
		
		List<Element> actionElements = doc.selectNodes(xpath);

		for(Element actionElement : actionElements){
			String id = actionElement.attributeValue("id");
			String actionClassName = actionElement.attributeValue("class");
			Class actionClazz = Class.forName(actionClassName);
			Object actionObject = actionClazz.newInstance();
			//存放action对象到临时存储空间中			
			actionObjectMap.put(id, actionObject);
			
			//组合Acion和Service之间的关系
			
			List<Element> propertyElements = actionElement.elements("property");
			
			for(Element propertyElement : propertyElements){
				String refid = propertyElement.attributeValue("ref");
				String serviceId = refid ;
				//获取Service对象
				Object serviceObject = getServiceObject(serviceId);
				
				String propertyName = propertyElement.attributeValue("name"); 
				String setPropertyName = "set"+propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
				
				//获取属性的类型，属性类型也是set方法的参数对象类型
				Field propertyField = actionClazz.getDeclaredField(propertyName);
				Class propertyType = propertyField.getType();
				//通过方法的名称，反射获取方法对象
				Method setPropertyMethodObject = actionClazz.getMethod(setPropertyName, propertyType);
				
				//反射调用setUserService方法，将Service对象关联到Action对象中。
				setPropertyMethodObject.invoke(actionObject, serviceObject);
				
			}
		}
	}
	

	/**
	 * 销毁系统的Bean对象
	 * @throws Exception
	 */
	public static void detoryBean() throws Exception{
		serviceObjectMap.clear();
		actionObjectMap.clear();
		System.gc();
	}
}
