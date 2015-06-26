package com.penjing.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.penjing.dao.BaseDao;
import com.penjing.entity.RemarkInfo;
import com.penjing.service.RemarkInfoService;
@Service("remarkInfoService")
public class RemarkInfoServiceImpl implements RemarkInfoService {
	@Resource
	private BaseDao<RemarkInfo> baseDao;
	
	public void setBaseDao(BaseDao<RemarkInfo> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public long changeVisitNum() {
		// TODO Auto-generated method stub
		String hql="from RemarkInfo";
		List<RemarkInfo> ls=baseDao.find(hql);
		if(ls.size()==0){
			RemarkInfo reinfo=new RemarkInfo();
			reinfo.setId(1);
			reinfo.setVisitNum((long)1);
			addNewRemarkInfo(reinfo);
			return (long)1;
		}else{
			List<RemarkInfo> lss=baseDao.find(hql);
			RemarkInfo o=lss.get(0);
			long num=o.getVisitNum()+(long)1;
			String hql1="update t_remarkinfo set visitNum="+num+" where id=1";
			baseDao.update(hql1);
			return num;
		}
	}

	@Override
	public List<RemarkInfo> getAllRemarkInfo() {
		// TODO Auto-generated method stub
		String hql="from RemarkInfo";
		List<RemarkInfo> ls=baseDao.find(hql);
		return ls;
	}

	@Override
	public void addNewRemarkInfo(RemarkInfo reinfo) {
		// TODO Auto-generated method stub
		baseDao.save(reinfo);
	}

	@Override
	public void updateRemarkInfo(RemarkInfo reinfo) {
		// TODO Auto-generated method stub
		baseDao.update(reinfo);
	}

	@Override
	public RemarkInfo getManagerInfo() {
		// TODO Auto-generated method stub
		String hql="from RemarkInfo";
		List<RemarkInfo> reinfo=baseDao.find(hql);
		if(reinfo.size()==0){
			return null;
		}else{
			return reinfo.get(0);
		}
	}

	@Override
	public void updateVisitNum(long visitNum) {
		// TODO Auto-generated method stub
		String hql="update t_remarkinfo set visitNum="+visitNum+" where id=1";
		baseDao.update(hql);
	}

}
