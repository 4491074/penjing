package com.penjing.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.penjing.dao.BaseDao;
import com.penjing.entity.Topic;
import com.penjing.entity.User;
import com.penjing.service.TopicService;
import com.penjing.util.StringUtil;

@Service("topicService")
public class TopicServiceImpl implements TopicService{

	@Resource
	private BaseDao<Topic> baseDao;
	
	@Resource
	private StringUtil stringUtil;
	
	@Override
	public void saveTopic(Topic topic) {
		// TODO Auto-generated method stub
		baseDao.save(topic);
	}

	@Override
	public void updateTopic(Topic topic) {
		// TODO Auto-generated method stub
		baseDao.update(topic);
	}

	@Override
	public Topic findTopicById(int id,int topicState) {
		// TODO Auto-generated method stub
		String hql = "select new Topic(t.topicId,t.time,t.lastTime,t.title,t.content,t.readNu,u.userId,u.userName,u.photo) from Topic t join t.user u where t.topicId=? and t.topicState=?";
		Object[] param = new Object[]{id,(byte)topicState};
		return baseDao.get(hql, param);
	}

	@Override
	public void deleteTopic(Topic topic) {
		// TODO Auto-generated method stub
		baseDao.delete(topic);
	}

	@Override
	public List<Topic> findAllList() {
		// TODO Auto-generated method stub
		String hql = "from Topic";
		return baseDao.find(hql);
	}


	@Override
	public Topic findTopicByName(String type,String value) {
		// TODO Auto-generated method stub
		String hql = "from Topic where "+type+"=?";
		Object[] param = new Object[]{value};
		return baseDao.get(hql, param);
	}

	@Override
	public List<Topic> pageList(int first, int max,int isTop,int topicState) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select new Topic(t.topicId,t.lastTime,t.title,t.isTop,t.topTime,u.userId,u.userName,u.photo) from Topic t join t.user u ");
		Object[] param = null;
		if(topicState>=0){
			if(isTop>=0){
				hql.append("where t.isTop=? and t.topicState=? order by t.lastTime desc");
				param = new Object[]{(byte)isTop,(byte)topicState};
			}else{
				hql.append("where t.topicState=? order by t.lastTime desc");
				param = new Object[]{(byte)topicState};
			}
		}else{
			if(isTop>=0){
				hql.append("where t.isTop=? order by t.lastTime desc");
				param = new Object[]{(byte)isTop};
			}else{
				hql.append("order by t.lastTime desc");
			}
		}
		List<Topic> topics = baseDao.find(hql.toString(), param, first, max);
		return topics;
	}
	
	@Override
	public List<Topic> pageListForManage(int first, int max,int isTop,int topicState) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select new Topic(t.topicId,t.time,t.readNu,t.title,t.isTop,t.topTime,t.topicState,u.userId,u.userName) from Topic t join t.user u ");
		Object[] param = null;
		if(topicState>=0){
			if(isTop>=0){
				hql.append("where t.isTop=? and t.topicState=? order by t.topicId asc");
				param = new Object[]{(byte)isTop,(byte)topicState};
			}else{
				hql.append("where t.topicState=? order by t.topicId asc");
				param = new Object[]{(byte)topicState};
			}
		}else{
			if(isTop>=0){
				hql.append("where t.isTop=? order by t.topicId asc");
				param = new Object[]{(byte)isTop};
			}else{
				hql.append("order by t.topicId asc");
			}
		}
		List<Topic> topics = baseDao.find(hql.toString(), param, first, max);
		return topics;
	}
	
	@Override
	public List<Topic> pageList(User user, int first, int max, int topicState) {
		// TODO Auto-generated method stub
		String hql = "select new Topic(t.topicId,t.lastTime,t.title) from Topic t where t.user=? and t.topicState=? order by t.lastTime desc";
		Object[] param = new Object[]{user,(byte)topicState};
		return baseDao.find(hql, param, first, max);
	}

	@Override
	public Long count(int isTop,int topicState) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select count(*) from Topic ");
		Object[] param = null;
		if(topicState>=0){
			if(isTop>=0){
				hql.append("where isTop=? and topicState=?");
				param = new Object[]{(byte)isTop,(byte)topicState};
			}else{
				hql.append("where topicState=?");
				param = new Object[]{(byte)topicState};
			}
		}else{
			if(isTop>=0){
				hql.append("where isTop=?");
				param = new Object[]{(byte)isTop};
			}else{
			}
		}
		return baseDao.count(hql.toString(), param);
	}

	@Override
	public void updateIsTop(int topicId, byte isTop) {
		// TODO Auto-generated method stub
		String hql = "update Topic set isTop=? where topicId=?";
		Object[] param = new Object[]{isTop,topicId};
		baseDao.update(hql, param);
	}

	@Override
	public void updateReadNu(int topicId, long readNu) {
		// TODO Auto-generated method stub
		String hql = "update Topic set readNu=? where topicId=?";
		Object[] param = new Object[]{readNu,topicId};
		baseDao.update(hql, param);
	}

	@Override
	public void updateLastTime(int topicId, Date lastTime) {
		// TODO Auto-generated method stub
		String hql = "update Topic set lastTime=? where topicId=?";
		Object[] param = new Object[]{lastTime,topicId};
		baseDao.update(hql, param);
	}

	@Override
	public Long count(User user, int topicState) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Topic where user=? and topicState=?";
		Object[] param = new Object[]{user,(byte)topicState};
		return baseDao.count(hql, param);
	}

	@Override
	public void updateTopic(int topicId, byte isTop, Date topTime,
			byte topicState) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("update Topic set isTop=?");
		Object[] param = null;
		if(isTop == 1){
			hql.append(",topTime=?");
			param = new Object[]{isTop,topTime,topicState,topicId};
		}else{
			param = new Object[]{isTop,topicState,topicId};
		}
		hql.append(",topicState=? where topicId=?");
		baseDao.update(hql.toString(), param);
	}

	@Override
	public int deleteTopi(String ids) {
		// TODO Auto-generated method stub
		String sql = "delete from t_topic where topicId in ("+ids+")";
		return baseDao.delete(sql);
	}


	
}
