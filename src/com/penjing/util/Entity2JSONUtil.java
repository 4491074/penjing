package com.penjing.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.penjing.entity.Forum;
import com.penjing.entity.News;
import com.penjing.entity.NewsBoard;
import com.penjing.entity.Order;
import com.penjing.entity.PenJing;
import com.penjing.entity.PenJingList;
import com.penjing.entity.PenJingPicture;
import com.penjing.entity.PenJingType;
import com.penjing.entity.RemarkInfo;
import com.penjing.entity.Reply;
import com.penjing.entity.Role;
import com.penjing.entity.Topic;
import com.penjing.entity.User;
import com.penjing.service.PenJingPictureService;
import com.penjing.service.PenJingTypeService;

public class Entity2JSONUtil {
//	@Resource  
//	private PenJingPictureService penJingPictureService;
//	
//	private static Entity2JSONUtil entity2JSONUtil;
//
//	@PostConstruct 
//	public void init() {  
//		entity2JSONUtil = this;  
//		entity2JSONUtil.penJingPictureService = this.penJingPictureService;
//	} 

	public static void object2JSON(Object[] object,String[] str,JSONObject jsonObject){
		for(int i=0;i<str.length;i++){
			jsonObject.put(str[i], object[i]);
		}
	}
	
	public static String returnTrueOrFalse(Boolean b){
		String r = "不允许";
		if(b == true)
			r = "允许";
		return r;
	}
	/*
	 * 将角色集合封装进JSONArray
	 */
	public static void role2JSON(List<Role> roles,JSONArray array){
		for(Role r:roles){
			JSONObject jo = new JSONObject();
			if((Integer)r.getRoleId() == null || (Byte)r.getRoleStatus() == null){
				continue;
			}else{
				jo.put("roleId", r.getRoleId());
				if(r.getRoleStatus()==1){
					jo.put("roleStatus", "正常");
				}else {
					jo.put("roleStatus", "冻结");
				}
			}
			if(r.getRoleName()!=null){
				jo.put("roleName", r.getRoleName());
			}
			if(r.getRoleDescription() == null){
				jo.put("roleDescription", "");
			}else{
				jo.put("roleDescription", r.getRoleDescription());
			}
			if(r.getPublishPenJing()!=null){
				jo.put("publishPenJing", returnTrueOrFalse(r.getPublishPenJing()));
			}
			if(r.getAuditPenJing()!=null){
				jo.put("auditPenJing", returnTrueOrFalse(r.getAuditPenJing()));			
						}
			if(r.getManagePenJing()!=null){
				jo.put("managePenJing", returnTrueOrFalse(r.getManagePenJing()));
			}
			if(r.getManageOrder()!=null){
				jo.put("manageOrder", returnTrueOrFalse(r.getManageOrder()));
			}
			if(r.getPublishNews()!=null){
				jo.put("publishNews", returnTrueOrFalse(r.getPublishNews()));
			}
			if(r.getAuditNews()!=null){
				jo.put("auditNews", returnTrueOrFalse(r.getAuditNews()));
			}
			if(r.getManageNews()!=null){
				jo.put("manageNews", returnTrueOrFalse(r.getManageNews()));
			}
			if(r.getCreatTopic()!=null){
				jo.put("creatTopic", returnTrueOrFalse(r.getCreatTopic()));
			}
			if(r.getManageForum()!=null){
				jo.put("manageForum", returnTrueOrFalse(r.getManageForum()));
			}
			if(r.getManageRole()!=null){
				jo.put("manageRole", returnTrueOrFalse(r.getManageRole()));
			}
			if(r.getManageUser()!=null){
				jo.put("manageUser", returnTrueOrFalse(r.getManageUser()));
			}
			if(r.getManageInfo()!=null){
				jo.put("manageInfo", returnTrueOrFalse(r.getManageInfo()));
			}
			
			array.add(jo);
		}
	}
	
