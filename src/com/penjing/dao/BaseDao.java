package com.penjing.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T>{
	public Serializable save(T o); //添加
	public void delete(T o);  //删除
	public int delete(String sql);  //批量删除，返回删除数目
	public void update(T o);// 修改
	public void update(String sql);// 批量修改
	public T get(Class<T> c, Serializable id); //根据id查找
	public T get(String hql,Object[] param); //通过hql语句查找单个
	public List<T> find(String hql); //通过hql查找多个
	public List<T> find(String hql,Object[] param); 
	public void update(String hql,Object[] param); //更新部分属性
	public List<T> find1(String hql);
	public List<Object> find2(String hql);
	public List<T> find(String hql, Object[] param, Integer first, Integer max);
	public Long count(String hql, Object[] param);
}
