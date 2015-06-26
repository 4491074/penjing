package com.penjing.action;

import java.util.ArrayList;
import java.util.Iterator;
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
import com.penjing.entity.PenJingType;
import com.penjing.service.PenJingTypeService;
import com.penjing.util.Entity2JSONUtil;
@ResultPath("/")
@ParentPackage(value="crud-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"}),
	  @Result(name="noLogin",type="chain",location="loginErrorAjax"),
	  @Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax")
	})
public class PenJingTypeAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private PenJingTypeService penJingTypeService;
	private JSONObject result;
	
	private String page;
	private String rows;
	
	private int penJingTypeId;
	private String penJingTypeName;
	private String   penJingTypeDescription;
	private int penJingTypeStatus;
	private String penJingTypeIds;
	private JSONArray array;
	private HttpServletRequest request;
	
	
	
	
	public JSONArray getArray() {
		return array;
	}
	public void setPenJingTypeIds(String penJingTypeIds) {
		this.penJingTypeIds = penJingTypeIds;
	}
	public void setPenJingTypeId(int penJingTypeId) {
		this.penJingTypeId = penJingTypeId;
	}
	public void setPenJingTypeName(String penJingTypeName) {
		this.penJingTypeName = penJingTypeName;
	}
	public void setPenJingTypeDescription(String penJingTypeDescription) {
		this.penJingTypeDescription = penJingTypeDescription;
	}
	public void setPenJingTypeStatus(int penJingTypeStatus) {
		this.penJingTypeStatus = penJingTypeStatus;
	}
	public JSONObject getResult() {
		return result;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public void setPenJingTypeService(PenJingTypeService penJingTypeService) {
		this.penJingTypeService = penJingTypeService;
	}
	
	
	@Action(value="/penjings/AllPenJingType")
	public String getAllPenJingType(){
		JSONArray jsonArray=new JSONArray();
		result=new JSONObject();
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		List<PenJingType> ls=penJingTypeService.getAllPenJingType((Integer.parseInt(page)-1)*Integer.parseInt(rows),Integer.parseInt(rows),-1);
		Entity2JSONUtil.PJType2JSON(ls,jsonArray,penJingTypeService);
		result.put("total",penJingTypeService.count(-1));
		result.put("rows",jsonArray);
		return "json";
	}
	
	@Action(value="/penjingType/getPenjingType",results={@Result(name="array",type="json",params={"root","array"})})
	public String getPenJingType(){
		array = new JSONArray();
		List<PenJingType> roles= penJingTypeService.findAllListForBox((byte)-1);
		Entity2JSONUtil.penjingType2JSON1(roles, array);
		return "array";
	}
	/*
	 * 添加一个盆景类型
	 */
	@Action(value="/penjings/AddPenJingType",
			interceptorRefs={@InterceptorRef("penJingInterceptor")})
	public String AddPenJingType(){
		result = new JSONObject();
		PenJingType p=new PenJingType();
		p.setPenJingTypeName(penJingTypeName);
		p.setPenJingTypeDescription(penJingTypeDescription);
		p.setPenJingTypeStatus((byte)penJingTypeStatus);
		penJingTypeService.addPenJingType(p);
		if(p.getPenJingTypeStatus() == 1){
			@SuppressWarnings("unchecked")
			List<PenJingType> penjingType = (List<PenJingType>)request.getServletContext().getAttribute("penjingType");
			if(penjingType == null){
				penjingType = new ArrayList<PenJingType>();
			}
			penjingType.add(new PenJingType(p.getPenJingTypeId(),p.getPenJingTypeName()));
			request.getServletContext().setAttribute("penjingType", penjingType);
		}
		result.put("result",1);
		return "json";
	}
	/*
	 * 删除一个盆景类型
	 */
	@Action(value="/penjings/DelPenJingType",
			interceptorRefs={@InterceptorRef("penJingInterceptor")})
	public String delPenJingType(){
		result = new JSONObject();
		int num = penJingTypeService.delPenJingType(penJingTypeIds);
		@SuppressWarnings("unchecked")
		List<PenJingType> penjingType = (List<PenJingType>)request.getServletContext().getAttribute("penjingType");
		if(penjingType != null){
			for(String str:penJingTypeIds.split(",")){
				Iterator<PenJingType> iterator  = penjingType.iterator(); 
				while(iterator.hasNext()){
					String id = iterator.next().getPenJingTypeId()+"";
					if(id.equals(str)){
						iterator.remove();
						break;
					}
				}
			}
		}
		request.getServletContext().setAttribute("penjingType", penjingType);
		result.put("msg","你已经成功删除了"+num+"条数据！");
		return "json";
	}
	
	/*
	 * 修改一个盆景类型
	 */
	@Action(value="/penjings/updatePenJingType",
			interceptorRefs={@InterceptorRef("penJingInterceptor")})
	public String updatePenJingType(){
		result = new JSONObject();
		PenJingType p=new PenJingType();
		p.setPenJingTypeId(penJingTypeId);
		p.setPenJingTypeName(penJingTypeName);
		p.setPenJingTypeDescription(penJingTypeDescription);
		p.setPenJingTypeStatus((byte)penJingTypeStatus);
		penJingTypeService.updatePenJingType(p);
		@SuppressWarnings("unchecked")
		List<PenJingType> penjingType = (List<PenJingType>)request.getServletContext().getAttribute("penjingType");
		if(penjingType == null){
			if(p.getPenJingTypeStatus() == 1){
				penjingType = new ArrayList<PenJingType>();
				penjingType.add(new PenJingType(p.getPenJingTypeId(),p.getPenJingTypeName()));
			}
		}else{
			int i = 0;
			for(PenJingType pjt:penjingType){
				if(pjt.getPenJingTypeId()==p.getPenJingTypeId()){
					if(p.getPenJingTypeStatus() == 1){
						pjt.setPenJingTypeName(p.getPenJingTypeName());
					}else{
						penjingType.remove(pjt);
					}
					i = 1;
					break;
				}
				if(i == 0){
					if(p.getPenJingTypeStatus() == 1){
						penjingType.add(new PenJingType(p.getPenJingTypeId(),p.getPenJingTypeName()));
					}
				}
			}
		}
		request.getServletContext().setAttribute("penjingType", penjingType);
		result.put("result", 1);
		return "json";
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
}
