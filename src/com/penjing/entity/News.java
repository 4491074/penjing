package com.penjing.entity;

import java.util.Date;

/*
 * 新闻
 */
public class News{

	private int newsId; //新闻编号
	private String newsTitle; //新闻标题
	private String newsContent; //新闻内容
	private long readNu;//阅读量、点击量
	private User newsPublisher; //新闻发布者
	private User newsAssessor; //新闻审批者
	private Date publishTime; //新闻发布时间
	private NewsBoard newsBoard; //新闻所属板块
	private byte newsStatus; //新闻状态（0-未审核，1-通过，2-未通过）
	private String remark; //备注
	
	public long getReadNu() {
		return readNu;
	}
	public void setReadNu(long readNu) {
		this.readNu = readNu;
	}
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public User getNewsPublisher() {
		return newsPublisher;
	}
	public void setNewsPublisher(User newsPublisher) {
		this.newsPublisher = newsPublisher;
	}
	public User getNewsAssessor() {
		return newsAssessor;
	}
	public void setNewsAssessor(User newsAssessor) {
		this.newsAssessor = newsAssessor;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public NewsBoard getNewsBoard() {
		return newsBoard;
	}
	public void setNewsBoard(NewsBoard newsBoard) {
		this.newsBoard = newsBoard;
	}
	public byte getNewsStatus() {
		return newsStatus;
	}
	public void setNewsStatus(byte newsStatus) {
		this.newsStatus = newsStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
