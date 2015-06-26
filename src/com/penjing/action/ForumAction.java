package com.penjing.action;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.penjing.entity.Forum;
import com.penjing.service.ForumService;
import com.penjing.util.Entity2JSONUtil;
@ResultPath("/")
@ParentPackage(value="json-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"})
	})
public class ForumAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private ServletContext application;
	@Resource
	private ForumService forumService;
	private String forumId;
	private String forumName;
	private String notice;
	private int pageNu;
	private int forumStatus;
	private List<Forum> forums;
	private JSONObject result;
	private JSONArray array;
	private String page;
	private String rows;
	
	
	
	
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public void setPageNu(int pageNu) {
		this.pageNu = pageNu;
	}
	public void setForumStatus(int forumStatus) {
		this.forumStatus = forumStatus;
	}
	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public JSONObject getResult() {
		return result;
	}
	public List<Forum> getForums() {
		return forums;
	}
	public void setForumId(String forumId) {
		this.forumId = forumId;
	}
	public String getForumId() {
		return forumId;
	}
	public String get(){
		Forum forum = (Forum)application.getAttribute("forum_"+forumId);
		if(forum == null){
			return "errorForum";
		}
		request.setAttribute("forum", forum);
		return "topic";
	}
	
	/*
	 * 得到所有论坛
	 */
	@Action(value="/forum/getAllForum")
	public String getAllForum(){
		result=new JSONObject();
		array=new JSONArray();
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		List<Forum> forums=forumService.getAllForum((Integer.parseInt(page)-1)*Integer.parseInt(rows),Integer.parseInt(rows),-1);
		Entity2JSONUtil.forum2JSON(forums, array);
		result.put("total",forums.size());
		result.put("rows", array);
		return "json";
	}
	@Action(value="/forum/updateForum")
	public String update(){
		result=new JSONObject();
		Forum f=new Forum();
		f.setForumId((byte)Integer.parseInt(forumId));
		f.setForumName(forumName);
		f.setNotice(notice);
		f.setPageNu((byte)pageNu);
		f.setForumStatus((byte)forumStatus);
		forumService.updateForum(f);
		request.getServletContext().setAttribute("forum", f);
		result.put("result", 1);
		return "json";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	

	@Override
	public void setServletContext(ServletContext application) {
		// TODO Auto-generated method stub
		this.application = application;
		
	}
	
}
