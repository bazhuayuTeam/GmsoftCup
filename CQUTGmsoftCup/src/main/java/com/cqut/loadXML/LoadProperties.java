package com.cqut.loadXML;

import java.util.*;
import java.io.*;

public class LoadProperties {
	private Properties pro = new Properties();
	
	public Properties getProperties(String fileName){
		InputStream input = this.getClass().getClassLoader().getResourceAsStream(fileName);
		
		try{
			pro.load(input);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return pro;
	}
}
