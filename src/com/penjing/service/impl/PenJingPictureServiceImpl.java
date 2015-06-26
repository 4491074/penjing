package com.penjing.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.penjing.dao.BaseDao;
import com.penjing.entity.PenJingPicture;
import com.penjing.service.PenJingPictureService;
@Service("penJingPictureService")
public class PenJingPictureServiceImpl implements PenJingPictureService{
	@Resource
	private BaseDao<PenJingPicture> baseDao;
	@Override
	public List<PenJingPicture> getAllPenJingPicture(int status, int penJingId) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select new PenJingPicture(penJingPictureId,pictureUrl,pictureStatus) from PenJingPicture where penJing.penJingId=?");
		Object[] params = null;
			if(status < 0){
				params = new Object[]{penJingId};
			}else{
				hql.append(" and pictureStatus=?");
				params = new Object[]{penJingId,(byte)status};
			}
		return baseDao.find(hql.toString(), params);
	}
	@Override
	public void save(PenJingPicture penjingPicture) {
		// TODO Auto-generated method stub
		baseDao.save(penjingPicture);
	}
	@Override
	public List<PenJingPicture> get(String ids) {
		// TODO Auto-generated method stub
		List<PenJingPicture> pictures = new ArrayList<PenJingPicture>();
		String hql = "select new PenJingPicture(penJingPictureId,pictureUrl) from PenJingPicture where penJingPictureId=?";
		Object[] params = null;
		for(String str:ids.split(",")){
			params = new Object[]{Long.parseLong(str)};
			pictures.add(baseDao.get(hql, params));
		}
		return pictures;
	}
	@Override
	public int delPenJingPictures(String ids) {
		// TODO Auto-generated method stub
		String sql = "delete from t_penJingPicture where penJingPictureId in ("+ids+")";
		return baseDao.delete(sql);
	}
	@Override
	public void updatePenjingPicture(String ids, byte state) {
		// TODO Auto-generated method stub
		String sql = "update t_penJingPicture set pictureStatus="+state+" where penJingPictureId in ("+ids+")";
		baseDao.update(sql);
	}
	@Override
	public List<PenJingPicture> getPictureForInfo(int status, int penJingId) {
		// TODO Auto-generated method stub
				StringBuffer hql = new StringBuffer("select new PenJingPicture(pictureUrl) from PenJingPicture where penJing.penJingId=?");
				Object[] params = null;
					if(status < 0){
						params = new Object[]{penJingId};
					}else{
						hql.append(" and pictureStatus=?");
						params = new Object[]{penJingId,(byte)status};
					}
				return baseDao.find(hql.toString(), params);
	}
	
}
