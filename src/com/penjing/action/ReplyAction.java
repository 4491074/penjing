package com.penjing.action;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.penjing.entity.Reply;
import com.penjing.entity.Topic;
import com.penjing.entity.User;
import com.penjing.service.ReplyService;
import com.penjing.util.Entity2JSONUtil;
import com.penjing.util.StringUtil;
@ResultPath("/")
@ParentPackage(value="crud-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"})
	})
public class ReplyAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	@Resource
	private ReplyService replyService;
	private String topicId;
	private String replyId;
	private String replyContent;
	private JSONObject result;
	private String replyPage;
	private List<Reply> replys;
	private List<Topic> ts;
	private String lastTime;
	private Long replyNu;
	private Date time;
	private List<String> pages;
	private User user;
	private String error;
	private String page;
	private String rows;
	private String replyState;
	
	public void setReplyState(String replyState) {
		this.replyState = replyState;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getError() {
		return error;
	}
	public List<Topic> getTs() {
		return ts;
	}
	public void setTs(List<Topic> ts) {
		this.ts = ts;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTopicId() {
		return topicId;
	}
	public List<String> getPages() {
		return pages;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Long getReplyNu() {
		return replyNu;
	}
	public String getLastTime() {
		return lastTime;
	}
	public List<Reply> getReplys() {
		return replys;
	}
	public void setReplyPage(String replyPage) {
		this.replyPage = replyPage;
	}
	public String getReplyPage() {
		return replyPage;
	}
	public JSONObject getResult() {
		return result;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	/*
	 * 发表帖子回复
	 * 通过topicId获取帖子以及帖子的回帖数
	 * 成功后chain ReplyAction进行持久层操作
	 * 失败返回json数据
	 */
	@Action(value="/reply/replyCreat",
			results={@Result(name="topic",type="chain",location="topic_setLastTime",params={"time","time","topicId","topicId"})}
	)
	public String replyCreat(){
		result = new JSONObject();
		HttpSession session = request.getSession();
		user = (User)session.getAttribute("user");
		if(user==null){
			result.put("result", 2);
			return "json";
		}
		Topic topic = new Topic();
		topic.setTopicId(Integer.parseInt(topicId));
		replyNu = replyService.count(Integer.parseInt(topicId), -1);
		if(replyNu==null){
			result.put("result", 3);
			return "json";
		}
		Reply reply = new Reply();
		reply.setUser(user);
		time = new Date();
		reply.setTime(time);
		reply.setTopic(topic);
		reply.setFloor(replyNu+1);
		reply.setContent(replyContent);
		reply.setReplyState((byte)1);
		replyService.saveReply(reply);
		return "topic";
	}
	
	/*
	 * 根据topicId获取该帖子的分页回复
	 * 每页8条回复
	 * 返回每条回复的相关数据以及总回复数和最后回复时间
	 */
	@Action(value="/reply/getReply",results={@Result(name="success",location="../jsp/reply.jsp")})
	public String getReply() throws ParseException{
		Topic topic = new Topic();
		replyNu = replyService.count(Integer.parseInt(topicId), 1);
		topic.setTopicId(Integer.parseInt(topicId));
		int first = (Integer.parseInt(replyPage)-1)*8;
		replys = replyService.pageList(topic, first, 8, 1);
		for(Reply r:replys){
			r.setTimeString(StringUtil.date2DateSubtract(r.getTime()));
		}
		Reply reply = replyService.getReplyAboutLastTime(topic, 1);
		if(reply==null){
			lastTime = "--";
		}else{
			lastTime = StringUtil.date2DateSubtract(reply.getLastTime());
		}
		pages = new ArrayList<String>();
		pages.add(1+"");
		StringUtil.setPages(replyPage, (replyNu+7)/8+"", pages);
		request.setAttribute("pages", pages);
		request.setAttribute("page", replyPage);
		return SUCCESS;
	}
	
	/*
	 * 管理员查看所有回帖
	 */
	@Action(value="/reply/getAllReply",
			interceptorRefs={@InterceptorRef("forumInterceptor")},
			results={@Result(name="noLogin",type="chain",location="loginErrorAjax"),
					@Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax")
			})
	public String getAllReply() throws ParseException{
		result = new JSONObject();
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		replys = replyService.pageListForManage(Integer.parseInt(topicId),(Integer.parseInt(page)-1)*Integer.parseInt(rows),Integer.parseInt(rows),-1);
		Long rn = replyService.count(Integer.parseInt(topicId),-1);
		result.put("total", rn);
		JSONArray array = new JSONArray();
		Entity2JSONUtil.reply2JSON(replys, array);
		result.put("rows", array);
		return "json";
	}
	/*
	 * 获取帖子的回帖数以及最后回帖者
	 */
	@Actions(@Action(value="Reply_getLastReply",results={@Result(name="forum",location="jsp/forumTopics.jsp")}))
	public String getLastReply() throws ParseException{
		if(ts!=null){
			for(Topic t:ts){
				t.setLastTimeString(StringUtil.date2DateSubtract(t.getLastTime()));
				t.setReplyNu(replyService.count(t.getTopicId(), -1));
				Reply r = replyService.getReplyAboutLastReplyUser(t, 1);
				t.setLastReplyUser(new User());
				if(r == null){
					t.getLastReplyUser().setUserId(0);
					t.getLastReplyUser().setUserName("--");
				}else{
					t.getLastReplyUser().setUserId(r.getUserId());
					t.getLastReplyUser().setUserName(r.getUserName());
				}
			}
		}
		return "forum";
	}
	
	/*
	 * ajax删除数据
	 */
	@Action(value="/topic/delTopics",
			interceptorRefs={@InterceptorRef("forumInterceptor")},
			results={@Result(name="noLogin",type="chain",location="loginErrorAjax"),
					@Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax"),
					@Result(name="toTopic",type="chain",location="topic_delTopics",params={"result","result","topicId","topicId"})
			})
	public String delTopic(){
		result = new JSONObject();
		if(topicId != null){
			String[] topicIds = topicId.split(",");
			int rn = 0;
			for(String str:topicIds){
				rn+=replyService.deleteReply(Integer.parseInt(str));
			}
			result.put("ru", rn);
			return "toTopic";
		}else{
			result.put("rn", 0);
		}
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * ajax修改回帖
	 * 返回json
	 */
	@Action(value="/reply/editReply",
			interceptorRefs={@InterceptorRef("forumInterceptor")},
			results={@Result(name="noLogin",type="chain",location="loginErrorAjax"),
					@Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax")
			})
	public String editReply() throws Exception{
		result = new JSONObject();
		if(replyId!=null){
			replyService.updateReply(Long.parseLong(replyId), Byte.parseByte(replyState));
			result.put("result", 1);
		}else{
			result.put("result", 2);
		}
		return "json";
	}
	
	/*
	 * ajax删除数据
	 */
	@Action(value="/reply/delReply",
			interceptorRefs={@InterceptorRef("forumInterceptor")},
			results={@Result(name="noLogin",type="chain",location="loginErrorAjax"),
					@Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax")
			})
	public String delReply(){
		result = new JSONObject();
		if(replyId != null){
			result.put("nu", replyService.deleteReply(replyId));
		}else{
			result.put("nu", 0);
		}
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * 获取帖子的回帖数以及最后回帖者
	 */
	@Actions(@Action(value="Reply_getRepliesNu"))
	public String getRepliesNu() throws ParseException{
		if(ts!=null){
			for(Topic t:ts){
				t.setReplyNu(replyService.count(t.getTopicId(), -1));
			}
			JSONArray array = new JSONArray();
			Entity2JSONUtil.topic2JSON(ts, array);
			result.put("rows", array);
		}
		return "json";
	}
	
	public void setResult(JSONObject result) {
		this.result = result;
	}
	/*
	 * 获取帖子的回帖数以及最后回帖者
	 * 返回JSON数据
	 */
	@Actions(@Action(value="reply_getLastReplyReturnJson"))
	public String getLastReplyReturnJson() throws ParseException{
		result = new JSONObject();
		if(ts!=null){
			JSONArray ja = new JSONArray();
			for(Topic t:ts){
				JSONObject j = new JSONObject();
				j.put("lastTimeString", StringUtil.date2DateSubtract(t.getLastTime()));
				j.put("replyNu", replyService.count(t.getTopicId(), 1));
				Reply r = replyService.getReplyAboutLastReplyUser(t, 1);
				t.setLastReplyUser(new User());
				if(r == null){
					j.put("lastUserId", 0);
					j.put("lastUserName", "--");
				}else{
					j.put("lastUserId", r.getUserId());
					j.put("lastUserName", r.getUserName());
				}
				j.put("topicId", t.getTopicId());
				j.put("title", t.getTitle());
				j.put("forumId", t.getForumId());
				j.put("forumName", t.getForumName());
				ja.add(j);
			}
			result.put("data", ja);
			result.put("result", 1);
		}else{
			result.put("result", 1);
		}
		return "json";
	}
	
	
	/*
	 * 获取用户的回帖信息(回帖数以及最近的三次回帖信息)
	 * 以及用户发表的主题的回帖数以及最后回帖人
	 * 用户获取失败返回错误页面，
	 */
	@Action(value="Reply_getUserReply",results={@Result(name="error",location="jsp/error.jsp"),
			@Result(name="user",location="jsp/userInfo.jsp")})
	public String getUserReply() throws ParseException{
		if(user==null){
			error = "系统错误，获取失败";
			return "error";
		}
		if(ts != null){
			for(Topic t : ts){
				t.setLastTimeString(StringUtil.date2DateSubtract(t.getLastTime()));
				t.setReplyNu(replyService.count(t.getTopicId(), 1));
				Reply r = replyService.getReplyAboutLastReplyUser(t, 1);
				t.setLastReplyUser(new User());
				if(r == null){
					t.getLastReplyUser().setUserId(0);
					t.getLastReplyUser().setUserName("--");
				}else{
					t.getLastReplyUser().setUserId(r.getUserId());
					t.getLastReplyUser().setUserName(r.getUserName());
				}
			}
		}
		user.setRepliesNum(replyService.count(user, 1));
		replys = replyService.pageList(user, 0, 3, 1);
		for(Reply r:replys){
			r.setTimeString(StringUtil.date2DateSubtract(r.getTime()));
		}
		return "user";
	}
	/*
	 * 获取用户更多的回帖记录
	 * 每次最多返回3条
	 */
	@Action(value="/reply/reply_getMore")
	public String getMore() throws ParseException{
		result = new JSONObject();
		int first = Integer.parseInt(request.getParameter("first"));
		int userId = Integer.parseInt(request.getParameter("id"));
		if(first>=9){
			result.put("result", 2);
		}else{
			JSONArray ja = new JSONArray();
			user = new User(userId);
			replys = replyService.pageList(user, first, 3, 1);
			result.put("result", 1);
			for(Reply r : replys){
				JSONObject j = new JSONObject();
				j.put("replyId", r.getReplyId());
				j.put("content", r.getContent());
				j.put("timeString", StringUtil.date2DateSubtract(r.getTime()));
				j.put("topicId", r.getTopicId());
				j.put("topicTitle", r.getTopicTitle());
				j.put("userId", r.getUserId());
				j.put("userName", r.getUserName());
				ja.add(j);
			}
			result.put("data", ja);
		}
		
		return "json";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
}
