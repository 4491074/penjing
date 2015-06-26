package com.penjing.entity;

import java.util.Date;

public class Reply {

	private Long replyId;
	private Long floor; //楼层数
	private User user;
	private Topic topic;
	private String content;
	private Date time;
	private byte replyState;
	private int userId;
	private String userName;
	private String photo;
	private Date lastTime;
	private String timeString;
	private int topicId;
	private String topicTitle;
	
	public Reply(Long replyId,Long floor,String content,Date time,byte replyState,int userId,String userName){
		this.replyId = replyId;
		this.floor = floor;
		this.content = content;
		this.time = time;
		this.replyState = replyState;
		this.userId = userId;
		this.userName = userName;
	}
	
	public Reply(Long replyId,String content,Date time,int topicId,String topicTitle,int userId,String userName){
		this.replyId = replyId;
		this.content = content;
		this.time = time;
		this.topicId = topicId;
		this.topicTitle = topicTitle;
		this.userId = userId;
		this.userName = userName;
	}
	public Reply(){}
	public Reply(int userId,String userName){
		this.userId = userId;
		this.userName = userName;
	}
	public Reply(Date lastTime){
		this.lastTime = lastTime;
	}
	public Reply(Long replyId,Long floor,String content,Date time,int userId,String userName,String photo){
		this.replyId = replyId;
		this.floor = floor;
		this.content = content;
		this.time = time;
		this.userId = userId;
		this.userName = userName;
		this.photo = photo;
	}
	
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public String getTopicTitle() {
		return topicTitle;
	}
	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}
	public String getTimeString() {
		return timeString;
	}
	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public byte getReplyState() {
		return replyState;
	}
	public void setReplyState(byte replyState) {
		this.replyState = replyState;
	}
	
	public Long getReplyId() {
		return replyId;
	}
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	public Long getFloor() {
		return floor;
	}
	public void setFloor(Long floor) {
		this.floor = floor;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
}
