package com.penjing.service;
import java.util.List;
import com.penjing.entity.RemarkInfo;
public interface RemarkInfoService {
	public long changeVisitNum();
	public List<RemarkInfo> getAllRemarkInfo();
	public void addNewRemarkInfo(RemarkInfo reinfo);
	public void updateRemarkInfo(RemarkInfo reinfo);
	public RemarkInfo getManagerInfo();
	public void updateVisitNum(long visitNum);
	
}
