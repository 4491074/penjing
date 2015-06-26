package com.penjing.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.penjing.dao.BaseDao;
import com.penjing.entity.News;
import com.penjing.service.NewsService;
@Service("newsService")
public class NewsServiceImpl implements NewsService{

	
	@Resource
	private BaseDao<News> newsDao;
	@Override
	public List<News> getNewsByBdId(int nBdId) {
		// TODO Auto-generated method stub
		String hql="from News where newsStatus=1 and newsBoard="+nBdId;
		List<News> news=(List<News>)newsDao.find(hql);
		return news;
	}
	public News getNewsById(int newsId){
		News news=newsDao.get(News.class, newsId);
		return news;
	}
	@Override
	public List<News> getMyNews(int first,int max,int publisherId) {
		// TODO Auto-generated method stub
		
		String hql="from News n inner join fetch n.newsPublisher pubisher inner join fetch n.newsBoard newsBd where pubisher.userId="+publisherId;
		List<News> news=(List<News>)newsDao.find(hql,null,first,max);
		
		//String hql1="from News n inner join fetch n.newsPublisher pubisher inner join fetch n.newsBoard newsBd where n.newsAssessor=null and pubisher.userId="+publisherId;
		//List<News> news1=(List<News>)newsDao.find(hql1);
		//news.addAll(news1);
		return news;
	}
	@Override
	public void save(News news) {
		// TODO Auto-generated method stub
		newsDao.save(news);
	}
	@Override
	public void updateReadNu(int id) {
		// TODO Auto-generated method stub
		News n=getNewsById(id);
		int readNu=(int)n.getReadNu()+1;
		n.setReadNu((long)readNu);
		newsDao.update(n);
	}
	@Override
	public void deleteNews(int id) {
		// TODO Auto-generated method stub
		News n=getNewsById(id);
		newsDao.delete(n);
	}
	@Override
	public void updateNews(News n) {
		// TODO Auto-generated method stub
		newsDao.update(n);
	}
	@Override
	public List<News> getAllNews(int first,int max,int status) {
		// TODO Auto-generated method stub
		String hql=null;
		if(status==-1){
			hql="from News n inner join fetch n.newsPublisher pubisher inner join fetch n.newsBoard newsBd ";
		}else{
			hql="from News n inner join fetch n.newsPublisher pubisher inner join fetch n.newsBoard newsBd where n.newsStatus="+status;
		}
		List<News> news=(List<News>)newsDao.find(hql,null,first,max);
		return news;
	}
	@Override
	public int count(int status,String userId) {
		// TODO Auto-generated method stub
		String hql=null;
		if(userId==null){
			if(status==-1){
				hql="from News";
			}else{
				hql="from News where newsStatus="+status;
			}
		}else{
			hql="from News n inner join fetch n.newsPublisher pubisher inner join fetch n.newsBoard newsBd where pubisher.userId="+Integer.parseInt(userId);
		}
		List<News> news=(List<News>)newsDao.find(hql);
		return news.size();
	}

	
}
