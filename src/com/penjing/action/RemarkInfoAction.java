package com.penjing.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.penjing.entity.RemarkInfo;
import com.penjing.service.ForumService;
import com.penjing.service.RemarkInfoService;
import com.penjing.util.Entity2JSONUtil;

@ResultPath("/")
@ParentPackage(value="json-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"})
	})
public class RemarkInfoAction implements ServletRequestAware{
	@Resource
	private RemarkInfoService remarkInfoService;
	private JSONObject result;
	private JSONArray array;
	private int Id;
	private String managerName;
	private String managerTel;
	private String address;
	private HttpServletRequest request;
	
	public void setId(int id) {
		Id = id;
	}


	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}


	public void setManagerTel(String managerTel) {
		this.managerTel = managerTel;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public void setRemarkInfoService(RemarkInfoService remarkInfoService) {
		this.remarkInfoService = remarkInfoService;
	}
	
	
	public JSONObject getResult() {
		return result;
	}


	@Action(value="/remark/getRemarkInfo")
	public String getAllRemarkInfo(){
		result=new JSONObject();
		array=new JSONArray();
		List<RemarkInfo> reinfos=remarkInfoService.getAllRemarkInfo();
		Entity2JSONUtil.RemarkInfo2JSON(reinfos, array);
		result.put("total",reinfos.size());
		result.put("rows", array);
		return "json";
	}
	
	
	@Action(value="/remark/updateReInfo")
	public String updateRemarkInfo(){
		result=new JSONObject();
		RemarkInfo reinfo=new RemarkInfo();
		reinfo.setId(Id);
		reinfo.setManagerName(managerName);
		reinfo.setManagerTel(managerTel);
		reinfo.setAddress(address);
		remarkInfoService.updateRemarkInfo(reinfo);
		request.getServletContext().setAttribute("managerTel", managerTel);
		result.put("result", 1);
		return "json";
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	
	
	
	
	
	
}
