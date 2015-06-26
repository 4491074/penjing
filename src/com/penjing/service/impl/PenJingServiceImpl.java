package com.penjing.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.penjing.dao.BaseDao;
import com.penjing.entity.PenJing;
import com.penjing.service.PenJingService;
@Service("penJingService")
public class PenJingServiceImpl implements PenJingService{
	@Resource
	private BaseDao<PenJing> baseDao;
	@Override
	public List<PenJing> getAllPenJing(int first, int max, int status, int userId) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select new PenJing(p.penJingId,p.penJingName,p.penJingTitle,p.penJingDescription,p.publishTime,p.penJingStatus,p.remark,t.penJingTypeId,t.penJingTypeName) from PenJing p join p.type t where p.publisher.userId=?");
		Object[] params = null;
			if(status < 0){
				params = new Object[]{userId};
			}else{
				hql.append(" and p.penJingStatus=?");
				params = new Object[]{userId,(byte)status};
			}
		hql.append(" order by p.penJingId asc");
		return baseDao.find(hql.toString(), params, first, max);
	}
	@Override
	public Long count(int status, String userId) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select count(*) from PenJing");
		Object[] params = null;
		if(userId == null){
			if(status < 0){
			}else{
				hql.append(" where penJingStatus=?");
				params = new Object[]{(byte)status};
			}
		}else{
			if(status < 0){
				hql.append(" where publisher.userId=?");
				params = new Object[]{Integer.parseInt(userId)};
			}else{
				hql.append(" where publisher.userId=? and penJingStatus=?");
				params = new Object[]{Integer.parseInt(userId),(byte)status};
			}
		}
		return baseDao.count(hql.toString(), params);
	}
	@Override
	public List<PenJing> getAllPenJing(int first, int max, int status,String penjingType) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select new PenJing(p.penJingId,p.penJingName,p.penJingTitle,p.penJingDescription,p.publishTime,p.mainPicture,p.penJingStatus,p.remark,t.penJingTypeName,pu.userName) from PenJing p join p.type t join p.publisher pu");
		Object[] params = null;
			if(status < 0){
				if(penjingType != null && !"".equals(penjingType)){
					hql.append(" where t.penJingTypeId=?");
					params = new Object[]{Integer.parseInt(penjingType)};
				}else{
				}
			}else{
				if(penjingType != null && !"".equals(penjingType)){
					hql.append(" where t.penJingTypeId=? and p.penJingStatus=?");
					params = new Object[]{Integer.parseInt(penjingType),(byte)status};
				}else{
					hql.append(" where p.penJingStatus=?");
					params = new Object[]{(byte)status};
				}
			}
		hql.append(" order by p.penJingId asc");
		List<PenJing> ps = baseDao.find(hql.toString(), params, first, max);
		return ps;
	}
	@Override
	public int savePenJing(PenJing penjing) {
		// TODO Auto-generated method stub
		return (Integer)baseDao.save(penjing);
	}
	@Override
	public void updatePenjing(PenJing penjing) {
		// TODO Auto-generated method stub
		String hql = "update PenJing set penJingName=?,penJingTitle=?,type=?,penJingDescription=?,penJingStatus=?,remark=? where penJingId=?";
		Object[] params = new Object[]{penjing.getPenJingName(),penjing.getPenJingTitle(),penjing.getType(),penjing.getPenJingDescription(),
				(byte)0,penjing.getRemark(),penjing.getPenJingId()
		};
		baseDao.update(hql,params);
	}
	@Override
	public void deletePenjing(int penjingId) {
		// TODO Auto-generated method stub
		baseDao.delete(getPenjing(penjingId));
	}
	@Override
	public PenJing getPenjing(int penjingId) {
		// TODO Auto-generated method stub
		return baseDao.get(PenJing.class, penjingId);
	}
	@Override
	public void updatePenjing(int penjingId,String mainPicture, byte penjingState) {
		// TODO Auto-generated method stub
		String hql = "update PenJing set mainPicture=?,penJingStatus=? where penJingId=?";
		Object[] params = new Object[]{mainPicture,penjingState,penjingId};
		baseDao.update(hql, params);
	}
	@Override
	public PenJing getPenjingForOrder(int penjingId) {
		// TODO Auto-generated method stub
		String hql = "select new PenJing(penJingId,penJingName,penJingTitle,mainPicture,penJingStatus) from PenJing where penJingId=?";
		Object[] params = new Object[]{penjingId};
		return baseDao.get(hql, params);
	}
	@Override
	public List<PenJing> getHomePenJing(int first, int max, int status,
			String penjingTypeId) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select new PenJing(p.penJingId,p.penJingName,p.mainPicture) from PenJing p join p.type t");
		Object[] params = null;
			if(status < 0){
				if(penjingTypeId != null && !"".equals(penjingTypeId)){
					hql.append(" where t.penJingTypeId=?");
					params = new Object[]{Integer.parseInt(penjingTypeId)};
				}else{
				}
			}else{
				if(penjingTypeId != null && !"".equals(penjingTypeId)){
					hql.append(" where t.penJingTypeId=? and p.penJingStatus=?");
					params = new Object[]{Integer.parseInt(penjingTypeId),(byte)status};
				}else{
					hql.append(" where p.penJingStatus=?");
					params = new Object[]{(byte)status};
				}
			}
		hql.append(" order by p.publishTime desc");
		List<PenJing> ps = baseDao.find(hql.toString(), params, first, max);
		return ps;
	}
	@Override
	public PenJing getPenjingInfo(int penjingId, byte penjingStatus) {
		// TODO Auto-generated method stub
		String hql = "select new PenJing(penJingId,penJingName,penJingTitle,penJingDescription,publishTime) from PenJing where penJingId=? and penJingStatus=?";
		Object[] params = new Object[]{penjingId,penjingStatus};
		return baseDao.get(hql, params);
	}
	
}
