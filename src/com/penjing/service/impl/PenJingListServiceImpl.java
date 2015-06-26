package com.penjing.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.penjing.dao.BaseDao;
import com.penjing.entity.PenJingList;
import com.penjing.service.PenJingListService;
@Service("penjingListService")
public class PenJingListServiceImpl implements PenJingListService{
	@Resource
	private BaseDao<PenJingList> baseDao;

	@Override
	public void save(PenJingList penjingList) {
		// TODO Auto-generated method stub
		baseDao.save(penjingList);
	}
	
	@Override
	public void delete(long orderId) {
		// TODO Auto-generated method stub
		String sql = "delete from t_penJingList where orderId="+orderId;
		baseDao.delete(sql);
	}

	@Override
	public List<PenJingList> pageList(long orderId) {
		// TODO Auto-generated method stub
		String hql = "select new PenJingList(pjl.penJingListId,pjl.count,pjl.listNote,pj.penJingId,pj.penJingName,pj.penJingTitle,pj.mainPicture,pj.penJingStatus,pj.publisher.userName,pj.type.penJingTypeName) from PenJingList pjl join pjl.penJing pj where pjl.order.orderId=? order by penJingListId asc";
		Object[] param = new Object[]{orderId};
		return baseDao.find(hql, param);
	}

	@Override
	public long count(long order) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from PenJingList where order.orderId=?";
		Object[] param = new Object[]{order};
		return baseDao.count(hql, param);
	}

	@Override
	public void update(long penjingListId, int count, String listNote) {
		// TODO Auto-generated method stub
		PenJingList pjl = get(penjingListId);
		pjl.setCount(count);
		pjl.setListNote(listNote);
		baseDao.update(pjl);
	}



	@Override
	public PenJingList get(long penjingListId) {
		// TODO Auto-generated method stub
		return baseDao.get(PenJingList.class, penjingListId);
	}



	@Override
	public int delete(String penjingListIds) {
		// TODO Auto-generated method stub
		String sql = "delete from t_penJingList where penJingListId in ("+penjingListIds+")";
		return baseDao.delete(sql);
	}



	@Override
	public PenJingList get(long orderId, int penjingId) {
		// TODO Auto-generated method stub
		String hql = "select new PenJingList(pjl.penJingListId,pjl.count,pjl.listNote,pj.penJingId,pj.penJingName,pj.penJingTitle,pj.mainPicture,pj.penJingStatus) from PenJingList pjl join pjl.penJing pj where pj.penJingId=? and pjl.order.orderId=?";
		Object[] params = new Object[]{penjingId,orderId};
		return baseDao.get(hql, params);
	}
	
}
