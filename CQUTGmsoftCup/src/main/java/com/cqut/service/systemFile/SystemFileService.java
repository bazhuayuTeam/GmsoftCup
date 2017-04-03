package com.cqut.service.systemFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.io.FileTransfer;
import org.springframework.stereotype.Controller;

import util.StringUtil;

import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.systemFile.customInterface.ISystemFileEntityDao;
import com.cqut.dao.systemFile.customInterface.ISystemFileMapDao;
import com.cqut.entity.systemFile.SystemFile;
import com.cqut.service.systemFile.customInterface.ISystemFileService;
import com.cqut.util.SessionUtil;
import com.cqut.util.fileUtil.FileUtil;

@Controller  
@RemoteProxy
public class SystemFileService implements ISystemFileService {

	@Resource(name = "systemFileMapDao")
	private ISystemFileMapDao mapDao;
	@Resource(name = "systemFileEntityDao")
	private ISystemFileEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	@Resource
	private SessionUtil sessionUtil;
	
	public SystemFile createFile(String fullPath, String fileName, String creator, String fileType){
		File file = new File(fullPath);
		if(!file.exists()){
			return null;
		}
		
		try {
			FileInputStream fis = new FileInputStream(file);
			String type = FileUtil.getFileType(fullPath); 
			if(fileType != null){
				type = fileType;
			}
			if(type == null){
				type = "未知类型";
			}
			return createFile(fis, fileName, creator, type, file.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public SystemFile createFile(InputStream inputStream, String fileName, String creator, String fileType, Long fileSize){
		String filePath = FileUtil.saveFile(inputStream,fileName);
		if(filePath == null){
			return null;
		}
		
		SystemFile file = new SystemFile();
		file.setFileName(fileName);
		file.setCreator(creator);
		file.setFileSize(fileSize);
		file.setFileType(fileType);
		file.setNewTime(Calendar.getInstance().getTime());
		file.setSavePath(filePath);
		
		file = (SystemFile)commonDao.merge(file);
		
		return file != null ? file : null;
	}
	@RemoteMethod
	public SystemFile uploadFile(FileTransfer fileTransfer ,String createrId){
		String filePath = null;
		try {
			fileTransfer.getMimeType();
			filePath = FileUtil.saveFile(fileTransfer.getInputStream(),fileTransfer.getFilename());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(filePath == null){
			return null;
		}
		SystemFile file = new SystemFile();
		try {
			file.setFileName(new String(fileTransfer.getFilename().getBytes("ISO-8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		file.setCreator(createrId);
		file.setFileSize(fileTransfer.getSize());
		file.setFileType(fileTransfer.getMimeType());
		file.setNewTime(Calendar.getInstance().getTime());
		file.setSavePath(filePath);
		file = (SystemFile)commonDao.merge(file);
		return file != null ? file : null;
	}
	
	@RemoteMethod
	public SystemFile uploadImg(FileTransfer fileTransfer ,String createrId){
		String filePath = null;
		String mimeType = null;
		try {
			mimeType = fileTransfer.getMimeType();
			mimeType = mimeType == null ? "" : mimeType.toUpperCase();
			if(mimeType.indexOf("IMAGE") < 0){//不是图片文件
				return null;
			}
			
//			filePath = FileUtil.saveImg(fileTransfer.getInputStream()/*拿到上传文件的流*/,1000/*, StringUtil.subStringByLastFlag(fileTransfer.getFilename(), ".")*/);
			filePath = FileUtil.saveImg(fileTransfer.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(filePath == null){
			return null;
		}
		SystemFile file = new SystemFile();
		try {
			file.setFileName(new String(fileTransfer.getFilename().getBytes("ISO-8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		file.setCreator(createrId);
		file.setFileSize(fileTransfer.getSize());
		file.setFileType(fileTransfer.getMimeType());
		file.setNewTime(Calendar.getInstance().getTime());
		file.setSavePath(filePath);
		file = (SystemFile)commonDao.merge(file);
		return file != null ? file : null;
	}
	
	@RemoteMethod
	public List<SystemFile> uploadFiles(FileTransfer[] fileTransfers, String createrId){
		List<SystemFile> files = new ArrayList<SystemFile>();
		SystemFile temp = null;
		for(FileTransfer item : fileTransfers){
			temp = uploadFile(item, createrId);
			if(temp != null){
				files.add(temp);
			}
		}
		return files;
	}
	
	@RemoteMethod
	public List<SystemFile> uploadFilesByDwr(FileTransfer[] fileTransfers, HttpServletRequest request){
		//这里需要从request中获取ID
		Object id = sessionUtil.getOperatorId(request);
		return uploadFiles(fileTransfers, id != null ? id.toString() : null);
	}
	
	@RemoteMethod
	public FileTransfer downloadFile(String fileId){
		SystemFile systemFile = findSystemFile(fileId);
		if(systemFile == null){
			return null;
		}
		FileInputStream fis = FileUtil.getFileInputStream(systemFile.getSavePath());
		if(fis == null){
			return null;
		}
		String fileName = systemFile.getFileName();
		try {
			fileName = new String(fileName.getBytes("GBK"),"iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	FileTransfer ft = new FileTransfer(fileName, systemFile.getFileType(), systemFile.getFileSize(), fis);
		return new FileTransfer(fileName, systemFile.getFileType(), systemFile.getFileSize(), fis);
		
	}
  
	@RemoteMethod
	public String deleteFile(String id){
		try{
			String filePath = entityDao.findFilePath(id);
			commonDao.delete(SystemFile.class, id);
			FileUtil.deleteFile(filePath);
			return id;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	@RemoteMethod
	public void deleteFiles(String[] ids) {
		try{
			for (int i = 0, length = ids.length; i < length; i++) {
				String id = ids[i];
				String filePath = entityDao.findFilePath(id);
				commonDao.delete(SystemFile.class, id);
				FileUtil.deleteFile(filePath);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RemoteMethod
	public SystemFile findSystemFile(String id){
		return (SystemFile)commonDao.get(SystemFile.class, id);
	}
	
	public File findDiskFile(String id){
		SystemFile sf = (SystemFile)commonDao.get(SystemFile.class, id);
		if(sf == null){
			return null;
		}
		String filePath = entityDao.findFilePath(id);
		File file = new File(filePath);
		return file.exists() ? file : null;
		
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findSystemFiles(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findSystemFiles(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findSystemFiles(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getSystemFile(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findSystemFiles(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	@RemoteMethod
	public List<SystemFile> findSystemFileByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findSystemFiles(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(SystemFile.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(SystemFile value) {
		return deleteById(value.getID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(SystemFile.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(SystemFile[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (SystemFile item : values) {
			ids[index++] = item.getID();
		}
		return deleteByIds(ids);
	}

	public boolean save(Map<String, Object> value) {
		return save(new SystemFile(value));
	}

	@RemoteMethod
	public boolean save(SystemFile value) {
		return commonDao.merge(value) != null ? true : false;
	}
	
	@Override
	@RemoteMethod
	public boolean saveEntity(SystemFile value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new SystemFile(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(SystemFile value) {
		SystemFile systemFile = (SystemFile) commonDao.merge(value);
		if (systemFile != null) {
			return systemFile.getProperties();
		}
		return null;
	}
	
	@Override
	@RemoteMethod
	public int updateSystemFile(SystemFile data, String condition) {
		return mapDao.updateSystemFile(data.getProperties(), condition);
	}

	@Override
	@RemoteMethod
	public SystemFile uploadFile(FileTransfer fileTransfer, String fileName,
			String createrId) {
		String filePath = null;
		boolean flag = false;
		try {
			filePath = FileUtil.saveFile(fileTransfer.getInputStream(), fileTransfer.getFilename());
		} catch (IOException e) {
			e.printStackTrace();
			flag = true;
		}
		if(flag){
			try {
				filePath = FileUtil.saveFile(fileTransfer.getInputStream(),fileTransfer.getFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(filePath == null){
			return null;
		}
		SystemFile file = new SystemFile();
		file.setFileName(fileName);
		file.setID(createrId);
		//file.setCreator(createrId);
		file.setFileSize(fileTransfer.getSize());
		file.setFileType(fileTransfer.getMimeType());
		file.setSavePath(filePath);
		file = (SystemFile)commonDao.merge(file);
		return file != null ? file : null;
	}

	@Override
	@RemoteMethod
	public List<SystemFile> uploadFiles(FileTransfer[] fileTransfers,
			String[] fileNames,HttpServletRequest request) {
		/*String operatorName = (String)request.getSession().getAttribute(OperatorUtil.OPERATOR_ID);
		if(null == operatorName){
			return null;
		}*/
		List<SystemFile> files = new ArrayList<SystemFile>();
		SystemFile temp = null;
		FileTransfer item = null;
		for(int i=0;i<fileTransfers.length;i++){
			item = fileTransfers[i];
			temp = uploadFile(item, fileNames[i],"");
			if(temp != null){
				files.add(temp);
			}
		}
		return files;
	}
	
	private static String getName(String name){
		int index = name.lastIndexOf(".");
		if(index<0){
			return "";
		}
		return name.substring(index);
		
	}
	@RemoteMethod
	public List<Map<String, Object>>  findFile(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		if(condition!=null&&condition!=""){
			String[] conditions=condition.split("[,]");
			String newCondition = "(";
			for(int i=0;i<conditions.length;i++){
				if(i!=conditions.length-1){
					newCondition+=conditions[i]+",";
				}
				else {
					newCondition+=conditions[i]+")";
				}
			}
			data = this.findMapByPropertiesQuick(properties, " SystemFile.id IN"+newCondition+"",needLink);
		}
		
		return data;
	}
	
	@RemoteMethod
	public boolean deleteByCondition(String condition) {
		String sql = "DELETE FROM SYSTEMFILE WHERE " + condition;
		return commonDao.execute(sql) >= 0 ? true : false;
	}
}
