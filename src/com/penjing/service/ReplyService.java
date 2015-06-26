package com.penjing.service;

import java.util.List;

import com.penjing.entity.Reply;
import com.penjing.entity.Topic;
import com.penjing.entity.User;

public interface ReplyService {

	public void saveReply(Reply reply);
	
	public void updateReply(Reply reply);
	
	public void updateReply(Long replyId,byte replyState);
	
	public void deleteReply(Reply reply);
	
	public int deleteReply(String replyIds);
	
	public int deleteReply(int topicId);
	
	public List<Reply> findAllList();
	
	/*
	 * 根据帖子获取回帖信息
	 * 进行分页
	 */
	public List<Reply> pageList(Topic topic,int first,int max,int replyState);
	
	public List<Reply> pageListForManage(int topicId,int first,int max,int replyState);
	
	/*
	 * 根据用户获取回帖信息
	 * 进行分页
	 */
	public List<Reply> pageList(User user,int first,int max,int replyState);
	
	public Long count(int topicId,int replyState);
	
	public Long count(User user,int replyState);
	
	public Reply getReplyAboutLastTime(Topic topic,int replyState);
	
	public Reply getReplyAboutLastReplyUser(Topic topic,int replyState);
}
