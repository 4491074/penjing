package com.penjing.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.penjing.dao.BaseDao;
import com.penjing.util.JdbcConn;

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T>{

private SessionFactory sessionFactory;
private JdbcConn jdbc;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	public void setJdbc(JdbcConn jdbc) {
		this.jdbc = jdbc;
	}



	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Serializable save(Object o) {
		// TODO Auto-generated method stub
		return getSession().save(o);
	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
		getSession().delete(o);
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		getSession().update(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql) {
		// TODO Auto-generated method stub
		return getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Object[] param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Class<T> c, Serializable id) {
		return (T) getSession().get(c, id);
	}

	@Override
	public T get(String hql, Object[] param) {
		// TODO Auto-generated method stub
		List<T> li = find(hql,param);
		if(li != null && li.size()>0){
			return li.get(0);
		}
		return null;
	}

	@Override
	public void update(String hql, Object[] param) {
		// TODO Auto-generated method stub
		Query q = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find1(String hql) {
		return getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> find2(String hql) {
		return getSession().createQuery(hql).list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Object[] param, Integer first, Integer max) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult(first).setMaxResults(max).list();
	}
	@Override
	public Long count(String hql, Object[] param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return (Long)q.uniqueResult();
	}

	@Override
	public int delete(String sql) {
		// TODO Auto-generated method stub
		int r = 0;
		try {
			Connection conn = jdbc.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			r = ps.executeUpdate();
			jdbc.CloseConnect(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public void update(String sql) {
		// TODO Auto-generated method stub
		try {
			Connection conn = jdbc.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			@SuppressWarnings("unused")
			int n=ps.executeUpdate();
			jdbc.CloseConnect(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
