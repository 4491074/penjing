package com.penjing.service;

import java.util.List;

import com.penjing.entity.Order;


public interface OrderService {
	/*
	 * 盆景分页查找
	 */
	public List<Order> pageList(int first,int max,byte state);
	
	/*
	 * 数量查找
	 */
	public long count(byte state);
	
	/*
	 * 添加一个订单
	 */
	public long save(Order order);
	
	/*
	 * 跟新一个订单
	 */
	public void update(Order order);
	
	/*
	 * 删除订单
	 */
	public int delete(String orderIds);
}
