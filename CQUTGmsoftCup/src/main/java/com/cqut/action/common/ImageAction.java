package com.cqut.action.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import com.cqut.service.systemFile.SystemFileService;
import com.cqut.util.SystemUtil;
import com.cqut.util.fileUtil.FileUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ImageAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imgId;
	@Resource(name = "systemFileService")
	private SystemFileService systemFileService;
	private InputStream imageStream;
	private final static String FILE_TYPE = "image/jpeg";
	private final static int BUFFERED_SIZE = 2048;
	private final static String DEFAULT_IMG_PATH = SystemUtil.getBasePath()
			.replaceAll("WEB-INF/classes/", "/images/lin.png");

	public String execute() {
		File file = null;
		Map<String, Object> fileMap = null;
		if (null == imgId || imgId == "") {
			file = new File(DEFAULT_IMG_PATH);
		} else {
			fileMap = systemFileService.getSystemFile(new String[] {
					"savePath", "fileType" }, "id='" + imgId + "'", false);
		}

		if (null == fileMap || null == fileMap.get("savePath")) {
			file = new File(DEFAULT_IMG_PATH);
		} else {
			System.out.println(getConfigureFilePath());
			/*System.out.println(fileMap.get("savePath").toString());
			String[] data = (fileMap.get("savePath").toString()).split("uploadFile");
			imageStream = FileUtil.getFileInputStream(getConfigureFilePath()+"/CQUTProfessionalAssessmentSysNew/uploadFile"+data[1]);*/
			System.out.println(getConfigureFilePath()+((String) fileMap
					.get("savePath")).split("uploadFile")[1]);
			imageStream = FileUtil.getFileInputStream(getConfigureFilePath()+((String) fileMap
					.get("savePath")).split("uploadFile")[1]);
		}

		if (imageStream == null) {
			try {
				file = new File(DEFAULT_IMG_PATH);
				imageStream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		//super.validate();
		return SUCCESS;
	}
	
	//获取filePath.properties设置的开始路径
	private static String getConfigureFilePath(){
		try {
			String path=SystemUtil.getBasePath();
            InputStream in = new BufferedInputStream (new FileInputStream(path+"filePath.properties"));
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

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getImgId() {
		return imgId;
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
	
	public void setImageBinaryString(String binaryString) {
		
	}

	/*
	public String getImageBinaryString() throws IOException {
		return encode(imageStream);
	}
	
	private String encode(InputStream in)throws IOException{  
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();  
        byte[] data = new byte[in.available()];  
        in.read(data);  
        return encoder.encode(data);  
    }  
    */
}
