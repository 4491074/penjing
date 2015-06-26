package com.penjing.entity;

import java.util.HashSet;
import java.util.Set;

/*
 * 角色、权限
 */
public class Role{

	private int roleId; //角色编号
	private String roleName; //角色名称
	private String roleDescription; //角色描述
	private Boolean publishPenJing; //是否可以发布盆景
	private Boolean auditPenJing; //是否可以审批盆景
	private Boolean managePenJing; //是否可以管理盆景
	private Boolean manageOrder; //是否可以管理订单
	private Boolean publishNews; //是否可以发布新闻
	private Boolean auditNews; //是否可以审批新闻
	private Boolean manageNews; //是否可以管理新闻
	private Boolean creatTopic; //是否能够创建主题帖
	private Boolean manageForum; //是否可以管理论坛
	private Boolean manageRole; //是否可以管理角色（查看、增加、删除、修改角色）
	private Boolean manageUser; //是否可以管理用户
	private Boolean manageInfo; //是否可以管理页面所展示的信息
	private byte roleStatus; //角色状态
	private Set<User> users = new HashSet<User>();
	
	public Role(){}
	
	public Role(int roleId,String roleName,byte roleStatus){
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleStatus = roleStatus;
	}
	
	public Role(Boolean b){
		this.publishPenJing = b;
		this.auditPenJing = b;
		this.managePenJing = b;
		this.publishNews = b;
		this.auditNews = b;
		this.manageNews = b;
		this.creatTopic = b;
		this.manageForum = b;
		this.manageRole = b;
		this.manageUser = b;
		this.manageInfo = b;
	}
	
	public Boolean getManageOrder() {
		return manageOrder;
	}

	public void setManageOrder(Boolean manageOrder) {
		this.manageOrder = manageOrder;
	}

	public Boolean getManageInfo() {
		return manageInfo;
	}
	public void setManageInfo(Boolean manageInfo) {
		this.manageInfo = manageInfo;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public Boolean getPublishPenJing() {
		return publishPenJing;
	}
	public void setPublishPenJing(Boolean publishPenJing) {
		this.publishPenJing = publishPenJing;
	}
	public Boolean getAuditPenJing() {
		return auditPenJing;
	}
	public void setAuditPenJing(Boolean auditPenJing) {
		this.auditPenJing = auditPenJing;
	}
	public Boolean getManagePenJing() {
		return managePenJing;
	}
	public void setManagePenJing(Boolean managePenJing) {
		this.managePenJing = managePenJing;
	}
	public Boolean getPublishNews() {
		return publishNews;
	}
	public void setPublishNews(Boolean publishNews) {
		this.publishNews = publishNews;
	}
	public Boolean getAuditNews() {
		return auditNews;
	}
	public void setAuditNews(Boolean auditNews) {
		this.auditNews = auditNews;
	}
	public Boolean getManageNews() {
		return manageNews;
	}
	public void setManageNews(Boolean manageNews) {
		this.manageNews = manageNews;
	}
	public Boolean getManageRole() {
		return manageRole;
	}
	public void setManageRole(Boolean manageRole) {
		this.manageRole = manageRole;
	}
	public Boolean getManageUser() {
		return manageUser;
	}
	public void setManageUser(Boolean manageUser) {
		this.manageUser = manageUser;
	}
	public byte getRoleStatus() {
		return roleStatus;
	}
	public void setRoleStatus(byte roleStatus) {
		this.roleStatus = roleStatus;
	}
	public Boolean getCreatTopic() {
		return creatTopic;
	}
	public void setCreatTopic(Boolean creatTopic) {
		this.creatTopic = creatTopic;
	}
	public Boolean getManageForum() {
		return manageForum;
	}
	public void setManageForum(Boolean manageForum) {
		this.manageForum = manageForum;
	}
	
	
}
