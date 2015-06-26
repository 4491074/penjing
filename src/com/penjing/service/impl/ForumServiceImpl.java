package com.penjing.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.penjing.dao.BaseDao;
import com.penjing.entity.Forum;
import com.penjing.service.ForumService;
import com.penjing.util.StringUtil;

@Service("forumService")
public class ForumServiceImpl implements ForumService{

	@Resource
	private BaseDao<Forum> baseDao;
	
	@Resource
	private StringUtil stringUtil;
	
	@Override
	public void saveForum(Forum forum) {
		// TODO Auto-generated method stub
		baseDao.save(forum);
	}

	@Override
	public void updateForum(Forum forum) {
		// TODO Auto-generated method stub
		baseDao.update(forum);
	}

	@Override
	public Forum getForum() {
		// TODO Auto-generated method stub
		String hql = "select new Forum(forumName,notice,pageNu) from Forum where forumId = 1";
		return baseDao.get(hql, null);
	}

	@Override
	public void deleteForum(Forum forum) {
		// TODO Auto-generated method stub
		baseDao.delete(forum);
	}

	@Override
	public List<Forum> getAllForum(int first,int max,int status) {
		// TODO Auto-generated method stub
		String hql="from Forum";
		if(status>-1){
			hql=hql+" where forumStatus="+status;
		}
		return baseDao.find(hql, null, first, max);
	}


}
