package com.penjing.entity;

public class PenJingList {

	private long penJingListId; 
	private Order order;
	private PenJing penJing;
	private int count; //数量
	private String listNote;
	
	
	private int penJingId; //盆景编号
	private String penJingName; //盆景名称
	private String penJingTitle; //盆景标题
	private String mainPicture; //盆景主要展示的一张图片
	private byte penJingStatus;  //盆景状态 0-为审核  1-审核通过  2-审核未通过
	private String penJingTypeName;
	private String publisherName;
	
	public PenJingList(){}
	public PenJingList(long penJingListId,int count,String listNote,int penJingId,String penJingName,String penJingTitle,String mainPicture,byte penJingStatus){
		this.penJingListId = penJingListId;
		this.count = count;
		this.penJingId = penJingId;
		this.listNote = listNote;
		this.penJingName = penJingName;
		this.penJingTitle = penJingTitle;
		this.mainPicture = mainPicture;
		this.penJingStatus = penJingStatus;
		
	}
	
	public PenJingList(long penJingListId,int count,String listNote,int penJingId,String penJingName,String penJingTitle,String mainPicture,byte penJingStatus,String penJingTypeName,String publisherName){
		this.penJingListId = penJingListId;
		this.count = count;
		this.listNote = listNote;
		this.penJingId = penJingId;
		this.penJingName = penJingName;
		this.penJingTitle = penJingTitle;
		this.mainPicture = mainPicture;
		this.penJingStatus = penJingStatus;
		this.penJingTypeName = penJingTypeName;
		this.publisherName = publisherName;
		
	}
	
	public long getPenJingListId() {
		return penJingListId;
	}
	public void setPenJingListId(long penJingListId) {
		this.penJingListId = penJingListId;
	}
	
	public int getPenJingId() {
		return penJingId;
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
	public String getPenJingTitle() {
		return penJingTitle;
	}
	public void setPenJingTitle(String penJingTitle) {
		this.penJingTitle = penJingTitle;
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
	public String getPenJingTypeName() {
		return penJingTypeName;
	}
	public void setPenJingTypeName(String penJingTypeName) {
		this.penJingTypeName = penJingTypeName;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public PenJing getPenJing() {
		return penJing;
	}
	public void setPenJing(PenJing penJing) {
		this.penJing = penJing;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getListNote() {
		return listNote;
	}
	public void setListNote(String listNote) {
		this.listNote = listNote;
	}
	
}
