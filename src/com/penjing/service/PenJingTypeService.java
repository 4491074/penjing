package com.penjing.service;

import java.util.List;

import com.penjing.entity.PenJingType;

public interface PenJingTypeService {

	public List<PenJingType> getAllPenJingType(int first,int max,int status);
	
	public List<PenJingType> findAllListForBox(int status);
	
	public List<PenJingType> findAllList(byte status);
	
	public Long countPenJing(int typeId);
	
	public void addPenJingType(PenJingType p);
	
	public int delPenJingType(String typeId);
	
	public void updatePenJingType(PenJingType p);
	
	public Long count(int typeState);

}
