package com.penjing.entity;

import java.util.HashSet;
import java.util.Set;

/*
 * 盆景分类
 */
public class PenJingType{

	private int penJingTypeId; //分类编号
	private String penJingTypeName; //类别名称
	private String penJingTypeDescription; //类型描述
	private byte penJingTypeStatus; //类型状态
	private Set<PenJing> penJings = new HashSet<PenJing>();
	
	public PenJingType(){}
	public PenJingType(int penJingTypeId,String penJingTypeName,byte penJingTypeStatus){
		this.penJingTypeId = penJingTypeId;
		this.penJingTypeName = penJingTypeName;
		this.penJingTypeStatus = penJingTypeStatus;
	}
	
	public PenJingType(int penJingTypeId,String penJingTypeName){
		this.penJingTypeId = penJingTypeId;
		this.penJingTypeName = penJingTypeName;
	}
	
	public Set<PenJing> getPenJings() {
		return penJings;
	}
	public void setPenJings(Set<PenJing> penJings) {
		this.penJings = penJings;
	}
	public int getPenJingTypeId() {
		return penJingTypeId;
	}
	public void setPenJingTypeId(int penJingTypeId) {
		this.penJingTypeId = penJingTypeId;
	}
	public String getPenJingTypeName() {
		return penJingTypeName;
	}
	public void setPenJingTypeName(String penJingTypeName) {
		this.penJingTypeName = penJingTypeName;
	}
	public String getPenJingTypeDescription() {
		return penJingTypeDescription;
	}
	public void setPenJingTypeDescription(String penJingTypeDescription) {
		this.penJingTypeDescription = penJingTypeDescription;
	}
	public byte getPenJingTypeStatus() {
		return penJingTypeStatus;
	}
	public void setPenJingTypeStatus(byte penJingTypeStatus) {
		this.penJingTypeStatus = penJingTypeStatus;
	}
	
}
