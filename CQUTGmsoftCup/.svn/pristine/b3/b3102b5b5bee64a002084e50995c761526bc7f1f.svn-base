package com.cqut.loadXML;

import java.util.Properties;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ParseXML {

	// 定义一个Properties 用来存放 dbhost dbuser dbpassword的值
	private Properties props;

	// 这里的props
	public Properties getProps() {
		return this.props;
	}

	public void parse(String filename) throws Exception {

		// 将我们的解析器对象化
		ConfigParser handler = new ConfigParser();

		// 获取SAX工厂对象
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(false);
		factory.setValidating(false);

		// 获取SAX解析
		SAXParser parser = factory.newSAXParser();


		try {
			// 将解析器和解析对象myenv.xml联系起来,开始解析
			//parser.parse(confURL.toString(), handler);
			parser.parse(filename,handler);
			// 获取解析成功后的属性 以后 我们其他应用程序只要调用本程序的props就可以提取出属性名称和值了
			props = handler.getProps();
		} finally {
			factory = null;
			parser = null;
			handler = null;
		}

	}

}