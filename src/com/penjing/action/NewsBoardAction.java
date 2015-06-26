package com.penjing.action;

import java.util.ArrayList;
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

import com.opensymphony.xwork2.ActionSupport;
import com.penjing.entity.NewsBoard;
import com.penjing.service.NewsBoardService;
import com.penjing.service.NewsService;
import com.penjing.util.Entity2JSONUtil;
@ResultPath("/")
@ParentPackage(value="crud-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"})
	})
public class NewsBoardAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private NewsBoardService newsBdService;
	@Resource
	private NewsService newsService;
	private String page;
	private String rows;
	private JSONObject result;
	private JSONArray array;
	private String newsBoardId;
	private String newsBoardName;
	private String newsBoardparent;
	private String newsBoardDescription;
	private String newsBoardStatus;
	
	private HttpServletRequest request;

	public JSONObject getResult() {
		return result;
	}

	public JSONArray getArray() {
		return array;
	}

	public void setArray(JSONArray array) {
		this.array = array;
	}

	public void setNewsBoardId(String newsBoardId) {
		this.newsBoardId = newsBoardId;
	}
	

	public void setNewsBoardStatus(String newsBoardStatus) {
		this.newsBoardStatus = newsBoardStatus;
	}

	public void setNewsBdService(NewsBoardService newsBdService) {
		this.newsBdService = newsBdService;
	}
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}
	
	public void setNewsBoardparent(String newsBoardparent) {
		this.newsBoardparent = newsBoardparent;
	}

	public void setNewsBoardDescription(String newsBoardDescription) {
		this.newsBoardDescription = newsBoardDescription;
	}

	public void setNewsBoardName(String newsBoardName) {
		this.newsBoardName = newsBoardName;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/*
	 * 得到所有新闻版块
	 */
	@Action(value="getAllNewsBd")
	public String getAllNewsBds(){
		result=new JSONObject();
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		List<NewsBoard> newsbds=newsBdService.getAllNewsBd(-1);
		List<NewsBoard> fynewsbds=getFenYeNewsBd(Integer.parseInt(page),Integer.parseInt(rows),newsbds);
		JSONArray array = getJSONNewsBd(fynewsbds);
		result.put("total",newsbds.size());
		result.put("rows", array);
		return "json";
	}

	/*
	 * 检查NewsBoard下面是否有子类，如果有子类不能进行删除
	 */
	@Action(value="/newsBd/delNewsBd")
	public String delNewsBdId(){
		result=new JSONObject();
		String[] newsBdIds=newsBoardId.split(",");
		List<NewsBoard> pnewsBd=newsBdService.getParentNewsBoard(-1);
		int num=0;
		for(String strid:newsBdIds){
			int k=0;
			for(NewsBoard pnbd:pnewsBd){
			if(Integer.valueOf(strid)==pnbd.getNewsBoardId()){
				int n=newsBdService.checkPnewsBd(Integer.valueOf(strid));
				if(n==1){
					k++;
					result.put("errormsg","不能删除id="+strid+"新闻版块，该版块有子类");
					return "json";
				}else{
					k++;
					newsBdService.delNewsBd(Integer.valueOf(strid));num++;
					//newsService.delNewsOfBd(Integer.valueOf(strid));
				}
			}
			}
			if(k==0){
				newsBdService.delNewsBd(Integer.valueOf(strid));num++;
				//newsService.delNewsOfBd(Integer.valueOf(strid));
			}
		}
		restoreNewsBd();
		result.put("msg","你已经成功删除了<font color=red>"+num+"</font>条数据");
		return "json";
	}
	
	/*
	 * 保存一个新的NewsBoard
	 */
	@Action(value="/newsBd/saveNewsBd")
	public String saveNewsBoard(){
		result = new JSONObject();
		NewsBoard newsBd=new NewsBoard();
		if(newsBoardName!=null){
			newsBd.setNewsBoardName(newsBoardName);
		}
		if(newsBoardparent.length()==0){
			newsBd.setNewsBoardparent(null);
		}else{
			if(Integer.parseInt(newsBoardparent)==0||newsBoardparent.equals("")){
				newsBd.setNewsBoardparent(null);
			}else{
				int newsBdId=Integer.parseInt(newsBoardparent);
				NewsBoard n=newsBdService.getNewsBoardId(newsBdId);
				newsBd.setNewsBoardparent(n);
			}
		}
		newsBd.setNewsBoardStatus((byte)Integer.parseInt(newsBoardStatus));
		newsBd.setNewsBoardDescription(newsBoardDescription);
		newsBdService.addNewsBoard(newsBd);
		restoreNewsBd();
		result.put("result",1);
		return "json";
	}
	
	@Action(value="/newsBd/pnewsBdName",results={@Result(name="array",type="json",params={"root","array"})})
	public String getPnewsBdNames(){
		array = new JSONArray();
		List<NewsBoard> newsBdNames=newsBdService.getParentNewsBoard(1);
		Entity2JSONUtil.NewsBoard2JSON1(newsBdNames, array,"parent");
		return "array";
	}
	@Action(value="/newsBd/snewsBdName",results={@Result(name="array",type="json",params={"root","array"})})
	public String getSnewsBdNames(){
		array = new JSONArray();
		List<NewsBoard> newsBdNames=newsBdService.getAllNewsBoard(1);
		Entity2JSONUtil.NewsBoard2JSON1(newsBdNames, array,"son");
		return "array";
	}
	
	/*
	 * 修改NewsBoard
	 */
	@Action(value="/newsBd/updateNewsBd")
	public String updateNewsBoard(){
		String mat="^[0-9]*$";
		result = new JSONObject();
		NewsBoard n=new NewsBoard();
		n.setNewsBoardId(Integer.parseInt(newsBoardId));
		n.setNewsBoardName(newsBoardName);
		if(newsBoardparent.matches(mat)){
			if(Integer.parseInt(newsBoardparent)==0){
				n.setNewsBoardparent(null);
			}else{
				NewsBoard p=newsBdService.getNewsBdById(newsBoardparent);
				n.setNewsBoardparent(p);
			}
		}else{
			if(newsBoardparent.equals("无")){
				n.setNewsBoardparent(null);
			}else{
				NewsBoard p=newsBdService.getNewsBdByName(newsBoardparent);
				n.setNewsBoardparent(p);
			}
		}
		
		n.setNewsBoardDescription(newsBoardDescription);
		n.setNewsBoardStatus((byte)Integer.parseInt(newsBoardStatus));
		newsBdService.updateNewsBoard(n);
		restoreNewsBd();
		result.put("result",1);
		return "json";
	}
	
	/*
	 * 将ArrayList<NewsBoard>转化为jsonArray
	 */
	public static JSONArray getJSONNewsBd(List<NewsBoard> newsbds){
		JSONArray array = new JSONArray();
		for(NewsBoard newbd:newsbds){
			JSONObject oj=new JSONObject();
			oj.put("newsBoardId",newbd.getNewsBoardId());
			oj.put("newsBoardName", newbd.getNewsBoardName());
			if(newbd.getNewsBoardparent()!=null){
				oj.put("newsBoardparent", newbd.getNewsBoardparent().getNewsBoardName());
			}else{
				oj.put("newsBoardparent", "无");
			}
			if(newbd.getNewsBoardStatus()==1){
				oj.put("newsBoardStatus", "正常");
			}else{
				oj.put("newsBoardStatus", "冻结");
			}
			if(newbd.getNewsBoardDescription()!=null){
				oj.put("newsBoardDescription", newbd.getNewsBoardDescription());
			}
			array.add(oj);
		}
		return array;
	}
	/*
	 * 采用假分页方式将NewsBoard分页
	 */
	public List<NewsBoard> getFenYeNewsBd(int page,int rows,List<NewsBoard> nbds){
		List<NewsBoard> nbd=new ArrayList<NewsBoard>();
		int pageSize=0;
		if(page==1){
			if(nbds.size()>rows){
				pageSize=rows;
			}else{
				pageSize=nbds.size();
			}
			for(int k=0;k<pageSize;k++){
				nbd.add(nbds.get(k));
			}
		}else{
			for(int k=(page-1)*rows;k<=page*rows-1;k++){
				if(k==nbds.size()){
					break;
				}
				nbd.add(nbds.get(k));
			}
		}
		return nbd;
	}
	

	
	public void restoreNewsBd(){
		request.getServletContext().removeAttribute("pNewsBd");
		request.getServletContext().removeAttribute("sNewsBd");
		List<NewsBoard> pNewsBd=newsBdService.getParentNewsBoard(1);
		List<NewsBoard> sNewsBd=newsBdService.getSonNewsBoard(1);
		request.getServletContext().setAttribute("pNewsBd", pNewsBd);
		request.getServletContext().setAttribute("sNewsBd", sNewsBd);
	}
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		this.request=req;
	}	
}
