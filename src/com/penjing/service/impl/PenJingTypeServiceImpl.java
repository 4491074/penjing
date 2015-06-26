package com.penjing.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.penjing.dao.BaseDao;
import com.penjing.entity.PenJing;
import com.penjing.entity.PenJingType;
import com.penjing.service.PenJingTypeService;
@Service("penJingTypeService")
public class PenJingTypeServiceImpl  implements PenJingTypeService{
	@Resource
	private BaseDao<PenJingType> baseDao;
	@Resource
	private BaseDao<PenJing> penJingDao;
	@Override
	public List<PenJingType> getAllPenJingType(int first, int max, int status) {
		// TODO Auto-generated method stub
		String hql=null;
		if(status==-1){
			hql="from PenJingType";
		}else{
			hql="from PenJingType where penJingTypeStatus="+status;
		}
		List<PenJingType> pjtypes=baseDao.find(hql, null, first, max);
		return pjtypes;
	}

	@Override
	public Long countPenJing(int typeId) {
		// TODO Auto-generated method stub
		String hql="select count(*) from PenJing where type="+typeId;
		return penJingDao.count(hql, null);
	}

	@Override
	public void addPenJingType(PenJingType p) {
		// TODO Auto-generated method stub
		baseDao.save(p);
	}

	
	@Override
	public int delPenJingType(String typeId) {
		// TODO Auto-generated method stub
		String sql="delete from t_penJingType where penJingTypeId in ("+typeId+")";
		return penJingDao.delete(sql);
	}


	@Override
	public void updatePenJingType(PenJingType p) {
		// TODO Auto-generated method stub
		baseDao.update(p);
	}


	@Override
	public Long count(int typeState) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select count(*) from PenJingType");
		Object[] param = null;
		if(typeState>-1){
			hql.append(" where penJingTypeStatus=?");
			param = new Object[]{typeState};
		}
		return baseDao.count(hql.toString(), param);
	}

	@Override
	public List<PenJingType> findAllListForBox(int status) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select new PenJingType(penJingTypeId,penJingTypeName,penJingTypeStatus) from PenJingType");
		Object[] param = null;
		if(status<0){
		}else{
			hql.append("where penJingTypeStatus=?");
			param = new Object[]{status};
		}
		return baseDao.find(hql.toString(), param);
	}
	
	@Override
	public List<PenJingType> findAllList(byte status) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select new PenJingType(penJingTypeId,penJingTypeName) from PenJingType");
		Object[] param = null;
		if(status<0){
		}else{
			hql.append(" where penJingTypeStatus=?");
			param = new Object[]{status};
		}
		return baseDao.find(hql.toString(), param);
	}

	
}
