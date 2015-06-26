package com.penjing.service;

import java.util.Date;
import java.util.List;

import com.penjing.entity.Topic;
import com.penjing.entity.User;

public interface TopicService {

	public void saveTopic(Topic topic);
	
	public void updateTopic(Topic topic);
	
	public void updateTopic(int topicTd,byte isTop,Date topTime,byte topicState);
	
	public void updateIsTop(int topicId, byte isTop);
	
	public void updateReadNu(int topicId,long readNu);
	
	public void updateLastTime(int topicId,Date lastTime);
	
	public Topic findTopicById(int id,int topicState);
	
	public Topic findTopicByName(String type,String value);
	
	public void deleteTopic(Topic topic);
	
	public int deleteTopi(String ids);
	
	public List<Topic> findAllList();
	
	public List<Topic> pageList(int first,int max,int isTop,int topicState);
	
	public List<Topic> pageListForManage(int first,int max,int isTop,int topicState);
	
	public List<Topic> pageList(User user,int first,int max,int topicState);
	
	public Long count(int isTop,int topicState);
	
	public Long count(User user,int topicState);
	
	
}
