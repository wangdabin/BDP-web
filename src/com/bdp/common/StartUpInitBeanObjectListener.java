package com.bdp.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 负责创建系统的Bean对象，只创建一次。而且在服务器启动时创建
 * 
 * 监听ServletContext对象，如果这个对象被创建，就会执行监听器的初始化方法(contextInitialized)。
 * 如果这个对象被销毁，就会执行监听器的销毁方法(contextDestroyed)
 * @author xuend
 *
 */
public class StartUpInitBeanObjectListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("系统正在销毁Bean对象....");
		try {
			BeanFactory.detoryBean();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("系统销毁Bean对象完成....");
	}

	public void contextInitialized(ServletContextEvent arg0) {		
		System.out.println("系统正在创建Bean对象....");
		try {
			BeanFactory.createBean();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("系统创建Bean对象完成....");
	}

}
