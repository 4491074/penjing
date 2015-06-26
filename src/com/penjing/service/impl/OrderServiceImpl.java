package com.penjing.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.penjing.dao.BaseDao;
import com.penjing.entity.Order;
import com.penjing.service.OrderService;
@Service("orderService")
public class OrderServiceImpl implements OrderService{
	@Resource
	private BaseDao<Order> baseDao;

	@Override
	public List<com.penjing.entity.Order> pageList(int first, int max,byte state) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from Order");
		Object[] params = null;
		if(state > -1){
			hql.append(" where orderState=?");
			params = new Object[]{state};
		}
		hql.append(" order by orderId asc");
		return baseDao.find(hql.toString(), params);
	}

	@Override
	public long count(byte state) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select count(*) from Order");
		Object[] param = null;
		if(state>-1){
			hql.append("where orderState=?");
			param = new Object[]{state};
		}
		return baseDao.count(hql.toString(), param);
	}

	@Override
	public long save(Order order) {
		// TODO Auto-generated method stub
		return (Long)baseDao.save(order);
	}

	@Override
	public void update(Order order) {
		// TODO Auto-generated method stub
		String hql = "update Order set customerName=?,customerPhone=?,customerAdd=?,distributionTime=?,orderState=?,note=? where orderId=?";
		Object[] params = new Object[]{order.getCustomerName(),order.getCustomerPhone(),order.getCustomerAdd(),order.getDistributionTime(),order.getOrderState(),order.getNote(),order.getOrderId()};
		baseDao.update(hql, params);
	}

	@Override
	public int delete(String orderIds) {
		// TODO Auto-generated method stub
		String sql = "delete from t_order where orderId in ("+orderIds+")";
		return baseDao.delete(sql);
	}
	
}
