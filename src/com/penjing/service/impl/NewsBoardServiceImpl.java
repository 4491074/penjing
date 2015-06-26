package com.penjing.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.penjing.dao.BaseDao;
import com.penjing.entity.NewsBoard;
import com.penjing.service.NewsBoardService;
import com.penjing.util.ComparatorNewsBd;
@Service("newsBdService")
public class NewsBoardServiceImpl implements NewsBoardService{

	@Resource
	private BaseDao<NewsBoard> newsbdDao;
	
	@Override
	public List<NewsBoard> getParentNewsBoard(int status) {
		// TODO Auto-generated method stub
		String hql=null;
		if(status==-1){
			hql="from NewsBoard where newsBoardparent is null";
		}else{
		 hql="from NewsBoard where newsBoardparent is null and newsBoardStatus="+status;
		}
		List<NewsBoard> pnewsbd=newsbdDao.find(hql);
		return pnewsbd;
	}

	@Override
	public List<NewsBoard> getSonNewsBoard(int status) {
		// TODO Auto-generated method stub
		String hql=null;
		if(status==-1){
			hql="from NewsBoard where newsBoardparent is not null";
		}else{
		 hql="from NewsBoard where newsBoardparent is not null and newsBoardStatus="+status;
		}
		List<NewsBoard> snewsbd=newsbdDao.find(hql);
		return snewsbd;
	}
	/*
	 * 通过newsBdId得到新闻版块名称
	 */
	public String getNewsBdNameById(int newsBdId){
		NewsBoard newsBd=newsbdDao.get(NewsBoard.class,newsBdId);
		return newsBd.getNewsBoardName();
	}
	

	@Override
	public void addNewsBoard(NewsBoard newsbd) {
		// TODO Auto-generated method stub
		newsbdDao.save(newsbd);
	}

	

	@Override
	public void delParentNewsBoard(NewsBoard pNewsBoard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delSonNewsBoard(NewsBoard sNewsBoard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateParentNewsBoard(NewsBoard pNewsBoard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNewsBoard(NewsBoard sNewsBoard) {
		// TODO Auto-generated method stub
		newsbdDao.update(sNewsBoard);
	}
/*
 * (non-Javadoc)
 * @see com.penjing.service.NewsBoardService#getAllNewsBd()得到所有新闻版块
 */
	@Override
	public List<NewsBoard> getAllNewsBd(int status) {
		// TODO Auto-generated method stub
		String hql="from NewsBoard snewbd";
		Object[] param = null;
		if(status<0){
		}else{
			hql=hql+"where snewbd.newsBoardStatus=?";
			param = new Object[]{status};
		}
		List<NewsBoard> newsbd=newsbdDao.find(hql,param);
		ComparatorNewsBd cnewsBd=new ComparatorNewsBd(newsbd);
		return cnewsBd.sortNewsBoard();
	}

	@Override
	public NewsBoard getNewsBoardId(int newsBdId) {
		// TODO Auto-generated method stub
		NewsBoard n=newsbdDao.get(NewsBoard.class, newsBdId);
		return n;
	}

	@Override
	public int checkPnewsBd(int newsBdId) {
		// TODO Auto-generated method stub
		String hql="from NewsBoard snewbd inner join fetch snewbd.newsBoardparent pnewbd where pnewbd.newsBoardId="+newsBdId;
		List<NewsBoard> newsbd=newsbdDao.find(hql);
		if(newsbd.size()>0){
			return 1;
		}
		return 0;
	}

	@Override
	public int delNewsBd(int newsBdId) {
		// TODO Auto-generated method stub
		String hql="delete from t_news where t_news.newsBoard="+newsBdId;
		newsbdDao.delete(hql);
		String hql1="delete from t_newsboard where t_newsboard.newsBoardId="+newsBdId;
		return newsbdDao.delete(hql1);
	}

	@Override
	public NewsBoard getNewsBdById(String id) {
		// TODO Auto-generated method stub
		int newsbdid=Integer.parseInt(id);
		return newsbdDao.get(NewsBoard.class, newsbdid);
	}

	@Override
	public NewsBoard getNewsBdByName(String name) {
		// TODO Auto-generated method stub
		String hql="from NewsBoard where newsBoardName='"+name+"'";
		List<NewsBoard> newsBds=newsbdDao.find(hql);
		return newsBds.get(0);
	}

	@Override
	public List<NewsBoard> getAllNewsBoard(int status) {
		// TODO Auto-generated method stub
		String hql=null;
		if(status==-1){
			hql="from NewsBoard";
		}else{
		 hql="from NewsBoard where newsBoardStatus="+status;
		}
		List<NewsBoard> pnewsbd=newsbdDao.find(hql);
		return pnewsbd;
	}

	
	

}
