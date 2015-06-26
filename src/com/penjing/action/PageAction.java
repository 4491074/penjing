package com.penjing.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.penjing.entity.Role;
import com.penjing.entity.User;
import com.penjing.filter.NewsBoardFilter;
import com.penjing.service.NewsBoardService;
import com.penjing.service.RemarkInfoService;
import com.penjing.util.StringUtil;
@ResultPath("/")
@ParentPackage(value="crud-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"}),
	  @Result(name="noUserAuthority",type="chain",location="noAuthority"),
	  @Result(name="noLogin", type="chain",location="loginError")
	})
public class PageAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prePage; //用户的上一个请求页面
	private String nowPage; //用户当前请求地址
	private HttpServletRequest request;
	
	private User user;
	private Role role;
	private String page;
	private String id;
	private String error;
	private String newsBdId;   //新闻版块ID
	private String newsPage;   //新闻页面
	@Resource
	private NewsBoardService newsBdService;
	@Resource
	private RemarkInfoService remarkInfoService;

	
	public String getError() {
		return error;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setNewsPage(String newsPage) {
		this.newsPage = newsPage;
	}

	public String getPrePage() {
		if(prePage == null){
			prePage = "home.jsp";
		}
		return prePage;
	}
	
	public User getUser() {
		return user;
	}

	public Role getRole() {
		return role;
	}

	public String getNowPage() {
		if(nowPage == null){
			nowPage = "jsp/home.jsp";
		}
		return nowPage;
	}
	
	public void setNewsBdId(String newsBdId) {
		this.newsBdId = newsBdId;
	}

	
	
	public void setRemarkInfoService(RemarkInfoService remarkInfoService) {
		this.remarkInfoService = remarkInfoService;
	}

	public void setNewsBdService(NewsBoardService newsBdService) {
		this.newsBdService = newsBdService;
	}

	/*
	 * 保存用户的当前页面课上一次页面
	 */
	private void savePage(String now){
		prePage = nowPage;
		nowPage = now;
		request.setAttribute("nowPage", nowPage);
	}
	/*
	 * 跳转至主页
	 */
	@Action(value="home",results={@Result(name="success", location="index.jsp")})
	public String getHome(){
		if(id == null){
			id = 0+"";
		}
		savePage("penjings/homePenjing?penjingType="+id);
		return "success";
	}
	/*
	 * 跳转至非物质文化遗产
	 */
	@Action(value="cultural",results={@Result(name="success", location="index.jsp")})
	public String getCultural(){
		savePage("jsp/cultural.jsp");
		return "success";
	}
	/*
	 * 跳转至领导关怀
	 */
	@Action(value="lead",results={@Result(name="success", location="index.jsp")})
	public String getLead(){
		savePage("jsp/building.jsp?li=2");
		return "success";
	}
	/*
	 * 跳转至活动纪实
	 */
	@Action(value="activity",results={@Result(name="success", location="index.jsp")})
	public String getActivity(){
		savePage("jsp/building.jsp?li=3");
		return "success";
	}
	/*
	 * 跳转至盆景知识
	 */
	@Action(value="knowledge",results={@Result(name="success", location="index.jsp")})
	public String getKnowledge(){
		savePage("jsp/knowledge.jsp");
		return "success";
	}
	/*
	 * 跳转至新闻列表
	 * value为请求方式
	 * location为成功后跳转的地址
	 * savePage为index.jsp中子页面的地址，以及记录用户的历史请求
	 */
	@Action(value="news",results={@Result(name="success", location="index.jsp")})
	public String getNews(){
		if(newsBdId == null){
			newsBdId=String.valueOf(NewsBoardFilter.defaultNewsBdId);
		}else{
			newsBdId = StringUtil.removeChar(newsBdId);
		}
		if(newsPage==null){
			newsPage=String.valueOf(1);
		}else{
			newsPage=StringUtil.removeChar(newsPage);
		}
		savePage("getNewsByNewsBdId?newsBdId="+newsBdId+"&&page="+newsPage);
		return "success";
	}
	/*
	 * 跳转至企业
	 */
	@Action(value="enterprise",results={@Result(name="success", location="index.jsp")})
	public String getEnterprise(){
		savePage("jsp/enterprise.jsp");
		return "success";
	}
	/*
	 * 跳转至论坛
	 */
	@Action(value="forums",results={@Result(name="success", location="jsp/bbs.jsp")})
	public String getForum(){
		if(page == null){
			page = 1+"";
		}else{
			page = StringUtil.removeChar(page);
		}
		savePage("jsp/forum.jsp?page="+page);
		return SUCCESS;
	}
	
	/*
	 * 跳转至主题帖
	 */
	@Action(value="topics",results={@Result(name="success", location="jsp/bbs.jsp")})
	public String getTopic(){
		if(id == null){
			error = "查看的主题不存在";
			savePage("jsp/error.jsp");
		}else{
			id = StringUtil.removeChar(id);
		}
		savePage("topic/getTopic?topicId="+id);
		return SUCCESS;
	}
	
	/*
	 * 跳转至论坛用户资料
	 */
	@Action(value="users",results={@Result(name="success", location="jsp/bbs.jsp")})
	public String getUsers(){
		if(id == null){
			error = "查看的用户不存在";
			savePage("jsp/error.jsp");
		}else{
			id = StringUtil.removeChar(id);
		}
		savePage("user/getUser?userId="+id);
		return SUCCESS;
	}

	
	/*
	 * 跳转至登录
	 */
	@Action(value="login",results={@Result(name="success", location="index.jsp")})
	public String getLogin(){
		savePage("jsp/login.jsp");
		return "success";
	}
	/*
	 * 跳转至注册
	 */
	@Action(value="enroll",results={@Result(name="success", location="index.jsp")})
	public String getEnroll(){
		savePage("jsp/enroll.jsp");
		return "success";
	}
	
	/*
	 * 跳转至盆景信息页面
	 */
	@Action(value="penjingInfo",results={@Result(name="success", location="index.jsp")})
	public String getpenjingInfo(){
		savePage("penjings/penjingInfo?penjingId="+id);
		return "success";
	}
	
	
	/*
	 * 返回用户资料页面
	 */
	@Action(value="userManage",
			results={@Result(name="success", location="WEB-INF/backManage/userManage.jsp")},
			interceptorRefs={@InterceptorRef("userInterceptor")}		 
			)
	public String getUserManage(){
		return SUCCESS;
	}
	
	/*
	 * 返回用户资料页面
	 */
	@Action(value="personalManage",
			results={@Result(name="success", location="WEB-INF/backManage/personalManage.jsp")
			}		 
			)
	public String getPersonalManage(){
		if(request.getSession().getAttribute("user")==null){
			return "noLogin";
		}
		return SUCCESS;
	}
	
	/*
	 * 返回帖子管理页面
	 */
	@Action(value="topicManage",
			results={@Result(name="success", location="WEB-INF/backManage/topicManage.jsp")},
			interceptorRefs={@InterceptorRef("forumInterceptor")}	 
			)
	public String getTopicManage(){
		return SUCCESS;
	}
	
	/*
	 * 返回回帖管理页面
	 */
	@Action(value="replyManage",
			results={@Result(name="success", location="WEB-INF/backManage/replyManage.jsp")},
			interceptorRefs={@InterceptorRef("forumInterceptor")}		 
			)
	public String getReplyManage(){
		request.setAttribute("page", "reply/getAllReply?topicId="+id);
		return SUCCESS;
	}
	
	/*
	 * 返回订单详情页面
	 */
	@Action(value="penjingListManage",results={@Result(name="success", location="WEB-INF/backManage/penjingListManage.jsp")},
			interceptorRefs={@InterceptorRef("orderInterceptor")})
	public String penjingListManage(){
		request.setAttribute("page", "penjingList/getAllPenjingList?orderId="+id);
		request.setAttribute("orderId", id);
		return SUCCESS;
	}
	
	/*
	 * 返回角色管理页面
	 */
	@Action(value="roleManage",
			results={@Result(name="success", location="WEB-INF/backManage/roleManage.jsp")
					 },
			interceptorRefs={@InterceptorRef("roleInterceptor")})
	public String getRoleManage(){
		return SUCCESS;
	}
	/*
	 * 返回新闻版块管理页面
	 */
	@Action(value="newsBdManage",results={@Result(name="success", location="WEB-INF/backManage/newsBdManage.jsp")},
			interceptorRefs={@InterceptorRef("newsManageInterceptor")}
	)
	public String getNewsBdManage(){
		
		return SUCCESS;
	}
	/*
	 * 返回我的新闻管理页面
	 */
	@Action(value="myNewsManage",results={@Result(name="success", location="WEB-INF/backManage/myNews.jsp")},
			interceptorRefs={@InterceptorRef("publishNInterceptor")})
	public String getMyNews(){
		
		return SUCCESS;
	}
	/*
	 * 返回新闻管理页面
	 */
	@Action(value="newsManage",results={@Result(name="success", location="WEB-INF/backManage/newsManage.jsp")},
			interceptorRefs={@InterceptorRef("auditNInterceptor")})
	public String getNewsManage(){
		return SUCCESS;
	}
	/*
	 * 返回新闻审核页面
	 */
	@Action(value="newsCheck",results={@Result(name="success", location="WEB-INF/backManage/newsCheck.jsp")},
			interceptorRefs={@InterceptorRef("newsManageInterceptor")})
	public String getNewsCheck(){
		
		return SUCCESS;
	}
	
	/*
	 * 跳转至后台管理主页
	 */
	@Action(value="manage",results={@Result(name="success", location="WEB-INF/backManage/back.jsp"),
			@Result(name="noRole", location="role_getRoles",type="chain",params={"user","user"}),
			@Result(name="noLogin", location="login",type="redirect")})
	public String getManage(){
		HttpSession session = request.getSession();
		user = (User)session.getAttribute("user");
		if(user == null){
			return "noLogin";
		}
		role = (Role)request.getServletContext().getAttribute("role_"+user.getRoleId());
		if(role == null){
			return "noRole";
		}
		return "success";
	}
	
	/*
	 * 盆景类型管理
	 */
	@Action(value="penJingTypeManage",results={@Result(name="success", location="WEB-INF/backManage/PenJingType.jsp")},
			interceptorRefs={@InterceptorRef("penJingInterceptor")})
	public String penJingTypeManage(){
		return "success";
	}
	
	/*
	 * 我的盆景管理
	 */
	@Action(value="myPenjingManage",results={@Result(name="success", location="WEB-INF/backManage/myPenjingManage.jsp")},
			interceptorRefs={@InterceptorRef("publishPJInterceptor")})
	public String myPenjingManage(){
		return "success";
	}
	
	/*
	 * 所有盆景管理
	 */
	@Action(value="penJingManage",results={@Result(name="success", location="WEB-INF/backManage/penJingManage.jsp")},
			interceptorRefs={@InterceptorRef("penJingInterceptor")})
	public String penjingManage(){
		if(id != null){
			request.setAttribute("page", "penjing/getAllPenjing?penjingType="+id);
		}
		return "success";
	}
	
	/*
	 * 盆景审核
	 */
	@Action(value="penjingCheckManage",results={@Result(name="success", location="WEB-INF/backManage/checkPenJingManage.jsp")},
			interceptorRefs={@InterceptorRef("auditPJInterceptor")})
	public String penjingCheckManage(){
		return "success";
	}
	
	/*
	 * 订单管理
	 */
	@Action(value="orderManage",results={@Result(name="success", location="WEB-INF/backManage/orderManage.jsp")},
			interceptorRefs={@InterceptorRef("orderInterceptor")})
	public String orderManage(){
		return "success";
	}
	
	/*
	 * 返回首页图片管理页面
	 */
	@Action(value="getHeadImgManage",results={@Result(name="success", location="WEB-INF/backManage/HeadImageManage.jsp")})
	public String getHeadImgManage(){
		
		return SUCCESS;
	}
	
	/*
	 * 论坛管理
	 */
	@Action(value="forumManage",results={@Result(name="success", location="WEB-INF/backManage/forumManage.jsp")},
			interceptorRefs={@InterceptorRef("infoInterceptor")})
	public String forumManage(){
		return "success";
	}
	
	/*
	 * 返回备注管理页面
	 */
	@Action(value="remarkInfoManage",results={@Result(name="success", location="WEB-INF/backManage/remarkInfoManage.jsp")})
	public String getRemarkInfoManage(){
		
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	
}
