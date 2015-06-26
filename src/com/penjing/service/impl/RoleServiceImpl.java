package com.penjing.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.penjing.dao.BaseDao;
import com.penjing.entity.Role;
import com.penjing.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	
	@Resource
	private BaseDao<Role> baseDao;
	
	@Override
	public int saveRole(Role role) {
		// TODO Auto-generated method stub
		return (int)baseDao.save(role);
	}

	@Override
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		baseDao.update(role);
	}

	@Override
	public void deleteRole(Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Role> findAllList(byte roleStatus) {
		// TODO Auto-generated method stub
		String hql = "from Role where roleStatus=?";
		Object[] param = new Object[]{roleStatus};
		return baseDao.find(hql, param);
	}

	@Override
	public Role find(int roleId) {
		// TODO Auto-generated method stub
		String hql = "from Role where roleId=?";
		Object[] param = new Object[]{roleId};
		return baseDao.get(hql, param);
	}

	@Override
	public List<Role> pageList(int first, int max, int roleStatus) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from Role ");
		Object[] param = null;
		if(roleStatus<0){
		}else{
			hql.append("where roleState=?");
			param = new Object[]{roleStatus};
		}
		return baseDao.find(hql.toString(), param, first, max);
	}

	@Override
	public Long count(int roleState) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select count(*) from Role");
		Object[] param = null;
		if(roleState<0){
		}else{
			hql.append("where roleStatus=?");
			param = new Object[]{roleState};
		}
		return baseDao.count(hql.toString(), param);
	}

	@Override
	public int deleteRole(String roleIds) {
		// TODO Auto-generated method stub
		String sql = "delete from t_role where roleId in ("+roleIds+")";
		return baseDao.delete(sql);
	}

	@Override
	public List<Role> findAllListForBox(byte roleStatus) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select new Role(roleId,roleName,roleStatus) from Role");
		Object[] param = null;
		if(roleStatus<0){
		}else{
			hql.append("where roleStatus=?");
			param = new Object[]{roleStatus};
		}
		return baseDao.find(hql.toString(), param);
	}
}
