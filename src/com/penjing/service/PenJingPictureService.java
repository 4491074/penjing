package com.penjing.service;

import java.util.List;

import com.penjing.entity.PenJingPicture;

public interface PenJingPictureService {
	
	/*
	 * 获取盆景分页
	 */
	public List<PenJingPicture> getAllPenJingPicture(int status,int penJingId);
	
	/*
	 * 获取盆景分页
	 */
	public List<PenJingPicture> getPictureForInfo(int status,int penJingId);
	
	/*
	 * 保存一个盆景图片
	 */
	public void save(PenJingPicture penjingPicture);
	
	/*
	 * 根据盆景图片id获取图片集合
	 */
	public List<PenJingPicture> get(String ids);
	
	/*
	 * 根据id批量删除盆景图片
	 */
	public int delPenJingPictures(String ids);
	
	/*
	 * 更新盆景图片的状态
	 */
	public void updatePenjingPicture(String ids,byte state);
}
