package com.penjing.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.penjing.entity.Role;
import com.penjing.entity.User;
import com.penjing.service.UserService;
import com.penjing.util.Entity2JSONUtil;
import com.penjing.util.MD5Util;
import com.penjing.util.StringUtil;
@ResultPath("/")
@ParentPackage(value="crud-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"})
	})
public class UserAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	@Resource
	private UserService userService;
	private User user;
	private String value;
	private JSONObject result;
	private int userId;
	private String error;
	private String roleId;
	private String userStatus;
	private Role role;
	private String page;
	private String rows;
	private String from;
	private File photo;
	private String photoFileName;
	private String photoContentType;
	
	private String userName;
	private String oldPwd;
	private String newPwd;
	
	
	public void setPage(String page) {
		this.page = page;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
	
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public String getError() {
		return error;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public JSONObject getResult() {
		return result;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	/*
	 * 注册时验证用户名是否存在
	 */
	@Actions({
		@Action(value="/user/check_userName")
	})
	public String checkUsrName(){
		result = new JSONObject();
		user = userService.findUserByValue("userName", value);
		if(user == null){
			result.put("result", 1);
		}else{
			result.put("result", 2);
		}
		return "json";
	}
	/*
	 * 注册时验证邮箱是否存在
	 */
	@Actions({
		@Action(value="/user/check_mail")
	})
	public String checkMail(){
		result = new JSONObject();
		user = userService.findUserByValue("mail", value);
		if(user == null){
			result.put("result", 1);
		}else{
			result.put("result", 2);
		}
		return "json";
	}
	/*
	 * 注册时验证电话是否存在
	 */
	@Actions({
		@Action(value="/user/check_phone"
		)
	})
	public String checkPhone(){
		result = new JSONObject();
		user = userService.findUserByValue("phone", value);
		if(user == null){
			result.put("result", 1);
		}else{
			result.put("result", 2);
		}
		return "json";
	}
	/*
	 * 注册用户
	 */
	@Actions({
		@Action(value="/user/enroll")
	})
	public String enroll() throws InterruptedException{
		result = new JSONObject();
		try {
			user.setEnrollTime(new Date());
			user.setLastTime(new Date());
			StringUtil.removeBlank(user); //取出空格
			user.setPassword(MD5Util.getEncryptedPwd(user.getPassword()));
			user.setUserStatus((byte)1);
			userService.saveUser(user);
			result.put("result", 1);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("result", 2);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("result", 2);
		}
		return "json";
	}
	
	/*
	 * ajax修改用户
	 * 返回json
	 */
	@Action(value="/user/editUsers",
			interceptorRefs={@InterceptorRef("userInterceptor")},
			results={@Result(name="noLogin",type="chain",location="loginErrorAjax"),
					@Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax")
			})
	public String editUsers() throws Exception{
		result = new JSONObject();
		if(roleId!=null&&userStatus!=null&&(Integer)userId!=null){
			userService.update(userId, Integer.parseInt(roleId), Byte.parseByte(userStatus));
			result.put("result", 1);
		}else{
			result.put("result", 2);
		}
		return "json";
	}
	
	/*
	 * ajax修改用户头像
	 * 返回json
	 */
	@Action(value="/user/submitPhoto",
			interceptorRefs={@InterceptorRef(value="fileUpload",params={"allowedTypes","image/png,image/gif,image/jpeg,image/jpg","maximumSize","256*1024"}),
							 @InterceptorRef("defaultStack")},
			results={@Result(name="input",location="tooLarge",type="chain")}				 
			)
	public String submitPhoto(){
		result = new JSONObject();
		user = (User)request.getSession().getAttribute("user");
		if(user == null){
			result.put("result", 2);
			return "json";
		}
		String path = request.getSession().getServletContext().getRealPath("/photo");
//		String path = org.apache.struts2.ServletActionContext.getServletContext().getContextPath();
		String photoPath = StringUtil.photoName(user.getUserId(), photoFileName);
		File saveFile = new File(new File(path), photoPath); //根据地址以及文件名生成文件
		try {
			FileUtils.copyFile(photo, saveFile);
			userService.updatePhoto(user.getUserId(), "photo/"+photoPath);
			String oldPhoto = user.getPhoto();
			if(oldPhoto != null && !oldPhoto.equals("photo/"+photoPath)){
				oldPhoto = oldPhoto.replace("photo/", "");
				File delFile = new File(new File(path), oldPhoto); //根据地址以及文件名生成文件
				if (delFile.isFile() && delFile.exists()) {  
					delFile.delete();  
			    }
				user.setPhoto("photo/"+photoPath);
			}
			result.put("photo", "photo/"+photoPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * 用户ajax修改自己的资料
	 * 返回json
	 */
	@Action(value="/user/editMyInfo")
	public String editMyInfo() throws Exception{
		result = new JSONObject();
		Object o = request.getSession().getAttribute("user");
		if(o == null){
			result.put("result", 2); //用户未登录
			return "json";
		}else{
			userId = ((User)o).getUserId();
		}
		User u = userService.findUserByValue("mail", user.getMail());
		if(u != null && u.getUserId() != userId){
			result.put("result", 3); //邮箱已注册
			return "json";
		}
		u = userService.findUserByValue("phone", user.getPhone());
		if(u != null && u.getUserId() != userId){
			result.put("result", 4); //电话已注册
			return "json";
		}
		userService.update(((User)o).getUserId(), user.getMail(), user.getPhone(), user.getUserDescription());	
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * 管理员查看所有用户
	 */
	@Action(value="/user/getAllUser")
	public String getAllUser() throws ParseException{
		result = new JSONObject();
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		List<User> users= userService.pageList((Integer.parseInt(page)-1)*Integer.parseInt(rows),Integer.parseInt(rows), -1);
		JSONArray array = new JSONArray();
		Entity2JSONUtil.user2JSON(users, array);
		result.put("total", userService.count(-1));
		result.put("rows", array);
		return "json";
	}
	
	
	/*
	 * 管理员查看所有用户
	 */
	@Action(value="/user/getMyInfo")
	public String getMyInfo() throws ParseException{
		result = new JSONObject();
		user = (User)request.getSession().getAttribute("user");
		if(user == null)
			return "json";
		user = userService.getMyInfo(user.getUserId());
		JSONArray array = new JSONArray();
		Entity2JSONUtil.myInfo2JSON(user, array);
		result.put("total", 1);
		result.put("rows", array);
		return "json";
	}
	
	/*
	 * 退出登录
	 */
	@Action(value="/user/logout")
	public String logout(){
		result = new JSONObject();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		result.put("result", 1);
		return "json";
	}
	
	/*
	 * 登录验证
	 */
	@Action(value="/user/login")
	public String login() throws InterruptedException {
		// TODO Auto-generated method stub
		result = new JSONObject();
		User u = userService.findUserByuserName(user.getUserName());
		if(u == null){
			result.put("result", 2);
		}else if(u.getUserStatus() != 1){
			result.put("result", 4);
		}else{
			try {
				if(MD5Util.validPassword(user.getPassword(), u.getPassword())){
					HttpSession session = request.getSession();
					Role role = (Role)request.getServletContext().getAttribute("role_"+user.getRoleId());
					if(role == null){
						session.setAttribute("topic", true);
					}else{
						session.setAttribute("topic", role.getCreatTopic());
					}
					u.setPassword(null);
					userService.updateLastTime(u.getUserId(), new Date());
					session.setAttribute("user", u);
					result.put("result", 1);
				}else{
					result.put("result", 3);
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "json";
	}
	
	/*
	 * 获取用户资料
	 * 个人资料，从session获取
	 * 他人资料从数据库查询
	 * 无效用户。errorMsg="你查找的用户不存在"
	 */
	@Actions({
		@Action(value="/user/getUser",
				results={@Result(name="error",location="../jsp/error.jsp"),
						@Result(name="topic",type="chain", location="topic_getUserTopic",params={"user","user"})
		}
				)
	})
	public String getUserInfo(){
		userId = Integer.parseInt(StringUtil.removeChar(userId+""));
		user = userService.getUserInfo(userId);
		if(user==null){
			error = "该用户不存在！";
			return "error";
		}
		user.setEnrollTimeString(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(user.getEnrollTime()));
		user.setLastTimeString(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(user.getLastTime()));
		return "topic";
	}
	
	/*
	 * 删除角色时修改该角色下的用户角色
	 */
	@Actions({
		@Action(value="/role/delRole",
				interceptorRefs={@InterceptorRef("roleInterceptor")},
				results={@Result(name="noLogin",type="chain",location="loginErrorAjax"),
						@Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax"),
						@Result(name="role",type="chain", location="role_delRole",params={"roleId","roleId"})
				})
	})
	public String changeUserRole(){
		result = new JSONObject();
		if(roleId != null){
			String[] roleIds = roleId.split(",");
			for(String str:roleIds){
				userService.updateUserRole(str);
			}
		}else{
			result.put("result", 2);
			return "json";
		}
		return "role";
	}
	
	/*
	 * 用户角色不存在时将角色设置为默认游客
	 */
	@Actions({
		@Action(value="user_changeRole",
				results={@Result(name="success",location="WEB-INF/backManage/back.jsp")}
				)
	})
	public String changeUserRole1(){
		role = (Role)request.getServletContext().getAttribute("role_1");
		userService.updateUserRole(user.getUserId(), role);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
	

	/*
	 * 修改用户密码
	 */
	@Action(value="/user/checkOldPwd")
	public String checkOldPwd() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		result = new JSONObject();
		User u = userService.findUserByuserName(userName);
		if(MD5Util.validPassword(oldPwd, u.getPassword())){	
		}else{
			result.put("result", 0);
		}
		return "json";
	}
	/*
	 * 检查旧密码是否合格
	 */
	@Action(value="/user/setNewPwd")
	public String setNewPwd() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		result = new JSONObject();
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		userService.updateUserPwd(MD5Util.getEncryptedPwd(newPwd), u.getUserId());
		session.removeAttribute("user");
		result.put("result",1);
		return "json";
	}

}
