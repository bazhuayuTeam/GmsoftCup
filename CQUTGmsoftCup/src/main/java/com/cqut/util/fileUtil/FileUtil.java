package com.cqut.util.fileUtil;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.directwebremoting.io.FileTransfer;

import com.cqut.util.BeanUtil;
import com.cqut.util.SystemUtil;


/**
 * 文件服务
 * @author 谌毅
 *
 */
public class FileUtil {

	/**
	 * 文件保存根地址
	 */
	public static String basePath = null;
	
	static{
		InputStream inStream = FileUtil.class.getClassLoader().getResourceAsStream("filePath.properties"); 
		try{
			Properties properties=new Properties();
			properties.load(inStream);
			basePath=properties.getProperty("diskPath");//存到磁盘的地址
			inStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		/*basePath = SystemUtil.getBasePath() + "";
		basePath = basePath.replaceAll("WEB-INF/classes", "uploadFiles");
		System.out.println("basePath:" + basePath) ;*/
		//basePath = "D:/";
	}
	
	
	/**
	 * 保存文件
	 * @param inputStream 文件流
	 * @return 返回文件绝对路径
	 */
	public static String saveFile(InputStream inputStream){
		String fileId = BeanUtil.createId();
		String path = basePath + fileId.substring(0, 4) + "/" + fileId.substring(4, 6) + "/"+ fileId.substring(6, 8);
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		file = new File(path + "/" + fileId);
		
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(file);
			int bytesRead;
			byte[] contents = new byte[5*1024];
			while ((bytesRead = inputStream.read(contents)) != -1) { 
				fos.write(contents,0,bytesRead);
	        } 
			fos.flush();
			fos.close();
			inputStream.close();
			return path + "/" + fileId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String saveImg(InputStream inputStream){//limit 代表长和宽最大为多少
		String fileId = BeanUtil.createId();
		String path = basePath + fileId.substring(0, 4) + "/" + fileId.substring(4, 6) + "/"+ fileId.substring(6, 8);
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		file = new File(path + "/" + fileId);
		
		
		try{//图片处理 
			BufferedImage bi = ImageIO.read(inputStream);//读取图片流
			double ratio = 1.0;//压缩比例
			int w = bi.getWidth();//上传图片的宽度
			int h = bi.getHeight();//上传图片的高度

			//ratio = (w > h ? w : h)/*长或宽，取最长那个。*/ > limit/*如果最长的大于限制长度*/ ? (limit/(w > h ? w : h))/*true 算出压缩比例*/ : ratio/*false 不压缩*/;//
			int newW = (int)Math.floor(w * ratio),newH = (int)Math.floor(h * ratio);//算出压缩后的长宽
			BufferedImage tag = new BufferedImage(newW,newH,BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();//画图
			g.drawImage(bi.getScaledInstance(newW, newH, BufferedImage.SCALE_SMOOTH), 0, 0,null);//getScaledInstance 将图片放大或者缩小  BufferedImage.SCALE_SMOOTH 平滑一点，图片质量好点，但是效率要慢一点。
			g.dispose();//释放资源。
			ImageIO.write(tag, "JPEG",file);//画好了之后 写到文件file里面数据格式为 JPEG

//			fos = new FileOutputStream(file);
//			int bytesRead;
//			byte[] contents = new byte[5*1024];
//			while ((bytesRead = inputStream.read(contents)) != -1) { 
//				fos.write(contents,0,bytesRead);
//	        } 
//			fos.flush();
//			fos.close();
			inputStream.close();
			return path + "/" + fileId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取文件类型（扩展名）
	 * @param fileName
	 * @return
	 */
	public static String getFileType(String fileName){
		int index = fileName.lastIndexOf(".");
		if(index == -1){
			return null;
		}
		return fileName.substring(index);
	}
	
	/**
	 * 删除文件
	 * @param path 文件地址
	 * @return
	 */
	public static boolean deleteFile(String path){
		File file = new File(path);
		if(file.exists()){
			return file.delete();
		}
		return true;
	}
	
	public static FileInputStream getFileInputStream(String path){
		File file = new File(path);
		if(!file.exists()){
			return null;
		}
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @author Hzw
	 * 返回文件流，用于向dwr类的service类提供流。
	 * dwr方法只需将该方法返回的流继续返回给客户浏览端
	 * 出于安全方面原因考虑，请不要将该方法开放给客户端。
	 *  @param fileName 下载者下载到本地后的文件名
	 *  @param file 服务器要下载的文件
	 *  @return FileTransfer 文件传输流
	 * */
	public static FileTransfer getFileTransfer(String fileName,File file,String mimeType)
	{
		FileInputStream fis = null;
		if(!file.exists())
		{
			fis = null;
		}
		try 
		{
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e)
		{
			fis = null;
		}
		try {
			fileName = new String(fileName.getBytes("GBK"),"iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(fis == null)
		{
			return null;
		}
//		"application/vnd.ms-excel"
		return new FileTransfer(fileName,mimeType,fis);
	}

	public static void main(String[] args){
		System.out.println(FileUtil.basePath);
	}
	
}
