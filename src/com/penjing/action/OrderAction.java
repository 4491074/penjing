package com.penjing.action;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.penjing.entity.Order;
import com.penjing.service.OrderService;
import com.penjing.util.Entity2JSONUtil;
@ResultPath("/")
@ParentPackage(value="crud-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"}),
	  @Result(name="noLogin",type="chain",location="loginErrorAjax"),
	  @Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax")
	})
public class OrderAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private OrderService orderService;
	private String page;
	private String rows;
	private JSONObject result;
	private Order order;
	private String penjingList;
	private String orderId;
	private HttpServletRequest request;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPenjingList() {
		return penjingList;
	}
	public void setPenjingList(String penjingList) {
		this.penjingList = penjingList;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
	public JSONObject getResult() {
		return result;
	}

	public void setPage(String page) {
		this.page = page;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	
	/*
	 * 管理员查看所有订单
	 */
	@Action(value="/order/getAllOrder")
	public String getAllOrder() throws ParseException{
		result = new JSONObject();
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		List<Order> orders= orderService.pageList((Integer.parseInt(page)-1)*Integer.parseInt(rows),Integer.parseInt(rows), (byte)-1);
		JSONArray array = new JSONArray();
		Entity2JSONUtil.order2JSON(orders, array);
		result.put("total", orderService.count((byte)-1));
		result.put("rows", array);
		return "json";
	}
	
	/*
	 * 管理员编辑订单
	 */
	@Action(value="/order/editOrder",
			interceptorRefs={@InterceptorRef("orderInterceptor")})
	public String editOrder(){
		result = new JSONObject();
		orderService.update(order);
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * 管理员删除订单
	 */
	@Action(value="order_delOrder")
	public String delOrder(){
		result = new JSONObject();
		result.put("nu", orderService.delete(orderId));
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * 管理员添加订单
	 */
	@Action(value="/order/addOrder",
			results={@Result(name="success",type="chain",location="penjingList_addPenjingList",params={"penjingList","penjingList","orderId","orderId"})},
			interceptorRefs={@InterceptorRef("orderInterceptor")})
	public String addOrder(){
		order.setTime(new Date());
		orderId = orderService.save(order)+"";
		if(penjingList == null || "".equals(penjingList)){
			result = new JSONObject();
			result.put("result", 1);
			return "json";
		}
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
	
}
