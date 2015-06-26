package com.penjing.action;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.penjing.entity.Role;
import com.penjing.entity.User;
import com.penjing.service.RoleService;
import com.penjing.util.Entity2JSONUtil;
import com.penjing.util.StringUtil;
@ResultPath("/")
@ParentPackage(value="crud-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"})
	})
public class RoleAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private ServletContext application;
	@Resource
	private RoleService roleService;
	private User user;
	private Role role;
	private String error;
	private JSONObject result;
	private String page;
	private String rows;
	private String roleName;
	private String roleDescription;
	private String roleAuthority;
	private byte roleState;
	private String roleId;
	private JSONArray array;
	
	public JSONArray getArray() {
		return array;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public void setRoleState(byte roleState) {
		this.roleState = roleState;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public void setRoleAuthority(String roleAuthority) {
		this.roleAuthority = roleAuthority;
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

	public JSONObject getResult() {
		return result;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	@Action(value="role_getRoles",results={@Result(name="success", location="WEB-INF/backManage/back.jsp"),
			@Result(name="noRole", location="user_changeRole",params={"user","user"})
			})
	public String getRoles(){
		role = roleService.find(user.getRoleId());
		if(role == null){
			return "noRole";  //该用户的角色不存在，将用户的角色修改为1
		}else{
			if(role.getRoleStatus() == 1){
				application.setAttribute("role_"+role.getRoleId(), role); //该角色存在且状态正常，存入application
			}else{
					role = (Role)application.getAttribute("role_1");  //该角色存在且状态为冻结，转为游客角色
			}
		}
		return SUCCESS;
	}
	/*
	 * 管理员查看所有角色
	 */
	@Action(value="/role/getAllRole")
	public String getAllRoles(){
		result = new JSONObject();
		if(page==null)
			page = 1+"";
		if(rows==null)
			rows = 20+"";
		List<Role> roles= roleService.pageList((Integer.parseInt(page)-1)*Integer.parseInt(rows),Integer.parseInt(rows), -1);
		JSONArray array = new JSONArray();
		Entity2JSONUtil.role2JSON(roles, array);
		result.put("total", roleService.count(-1));
		result.put("rows", array);
		return "json";
	}
	
	/*
	 * 获取角色菜单
	 */
	@Action(value="/role/getUserRole",results={@Result(name="array",type="json",params={"root","array"})})
	public String getUserRoles(){
		array = new JSONArray();
		List<Role> roles= roleService.findAllListForBox((byte)-1);
		Entity2JSONUtil.role2JSON1(roles, array);
		return "array";
	}
	/*
	 * ajax添加角色
	 * 返回json
	 */
	@Action(value="/role/saveRole",
			interceptorRefs={@InterceptorRef("roleInterceptor")},
			results={@Result(name="noLogin",type="chain",location="loginErrorAjax"),
					@Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax")
			})
	public String saveRole() throws Exception{
		result = new JSONObject();
		role = new Role(false);
		if(roleName!=null){
			role.setRoleName(roleName);
			if(roleDescription != null){
				role.setRoleDescription(roleDescription);
			}
			role.setRoleStatus(roleState);
			StringUtil.String2RoleAuthority(roleAuthority, role);
			int id = roleService.saveRole(role);
			result.put("result", 1);
			result.put("id", id);
			role.setRoleId(id);
			if(role.getRoleStatus()==1){
				request.getServletContext().setAttribute("role_"+roleId, role);
			}
		}else{
			result.put("result", 2);
		}
		return "json";
	}
	
	/*
	 * ajax修改角色
	 * 返回json
	 */
	@Action(value="/role/editRole",
			interceptorRefs={@InterceptorRef("roleInterceptor")},
			results={@Result(name="noLogin",type="chain",location="loginErrorAjax"),
					@Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax")
			})
	public String editRole() throws Exception{
		result = new JSONObject();
		role = new Role(false);
		if(roleName!=null&&roleId!=null){
			role.setRoleId(Integer.parseInt(roleId));
			role.setRoleName(roleName);
			if(roleDescription != null){
				role.setRoleDescription(roleDescription);
			}
			role.setRoleStatus(roleState);
			StringUtil.String2RoleAuthority(roleAuthority, role);
			roleService.updateRole(role);
			result.put("result", 1);
			if(role.getRoleStatus()==1){
				request.getServletContext().setAttribute("role_"+roleId, role);
			}
		}else{
			result.put("result", 2);
		}
		return "json";
	}
	/*
	 * ajax删除数据
	 */
	@Action(value="role_delRole")
	public String delRole(){
		result = new JSONObject();
		if(roleId != null){
			result.put("nu", roleService.deleteRole(roleId));
			String[] ids = roleId.split(",");
			for(String str:ids){
				request.getServletContext().removeAttribute("role_"+str);
			}
			result.put("result", 1);
		}
		return "json";
	}
}
