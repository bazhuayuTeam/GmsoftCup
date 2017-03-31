package com.cqut.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.cqut.util.common.StringUtil;



public class FtpUtil {
	
	//ftp地址
	private static String url = null;
	//端口号
	private static int port = 0;
	//用户名
	private static String username = null;
	//密码
	private static String password = null;
	
	private static String downloadpath = null;
	
	static{
		InputStream inputStream = FtpUtil.class.getClassLoader().getResourceAsStream("config\\ftpConfig.properties");
		try {
			Properties prop = new Properties();
			
			prop.load(inputStream);
			url = prop.getProperty("FtpUrl");
			port = Integer.parseInt(prop.getProperty("FtpPort"));
			username = prop.getProperty("UserName");
			password = prop.getProperty("PassWord");
			downloadpath = prop.getProperty("DownLoadPath");
			
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/** 
 * Description: 向FTP服务器上传文件 
 * @Version1.0 
 * @authorwpf
 * @param filename 上传到FTP服务器上的文件名 
 * @throws FileNotFoundException 
 * @throws IOException 
 * @throws IOException 
 */
	public static String uploadFile(String filename, FileInputStream fis) throws FileNotFoundException{
		
	    String uploadPath = null;
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.connect(url, port);//连接FTP服务器  
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftp.login(username, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return uploadPath;
	        }
			Calendar curData = Calendar.getInstance();
			String cur_Year = ""+curData.get(Calendar.YEAR);
			String cur_Month = ""+(curData.get(Calendar.MONTH) + 1);
			String cur_Day = ""+curData.get(Calendar.DAY_OF_MONTH);
			//filename = cur_Year+Long.toString(curData.getTimeInMillis()).substring(0, 12);
	        ftp = createDir(ftp, cur_Year, cur_Month, cur_Day);
	        ftp.setFileType(FTP.BINARY_FILE_TYPE); 
	        ftp.storeFile(filename, fis);
	        
	        fis.close();  
	        ftp.logout();
	        uploadPath = "/"+cur_Year+"/"+cur_Month+"/"+cur_Day+"/"+filename;
System.out.println("ftp-----" + uploadPath);
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return uploadPath;
	}
	
	//ftp服务器创建多级文件目录
	public static FTPClient createDir(FTPClient ftp, String cur_Year, String cur_Month, String cur_Day) throws IOException{
        String uploadPath = "/"+cur_Year;
		ftp = createFtpDir(uploadPath, ftp);
		uploadPath = "/"+cur_Year+"/"+cur_Month;
		ftp = createFtpDir(uploadPath, ftp);
		uploadPath = "/"+cur_Year+"/"+cur_Month+"/"+cur_Day;
		ftp = createFtpDir(uploadPath, ftp);
		return ftp;
	}
	
	//服务器创建目录
	public static FTPClient createFtpDir(String dirPath, FTPClient ftp) throws IOException{
		if(!ftp.changeWorkingDirectory(dirPath)){
			if(ftp.makeDirectory(dirPath)){
				ftp.changeWorkingDirectory(dirPath);
				return ftp;
			}else{
				System.out.println("创建目录失败");
			}
		}
		return ftp;
	}

	public void testUpLoadFromDisk(){  
		try {
			FileInputStream fis = new FileInputStream(new File("D:\\tyyykw.wav"));
	        String flag = uploadFile("ok.wav",fis);  
//System.out.println(flag);
		} catch(IOException e) {
	        e.printStackTrace();  
	    }  
	}
	
	
	/** 
	 * Description: 从FTP服务器下载文件 
	 * @Version1.0 
	 * @author wpf 
	 * @param url FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param remotePath FTP服务器上的文件相对路径
	 * @param needDir 下载文件时是否创建目录
	 * @param localPath 下载后保存到本地的路径 
	 * @return 
	 */  
	public static String downFile(String remotePath, Boolean needDir) {  
	    String fileName = null;
	    String relativePath = null;
	    String downPath = null;
	    
	    if(!StringUtil.isEmpty(remotePath)){
	    	int lastIndex = remotePath.lastIndexOf("/")+1;
	    	fileName= remotePath.substring(lastIndex);
	    	relativePath = remotePath.substring(0, lastIndex);
	    }
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.connect(url, port);  
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftp.login(username, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return null;  
	        }
	        if(!StringUtil.isEmpty(relativePath)){
	        	ftp.changeWorkingDirectory(relativePath);//转移到FTP服务器目录
	        }
	        FTPFile[] fs = ftp.listFiles();
	        for(FTPFile ff:fs){  
	            if(ff.getName().equals(fileName)){ 
	            	File localFile = null;
	            	if(true == needDir) {
	            		System.out.println("-----need dir-----");
	            		downPath = judgeDirectory(downloadpath);
	            		downPath = downPath+"/"+ff.getName();
	            		localFile = new File(downPath+"/"+ff.getName()); 
	            	}
	            	else {
	            		System.out.println("-----no need dir-----");
	            		downPath = downloadpath + "/" + ff.getName();
	            		localFile = new File(downloadpath + "/" +ff.getName()); 
	            	}
	                OutputStream is = new FileOutputStream(localFile);   
	                ftp.retrieveFile(ff.getName(), is);  
	                is.close();
	            }  
	        }  
	          
	        ftp.logout();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    System.out.println("downPath-----------------------" + downPath);
	    return downPath;  
	}
	
	
	
	//判断文件夹是否存在
	public static String judgeDirectory(String path){
    	Calendar curData = Calendar.getInstance();
		String cur_Year = ""+curData.get(Calendar.YEAR);
		String cur_Month = ""+(curData.get(Calendar.MONTH) + 1);
		String cur_Day = ""+curData.get(Calendar.DAY_OF_MONTH);
		
		path = path+cur_Year+"/"+cur_Month+"/"+cur_Day;
		File file = new File(path);
		if(!file.exists() && !file.isDirectory()){
			file.mkdirs();
		}
		
		return path;
	}
	
	public boolean testDownFile() {
//		return downFile("/12/24/20131224160924098", "E:/downLoad/12");
		testUpLoadFromDisk();
		return true;
	}
	
	public static void main(String[] args) {
		//FTPClient fc = new FTPClient();
		//fc.connect(url, port);
//		fc.login(username, password);//登录  
		//System.out.println(new FtpUtil().testDownFile());
//		FtpUtil.downFile("/2014/5/20/20140520231908471.wav", false);
		try {
			System.out.println("encodedFilePath------");
			String uploadPath = FtpUtil.uploadFile("20140728164659964.wav", new FileInputStream("E:\\DownLoad\\2014\\07\\28\\20140728164659964.wav"));
			System.out.println("encodedFilePath------");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}
