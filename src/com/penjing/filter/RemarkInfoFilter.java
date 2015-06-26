package com.penjing.filter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.penjing.entity.RemarkInfo;
import com.penjing.service.RemarkInfoService;

public class RemarkInfoFilter implements ServletContextListener{
	
	private RemarkInfoService remarkInfoService; 
	private ApplicationContext ac;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		ServletContext application= event.getServletContext();
		ac=WebApplicationContextUtils.getWebApplicationContext(application);
		remarkInfoService=(RemarkInfoService)ac.getBean("remarkInfoService");
		long visitNum=(long) application.getAttribute("visitNum");
		remarkInfoService.updateVisitNum(visitNum);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		ServletContext application= event.getServletContext();
		ac=WebApplicationContextUtils.getWebApplicationContext(application);
		remarkInfoService=(RemarkInfoService)ac.getBean("remarkInfoService");
		RemarkInfo reinfo=remarkInfoService.getManagerInfo();
		if(reinfo==null){
			application.setAttribute("managerTel", "");
			application.setAttribute("visitNum", 0);
		}else{
			application.setAttribute("managerTel", reinfo.getManagerTel());
			application.setAttribute("visitNum", reinfo.getVisitNum());
		}
	
	}

}
