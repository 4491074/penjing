package com.penjing.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;


import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.penjing.entity.News;
import com.penjing.entity.NewsBoard;
import com.penjing.entity.User;
import com.penjing.service.NewsBoardService;
import com.penjing.service.NewsService;
import com.penjing.util.Entity2JSONUtil;
@ResultPath("/")
@ParentPackage(value="crud-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"}),
	  @Result(name="noLogin",type="chain",location="loginErrorAjax"),
	  @Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax")
	})
public class NewssAction extends ActionSupport implements RequestAware,ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private NewsService newsService;
	@Resource
	private NewsBoardService newsBdService;
	private int newsId;//新闻Id；
	private int newsBdId;
	private List<News> news=new ArrayList<News>();//用于保存newsBdID下面的news
	private Map<String, Object> request;
	private JSONObject result;
	
	//新闻分页
	private int size;
	private List lss;
	private String page;
	private String rows;
	private HttpServletRequest req;
	
	private String newsTitle;
	private String newsBoard;
	private String newsContent;
	private String remark;
	private String newsIds;
	private String newsStatus;
	

	
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public void setNewsBoard(String newsBoard) {
		this.newsBoard = newsBoard;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	
	public void setNewsStatus(String newsStatus) {
		this.newsStatus = newsStatus;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public void setRows(String rows) {
		this.rows = rows;
	}

	public void setNewsIds(String newsIds) {
		this.newsIds = newsIds;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	public JSONObject getResult() {
		return result;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	
	public void setNewsBdService(NewsBoardService newsBdService) {
		this.newsBdService = newsBdService;
	}
	public void setNewsBdId(int newsBdId) {
		this.newsBdId = newsBdId;
	}

	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request=arg0;
	}
	
	
	
	/*
	 * 通过id得到新闻
	 */
	@Action(value="getNewsById",results={@Result(name="success", location="jsp/news.jsp")})
	public String getNewsById(){
		PrintWriter out=null;
		try {
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		News news=newsService.getNewsById(newsId);
		String val=news.getNewsTitle()+","+news.getNewsContent()+","+news.getPublishTime().toString()
				+","+String.valueOf(news.getReadNu());
		newsService.updateReadNu(newsId);
		out.write(val);
		out.flush();
		return null;
	}
	/*
	 * 通过新闻版块，得到新闻
	 */
	@Action(value="getNewsByNewsBdId",results={@Result(name="success", location="jsp/news.jsp")})
	public String getNewsByNewsBdId(){
		news= newsService.getNewsByBdId(newsBdId);
		String newBdName=newsBdService.getNewsBdNameById(newsBdId);			//得到当前新闻版块名字
		request.put("newsBdId", newsBdId);
		request.put("newBdName", newBdName);
		size=news.size();
		int pageNo=1;
		int pageBegin=0;
		int pageSize=12;
		int lastPageNo=1;
		if(size%pageSize==0){
			lastPageNo=size/pageSize;
		}else{
			lastPageNo=size/pageSize+1;
		}
		request.put("lastPageNo", lastPageNo);
		if(page!=null&&Integer.parseInt(page)>1){
			if(Integer.parseInt(page)>lastPageNo){
				pageNo=lastPageNo;
			}else{
				pageNo=Integer.parseInt(page);
			}
			pageBegin=(pageNo-1)*pageSize;
		}else{
			pageNo=1;
		}
		request.put("pageNo", pageNo);
		List ls=getNewsByPageNo(pageBegin, pageSize);
		request.put("newsList", ls);
		return "success";
	}
	
	/*
	 * 通过 pageBegin,pageSize两个参数得到，当页展示的新闻
	 */
	public List<News> getNewsByPageNo(int pageBegin,int pageSize){
		List<News> ls=new ArrayList<News>();
		for(int n=pageBegin;n<pageSize+pageBegin;n++){
		if(n<size){
			News ne=(News)news.get(n);
			ls.add(ne);
		}else{
			break;
		}
		}
		return ls;
	}
	/*
	 * 得到我的新闻
	 */
	@Action(value="/news/getMyNews",results={@Result(name="fail", location="jsp/error.jsp")})
	public String gainMyNews(){
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		result=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		User user=(User) req.getSession().getAttribute("user");
		if(user==null){
			return "fail";
		}else{
			List<News> news=newsService.getMyNews((Integer.parseInt(page)-1)*Integer.parseInt(rows),Integer.parseInt(rows),user.getUserId());
			Entity2JSONUtil.News2Json(news, jsonArray);
			result.put("total",newsService.count(-1, String.valueOf(user.getUserId())));
			result.put("rows",jsonArray);
			return "json";
		}
	}
	/*
	 * 发布一个新的新闻
	 */
	@Action(value="/news/publishNews")
	public String publishNews(){
		result=new JSONObject();
		News news=new News();
		news.setNewsTitle(newsTitle);
		news.setNewsContent(newsContent);
		news.setReadNu((long)0);
		User user=(User) req.getSession().getAttribute("user");
		news.setNewsPublisher(user);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date publishTime=null;
		try {
			publishTime = df.parse(df.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		news.setPublishTime(publishTime);
		NewsBoard newsBd=newsBdService.getNewsBdById(newsBoard);
		news.setNewsBoard(newsBd);
		news.setNewsStatus((byte)0);
		news.setRemark(remark);
		newsService.save(news);
		result.put("result",1);
		return "json";
	}
	
	
	/*
	 * 删除新闻
	 */
	
	@Action(value="/news/deleteNews")
	public String delNews(){
		result=new JSONObject();
		String[] ids=newsIds.split(",");
		for(String id:ids){
			newsService.deleteNews(Integer.parseInt(id));
		}
		result.put("msg","你已经成功删除了<font color=red>"+ids.length+"</font>条数据");
		return "json";
	}
	
	/*
	 * 新闻批量审核
	 */
	@Action(value="/news/checkNews")
	public String checkNews(){
		result=new JSONObject();
		String[] ids=newsIds.split(",");
		for(String id:ids){
			News n=newsService.getNewsById(Integer.parseInt(id));
			if(newsStatus.equals("1")){
				n.setNewsStatus((byte)1);
			}
			if(newsStatus.equals("2")){
				n.setNewsStatus((byte)2);
			}
			User user=(User) req.getSession().getAttribute("user");
			if(user!=null)
				n.setNewsAssessor(user);
			newsService.updateNews(n);
		}
		result.put("msg","你已经成功审核了<font color=red>"+ids.length+"</font>条新闻");
		return "json";
	}
	
	
	/*
	 * 修改我的新闻
	 */
	@Action(value="/news/updateMyNews")
	public String updateMyNews(){
		String mat="^[0-9]*$";NewsBoard snbd=null;
		result=new JSONObject();
		News n=newsService.getNewsById(newsId);
		n.setNewsTitle(newsTitle);
		if(newsBoard.matches(mat)){
			snbd=newsBdService.getNewsBdById(newsBoard);
		}else{
			snbd=newsBdService.getNewsBdByName(newsBoard);
		}
		n.setNewsBoard(snbd);
		n.setNewsContent(newsContent);
		n.setRemark(remark);
		newsService.updateNews(n);
		result.put("result",1);
		return "json";
	}
	
	/*
	 * 得到所有新闻，返回管理页面
	 */
	@Action(value="/news/gainAllNews")
	public String gainAllNews(){
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		result=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		List<News> news=newsService.getAllNews((Integer.parseInt(page)-1)*Integer.parseInt(rows),Integer.parseInt(rows),-1);
		Entity2JSONUtil.News2Json(news, jsonArray);
		result.put("total",newsService.count(-1, null));
		result.put("rows",jsonArray);
		return "json";
	}
	
	/*
	 * 得到未审核的新闻
	 */
	@Action(value="/news/gainUnCheckNews")
	public String getUnCheckNews(){
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		result=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		List<News> news=newsService.getAllNews((Integer.parseInt(page)-1)*Integer.parseInt(rows),Integer.parseInt(rows),0);
		Entity2JSONUtil.News2Json(news, jsonArray);
		result.put("total",newsService.count(0, null));
		result.put("rows",jsonArray);
		return "json";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		this.req=req;
	}
	
}
