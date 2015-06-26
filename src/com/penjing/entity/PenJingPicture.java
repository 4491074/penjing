package com.penjing.entity;


/*
 * 盆景与对应的盆景图片一对一描述
 */
public class PenJingPicture{

	private long penJingPictureId;
	private PenJing penJing; //所属盆景
	private String pictureUrl; //图片地址
	private byte pictureStatus; //图片状态（0-未审核，1-通过，2-未通过）
	private String remark; //备注
	
	
	public PenJingPicture(){}
	public PenJingPicture(long penJingPictureId,String pictureUrl,byte pictureStatus){
		this.penJingPictureId = penJingPictureId;
		this.pictureUrl = pictureUrl;
		this.pictureStatus = pictureStatus;
	}
	
	public PenJingPicture(String pictureUrl){
		this.pictureUrl = pictureUrl;
	}
	
	public PenJingPicture(long penJingPictureId,String pictureUrl){
		this.penJingPictureId = penJingPictureId;
		this.pictureUrl = pictureUrl;
	}
	
	public PenJing getPenJing() {
		return penJing;
	}
	public void setPenJing(PenJing penJing) {
		this.penJing = penJing;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public byte getPictureStatus() {
		return pictureStatus;
	}
	public void setPictureStatus(byte pictureStatus) {
		this.pictureStatus = pictureStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getPenJingPictureId() {
		return penJingPictureId;
	}
	public void setPenJingPictureId(long penJingPictureId) {
		this.penJingPictureId = penJingPictureId;
	}
	
	
}
