package com.penjing.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//帖子
public class Topic {

	private int topicId; //帖子编号
	private User user; //发帖人
	private Date time; //发帖时间
	private Date lastTime; //最后回复时间
	private String title; //标题
	private String content; //内容
	private byte isTop; //是否置顶
	private Date topTime; //置顶截止时间
	private byte topicState; //帖子状态
	private Set<Reply> replys = new HashSet<Reply>(); //该帖子下的回复
	private int userId;
	private String userName;
	private String photo;
	private String timeString;
	private String lastTimeString;
	private long readNu;
	
	private long replyNu;
	private User lastReplyUser; //最后回复者
	private int forumId;
	private String forumName;
	
	public Topic(int topicId,Date lastTime,String title){
		this.topicId = topicId;
		this.lastTime = lastTime;
		this.title = title;
	}
	public Topic(int topicId,Date lastTime,String title,byte isTop,Date topTime,int userId,String userName,String photo){
		this.topicId = topicId;
		this.lastTime = lastTime;
		this.title = title;
		this.isTop = isTop;
		this.topTime = topTime;
		this.userId = userId;
		this.userName = userName;
		this.photo = photo;
	}
	public Topic(int topicId,Date time,long readNu,String title,byte isTop,Date topTime,byte topicState,int userId,String userName){
		this.topicId = topicId;
		this.time = time;
		this.readNu = readNu;
		this.title = title;
		this.isTop = isTop;
		this.topTime = topTime;
		this.topicState = topicState;
		this.userId = userId;
		this.userName = userName;
	}
	public Topic(int topicId,Date time,Date lastTime,String title,String content,long readNu,int userId,String userName,String photo){
		this.topicId = topicId;
		this.time = time;
		this.lastTime = lastTime;
		this.title = title;
		this.content = content;
		this.readNu = readNu;
		this.userId = userId;
		this.userName = userName;
		this.photo = photo;
	}
	
	
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public String getForumName() {
		return forumName;
	}
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
	public User getLastReplyUser() {
		return lastReplyUser;
	}
	public void setLastReplyUser(User lastReplyUser) {
		this.lastReplyUser = lastReplyUser;
	}
	public long getReplyNu() {
		return replyNu;
	}
	public void setReplyNu(long replyNu) {
		this.replyNu = replyNu;
	}
	public Set<Reply> getReplys() {
		return replys;
	}
	public void setReplys(Set<Reply> replys) {
		this.replys = replys;
	}
	
	public byte getTopicState() {
		return topicState;
	}
	public void setTopicState(byte topicState) {
		this.topicState = topicState;
	}
	public long getReadNu() {
		return readNu;
	}
	public void setReadNu(long readNu) {
		this.readNu = readNu;
	}
	public String getTimeString() {
		return timeString;
	}
	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	public String getLastTimeString() {
		return lastTimeString;
	}
	public void setLastTimeString(String lastTimeString) {
		this.lastTimeString = lastTimeString;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Topic(){}
	
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public byte getIsTop() {
		return isTop;
	}
	public void setIsTop(byte isTop) {
		this.isTop = isTop;
	}
	public Date getTopTime() {
		return topTime;
	}
	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}
	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", time=" + time + ", lastTime="
				+ lastTime + ", title=" + title + ", content=" + content
				+ ", userId=" + userId + ", userName=" + userName
				+ ", lastTimeString=" + lastTimeString + "]";
	}
	
	
}