	/*
	 * 将角色集合封装进JSONArray
	 */
	public static void role2JSON1(List<Role> roles,JSONArray array){
		for(Role r:roles){
			JSONObject jo = new JSONObject();
			if((Integer)r.getRoleId() == null || (Byte)r.getRoleStatus() == null){
				continue;
			}else{
				jo.put("id", r.getRoleId());
				if(r.getRoleStatus() == 1){
					jo.put("group", "正常状态的角色");
				}else{
					jo.put("group", "冻结的角色，限制使用");
				}
			}
			if(r.getRoleName()!=null){
				jo.put("text", r.getRoleName());
			}
			array.add(jo);
		}
	}
	
	/*
	 * 将盆景类型集合封装进JSONArray
	 */
	public static void penjingType2JSON1(List<PenJingType> penjignTypes,JSONArray array){
		for(PenJingType p:penjignTypes){
			JSONObject jo = new JSONObject();
			if((Integer)p.getPenJingTypeId() == null || (Byte)p.getPenJingTypeStatus() == null){
				continue;
			}else{
				jo.put("id", p.getPenJingTypeId());
				if(p.getPenJingTypeStatus() == 1){
					jo.put("group", "正常使用的盆景类型");
				}else{
					jo.put("group", "冻结的盆景类型");
				}
			}
			if(p.getPenJingTypeName()!=null){
				jo.put("text", p.getPenJingTypeName());
			}
			array.add(jo);
		}
	}
	/*
	 * 将父类新闻版块信息封装进json
	 */
	public static void NewsBoard2JSON1(List<NewsBoard> pnewsbd,JSONArray array,String str){
		JSONObject j = new JSONObject();
		if(str.equals("parent")){
			j.put("id",0);
			j.put("group", "正常状态的父类版块");
			j.put("text", "无");
		}
		array.add(j);
		for(NewsBoard n:pnewsbd){
			JSONObject jo = new JSONObject();
			if((Integer)n.getNewsBoardId() == null || (Byte)n.getNewsBoardStatus() == null){
				continue;
			}else{
				jo.put("id",n.getNewsBoardId());
				if(n.getNewsBoardStatus() == 1){
					if(str.equals("parent")){
						jo.put("group", "正常状态的父类版块");
					}
					if(str.equals("son")){
						if(n.getNewsBoardparent()!=null){
							jo.put("group", n.getNewsBoardparent().getNewsBoardName());
						}else{
							continue;
						}
					}
				}
				if(n.getNewsBoardName()!=null){
					jo.put("text", n.getNewsBoardName());
				}
				
				array.add(jo);
			}
		}
	}
	
	
	/*
	 * 将用户信息封装进json
	 */
	public static void user2JSON(List<User> user,JSONArray array) throws ParseException{
		for(User u:user){
			JSONObject jo = new JSONObject();
			if((Integer)u.getUserId() == null || (Byte)u.getUserStatus() == null || (Integer)u.getRoleId() == null){
				continue;
			}else{
				jo.put("userId", u.getUserId());
				jo.put("roleId", u.getRoleId());
				if(u.getUserStatus()==1){
					jo.put("userStatus", "正常");
				}else {
					jo.put("userStatus", "冻结");
				}
			}
			if(u.getUserName() != null){
				jo.put("userName", u.getUserName());
			}
			if(u.getMail() != null){
				jo.put("mail", u.getMail());
			}
			if(u.getPhone() != null){
				jo.put("phone", u.getPhone());
			}
			if(u.getEnrollTime() != null){
				jo.put("enrollTime", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(u.getEnrollTime()));
			}
			 
			if(u.getLastTime() != null){
				jo.put("lastTime", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(u.getLastTime()));
			}
			if(u.getUserDescription() != null){
				jo.put("userDescription", u.getUserDescription());
			}
			if(u.getRoleName() != null){
				jo.put("roleName", u.getRoleName());
			}
			array.add(jo);
		}
	}
	
