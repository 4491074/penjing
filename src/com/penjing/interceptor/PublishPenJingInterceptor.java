package com.penjing.interceptor;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.penjing.entity.Role;
import com.penjing.entity.User;

public class PublishPenJingInterceptor implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSONObject result;
	
	public JSONObject getResult() {
		return result;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		User user = (User)request.getSession().getAttribute("user");
		if(user != null){
			Role role = (Role)request.getServletContext().getAttribute("role_"+user.getRoleId());
			if(role !=null){
				if(role.getPublishPenJing()){
					return invocation.invoke();
				}
			}
		}else{
			return "noLogin";
		}
		return "noUserAuthority";
	}

}
