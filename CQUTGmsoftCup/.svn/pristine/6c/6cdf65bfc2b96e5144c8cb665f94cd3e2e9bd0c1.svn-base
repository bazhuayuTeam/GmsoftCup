/**
* 2011-9-3 
* jQueryLoader.java 
* author:helong
*/
package com.cqut.util;
import java.io.*;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.*;

public class JQueryLoader {
	//String path="http://localhost:8080/WebConnector/";

    private List<String> jsFilesList=new ArrayList<String>();//保存js文件的list
	private List<String> cssFilesList=new ArrayList<String>();//保存css文件list
	private String jsFilesStr="";//js文件引入语句
	private String cssFilesStr="";//css文件引入语句
    public JQueryLoader(String name ) {
    	String[] componentNames=name.split(",");
    	for(int i=0;i<componentNames.length;i++){
    		initComponent(componentNames[i]);
    	}
    	//生成js引入语句
		for(int num=0;num<jsFilesList.size();num++){
			jsFilesStr+="<script language='JavaScript' type='text/javascript' src='"+jsFilesList.get(num)+"'></script>";
		}
		//生成css引用语句
		for(int num=0;num<cssFilesList.size();num++){
			cssFilesStr+="<link rel='stylesheet' type='text/css' href='"+cssFilesList.get(num)+"'/>";
		}
    }
    
    public void initComponent(String componentName){
    	try {
    		String filePath = JQueryLoader.class.getClassLoader().getResource("JsCss.xml").getFile();
    		File f=new File(filePath);
    		if(!f.exists()){
    			f = new File(filePath.substring(1).replace("%20", " "));
    		}
    		SAXReader reader = new SAXReader();
            reader.setEncoding("utf-8");
            Document doc = reader.read(f);
            Element root = doc.getRootElement();
            Element component;
            for(Iterator i = root.elementIterator("component"); i.hasNext();) {
            	component=(Element)i.next();
            	if(component.attributeValue("name").toString().equals(componentName)){
            		//添加js文件
            		for(Iterator j = component.elementIterator("jsFile"); j.hasNext();) {
            			Element jsFileElement=(Element)j.next();
            			//判断是否有重复 无重复就添加路径
            			if(jsFilesList.indexOf(jsFileElement.attributeValue("src").toString())==-1){
            				jsFilesList.add(jsFileElement.attributeValue("src").toString());
            			}
            			
            		}
            		
            		//添加css文件
            		for(Iterator k = component.elementIterator("cssFile"); k.hasNext();) {
            			Element cssFileElement=(Element)k.next();
            			//判断是否有重复 无重复就添加路径
            			if(cssFilesList.indexOf(cssFileElement.attributeValue("src").toString())==-1){
            				cssFilesList.add(cssFileElement.attributeValue("src").toString());
            			}
            			
            		}
            		
            		
            		break;
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public String getJsFilesStr() {
		return jsFilesStr;
	}

	public void setJsFilesStr(String jsFilesStr) {
		this.jsFilesStr = jsFilesStr;
	}

	public String getCssFilesStr() {
		return cssFilesStr;
	}

	public void setCssFilesStr(String cssFilesStr) {
		this.cssFilesStr = cssFilesStr;
	}
    
    
}