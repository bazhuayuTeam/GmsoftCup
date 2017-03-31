package com.cqut.service.derive;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.io.FileTransfer;
import org.springframework.stereotype.Controller;

import com.cqut.dao.common.ICommonDao;
import com.cqut.service.derive.deriveInterface.IDeriveWordService;
import com.cqut.util.SystemUtil;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import com.cqut.util.SystemUtil;
import com.cqut.util.fileUtil.FileUtil;


@Controller  
@RemoteProxy
public class DeriveWordService implements IDeriveWordService{
	@Resource(name = "systemUtil")
	private SystemUtil systemUtil;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	
	/**
	 * 集合类对象转化为DOC文档对象
	 * @author fuqian 
	 * @see 该方法只适合一般导出
	 * @param doc Word文档对象
	 * @param data 要导出的包含数据的对象
	 * @param properties dataList对象中存放的Map中的Key值顺序
	 * @param wordName 导出文件的文件名
	 */
	public FileTransfer deriveWord(String templateName,Map<String, Object> data,String wordName){
		String filePath = systemUtil.getSystemParameter(templateName);
		//文件路径
		String basePath = filePath.substring(0,filePath.length()-5-templateName.length());
		//文件名+后缀(.doc)
		String fileName = filePath.substring(filePath.length()-5-templateName.length());
		Configuration cfg = new Configuration();  
		 TemplateLoader templateLoader=null;
		 File file = null;
	        cfg.setDefaultEncoding("utf-8");  
	          
	        try {  
//	            Template temp =  cfg.getTemplate("E:\\template\\"+templateName+".doc");  
	        	//Template temp =  cfg.getTemplate("/test.doc");
	        	
	        	templateLoader=new FileTemplateLoader(new File(basePath));  
	        	cfg.setTemplateLoader(templateLoader);  
	        	
	        	Template temp = cfg.getTemplate(fileName);  
	        	
	        	
	            temp.setEncoding("UTF-8");  
	            
	            //File file = new File("C:/Users/Administrator/Desktop/"+wordName+".doc");
	            file = File.createTempFile("WordExport", ".doc");
	            //一定要设置输出流的编码，否则会出错  
	            Writer docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));  
	              
	            
	            //导出  
	            temp.process(data, docout);  
	            
	            docout.close();
	            return FileUtil.getFileTransfer(wordName + ".doc", file, "application/msword");
	        }   
	        catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace(); 
	            return null;
	        } catch (TemplateException e) {
				e.printStackTrace();
				return null;
			}
	}
}
