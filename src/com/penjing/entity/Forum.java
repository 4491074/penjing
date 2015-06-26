package com.penjing.entity;


public class Forum {

	private byte forumId;
	private String forumName; //论坛名称
	private String notice;  //论坛公告
	private long topicNu;  //总帖数
	private int todayTopicNu; //今日发帖数
	private byte pageNu; //每一页多少个帖子，默认12
	private byte forumStatus; //论坛状态
	
	public Forum(byte forumId){
		this.forumId = forumId;
	}
	public Forum(){}
	public Forum(String forumName,String notice,byte pageNu){
		this.forumName = forumName;
		this.notice = notice;
		this.pageNu = pageNu;
	}
	
	
	public byte getForumId() {
		return forumId;
	}
	public void setForumId(byte forumId) {
		this.forumId = forumId;
	}
	public byte getPageNu() {
		return pageNu;
	}
	public void setPageNu(byte pageNu) {
		this.pageNu = pageNu;
	}
	public String getForumName() {
		return forumName;
	}
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public long getTopicNu() {
		return topicNu;
	}
	public void setTopicNu(long topicNu) {
		this.topicNu = topicNu;
	}
	public int getTodayTopicNu() {
		return todayTopicNu;
	}
	public void setTodayTopicNu(int todayTopicNu) {
		this.todayTopicNu = todayTopicNu;
	}
	public byte getForumStatus() {
		return forumStatus;
	}
	public void setForumStatus(byte forumStatus) {
		this.forumStatus = forumStatus;
	}
	
	
}
