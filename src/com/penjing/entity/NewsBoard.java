package com.penjing.entity;

import java.util.HashSet;
import java.util.Set;


/*
 * 新闻板块
 */
public class NewsBoard {
	private int newsBoardId; //新闻板块编号
	private String newsBoardName; //新闻板块名称
	private NewsBoard newsBoardparent; //父板块，没有父板块时为null
	private byte newsBoardStatus; //板块状态
	private String newsBoardDescription; //板块描述、简介
	
	private Set<News> news = new HashSet<News>(); //该板块下的新闻集合
	private Set<NewsBoard> newsBoards = new HashSet<NewsBoard>(); //该父板块下的子版块集合
	
	
	
	public NewsBoard(){}
	
	
	public Set<NewsBoard> getNewsBoards() {
		return newsBoards;
	}
	public void setNewsBoards(Set<NewsBoard> newsBoards) {
		this.newsBoards = newsBoards;
	}
	public Set<News> getNews() {
		return news;
	}
	public void setNews(Set<News> news) {
		this.news = news;
	}
	public int getNewsBoardId() {
		return newsBoardId;
	}
	public void setNewsBoardId(int newsBoardId) {
		this.newsBoardId = newsBoardId;
	}
	public String getNewsBoardName() {
		return newsBoardName;
	}
	public void setNewsBoardName(String newsBoardName) {
		this.newsBoardName = newsBoardName;
	}
	public NewsBoard getNewsBoardparent() {
		return newsBoardparent;
	}
	public void setNewsBoardparent(NewsBoard newsBoardparent) {
		this.newsBoardparent = newsBoardparent;
	}
	public byte getNewsBoardStatus() {
		return newsBoardStatus;
	}
	public void setNewsBoardStatus(byte newsBoardStatus) {
		this.newsBoardStatus = newsBoardStatus;
	}
	public String getNewsBoardDescription() {
		return newsBoardDescription;
	}
	public void setNewsBoardDescription(String newsBoardDescription) {
		this.newsBoardDescription = newsBoardDescription;
	}


	

	
}
