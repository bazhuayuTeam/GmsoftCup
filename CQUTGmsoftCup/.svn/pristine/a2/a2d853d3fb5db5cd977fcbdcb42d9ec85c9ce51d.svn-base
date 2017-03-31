package com.cqut.util;

import java.util.Date;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.extend.Alarm;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

@Controller
@RemoteProxy
public class CqutApplicationUtil implements ApplicationContextAware {
	  private static ApplicationContext applicationContext;     //Spring应用上下文环境 
	  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		  CqutApplicationUtil.applicationContext = applicationContext;
	  }
	 
	  public static ApplicationContext getApplicationContext() {
	    return applicationContext;
	  }
	  
	  @RemoteMethod
	  //获取服务器时间
	  public static Date getServerDate(){
		return new Date();
		  
	  }
}
