package com.penjing.service;

import java.util.List;

import com.penjing.entity.News;

public interface NewsService {
	/*
	 * 通过新闻版块ID得到新闻
	 */
	public List<News> getNewsByBdId(int nBdId);
	/*
	 * 通过Id得到新闻
	 */
	public News getNewsById(int newsId);
	/*
	 * 通过发布者Id得到我的新闻
	 */
	public List<News> getMyNews(int first,int max,int publisherId);
	/*
	 * 发布一个新闻
	 */
	public void save(News news);
	/*
	 * 修改阅读量
	 */
	public void updateReadNu(int id);
	/*
	 * 删除新闻
	 */
	public void deleteNews(int id);
	/*
	 *修改我的新闻 
	 */
	public void updateNews(News n);
	/*
	 * 得到所有新闻
	 */
	public List<News> getAllNews(int first,int max,int status);
	
	/*
	 * 检索出新闻的条数
	 */
	public int count(int status,String userId);
	
	
	
	
}
