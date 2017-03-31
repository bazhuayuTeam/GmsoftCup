package com.cqut.util.dialect;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5InnoDBDialect;

//此处继承的是Hibernate MySql方言类，视不同开发情况而定
public class MySQLDialect extends MySQL5InnoDBDialect {

	public MySQLDialect() {  
        super(); 
        //调用父类的registerHibernateType方法，注册Text类型
        registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());  
    } 
}
