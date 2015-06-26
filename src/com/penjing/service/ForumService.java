package com.penjing.service;


import java.util.List;

import com.penjing.entity.Forum;

public interface ForumService {

	public void saveForum(Forum forum);
	
	public void updateForum(Forum forum);
	
	public Forum getForum();
	
	public void deleteForum(Forum forum);
	
	public List<Forum> getAllForum(int frist,int max,int status);
	
	
}
