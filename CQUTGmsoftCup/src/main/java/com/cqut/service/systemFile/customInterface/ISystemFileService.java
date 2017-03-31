package com.cqut.service.systemFile.customInterface;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.io.FileTransfer;

import com.cqut.entity.systemFile.SystemFile;

public interface ISystemFileService {

	/**
	 * 保存文件
	 * @param inputStream 文件流
	 * @param fileName 文件名称
	 * @param creator 创建者
	 * @param fileType 文件类型
	 * @param fileSize 文件大小
	 * @return
	 */
	public SystemFile createFile(InputStream inputStream, String fileName, String creator, String fileType, Long fileSize);
	/**
	 * 保存文件
	 * @param fullPath 文件地址
	 * @param fileName
	 * @param creator
	 * @param fileType
	 * @return
	 */
	public SystemFile createFile(String fullPath, String fileName, String creator, String fileType);
	
	/**
	 * 下载文件
	 * @param fileId
	 * @return
	 */
	public FileTransfer downloadFile(String fileId);

	/**
	 * 上传单个文件
	 * @param fileTransfer
	 * @param createrId 创建者
	 * @return
	 */
	public SystemFile uploadFile(FileTransfer fileTransfer, String createrId);
	public SystemFile uploadFile(FileTransfer fileTransfer, String fileName, String createrId);
	/**
	 * 上传多个文件
	 * @param fileTransfers
	 * @param createrId
	 * @return
	 */
	public List<SystemFile> uploadFiles(FileTransfer[] fileTransfers, String createrId);
	public List<SystemFile> uploadFiles(FileTransfer[] fileTransfers, String[] fileNames,HttpServletRequest request);
	/**
	 * 删除文件
	 * @param id
	 * @return
	 */
	public String deleteFile(String id);
	/**
	 * 获取文件对象
	 * @param id
	 * @return
	 */
	public SystemFile findSystemFile(String id);
	/**
	 * 获取文件
	 * @param id
	 * @return
	 */
	public File findDiskFile(String id);
	
	/**
	 * 查询指定属性的值
	 * 
	 * @param properties 查询的属性
	 * @param condition 查询限制条件
	 * @param needLink 是否外键链接
	 * @param curPage 当前显示的页数
	 * @param limit 每页显示数量
	 * @return
	 */
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit);
	
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink);
	
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink);

	public List<SystemFile> findSystemFileByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit);
	
	public Map<String, Object> getSystemFile(String[] properties,
			String condition, boolean needLink);

	public boolean save(Map<String, Object> value);

	public boolean save(SystemFile value);

	public Map<String, Object> saveAndReturn(Map<String, Object> value);

	public Map<String, Object> saveAndReturn(SystemFile value);

	public boolean deleteById(String id);

	public boolean deleteByEntity(SystemFile value);

	public boolean deleteByIds(String[] ids);

	public boolean deleteByEntitys(SystemFile[] values);
	
	public int updateSystemFile(SystemFile data, String condition);
	
	boolean saveEntity(SystemFile value);
}
