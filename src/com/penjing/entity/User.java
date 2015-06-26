package com.penjing.entity;
/*
 * creat by wang at 2015.3.30
 * 用户表
 */

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User{
	private int userId; //用户id
	private String userName; //用户名，昵称
	private String password; //用户登录密码
	private Date enrollTime; //用户注册时间
	private Date lastTime;  //用户最后登录时间
	private String mail;  //用户邮箱
	private String phone;  //用户电话
	private String photo; //用户论坛头像地址;
	private String userDescription; //用户个人说明
	private Role role;  //用户所属角色
	private int roleId;
	private byte userStatus;  //用户当前状态   1.正常 2.冻结
	private Set<PenJing> myPenJing = new HashSet<PenJing>(); //我发布的盆景
	private Set<PenJing> myAssessPenJing = new HashSet<PenJing>(); //我审批的盆景，管理员独有
	private Set<News> myNews = new HashSet<News>(); //我发表的新闻
	private Set<News> myAssessNews = new HashSet<News>(); //我审批的新闻
	private Set<Topic> myTopics = new HashSet<Topic>(); //我创建的主题
	private Set<Reply> myReply = new HashSet<Reply>(); //我回复的帖子
	private Long topicNum; //发帖数
	private Long repliesNum; //回帖数
	private String enrollTimeString;
	private String lastTimeString;
	private String roleName;
	
	public User(){}
	
	public User(int userId){
		this.userId = userId;
	}
	
	public User(int userId,String userName,String password,String photo,byte userStatus,int roleId){
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.photo = photo;
		this.userStatus = userStatus;
		this.roleId = roleId;
	}
	
	public User(int userId,String userName,String photo,String mail,String phone,Date enrollTime,String userDescription){
		this.userId = userId;
		this.userName = userName;
		this.photo = photo;
		this.mail = mail;
		this.phone = phone;
		this.enrollTime = enrollTime;
		this.userDescription = userDescription;
	}
	
	public User(int userId,String userName,String mail,String phone,Date enrollTime,Date lastTime,String userDescription,String roleName,int roleId,byte userStatus){
		this.userId = userId;
		this.userName = userName;
		this.mail = mail;
		this.phone = phone;
		this.enrollTime = enrollTime;
		this.lastTime = lastTime;
		this.userDescription = userDescription;
		this.roleName = roleName;
		this.roleId = roleId;
		this.userStatus = userStatus;
	}
	
	public User(int userId,String userName,String photo,Date enrollTime,Date lastTime){
		this.userId = userId;
		this.userName = userName;
		this.photo = photo;
		this.enrollTime = enrollTime;
		this.lastTime = lastTime;
	}
	

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getLastTimeString() {
		return lastTimeString;
	}

	public void setLastTimeString(String lastTimeString) {
		this.lastTimeString = lastTimeString;
	}

	public String getEnrollTimeString() {
		return enrollTimeString;
	}

	public void setEnrollTimeString(String enrollTimeString) {
		this.enrollTimeString = enrollTimeString;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Long getTopicNum() {
		return topicNum;
	}

	public void setTopicNum(Long topicNum) {
		this.topicNum = topicNum;
	}

	public Long getRepliesNum() {
		return repliesNum;
	}

	public void setRepliesNum(Long repliesNum) {
		this.repliesNum = repliesNum;
	}

	public Set<Topic> getMyTopics() {
		return myTopics;
	}

	public void setMyTopics(Set<Topic> myTopics) {
		this.myTopics = myTopics;
	}

	public Set<Reply> getMyReply() {
		return myReply;
	}

	public void setMyReply(Set<Reply> myReply) {
		this.myReply = myReply;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Set<News> getMyAssessNews() {
		return myAssessNews;
	}
	public void setMyAssessNews(Set<News> myAssessNews) {
		this.myAssessNews = myAssessNews;
	}
	public Set<PenJing> getMyAssessPenJing() {
		return myAssessPenJing;
	}
	public void setMyAssessPenJing(Set<PenJing> myAssessPenJing) {
		this.myAssessPenJing = myAssessPenJing;
	}
	public Set<PenJing> getMyPenJing() {
		return myPenJing;
	}
	public void setMyPenJing(Set<PenJing> myPenJing) {
		this.myPenJing = myPenJing;
	}
	public Set<News> getMyNews() {
		return myNews;
	}
	public void setMyNews(Set<News> myNews) {
		this.myNews = myNews;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getEnrollTime() {
		return enrollTime;
	}
	public void setEnrollTime(Date enrollTime) {
		this.enrollTime = enrollTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public byte getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(byte userStatus) {
		this.userStatus = userStatus;
	}
	
	
}
