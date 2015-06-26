package com.penjing.service;

import java.util.List;

import com.penjing.entity.NewsBoard;

public interface NewsBoardService {
	/*
	 * 得到父类新闻版块
	 * */
	public List<NewsBoard> getParentNewsBoard(int status);
	/*
	 * 得到父类下的之类新闻版块
	 * */
	public List<NewsBoard> getSonNewsBoard(int status);
	/*
	 * 得到所有的新闻版块
	 * */
	public List<NewsBoard> getAllNewsBoard(int status);
	
   /*
    * 新添加新闻版块
    */
	public void addNewsBoard(NewsBoard newsbd);
	
	/*
	 * 删除父类新闻模块，及子类新闻模块
	 */
	public void delParentNewsBoard(NewsBoard pNewsBoard);
	/*
	 * 删除子类新闻模块
	 */
	public void delSonNewsBoard(NewsBoard sNewsBoard);
	/*
	 * 修改父类新闻模块的名字
	 * */
	public void updateParentNewsBoard(NewsBoard pNewsBoard);
	/*
	 * 修改新闻模块名字
	 */
	public void updateNewsBoard(NewsBoard sNewsBoard);
	/*
	 * 通过newsBdId得到新闻版块名称
	 */
	public String getNewsBdNameById(int newsBdId);
	/*
	 * 得到所有新闻版块,分页
	 */
	public List<NewsBoard> getAllNewsBd(int status);
	/*
	 * 通过ID得到NewsBoard
	 */
	public NewsBoard getNewsBoardId(int newsBdId);
	/*
	 * 检查父类下面是否有子类
	 */
	public int checkPnewsBd(int newsBdId);
	/*
	 * 通过Id删除新闻版块
	 */
	public int delNewsBd(int newsBdId);
	/*
	 * 通过新闻版块id得到新闻版块
	 */
	public NewsBoard getNewsBdById(String id);
	/*
	 * 通过新闻版块名得到新闻版块
	 */
	public NewsBoard getNewsBdByName(String name);
	
}
