package com.penjing.filter;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.penjing.entity.PenJingType;
import com.penjing.service.PenJingTypeService;

public class PenJingTypeFilter implements ServletContextListener {
	private PenJingTypeService penjingTypeService; 
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
		penjingTypeService=(PenJingTypeService)ac.getBean("penJingTypeService");
		List<PenJingType> types=penjingTypeService.findAllList((byte)1);
		application.setAttribute("penjingType", types);
	}
}
