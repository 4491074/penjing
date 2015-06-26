package com.penjing.action;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
@ResultPath("/")
@ParentPackage(value="json-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"}),
	  @Result(name="success",location="jsp/error.jsp")
	})
public class ErrorAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String error;
	private JSONObject result;
	
	public JSONObject getResult() {
		return result;
	}

	public String getError() {
		return error;
	}

	@Action(value="noAuthority")
	public String noAuthority(){
		error = "你的权限不够";
		return SUCCESS;
	}
	
	@Action(value="loginError")
	public String loginError(){
		error = "请重新登录";
		return SUCCESS;
	}
	
	@Action(value="noAuthorityAjax")
	public String noAuthorityAjax(){
		result = new JSONObject();
		result.put("result", 0);
		result.put("error", "你的权限不够");
		return "json";
	}
	
	@Action(value="loginErrorAjax")
	public String loginErrorAjax(){
		result = new JSONObject();
		result.put("result", 0);
		result.put("error", "请重新登录");
		return "json";
	}
	
	@Action(value="tooLarge")
	public String tooLargeErrorAjax(){
		result = new JSONObject();
		result.put("result", 3);
		return "json";
	}

}
