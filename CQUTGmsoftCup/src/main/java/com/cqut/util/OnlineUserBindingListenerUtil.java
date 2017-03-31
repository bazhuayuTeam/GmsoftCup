package com.cqut.util; 
  
import java.util.ArrayList; 
import java.util.List; 
import javax.servlet.ServletContext; 
import javax.servlet.http.HttpSession; 
import javax.servlet.http.HttpSessionBindingEvent; 
import javax.servlet.http.HttpSessionBindingListener; 
  
public class OnlineUserBindingListenerUtil implements HttpSessionBindingListener { 
    String operatorCode; 
      
    public OnlineUserBindingListenerUtil(String operatorCode){ 
        this.operatorCode=operatorCode; 
    } 
    @SuppressWarnings("unchecked")
	public void valueBound(HttpSessionBindingEvent event) { 
        HttpSession session = event.getSession(); 
        ServletContext application = session.getServletContext(); 
        // 把用户名放入在线列表 
        List<String> onlineUserList = (List<String>) application.getAttribute("onlineUserList"); 
        // 第一次使用前，需要初始化 
        if (onlineUserList == null) { 
            onlineUserList = new ArrayList<String>(); 
            application.setAttribute("onlineUserList", onlineUserList); 
        } 
        onlineUserList.add(this.operatorCode); 
    } 
  
    @SuppressWarnings("unchecked")
	public void valueUnbound(HttpSessionBindingEvent event) { 
        HttpSession session = event.getSession(); 
        ServletContext application = session.getServletContext(); 
  
        // 从在线列表中删除用户名 
        List<String> onlineUserList = (List<String>) application.getAttribute("onlineUserList"); 
        onlineUserList.remove(this.operatorCode); 
        //
  
    } 
  
}