package com.cqut.dao.common;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.NullableType;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.AbstractEntity;
import com.cqut.entity.ITreeStructureEntity;
import com.cqut.util.BeanUtil;
import com.cqut.util.common.StringUtil;

@Component
public class CommonDao extends BaseDao implements
		ICommonDao {

	public int execute(final String sql) {
		Object result = this.execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				int result = query.executeUpdate();
				return result;
			}
		});
		return (Integer)result;
	}
	
	public int executeForFlow(final String sql, final String argm, String flag) {
		Object result = this.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				if(argm != null){
					query.setString(0, argm);
				}
				int result = query.executeUpdate();
				return result;
			}
		});
		return (Integer)result;
	}

	@SuppressWarnings("unchecked")
	public List<Object> executeAndReturn(final String sql) {
		List<Object> list = this.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				//
				return query.list();
			}
		});
		return list;
	}

	public int executeHql(final String hql) {
		Object result = this.execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				int result = query.executeUpdate();
				return result;
			}
		});
		return (Integer)result;
	}

	@SuppressWarnings("unchecked")
	public List<Object> executeHqlAndReturn(final String sql) {
		List<Object> list = this.executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(sql);
				//
				return query.list();
			}
		});
		return list;
	}
	
	/**
	 * @description 使用通配符？来传参，执行查询
	 * @author ZhaoXin
	 * */
	@SuppressWarnings("unchecked")
	public List<Object> executeAndReturnParam(final String sql, final String[] params) {
		List<Object> list = this.executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
				for(int i=0; i<params.length; i++){
					query.setString(i, params[i]);
				}
				return query.list();
			}
		});
		return list;
	}
	
	public <T> void delete(Class<T> entity, Serializable entityId) {
		Object obj = super.load(entity, entityId);
		try{
			super.delete(obj);
			if(obj instanceof ITreeStructureEntity){
				changeChildrenProperty(entity, false);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public <T> void delete(Class<T> entity, Serializable[] entityIds) {
		for (Serializable entityId : entityIds) {
			this.delete(entity, entityId);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T findById(Class<T> entity, Serializable entityId) {
		return (T) super.get(entity, entityId);
	}
	
	@Override
	public Object merge(Object entity) throws DataAccessException {
		AbstractEntity ae = (AbstractEntity)entity;
		if(StringUtil.isEmpty(ae.getEntityKey())){
			//如果没有ID为他们统一添加
			ae.setEntityKey(BeanUtil.createId());
		}
		Object result = super.merge(ae);
		
		if(result instanceof ITreeStructureEntity && result != null){
			//处理树形结构的实体
			changeChildrenProperty(result, true);
		}
		return result;
	}
	
	/**
	 * 修改是否有子结点的字段
	 * @param entity
	 * @param type
	 */
	private void changeChildrenProperty(Object entity, boolean type){
		if(!(entity instanceof ITreeStructureEntity)){
			return;
		}
		if(type){
			doChildrenChange(entity, type);
		}else{
			ITreeStructureEntity tse = (ITreeStructureEntity)entity;
			String condition = tse.getParentPropertyName()+"="+tse.getParentPropertyValue();
			String hcpn = tse.getHasChildrenParpertyName();
			int count = super.getCount(new String[]{hcpn}, condition, false);
			if(count == 0){//没有子结点了
				doChildrenChange(entity, type);
			}
		}
	}
	
	private void doChildrenChange(Object entity, boolean type){
		ITreeStructureEntity tse = (ITreeStructureEntity)entity;
		String condition = tse.getRelationPropertiesName()+"="+tse.getParentPropertyValue();
		String hcpn = tse.getHasChildrenParpertyName();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(hcpn, type);
		updateProperties(entity.getClass(), data, condition);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> findSQL(final String sql, final Map<String , NullableType> scalar) {
		List<Object> list = this.executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				Iterator<String> iterator = scalar.keySet().iterator();
				while(iterator.hasNext()){
					String temp = iterator.next();
					query = query.addScalar(temp, scalar.get(temp));
				}
				return query.list();
			}
		});
		return list;
	}

	@Override
	public Class<?> getEntity() {
		return this.getClass();
	}
	
	/**
	 * 描述：进行批量添加  暂时未发现问题
	 * @return boolean
	 * @author ZhaoXin
	 * */
	public boolean saveEntities(AbstractEntity[] entities) {
		int insertCount = 10;	//	每次只添加10个
		Session session = null;
		boolean rs = true;
		
		if (entities.length > 0) {
			try {
				session = getSession();
				session.beginTransaction(); // 开启事务

				int count = 0;
				// 循环添加
				for (AbstractEntity ae : entities) {
					if (ae == null)
						continue;
					if(StringUtil.isEmpty(ae.getEntityKey())){
						//如果没有ID为他们统一添加
						ae.setEntityKey(BeanUtil.createId());
					}
					session.save(ae);
					count++;
					// 批插入的对象立即写入数据库并释放内存
					if (count % insertCount == 0) {
						session.flush();
						session.clear();
					}
				}
				session.getTransaction().commit(); // 提交事物
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback(); // 出错将回滚事物  
				rs = false; 
			}finally{
				session.close();	// 关闭session
			}
		}
		
		return rs;
	}
	
	//带分页的执行方法
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> executeAndReturnByParam(final String sql, final List<String> param, final int index, final int limit) {
		List<Object> list = this.executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				query.setFirstResult((index-1)*limit);
				query.setMaxResults(limit);
				for(int i=0; i<param.size(); i++){
					query.setString(i, param.get(i));
				}
				return query.list();
			}
		});
		
		return list;
	}
	
	//不带分页的执行方法
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> executeAndReturnParam(final String sql, final List<String> param) {
		List<Object> list = this.executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
				for(int i=0; i<param.size(); i++){
					query.setString(i, param.get(i));
				}
				return query.list();
			}
		});
		return list;
	}
	
	
	// 存取带clob类型的对象
	public boolean saveEntityWithClob(AbstractEntity entity){
		try{
			Session session = this.getSession();
			session.beginTransaction();
	        session.save(entity);
	        session.getTransaction().commit();
	        session.close();
		}catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	// 更新带clob类型的对象
	public boolean updateEntityWithClob(AbstractEntity entity){
		try{
			Session session = this.getSession();
			session.beginTransaction();
	        session.update(entity);
	        session.getTransaction().commit();
	        session.close();
		}catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	/***********通用交换展示顺序***by赵鑫***开始***************************************/
	/*通用交换展示顺序**/
	public boolean swapShowOrder(String majorKey1,int showOrder1,
			String majorKey2,int showOrder2,String tableName, String majorKeyFieldName, String showOrderFieldName) {
		String sql1 = "UPDATE " + tableName + " SET " + showOrderFieldName +" =" + showOrder1 + " WHERE " + majorKeyFieldName + " = '" + majorKey2 + "'";
		String sql2 = "UPDATE " + tableName + " SET " + showOrderFieldName +" =" + showOrder2 + " WHERE " + majorKeyFieldName + " = '" + majorKey1 + "'";

		boolean rs1 = this.execute(sql1) > 0 ? true : false;
		boolean rs2 = this.execute(sql2) > 0 ? true : false;
		return rs1 && rs2;
	}
	
	/**
	 * 默认展示顺序的字段名为showOrder
	 * */
	public boolean swapShowOrder(String majorKey1,int showOrder1,
			String majorKey2,int showOrder2,String tableName, String majorKeyFieldName) {
		return swapShowOrder(majorKey1, showOrder1, majorKey2, showOrder2, tableName, majorKeyFieldName, "showOrder");
	}
	
	/***********通用交换展示顺序***by赵鑫**结束****************************************/
}
















