package com.penjing.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.penjing.dao.BaseDao;
import com.penjing.entity.Reply;
import com.penjing.entity.Topic;
import com.penjing.entity.User;
import com.penjing.service.ReplyService;
import com.penjing.util.StringUtil;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService{

	@Resource
	private BaseDao<Reply> baseDao;
	
	@Resource
	private StringUtil stringUtil;
	
	@Override
	public void saveReply(Reply reply) {
		// TODO Auto-generated method stub
		baseDao.save(reply);
	}

	@Override
	public void updateReply(Reply reply) {
		// TODO Auto-generated method stub
		baseDao.update(reply);
	}

	@Override
	public void deleteReply(Reply reply) {
		// TODO Auto-generated method stub
		baseDao.delete(reply);
	}

	@Override
	public List<Reply> findAllList() {
		// TODO Auto-generated method stub
		String hql = "from Reply";
		return baseDao.find(hql);
	}

	@Override
	public List<Reply> pageList(Topic topic, int first, int max, int replyState) {
		// TODO Auto-generated method stub
		String hql = "select new Reply(r.replyId,r.floor,r.content,r.time,u.userId,u.userName,u.photo)from Reply r join r.user u where r.topic=? and r.replyState=?order by r.floor asc";
		Object[] param = new Object[]{topic,(byte)replyState};
		return baseDao.find(hql, param, first, max);
	}
	
	@Override
	public List<Reply> pageList(User user, int first, int max, int replyState) {
		// TODO Auto-generated method stub
		String hql = "select new Reply(r.replyId,r.content,r.time,t.topicId,t.title,u.userId,u.userName)from Reply r join r.topic t join t.user u where r.user=? and r.replyState=?order by r.time desc";
		Object[] param = new Object[]{user,(byte)replyState};
		return baseDao.find(hql, param, first, max);
	}

	@Override
	public Long count(int topicId, int replyState) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select count(*) from Reply where topic.topicId=?");
		Object[] param = null;
		if(replyState<0){
			param = new Object[]{topicId};
		}else{
			hql.append(" and replyState=?");
			param = new Object[]{topicId,(byte)replyState};
		}
		return baseDao.count(hql.toString(), param);
	}
	
	@Override
	public Long count(User user, int replyState) {
		// TODO Auto-generated method stub
		String hql ="select count(*) from Reply where user=? and replyState=?";
		Object[] param = new Object[]{user,(byte)replyState};
		return baseDao.count(hql.toString(), param);
	}

	@Override
	public Reply getReplyAboutLastTime(Topic topic, int replyState) {
		// TODO Auto-generated method stub
		String hql = "select new Reply(t.lastTime)from Reply r join r.topic t where r.topic=? and r.replyState=?";
		Object[] param = new Object[]{topic,(byte)replyState};
		return baseDao.get(hql, param);
	}

	@Override
	public Reply getReplyAboutLastReplyUser(Topic topic, int replyState) {
		// TODO Auto-generated method stub
		Reply r = null;
		String hql = "select new Reply(u.userId,u.userName)from Reply r join r.user u where r.topic=? and r.replyState=?order by r.floor desc";
		Object[] param = new Object[]{topic,(byte)replyState};
		List<Reply> rs = baseDao.find(hql, param, 0, 1);
		if(rs.size()==1){
			r = rs.get(0);
		}
		return r;
	}

	@Override
	public int deleteReply(int topicId) {
		// TODO Auto-generated method stub
		String sql = "delete from t_reply where topicId="+topicId;
		return baseDao.delete(sql);
	}

	@Override
	public List<Reply> pageListForManage(int topicId, int first, int max,
			int replyState) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select new Reply(r.replyId,r.floor,r.content,r.time,r.replyState,u.userId,u.userName) from Reply r join r.user u where r.topic.topicId=?");
		Object[] param = null;
		if(replyState>=0){
			hql.append(" and r.replyState=? order by r.replyId asc");
			param = new Object[]{topicId,replyState};
		}else{
			hql.append(" order by r.replyId asc");
			param = new Object[]{topicId};
		}
		return baseDao.find(hql.toString(), param, first, max);
	}

	@Override
	public void updateReply(Long replyId, byte replyState) {
		// TODO Auto-generated method stub
		String hql = "update Reply set replyState=? where replyId=?";
		Object[] param = new Object[]{replyState,replyId};
		baseDao.update(hql, param);
	}

	@Override
	public int deleteReply(String replyIds) {
		// TODO Auto-generated method stub
		String sql = "delete from t_reply where replyId in ("+replyIds+")";
		return baseDao.delete(sql);
	}

}