	/*
	 * 将用户信息封装进json
	 */
	public static void myInfo2JSON(User u,JSONArray array) throws ParseException{
			JSONObject jo = new JSONObject();
			if((Integer)u.getUserId() == null){
				return;
			}else{
				jo.put("userId", u.getUserId());
			}
			if(u.getUserName() != null){
				jo.put("userName", u.getUserName());
			}
			if(u.getPhoto() != null){
				jo.put("photo", u.getPhoto());
			}
			if(u.getMail() != null){
				jo.put("mail", u.getMail());
			}
			if(u.getPhone() != null){
				jo.put("phone", u.getPhone());
			}
			if(u.getEnrollTime() != null){
				jo.put("enrollTime", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(u.getEnrollTime()));
			}
			 
			if(u.getUserDescription() != null){
				jo.put("userDescription", u.getUserDescription());
			}
			if(u.getRoleName() != null){
				jo.put("roleName", u.getRoleName());
			}
			array.add(jo);
	}
	/*
	 * 把新闻封装成json数组
	 */
	public static void News2Json(List<News> news,JSONArray jsonArray){
		for(News n:news){
			JSONObject obj=new JSONObject();
			obj.put("newsId", n.getNewsId());
			obj.put("newsTitle", n.getNewsTitle());
			obj.put("newsContent", n.getNewsContent());
			obj.put("readNu", n.getReadNu());
			obj.put("newsPublisher", n.getNewsPublisher().getUserName());
			if(n.getNewsAssessor()==null){
				obj.put("newsAssessor", "空");
			}else{
				obj.put("newsAssessor", n.getNewsAssessor().getUserName());
			}
			String[] times=String.valueOf(n.getPublishTime()).split(" ");
			obj.put("publishTime", times[0]);
			obj.put("newsBoard", n.getNewsBoard().getNewsBoardName());
			if(n.getNewsStatus() == (byte)1){
				obj.put("newsStatus", "审核通过");
			}
			if(n.getNewsStatus() == (byte)0){
				obj.put("newsStatus", "未审核");
			}
			if(n.getNewsStatus() == (byte)2){
				obj.put("newsStatus", "审核未通过");
			}
			obj.put("remark", n.getRemark());
			jsonArray.add(obj);
		}
	}
	
