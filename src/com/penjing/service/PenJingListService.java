package com.penjing.service;

import java.util.List;

import com.penjing.entity.PenJingList;

public interface PenJingListService {
	/*
	 * 添加盆景列表
	 */
	public void save(PenJingList penjingList);
	/*
	 * 根据订单编号删除该订单的盆景
	 */
	public void delete(long orderId);
	/*
	 * 根据订单编号级批量删除该订单的盆景
	 */
	public int delete(String penjingListIds);
	
	/*
	 * 根据订单查找订单详情
	 */
	public List<PenJingList> pageList(long orderId);
	/*
	 * 订单列表盆景数量
	 */
	public long count(long order);
	/*
	 * 更新订单详情
	 */
	public void update(long penjingListId,int count,String listNote);
	
	/*
	 * 根据id获取盆景详情
	 */
	public PenJingList get(long penjingListId);
	
	/*
	 * 获取订单详情
	 * 根据订单号和盆景号
	 */
	public PenJingList get(long orderId,int penjingId);
}
