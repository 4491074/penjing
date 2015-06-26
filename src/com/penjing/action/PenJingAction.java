package com.penjing.action;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.penjing.entity.PenJing;
import com.penjing.entity.PenJingType;
import com.penjing.entity.User;
import com.penjing.service.PenJingPictureService;
import com.penjing.service.PenJingService;
import com.penjing.util.Entity2JSONUtil;
@ResultPath("/")
@ParentPackage(value="crud-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"}),
	  @Result(name="noLogin",type="chain",location="loginErrorAjax"),
	  @Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax")
	})
public class PenJingAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private PenJingService penjingService;
	@Resource  
	private PenJingPictureService penJingPictureService;
	private String page;
	private String rows;
	private HttpServletRequest request;
	private JSONObject result;
	private PenJing penjing;
	private String picturePath;
	private String penjingType;
	private String penJingName;
	private String penJingTitle;
	private String remark;
	private String penJingDescription;
	private String penjingId;
	private String deletePenjingIds;
	private String penjingState;
	private String mainPicture;
	private String passPicture;
	private String unPassPicture;
	private List<PenJing> penjings;
	private String first;
	private String error;
	
	public String getError() {
		return error;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public List<PenJing> getPenjings() {
		return penjings;
	}
	public String getPassPicture() {
		return passPicture;
	}
	public void setPassPicture(String passPicture) {
		this.passPicture = passPicture;
	}
	public String getUnPassPicture() {
		return unPassPicture;
	}
	public void setUnPassPicture(String unPassPicture) {
		this.unPassPicture = unPassPicture;
	}
	public void setMainPicture(String mainPicture) {
		this.mainPicture = mainPicture;
	}
	public void setPenjingState(String penjingState) {
		this.penjingState = penjingState;
	}
	public String getDeletePenjingIds() {
		return deletePenjingIds;
	}
	public void setDeletePenjingIds(String deletePenjingIds) {
		this.deletePenjingIds = deletePenjingIds;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public String getPenjingId() {
		return penjingId;
	}
	public void setPenjingId(String penjingId) {
		this.penjingId = penjingId;
	}
	public PenJing getPenjing() {
		return penjing;
	}
	public void setPenJingName(String penJingName) {
		this.penJingName = penJingName;
	}
	public void setPenJingTitle(String penJingTitle) {
		this.penJingTitle = penJingTitle;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setPenJingDescription(String penJingDescription) {
		this.penJingDescription = penJingDescription;
	}
	public void setPenjingType(String penjingType) {
		this.penjingType = penjingType;
	}
	public void setPenjing(PenJing penjing) {
		this.penjing = penjing;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
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
	 * 用户查看自己的盆景
	 */
	@Action(value="/penjings/getMyPenjing")
	public String getMyPenjing(){
		result = new JSONObject();
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			result.put("result", 2);
			return "json";
		}
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		List<PenJing> penjings= penjingService.getAllPenJing((Integer.parseInt(page)-1)*Integer.parseInt(rows),Integer.parseInt(rows), -1,user.getUserId());
		JSONArray array = new JSONArray();
		Entity2JSONUtil.myPenjing2JSON(penjings, array,penJingPictureService);
		result.put("total", penjingService.count(-1,user.getUserId()+""));
		result.put("rows", array);
		return "json";
	}
	
	/*
	 * 具体盆景页面
	 */
	@Action(value="/penjings/penjingInfo",
			results={@Result(name="noPenJing",location="../jsp/error.jsp",params={"error","error"}),
					 @Result(name="success",type="chain",location="penjingPicture_penjingInfo",params={"penjing","penjing"})
	})
	public String penjingInfo(){
		if(penjingId == null || "".equals(penjingId)){
			result = new JSONObject();
			result.put("result", 2);
			return "json";
		}
		penjing= penjingService.getPenjingInfo(Integer.parseInt(penjingId), (byte)1);
		if(penjing == null){
			error = "该盆景不存在或已经下架";
			return "noPenJing";
		}
		return SUCCESS;
	}
	
	/*
	 * 首页盆景展示
	 */
	@Action(value="/penjings/homePenjing",
			results={@Result(name="success",location="../jsp/home.jsp",params={"penjings","penjings"})})
	public String homePenjing(){
		if(penjingType == null || "".equals(penjingType) || 0 == Integer.parseInt(penjingType)){
			penjings = penjingService.getHomePenJing(0, 6, 1, null);
		}else{
			penjings = penjingService.getHomePenJing(0, 6, 1, penjingType);
		}
		ServletContext application= request.getServletContext();
		if(application.getAttribute("visitNum") != null){
			long visitNum=(long) application.getAttribute("visitNum");
			visitNum++;
			application.removeAttribute("visitNum");
			application.setAttribute("visitNum", visitNum);
		}
		return SUCCESS;
	}
	
	/*
	 * 首页盆景展示
	 */
	@Action(value="/penjings/getMore")
	public String getMore(){
		result = new JSONObject();
		if(first == null || "".equals(first)){
			result.put("result", 2);
			return "json";
		}
		if(penjingType == null || "".equals(penjingType) || 0 == Integer.parseInt(penjingType)){
			penjings = penjingService.getHomePenJing(Integer.parseInt(first), 6, 1, null);
		}else{
			penjings = penjingService.getHomePenJing(Integer.parseInt(first), 6, 1, penjingType);
		}
		if(penjings == null){
			result.put("result", 3);
			return "json";
		}
		JSONArray array = new JSONArray();
		Entity2JSONUtil.morePenjing2JSON(penjings, array);
		result.put("penjing", array);
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * 用户添加盆景
	 */
	@Action(value="/penjings/addPenjing",
			results={@Result(name="success",type="chain",location="penjingPicture_savePictures",params={"penjingId","penjingId","picturePath","picturePath"})},
			interceptorRefs={@InterceptorRef("publishPJInterceptor")}
			)
	public String addPenjing(){
		User user = (User)request.getSession().getAttribute("user");
		PenJing pj = new PenJing();
		PenJingType pjt = new PenJingType();
		pjt.setPenJingTypeId(Integer.parseInt(penjingType));
		pj.setPenJingName(penJingName);
		pj.setPenJingTitle(penJingTitle);
		pj.setPenJingDescription(penJingDescription);
		pj.setRemark(remark);
		pj.setType(pjt);
		pj.setPublisher(user);
		pj.setPublishTime(new Date());
		pj.setPenJingStatus((byte)0);
		penjingId = penjingService.savePenJing(pj)+"";
		if(penjingId == null){
			result = new JSONObject();
			result.put("result", 3);
			return "json";
		}
		if(picturePath == null || "".equals(picturePath)){
			result = new JSONObject();
			result.put("result", 1);
			result.put("penjingId", penjingId);
			return "json";
		}
		return SUCCESS;
	}
	
	/*
	 * 用户修改盆景
	 */
	@Action(value="/penjings/editPenjing",
			results={@Result(name="success",type="chain",location="penjingPicture_delPictures",params={"deletePenjingIds","deletePenjingIds"})},
			interceptorRefs={@InterceptorRef("publishPJInterceptor")}
			)
	public String editPenjing(){
		PenJing pj = new PenJing();
		PenJingType pjt = new PenJingType();
		pjt.setPenJingTypeId(Integer.parseInt(penjingType));
		pj.setPenJingName(penJingName);
		pj.setPenJingTitle(penJingTitle);
		pj.setPenJingDescription(penJingDescription);
		pj.setRemark(remark);
		pj.setType(pjt);
		pj.setPenJingId(Integer.parseInt(penjingId));
		penjingService.updatePenjing(pj);
		if(deletePenjingIds == null || "".equals(deletePenjingIds)){
			result = new JSONObject();
			result.put("result", 1);
			result.put("delNu", 0);
			return "json";
		}
		return SUCCESS;
	}
	
	/*
	 * 管理员审核盆景
	 * @Action(value="/penjing/checkPenjing" 盆景审核权限
	 * @Action(value="/penjing/checkPenjing_1" 盆景管理权限
	 */
	@Actions({@Action(value="/penjings/checkPenjing",
				results={@Result(name="success",type="chain",location="penjingPicture_checkPictures",
				params={"passPicture","passPicture","unPassPicture","unPassPicture","picturePath","picturePath"})},
				interceptorRefs={@InterceptorRef("publishPJInterceptor")}
			),
			@Action(value="/penjings/checkPenjing_1",
				results={@Result(name="success",type="chain",location="penjingPicture_checkPictures",
				params={"passPicture","passPicture","unPassPicture","unPassPicture","deletePenjingIds","deletePenjingIds","picturePath","picturePath"})},
				interceptorRefs={@InterceptorRef("penJingInterceptor")}
					)})
	public String checkPenjing(){
		if(penjingId == null || penjingState == null){
			result = new JSONObject();
			result.put("result", 2);
			return "json";
		}
		penjingService.updatePenjing(Integer.parseInt(penjingId),mainPicture, Byte.parseByte(penjingState));
		if(Integer.parseInt(penjingState) == 3){
			result = new JSONObject();
			result.put("result", 1);
			return "json";
		}
		return SUCCESS;
	}
	
	/*
	 * 用户删除盆景
	 * 管理员删除盆景
	 */
	
	@Actions({
		@Action(value="/penjings/delPenjing",
				interceptorRefs={@InterceptorRef("publishPJInterceptor")}),
		@Action(value="/penjings/manDelPenjing",
				interceptorRefs={@InterceptorRef("penJingInterceptor")})
	})
	public String delPenjing(){
		result = new JSONObject();
		if(picturePath != null && !"".equals(picturePath)){
			String path = request.getSession().getServletContext().getRealPath("/penjingPicture");
			for(String str:picturePath.split(",")){
				File delFile = new File(new File(path), str.replaceAll("penjingPicture/", "")); //根据地址以及文件名生成文件
				if (delFile.isFile() && delFile.exists()) {  
					delFile.delete();
			    }
			}
		}
		penjingService.deletePenjing(Integer.parseInt(penjingId));
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * 管理员查看所有盆景
	 */
	@Action(value="/penjings/getAllPenjing")
	public String getAllPenjing() throws ParseException{
		result = new JSONObject();
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		List<PenJing> penjings= penjingService.getAllPenJing((Integer.parseInt(page)-1)*Integer.parseInt(rows),Integer.parseInt(rows), -1,penjingType);
		JSONArray array = new JSONArray();
		Entity2JSONUtil.penjing2JSON(penjings, array,penJingPictureService);
		result.put("total", penjingService.count(-1,null));
		result.put("rows", array);
		return "json";
	}
	
	/*
	 * 管理员查找盆景添加订单
	 */
	
	@Actions({
		@Action(value="/penjings/searchPenjing"),
		@Action(value="penjing_searchPenjing")
	})
	public String searchPenjing(){
		result = new JSONObject();
		PenJing pj= penjingService.getPenjingForOrder(Integer.parseInt(penjingId));
		if(pj == null){
			result.put("result", 2);
			return "json";
		}
		Entity2JSONUtil.penjing2JSONForOrder(pj, result);
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * 管理员查看所有盆景
	 */
	@Action(value="/penjings/getCheckPenjing")
	public String getCheckPenjing() throws ParseException{
		result = new JSONObject();
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		List<PenJing> penjings= penjingService.getAllPenJing((Integer.parseInt(page)-1)*Integer.parseInt(rows),Integer.parseInt(rows), 0,null);
		JSONArray array = new JSONArray();
		Entity2JSONUtil.penjing2JSON(penjings, array,penJingPictureService);
		result.put("total", penjingService.count(0,null));
		result.put("rows", array);
		return "json";
	}
	
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
}
