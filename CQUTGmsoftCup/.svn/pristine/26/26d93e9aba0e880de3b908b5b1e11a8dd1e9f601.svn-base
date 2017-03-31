package com.cqut.dao.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.type.NullableType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;

import com.cqut.dao.HibernateDao;
import com.cqut.entity.AbstractEntity;

/**
 * 公共DAO接口
 * @author cy
 *
 */
@Service
public interface ICommonDao extends HibernateDao{
	public abstract <T> void delete(Class<T> entity, Serializable entityId);
	public abstract <T> void delete(Class<T> entity, Serializable[] entityIds);
	public abstract <T> T findById(Class<T> entity, Serializable entityId);
	/**
	 * 执行SQL
	 * @param sql
	 */
	public int execute(String sql);
	/**
	 * 给流程执行sql使用
	 * @param sql
	 * @param argm
	 * @param flag 未使用
	 * @return
	 */
	public int executeForFlow(final String sql, String argm, String flag);
	/**
	 * 执行SQL并返回
	 * @param sql
	 * @return
	 */
	public List<Object> executeAndReturn(String sql);
	/**
	 * 执行HQL
	 * @param sql
	 */
	public int executeHql(String hql);
	/**
	 * 执行HQL并返回
	 * @param hql
	 * @return
	 */
	
	/**
	 * 执行HQL并返回
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<Object> executeAndReturnParam(final String sql, final String[] params);
	
	public List<Object> executeHqlAndReturn(String hql);
	/**
	 * 在hibernate中使用sql的函数来查询需要特殊处理，
	 * 如SELECT count(*)，这个count函数就需要一个别名来处理，如count(*) AS e，然后给e一个hibernate的type
	 * @param sql
	 * @param scalar
	 * @return
	 */
	public List<Object> findSQL(String sql, Map<String , NullableType> scalar);
	
	/**
	 * 描述：进行批量添加  暂时未发现问题
	 * @return boolean
	 * @author ZhaoXin
	 * */
	public boolean saveEntities(AbstractEntity[] entities);
	
	public List<Object> executeAndReturnParam(final String sql, final List<String> param);
	
	public List<Object> executeAndReturnByParam(final String sql, final List<String> param, final int index, final int limit) ;
	
	public boolean saveEntityWithClob(AbstractEntity entity);
	
	public boolean updateEntityWithClob(AbstractEntity entity);
	
	/**
	 * 
	 * 需要交换的两个顺序
	 * majorKeyFieldName 主键的字段名字
	 * showOrderFieldName 展示顺序的字段名字
	 * @return boolean
	 * @author ZhaoXin
	 * */
	public boolean swapShowOrder(String majorKey1,int showOrder1,
			String majorKey2,int showOrder2,String tableName, String majorKeyFieldName, String showOrderFieldName);
	/**
	 *  majorKeyFieldName 主键的字段名字
	 * 默认展示顺序的字段名为showOrder
	 * @return boolean
	 * @author ZhaoXin
	 * */
	public boolean swapShowOrder(String majorKey1,int showOrder1,
			String majorKey2,int showOrder2,String tableName, String majorKeyFieldName);
}
