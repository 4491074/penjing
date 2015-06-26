package com.penjing.filter;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.penjing.entity.Forum;
import com.penjing.service.ForumService;

public class ForumFilter implements ServletContextListener{

	private ApplicationContext ac;
	private ForumService forumService;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 * 将论坛板块信息从数据库中取出
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		ServletContext application = event.getServletContext(); //获取application
		ac = WebApplicationContextUtils.getWebApplicationContext(application); //获取application
		forumService = (ForumService)ac.getBean("forumService");
		Forum forum = forumService.getForum();
		application.setAttribute("forum", forum);
	}

	
	
	
}
