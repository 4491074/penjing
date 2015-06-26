package com.penjing.action;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.penjing.entity.Forum;
import com.penjing.entity.Role;
import com.penjing.entity.Topic;
import com.penjing.entity.User;
import com.penjing.model.TopicNu;
import com.penjing.service.TopicService;
import com.penjing.util.StringUtil;
@ResultPath("/")
@ParentPackage(value="crud-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"}),
	  @Result(name="noLogin",type="chain",location="loginErrorAjax"),
	  @Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax")
	})
public class TopicAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	@Resource
	private TopicService topicService;
	private Topic topic;
	private JSONObject result;
	private TopicNu tn;
	private String page;
	private String rows;
	private User user;
	private List<Topic> ts;
	private List<String> forumPages;
	private String topicId;
	private Date time;
	private String error;
	private Forum forum;
	
	public void setResult(JSONObject result) {
		this.result = result;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public List<String> getForumPages() {
		return forumPages;
	}
	public Forum getForum() {
		return forum;
	}
	public String getError() {
		return error;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public TopicNu getTn() {
		return tn;
	}
	public List<Topic> getTs() {
		return ts;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public JSONObject getResult() {
		return result;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	/*
	 * 快速创建主题
	 */
	@Action(value="/topic/fastCreat",
			interceptorRefs={@InterceptorRef("forumInterceptor")})
	public String fastCreat() throws InterruptedException{
		result = new JSONObject();
		user = (User)request.getSession().getAttribute("user");
		if(user == null){
			result.put("result", 2);
		}else{
			topic.setUser(user);
			topic.setTime(new Date());
			topic.setLastTime(new Date());
			topic.setIsTop((byte) 0);
			topic.setTopicState((byte)1);
			topicService.saveTopic(topic);
			result.put("result", 1);
		}
		return "json";
	}
	
	/*
	 * ajax修改帖子
	 * 返回json
	 */
	@Action(value="/topic/editTopic",
			interceptorRefs={@InterceptorRef("forumInterceptor")})
	public String editTopic() throws Exception{
		result = new JSONObject();
		topicService.updateTopic(Integer.parseInt(topicId), topic.getIsTop(), topic.getTopTime(), topic.getTopicState());
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * ajax删除数据
	 */
	@Action(value="topic_delTopics")
	public String delTopic(){
		if(topicId != null){
			result.put("nu", topicService.deleteTopi(topicId));
		}else{
			result.put("nu", 0);
		}
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * 获取论坛页面的帖子信息
	 */
	@Action(value="/forum/getForum",results={@Result(name="toReply",type="chain",location="Reply_getLastReply",params={"ts","ts"}),
											@Result(name="success",location="../jsp/forumTopics.jsp")})
	public String getTopics(){
		forum = (Forum)request.getServletContext().getAttribute("forum");
		if(forum == null){
			error = "系统错误，论坛获取失败";
			return "error";
		}
		forumPages = new ArrayList<String>();
		forumPages.add(1+"");
		forum.setTopicNu(topicService.count(-1,1));
		if(forum.getTopicNu()>0){
			List<Topic> notTop = null;
			List<Topic> top = null;
			ts = new ArrayList<Topic>();
			tn = new TopicNu();
			tn.setPage(Integer.parseInt(page));
			tn.setPageNu(forum.getPageNu());
			tn.setTopTopicNu(Integer.parseInt(String.valueOf(topicService.count(1,1))));
			int p = (tn.getTopTopicNu()/tn.getPageNu());
			int y = (int) (tn.getTopTopicNu()%tn.getPageNu());
			if(y>0){
				p++;
			}
			int p1 = (int) (forum.getTopicNu()/tn.getPageNu());
			int y1 = (int) (forum.getTopicNu()%tn.getPageNu());
			if(y1>0){
				p1++;
			}
			StringUtil.setPages(page, p1+"", forumPages);
			tn.setFirst((tn.getPage()-1)*tn.getPageNu());
			if(p>=tn.getPage()){
				top = topicService.pageList(tn.getFirst(), tn.getPageNu(),1,1);
			}
			
			if(top!=null){
				Date now = new Date();
				for(Topic t :top ){
					if(t.getTopTime().before(now)){
						topicService.updateIsTop(t.getTopicId(), (byte)0);
					}else{
						ts.add(t);
					}
				}
				tn.setTopTopicNu(ts.size());
				
				if(ts.size()<tn.getPageNu()){
					notTop = topicService.pageList(tn.getFirst()-tn.getTopTopicNu(), tn.getPageNu()-ts.size(),0,1);
				}
				
			}else{
				notTop = topicService.pageList(tn.getFirst()-tn.getTopTopicNu(), tn.getPageNu(),0,1);
			}
			for(Topic t :notTop){
				ts.add(t);
			}
		}else{
			return SUCCESS;
		}
		request.setAttribute("pages", forumPages);
		request.setAttribute("page", page);
		return "toReply";
	}
	/*
	 * 查看具体主题帖
	 */
	@Action(value="/topic/getTopic",results={@Result(name="nullTopic",location="jsp/error.jsp"),
			@Result(name="success",location="../jsp/topic.jsp")})
	public String getTopic1() throws ParseException{
		topic = topicService.findTopicById(Integer.parseInt(StringUtil.removeChar(topicId)),1);
		if(topic==null){
			error = "查看的主题不存在";
			return "nullTopic";
		}
		topic.setReadNu(topic.getReadNu()+1);
		topicService.updateReadNu(topic.getTopicId(), topic.getReadNu());
		topic.setTimeString(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(topic.getTime()));
		topic.setLastTimeString(StringUtil.date2DateSubtract(topic.getLastTime()));
		return SUCCESS;
	}
	
	/*
	 * 管理员查看所有主题
	 */
	@Action(value="/topic/getAllTopic",results={@Result(name="toReply",type="chain",location="Reply_getRepliesNu",params={"result","result","ts","ts"})})
	public String getAllTopic() throws ParseException{
		result = new JSONObject();
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		ts = topicService.pageListForManage((Integer.parseInt(page)-1)*Integer.parseInt(rows),Integer.parseInt(rows),-1, -1);
		Long rn = topicService.count(-1,-1);
		result.put("total", rn);
		if(rn == 0){
			result.put("rows", "");
			return "json";
		}
		return "toReply";
	}
	
	/*
	 * 修改最后时间
	 */
	@Action(value="topic_setLastTime")
	public String setLastTime(){
		if(time == null){
			time = new Date();
		}
		result = new JSONObject();
		topicService.updateLastTime(Integer.parseInt(topicId), time);
		result.put("result", 1);
		return "json";
	}
	/*
	 * 获取用户的发表的帖子
	 */
	@Action(value="topic_getUserTopic",results={@Result(name="error",location="jsp/error.jsp"),
			@Result(name="reply",type="chain", location="Reply_getUserReply",params={"ts","ts","user","user"})})
	public String getUserTopic(){
		if(user==null){
			error = "系统错误，获取失败";
			return "error";
		}
		user.setTopicNum(topicService.count(user,1));
		ts = topicService.pageList(user, 0, 3, 1);
		return "reply";
	}
	/*
	 * 获取用户更多的帖子记录
	 * 每次最多获取3条记录
	 */
	@Action(value="/topic/topic_getMore",results={
			@Result(name="toReply",type="chain", location="reply_getLastReplyReturnJson",params={"ts","ts","user","user"})})
	public String getMore(){
		int first = Integer.parseInt(request.getParameter("first"));
		int userId = Integer.parseInt(request.getParameter("id"));
		result = new JSONObject();
		if(first>=9){
			result.put("result", 2);
			return "json";
		}else{
			user = new User(userId);
			ts = topicService.pageList(user, first, 3, 1);
			if(ts == null){
				result.put("result", 1);
				return "json";
			}
		}
		return "toReply";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
}
