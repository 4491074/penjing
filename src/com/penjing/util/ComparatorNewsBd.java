package com.penjing.util;

import java.util.ArrayList;
import java.util.List;

import com.penjing.entity.NewsBoard;

public class ComparatorNewsBd{
	private List<NewsBoard> snewsbd; 
	public ComparatorNewsBd(List<NewsBoard> snewsbd){
		this.snewsbd=snewsbd;
	}

	
	public 	List<NewsBoard> sortNewsBoard(){
		List<NewsBoard> pnewsbd=new ArrayList<NewsBoard>();
		for(NewsBoard nbd:snewsbd){
			if(nbd.getNewsBoardparent()==null){
				pnewsbd.add(nbd);
			}
		}
		List<NewsBoard> newsbd=new ArrayList<NewsBoard>();
		for(NewsBoard pnbd:pnewsbd){
			newsbd.add(pnbd);
			for(NewsBoard snbd:snewsbd){
				if(snbd.getNewsBoardparent()!=null){
					if(snbd.getNewsBoardparent().getNewsBoardId()==pnbd.getNewsBoardId())
						newsbd.add(snbd);
				}
			}
		}
		return newsbd;
	}

}
