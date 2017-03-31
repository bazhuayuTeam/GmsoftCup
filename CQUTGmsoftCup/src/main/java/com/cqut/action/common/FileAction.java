package com.cqut.action.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import com.cqut.service.systemFile.SystemFileService;
import com.cqut.util.SystemUtil;
import com.cqut.util.fileUtil.FileUtil;
import com.opensymphony.xwork2.ActionSupport;

public class FileAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileId;
	@Resource(name = "systemFileService")
	private SystemFileService systemFileService;
	private String fileName;
	private String fileType;
	private InputStream imageStream;
	private final static String FILE_TYPE = "image/jpeg";
	private final static int BUFFERED_SIZE = 4096;

	public String execute() {
		Map<String, Object> fileMap = null;
		if (null == fileId || fileId == "") {
			return ERROR;
		} else {
			fileMap = systemFileService.getSystemFile(new String[] {
					"savePath", "fileName", "fileSize" },
					"id='" + fileId + "'", false);
		}

		if (null == fileMap || null == fileMap.get("savePath")) {
			return ERROR;
		} else {
			setFileName(fileMap.get("fileName") + "");
			fileType = fileMap.get("fileType") + "";
			String savePath=fileMap.get("savePath").toString();
			imageStream = FileUtil.getFileInputStream(getConfigureFilePath()+"/CQWZZ"+savePath.split(":")[1]);
		}

		if (imageStream == null) {
			return ERROR;
		}
		// try {
		// fileName = new String(fileName.getBytes("ISO8859-1"),"UTF-8");
		// fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		System.out.println("fileName:  " + fileName);
		return SUCCESS;
	}

	//获取filePath.properties设置的开始路径
	private static String getConfigureFilePath(){
		try {
			String path=SystemUtil.getBasePath();
            InputStream in = new BufferedInputStream (new FileInputStream(path+"config/filePath.properties"));
            Properties props = new Properties();
            props.load(in);
            String value = props.getProperty ("diskPath");   
            in.close();
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
	}
	public void setFileId(String imgId) {
		this.fileId = imgId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setSystemFileService(SystemFileService systemFileService) {
		this.systemFileService = systemFileService;
	}

	public SystemFileService getSystemFileService() {
		return systemFileService;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}

	public InputStream getImageStream() {
		return imageStream;
	}

	public static String getFileType() {
		return FILE_TYPE;
	}

	public static int getBufferedSize() {
		return BUFFERED_SIZE;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return this.fileName;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