	/*
	 * 将主题帖信息封装进json
	 */
	public static void topic2JSON(List<Topic> topics,JSONArray array) throws ParseException{
		for(Topic t:topics){
			JSONObject jo = new JSONObject();
			if((Integer)t.getTopicId() == null || (Byte)t.getTopicState() == null || (Integer)t.getUserId() == null){
				continue;
			}else{
				jo.put("topicId", t.getTopicId());
				jo.put("userId", t.getUserId());
				if(t.getTopicState()==1){
					jo.put("topicState", "正常");
				}else {
					jo.put("topicState", "冻结");
				}
			}
			if(t.getUserName() != null){
				jo.put("userName", t.getUserName());
			}
			if((Long)t.getReadNu() != null){
				jo.put("readNu", t.getReadNu());
			}
			if(t.getTitle() != null){
				jo.put("title", t.getTitle());
			}
			if((Byte)t.getIsTop() != null && t.getIsTop() == 1){
				jo.put("isTop", "置顶");
				if(t.getTopTime() != null){
					jo.put("topTime", new SimpleDateFormat("yyyy/MM/dd HH:mm").format(t.getTopTime()));
				}
			}else{
				jo.put("isTop", "");
				jo.put("topTime", "");
			}
			if(t.getTime() != null){
				jo.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(t.getTime()));
			}
			
			if((Long)t.getReplyNu() != null){
				jo.put("replyNu", t.getReplyNu());
			}
			array.add(jo);
		}
	}
	/*
	 * 将我的盆景信息封装进json
	 */
	public static void myPenjing2JSON(List<PenJing> penjings,JSONArray array,PenJingPictureService penJingPictureService){
		for(PenJing p:penjings){
			JSONObject jo = new JSONObject();
			if((Integer)p.getPenJingId() == null || (Byte)p.getPenJingStatus() == null || (Integer)p.getPenJingTypeId() == null){
				continue;
			}else{
				jo.put("penJingId", p.getPenJingId());
				jo.put("penJingTypeId", p.getPenJingTypeId());
				if(p.getPenJingStatus()==0){
					jo.put("penJingStatus", "未审核");
				}else if(p.getPenJingStatus()==1){
					jo.put("penJingStatus", "通过审核");
				}else{
					jo.put("penJingStatus", "未通过审核");
				}
			}
			if(p.getPenJingName() != null){
				jo.put("penJingName", p.getPenJingName());
			}
			if(p.getPenJingTitle() != null){
				jo.put("penJingTitle", p.getPenJingTitle());
			}
			if(p.getPenJingDescription() != null){
				jo.put("penJingDescription", p.getPenJingDescription());
			}
			if(p.getPublishTime() != null){
				jo.put("publishTime", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(p.getPublishTime()));
			}
			if(p.getRemark() != null){
				jo.put("remark", p.getRemark());
			}
			if(p.getPenJingTypeName() != null){
				jo.put("penJingTypeName", p.getPenJingTypeName());
			}
			List<PenJingPicture> penjingPictures = penJingPictureService.getAllPenJingPicture(-1, p.getPenJingId());
			JSONArray a = new JSONArray();
			for(PenJingPicture pjp:penjingPictures){
				JSONObject j = new JSONObject();
				if(pjp.getPictureUrl() != null){
					j.put("pictureUrl", pjp.getPictureUrl());
				}
				if((Byte)pjp.getPictureStatus() != null){
					j.put("pictureStatus", pjp.getPictureStatus());
				}
				if((Long)pjp.getPenJingPictureId() != null){
					j.put("penJingPictureId", pjp.getPenJingPictureId());
				}
				a.add(j);
			}
			jo.put("pictures", a);
			array.add(jo);
		}
	}
	
	/*
	 * 将盆景信息封装进json
	 */
	public static void penjing2JSON(List<PenJing> penjings,JSONArray array,PenJingPictureService penJingPictureService) throws ParseException{
		for(PenJing p:penjings){
			JSONObject jo = new JSONObject();
			if((Integer)p.getPenJingId() == null || (Byte)p.getPenJingStatus() == null){
				continue;
			}else{
				jo.put("penJingId", p.getPenJingId());
				if(p.getPenJingStatus()==0){
					jo.put("penJingStatus", "未审核");
				}else if(p.getPenJingStatus()==1){
					jo.put("penJingStatus", "通过审核");
				}else{
					jo.put("penJingStatus", "未通过审核");
				}
			}
			if(p.getPenJingName() != null){
				jo.put("penJingName", p.getPenJingName());
			}
			if(p.getPenJingTitle() != null){
				jo.put("penJingTitle", p.getPenJingTitle());
			}
			if(p.getPenJingDescription() != null){
				jo.put("penJingDescription", p.getPenJingDescription());
			}
			if(p.getPublishTime() != null){
				jo.put("publishTime", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(p.getPublishTime()));
			}
			if(p.getMainPicture() != null){
				jo.put("mainPicture", p.getMainPicture());
			}
			if(p.getRemark() != null){
				jo.put("remark", p.getRemark());
			}
			if(p.getPenJingTypeName() != null){
				jo.put("penJingTypeName", p.getPenJingTypeName());
			}
			if(p.getPublisherName() != null){
				jo.put("publisherName", p.getPublisherName());
			}
			List<PenJingPicture> penjingPictures = penJingPictureService.getAllPenJingPicture(-1, p.getPenJingId());
			JSONArray a = new JSONArray();
			for(PenJingPicture pjp:penjingPictures){
				JSONObject j = new JSONObject();
				if(pjp.getPictureUrl() != null){
					j.put("pictureUrl", pjp.getPictureUrl());
				}
				if((Byte)pjp.getPictureStatus() != null){
					j.put("pictureStatus", pjp.getPictureStatus());
				}
				if((Long)pjp.getPenJingPictureId() != null){
					j.put("penJingPictureId", pjp.getPenJingPictureId());
				}
				a.add(j);
			}
			jo.put("pictures", a);
			array.add(jo);
		}
	}
	
	/*
	 * 获取更多盆景
	 */
	public static void morePenjing2JSON(List<PenJing> penjings,JSONArray array){
		for(PenJing p:penjings){
			JSONObject jo = new JSONObject();
			if((Integer)p.getPenJingId() == null){
				continue;
			}else{
				jo.put("penJingId", p.getPenJingId());
			}
			if(p.getPenJingName() != null){
				jo.put("penJingName", p.getPenJingName());
			}
			if(p.getMainPicture() != null){
				jo.put("mainPicture", p.getMainPicture());
			}
			array.add(jo);
		}
	}
	
	/*
	 * 将订单详情信息封装进json
	 */
	public static void penjingList2JSON(List<PenJingList> penjingLists,JSONArray array) throws ParseException{
		for(PenJingList pjl:penjingLists){
			JSONObject jo = new JSONObject();
			if((Long)pjl.getPenJingListId() == null || (Byte)pjl.getPenJingStatus() == null || (Integer)pjl.getPenJingId() == null){
				continue;
			}else{
				jo.put("penJingListId", pjl.getPenJingListId());
				jo.put("penJingId", pjl.getPenJingId());
				if(pjl.getPenJingStatus()==0){
					jo.put("penJingStatus", "未审核");
				}else if(pjl.getPenJingStatus()==1){
					jo.put("penJingStatus", "通过审核");
				}else{
					jo.put("penJingStatus", "未通过审核");
				}
			}
			if(pjl.getPenJingName() != null){
				jo.put("penJingName", pjl.getPenJingName());
			}
			if(pjl.getPenJingTitle() != null){
				jo.put("penJingTitle", pjl.getPenJingTitle());
			}
			if(pjl.getMainPicture() != null){
				jo.put("mainPicture", pjl.getMainPicture());
			}
			if(pjl.getPenJingTypeName() != null){
				jo.put("penJingTypeName", pjl.getPenJingTypeName());
			}
			if(pjl.getPublisherName() != null){
				jo.put("publisherName", pjl.getPublisherName());
			}
			if((Integer)pjl.getCount() != null){
				jo.put("count", pjl.getCount());
			}
			if(pjl.getListNote() != null){
				jo.put("listNote", pjl.getListNote());
			}
			array.add(jo);
		}
	}
	
	/*
	 * 将盆景信息封装进json
	 * 订单查询
	 */
	public static void penjing2JSONForOrder(PenJing penjing,JSONObject jo){
			if((Integer)penjing.getPenJingId() == null || (Byte)penjing.getPenJingStatus() == null){
				return;
			}else{
				jo.put("penJingId", penjing.getPenJingId());
				if(penjing.getPenJingStatus()==0){
					jo.put("penJingStatus", "未审核");
				}else if(penjing.getPenJingStatus()==1){
					jo.put("penJingStatus", "通过审核");
				}else{
					jo.put("penJingStatus", "未通过审核");
				}
			}
			if(penjing.getPenJingName() != null){
				jo.put("penJingName", penjing.getPenJingName());
			}
			if(penjing.getPenJingTitle() != null){
				jo.put("penJingTitle", penjing.getPenJingTitle());
			}
			if(penjing.getMainPicture() != null){
				jo.put("mainPicture", penjing.getMainPicture());
			}
			if(penjing.getRemark() != null){
				jo.put("remark", penjing.getRemark());
			}
	}
	
	/*
	 * 将盆景信息封装进json
	 * 订单查询
	 */
	public static void penjingList2JSONForOrder(PenJingList penjingList,JSONObject jo){
			if((Integer)penjingList.getPenJingId() == null || (Byte)penjingList.getPenJingStatus() == null){
				return;
			}else{
				jo.put("penJingId", penjingList.getPenJingId());
				if(penjingList.getPenJingStatus()==0){
					jo.put("penJingStatus", "未审核");
				}else if(penjingList.getPenJingStatus()==1){
					jo.put("penJingStatus", "通过审核");
				}else{
					jo.put("penJingStatus", "未通过审核");
				}
			}
			if(penjingList.getPenJingName() != null){
				jo.put("penJingName", penjingList.getPenJingName());
			}
			if(penjingList.getPenJingTitle() != null){
				jo.put("penJingTitle", penjingList.getPenJingTitle());
			}
			if(penjingList.getMainPicture() != null){
				jo.put("mainPicture", penjingList.getMainPicture());
			}if((Long)penjingList.getPenJingListId() != null){
				jo.put("penJingListId", penjingList.getPenJingListId());
			}
			if((Integer)penjingList.getCount() != null){
				jo.put("count", penjingList.getCount());
			}
			if(penjingList.getListNote() != null){
				jo.put("listNote", penjingList.getListNote());
			}
	}
	
	/*
	 * 将订单信息封装进json
	 */
	public static void order2JSON(List<Order> orders,JSONArray array) throws ParseException{
		for(Order o:orders){
			JSONObject jo = new JSONObject();
			if((Long)o.getOrderId() == null || (Byte)o.getOrderState() == null){
				continue;
			}else{
				jo.put("orderId", o.getOrderId());
				if(o.getOrderState()==0){
					jo.put("orderState", "其他");
				}else if(o.getOrderState()==1){
					jo.put("orderState", "交易中");
				}else if(o.getOrderState()==2){
					jo.put("orderState", "交易完成");
				}else if(o.getOrderState()==3){
					jo.put("orderState", "交易取消");
				}
			}
			if(o.getCustomerName() != null){
				jo.put("customerName", o.getCustomerName());
			}
			if(o.getCustomerPhone() != null){
				jo.put("customerPhone", o.getCustomerPhone());
			}
			if(o.getCustomerAdd() != null){
				jo.put("customerAdd", o.getCustomerAdd());
			}
			if(o.getTime() != null){
				jo.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(o.getTime()));
			}
			if(o.getDistributionTime() != null){
				jo.put("distributionTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(o.getDistributionTime()));
			}
			if(o.getNote() != null){
				jo.put("note", o.getNote());
			}
			array.add(jo);
		}
	}
	

	/*
	 * 将回帖帖信息封装进json
	 */
	public static void reply2JSON(List<Reply> replys,JSONArray array) throws ParseException{
		for(Reply r:replys){
			JSONObject jo = new JSONObject();
			if((Long)r.getReplyId() == null || (Byte)r.getReplyState() == null || (Integer)r.getUserId() == null){
				continue;
			}else{
				jo.put("replyId", r.getReplyId());
				jo.put("userId", r.getUserId());
				if(r.getReplyState()==1){
					jo.put("replyState", "正常");
				}else {
					jo.put("replyState", "冻结");
				}
			}
			if(r.getFloor() != null){
				jo.put("floor", r.getFloor());
			}
			if(r.getContent() != null){
				jo.put("content", r.getContent());
			}
			if(r.getUserName() != null){
				jo.put("userName", r.getUserName());
			}
			if(r.getTime() != null){
				jo.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(r.getTime()));
			}
			array.add(jo);
		}
	}
	
	
	/*
	 * 将论坛装换为json
	 */
	public static void forum2JSON(List<Forum> forum,JSONArray array){
			for(Forum f:forum){
				JSONObject obj = new JSONObject();
				obj.put("forumId", f.getForumId());
				obj.put("forumName", f.getForumName());
				obj.put("notice", f.getNotice());
				obj.put("pageNu", f.getPageNu());
				if(f.getForumStatus()==(byte)0)
					obj.put("forumStatus","冻结");
				if(f.getForumStatus()==(byte)1)
					obj.put("forumStatus","正常");
				array.add(obj);
			}
	}
	
	
	/*
	 * 将盆景类型封装成json
	 */
	public static void PJType2JSON(List<PenJingType> pjType,JSONArray array,PenJingTypeService penJingTypeService){
		for(PenJingType p:pjType){
			JSONObject obj=new JSONObject();
			obj.put("penJingTypeId", p.getPenJingTypeId());
			obj.put("penJingTypeName", p.getPenJingTypeName());
			Long num=penJingTypeService.countPenJing(p.getPenJingTypeId());
			obj.put("penJingNum", num);
			obj.put("penJingTypeDescription", p.getPenJingTypeDescription());
			if(p.getPenJingTypeStatus()==(byte)0){
				obj.put("penJingTypeStatus", "冻结");
			}
			if(p.getPenJingTypeStatus()==(byte)1){
				obj.put("penJingTypeStatus", "正常");
			}
			array.add(obj);
		}	
	}
	
	/*
	 * 将备注信息转换为json
	 */
	public static void RemarkInfo2JSON(List<RemarkInfo> reinfo,JSONArray array){
		for(RemarkInfo f:reinfo){
			JSONObject obj=new JSONObject();
			obj.put("id", f.getId());
			obj.put("managerName", f.getManagerName());
			obj.put("managerTel", f.getManagerTel());
			obj.put("address", f.getAddress());
			obj.put("visitNum", f.getVisitNum());
			array.add(obj);
		}
	}
	
}
