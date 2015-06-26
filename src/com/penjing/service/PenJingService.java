package com.penjing.service;

import java.util.List;

import com.penjing.entity.PenJing;

public interface PenJingService {
	
	/*
	 * 按照用户获取盆景分页
	 */
	public List<PenJing> getAllPenJing(int first,int max,int status,int userId);
	/*
	 * 计算盆景数量，
	 * status盆景状态，所有状态时为-1
	 * 	userId为发布盆景者，所有用户时为null
	 */
	public Long count(int status,String userId);
	
	/*
	 * 获取盆景分页
	 */
	public List<PenJing> getAllPenJing(int first,int max,int status,String penjingTypeId);
	
	/*
	 * 首页获取盆景分页
	 */
	public List<PenJing> getHomePenJing(int first,int max,int status,String penjingTypeId);
	
	/*
	 * 添加一个盆景
	 */
	public int savePenJing(PenJing penjing);
	
	/*
	 * 更新盆景
	 */
	public void updatePenjing(PenJing penjing);
	
	/*
	 * 更新盆景
	 */
	public void updatePenjing(int penjingId,String mainPicture,byte penjingState);
	
	/*
	 * 删除盆景
	 */
	public void deletePenjing(int penjingId);
	
	/*
	 * 获取盆景
	 */
	public PenJing getPenjing(int penjingId);
	
	/*
	 * 获取盆景添加订单
	 */
	public PenJing getPenjingForOrder(int penjingId);
	/*
	 * 获取具体盆景数据
	 */
	public PenJing getPenjingInfo(int penjingId,byte penjingStatus);
}
