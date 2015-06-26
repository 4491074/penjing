package com.penjing.action;


import java.text.ParseException;
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
import com.penjing.entity.PenJing;
import com.penjing.entity.PenJingList;
import com.penjing.service.PenJingListService;
import com.penjing.util.Entity2JSONUtil;
@ResultPath("/")
@ParentPackage(value="crud-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"}),
	  @Result(name="noLogin",type="chain",location="loginErrorAjax"),
	  @Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax")
	})
public class PenJingListAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	@Resource
	private PenJingListService penjingListService;
	private String penjingList;
	private String orderId;
	private String penjingId;
	private JSONObject result;
	private String rows;
	private String page;
	private int count;
	private String listNote;
	
	public void setCount(int count) {
		this.count = count;
	}
	public String getPenjingId() {
		return penjingId;
	}

	public void setPenjingId(String penjingId) {
		this.penjingId = penjingId;
	}

	public void setListNote(String listNote) {
		this.listNote = listNote;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public JSONObject getResult() {
		return result;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setPenjingList(String penjingList) {
		this.penjingList = penjingList;
	}
	
	/*
	 * 根据订单id添加盆景列表
	 */
	@Action(value="penjingList_addPenjingList")
	public String addPenjingList(){
		result = new JSONObject();
		if(orderId == null || "".equals(orderId)){
			result.put("result", 2);
			return "json";
		}
		if(penjingList == null || "".equals(penjingList)){
			result.put("result", 1);
			return "json";
		}
		Order or = new Order();
		or.setOrderId(Long.parseLong(orderId));
		JSONArray array = JSONArray.fromObject(penjingList);
		for(Object o:array){
			JSONObject jo = JSONObject.fromObject(o);
			PenJingList pjl = new PenJingList();
			PenJing pj = new PenJing();
			pj.setPenJingId((Integer)jo.get("penjingId"));
			pjl.setOrder(or);
			pjl.setPenJing(pj);
			pjl.setCount((Integer)jo.get("count"));
			pjl.setListNote(jo.getString("listNote"));
			penjingListService.save(pjl);
		}
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * 根据订单id添加盆景列表
	 */
	@Action(value="/penjingList/savePenjingList",
			interceptorRefs={@InterceptorRef("orderInterceptor")})
	public String savePenjingList(){
		result = new JSONObject();
		if(orderId == null || "".equals(orderId)){
			result.put("result", 2);
			return "json";
		}
		if(penjingId == null || "".equals(penjingId)){
			result.put("result", 3);
			return "json";
		}
		Order or = new Order();
		or.setOrderId(Long.parseLong(orderId));
		PenJingList pjl = new PenJingList();
		PenJing pj = new PenJing();
		pj.setPenJingId(Integer.parseInt(penjingId));
		pjl.setOrder(or);
		pjl.setPenJing(pj);
		pjl.setCount(count);
		pjl.setListNote(listNote);
		penjingListService.save(pjl);
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * 管理员删除订单
	 */
	@Action(value="/penjingList/delOrder",results={@Result(name="success",type="chain",location="order_delOrder",params={"orderId","orderId"})},
			interceptorRefs={@InterceptorRef("orderInterceptor")})
	public String delPenjingList(){
		if(orderId == null || "".equals(orderId)){
			result = new JSONObject();
			result.put("result", 2);
			return "json";
		}else{
			for(String str:orderId.split(",")){
				penjingListService.delete(Long.parseLong(str));
			}
		}
		return SUCCESS;
	}
	
	
	
	/*
	 * 管理员删除订单详情
	 */
	@Action(value="/penjingList/delPenjingList",
			interceptorRefs={@InterceptorRef("orderInterceptor")})
	public String delPenjingList_1(){
		result = new JSONObject();
		if(penjingList == null || "".equals(penjingList)){
			result.put("result", 2);
			return "json";
		}
		result.put("nu", penjingListService.delete(penjingList));
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * 管理员添加订单详情
	 */
	@Action(value="/penjings/searchPenjingToList",results={
			@Result(name="success",type="chain",location="penjing_searchPenjing",params={"penjingId","penjingId"})
	},
	interceptorRefs={@InterceptorRef("orderInterceptor")})
	public String searchPenjingToList(){
		if(orderId == null || penjingId == null){
			result = new JSONObject();
			result.put("result", 2);
			return "json";
		}
		PenJingList pjl = penjingListService.get(Long.parseLong(orderId), Integer.parseInt(penjingId));
		if(pjl != null){
			result = new JSONObject();
			result.put("result", 3);
			Entity2JSONUtil.penjingList2JSONForOrder(pjl, result);
			return "json";
		}
		return SUCCESS;
	}
	
	/*
	 * 管理员查看所有盆景详情
	 */
	@Action(value="/penjingList/getAllPenjingList")
	public String getAllPenjingList() throws ParseException{
		result = new JSONObject();
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		List<PenJingList> penjingLists = penjingListService.pageList(Long.parseLong(orderId));
		JSONArray array = new JSONArray();
		Entity2JSONUtil.penjingList2JSON(penjingLists, array);
		result.put("total", penjingListService.count(Long.parseLong(orderId)));
		result.put("rows", array);
		return "json";
	}
	
	/*
	 * 管理员修改订单详情
	 */
	@Action(value="/penjingList/editPenjingList",
			interceptorRefs={@InterceptorRef("orderInterceptor")})
	public String editPenjingList(){
		result = new JSONObject();
		if(penjingList == null || "".equals(penjingList)){
			result.put("result", 2);
			return "json";
		}
		penjingListService.update(Long.parseLong(penjingList), count, listNote);
		result.put("result", 1);
		return "json";
	}


	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
	
}
