package com.penjing.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {

	private long orderId;
	private String customerName; //顾客姓名
	private String customerPhone;//顾客电话
	private String customerAdd; //顾客地址
	private Date time; //订单生成时间
	private Date distributionTime; //配送时间
	private byte orderState; //订单状态（0-其他，1-交易中，2-交易完成，3-交易取消）
	private String note; //备注
	
	private Set<PenJingList> lists = new HashSet<PenJingList>(); //该板块下的新闻集合
	
	public Order(){}
	
	public Order(long orderId){
		this.orderId = orderId;
	}
	
	public Date getDistributionTime() {
		return distributionTime;
	}
	public void setDistributionTime(Date distributionTime) {
		this.distributionTime = distributionTime;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerAdd() {
		return customerAdd;
	}
	public void setCustomerAdd(String customerAdd) {
		this.customerAdd = customerAdd;
	}
	
	public Set<PenJingList> getLists() {
		return lists;
	}
	public void setLists(Set<PenJingList> lists) {
		this.lists = lists;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public byte getOrderState() {
		return orderState;
	}
	public void setOrderState(byte orderState) {
		this.orderState = orderState;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
