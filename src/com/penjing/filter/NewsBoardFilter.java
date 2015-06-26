package com.penjing.filter;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.penjing.entity.NewsBoard;
import com.penjing.service.NewsBoardService;
import com.penjing.service.NewsService;

public class NewsBoardFilter implements ServletContextListener {
	private NewsBoardService newsBdService; 
	@SuppressWarnings("unused")
	private NewsService newsService;
	private ApplicationContext ac;
	public static int defaultNewsBdId;

	
	public void setNewsBdService(NewsBoardService newsBdService) {
		this.newsBdService = newsBdService;
	}
	
	
	
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}



	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		ServletContext application= event.getServletContext();
		ac=WebApplicationContextUtils.getWebApplicationContext(application);
		newsBdService=(NewsBoardService)ac.getBean("newsBdService");
		newsService=(NewsService)ac.getBean("newsService");
		List<NewsBoard> pNewsBd=newsBdService.getParentNewsBoard(1);
		List<NewsBoard> sNewsBd=newsBdService.getSonNewsBoard(1);
		application.setAttribute("pNewsBd", pNewsBd);
		application.setAttribute("sNewsBd", sNewsBd);
		if(sNewsBd.size()>0){
			defaultNewsBdId=sNewsBd.get(0).getNewsBoardId();
		}
	}
}
