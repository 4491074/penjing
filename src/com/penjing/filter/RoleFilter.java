package com.penjing.filter;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.penjing.entity.Role;
import com.penjing.service.RoleService;

public class RoleFilter implements ServletContextListener {
	private RoleService roleService; 
	private ApplicationContext ac;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		ServletContext application= event.getServletContext();
		ac=WebApplicationContextUtils.getWebApplicationContext(application);
		roleService=(RoleService)ac.getBean("roleService");
		List<Role> roles=roleService.findAllList((byte)1);
		for(Role r:roles){
			r.setRoleDescription(null);
			application.setAttribute("role_"+r.getRoleId(), r);
		}
	}
}
