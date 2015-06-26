package com.penjing.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
 * 盆景
 */
public class PenJing{
	private int penJingId; //盆景编号
	private String penJingName; //盆景名称
	private String penJingTitle; //盆景标题
	private User publisher;  //盆景发布者
	private User assessor;  //盆景审批者
	private PenJingType type; //盆景类型
	private String penJingDescription;  //盆景描述
	private Date publishTime;  //盆景发布时间
	private String mainPicture; //盆景主要展示的一张图片
	private byte penJingStatus;  //盆景状态 0-为审核  1-审核通过  2-审核未通过
	private String remark;  //备注
	private Set<PenJingPicture> penJingPictures = new HashSet<PenJingPicture>();
	private Set<PenJingList> penJingList = new HashSet<PenJingList>();
	
	private int penJingTypeId;
	private String penJingTypeName;
	private String publisherName;
	private String assessorName;
	
	public PenJing(){}
	/*
	 * 我的盆景构造函数
	 */
	public PenJing(int penJingId,String penJingName,String penJingTitle,String penJingDescription,Date publishTime,byte penJingStatus,String remark,int penJingTypeId,String penJingTypeName){
		this.penJingId = penJingId;
		this.penJingName = penJingName;
		this.penJingTitle = penJingTitle;
		this.penJingDescription = penJingDescription;
		this.publishTime = publishTime;
		this.penJingStatus = penJingStatus;
		this.remark = remark;
		this.penJingTypeId = penJingTypeId;
		this.penJingTypeName = penJingTypeName;
	}
	
	/*
	 * 具体盆景信息
	 */
	public PenJing(int penJingId,String penJingName,String penJingTitle,String penJingDescription,Date publishTime){
		this.penJingId = penJingId;
		this.penJingName = penJingName;
		this.penJingTitle = penJingTitle;
		this.penJingDescription = penJingDescription;
		this.publishTime = publishTime;
	}
	
	/*
	 * 首页盆景构造函数
	 */
	public PenJing(int penJingId,String penJingName,String mainPicture){
		this.penJingId = penJingId;
		this.penJingName = penJingName;
		this.mainPicture = mainPicture;
	}
	/*
	 * 盆景管理构造函数
	 */
	public PenJing(int penJingId,String penJingName,String penJingTitle,String penJingDescription,Date publishTime,String mainPicture,byte penJingStatus,String remark,String penJingTypeName,String publisherName){
		this.penJingId = penJingId;
		this.penJingName = penJingName;
		this.penJingTitle = penJingTitle;
		this.penJingDescription = penJingDescription;
		this.publishTime = publishTime;
		this.mainPicture = mainPicture;
		this.penJingStatus = penJingStatus;
		this.remark = remark;
		this.publisherName = publisherName;
		this.penJingTypeName = penJingTypeName;
	}
	/*
	 * 订单查找
	 */
	public PenJing(int penJingId,String penJingName,String penJingTitle,String mainPicture,byte penJingStatus){
		this.penJingId = penJingId;
		this.penJingName = penJingName;
		this.penJingTitle = penJingTitle;
		this.mainPicture = mainPicture;
		this.penJingStatus = penJingStatus;
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
	public Set<PenJingPicture> getPenJingPictures() {
		return penJingPictures;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public Set<PenJingList> getPenJingList() {
		return penJingList;
	}
	public void setPenJingList(Set<PenJingList> penJingList) {
		this.penJingList = penJingList;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getAssessorName() {
		return assessorName;
	}
	public void setAssessorName(String assessorName) {
		this.assessorName = assessorName;
	}
	public void setPenJingPictures(Set<PenJingPicture> penJingPictures) {
		this.penJingPictures = penJingPictures;
	}
	public int getPenJingId() {
		return penJingId;
	}
	public String getPenJingTitle() {
		return penJingTitle;
	}
	public void setPenJingTitle(String penJingTitle) {
		this.penJingTitle = penJingTitle;
	}
	public void setPenJingId(int penJingId) {
		this.penJingId = penJingId;
	}
	public String getPenJingName() {
		return penJingName;
	}
	public void setPenJingName(String penJingName) {
		this.penJingName = penJingName;
	}
	public User getPublisher() {
		return publisher;
	}
	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}
	public User getAssessor() {
		return assessor;
	}
	public void setAssessor(User assessor) {
		this.assessor = assessor;
	}
	public PenJingType getType() {
		return type;
	}
	public void setType(PenJingType type) {
		this.type = type;
	}
	public String getPenJingDescription() {
		return penJingDescription;
	}
	public void setPenJingDescription(String penJingDescription) {
		this.penJingDescription = penJingDescription;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getMainPicture() {
		return mainPicture;
	}
	public void setMainPicture(String mainPicture) {
		this.mainPicture = mainPicture;
	}
	public byte getPenJingStatus() {
		return penJingStatus;
	}
	public void setPenJingStatus(byte penJingStatus) {
		this.penJingStatus = penJingStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
