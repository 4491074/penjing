package com.penjing.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.penjing.dao.BaseDao;
import com.penjing.entity.Role;
import com.penjing.entity.User;
import com.penjing.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private BaseDao<User> baseDao;

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		baseDao.save(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findUserByuserName(String userName) {
		// TODO Auto-generated method stub
		String hql = "select new User(u.userId,u.userName,u.password,u.photo,u.userStatus,r.roleId) from User u join u.role r where u.userName=? ";
		Object[] param = new Object[]{userName};
		return baseDao.get(hql, param);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAllList(byte userStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByValue(String field, String value) {
		// TODO Auto-generated method stub
		String hql = "select new User(userId) from User where "+field+"=?";
		Object[] param = new Object[]{value};
		return baseDao.get(hql, param);
	}

	@Override
	public void updateLastTime(int userId, Date time) {
		// TODO Auto-generated method stub
		String hql = "update User set lastTime=? where userId=?";
		Object[] param = new Object[]{time,userId};
		baseDao.update(hql, param);
	}

	@Override
	public User getUserInfo(int userId) {
		// TODO Auto-generated method stub
		String hql = "select new User(userId,userName,photo,enrollTime,lastTime) from User where userId=?";
		Object[] param = new Object[]{userId};
		return baseDao.get(hql, param);
	}

	@Override
	public void updateUserRole(String roleId) {
		// TODO Auto-generated method stub
		String sql = "update t_user set roleId=1 where roleId="+roleId;
		baseDao.update(sql);
	}

	@Override
	public void updateUserRole(int userId, Role role) {
		// TODO Auto-generated method stub
		String hql = "update User set role=? where userId=?";
		Object[] param = new Object[]{role,userId};
		baseDao.update(hql, param);
	}
	
	@Override
	public List<User> pageList(int first, int max, int userStatus) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select new User(u.userId,u.userName,u.mail,u.phone,u.enrollTime,u.lastTime,u.userDescription,r.roleName,r.roleId,u.userStatus) from User u join u.role r");
		Object[] param = null;
		if(userStatus<0){
		}else{
			hql.append(" where roleState=?");
			param = new Object[]{userStatus};
		}
		hql.append(" order by u.userId asc");
		return baseDao.find(hql.toString(), param, first, max);
	}

	@Override
	public Long count(int userState) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select count(*) from User");
		Object[] param = null;
		if(userState<0){
		}else{
			hql.append("where roleState=?");
			param = new Object[]{userState};
		}
		return baseDao.count(hql.toString(), param);
	}

	@Override
	public void update(int userId, int roleId, byte userStatus) {
		// TODO Auto-generated method stub
		String sql = "update t_user set roleId="+roleId+",userStatus="+userStatus+" where userId="+userId;
		baseDao.update(sql);
	}

	@Override
	public User getMyInfo(int userId) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select new User(u.userId,u.userName,u.photo,u.mail,u.phone,u.enrollTime,u.userDescription) from User u where u.userId=?");
		Object[] param = new Object[]{userId};
		return baseDao.get(hql.toString(), param);
	}

	@Override
	public void update(int userId, String mail, String phone,
			String userDescription) {
		// TODO Auto-generated method stub
		String hql = "update User set mail=?,phone=?,userDescription=? where userId=?";
		Object[] param = new Object[]{mail,phone,userDescription,userId};
		baseDao.update(hql, param);
	}

	@Override
	public void updatePhoto(int userId, String photoPath) {
		// TODO Auto-generated method stub
		String hql = "update User set photo=? where userId=?";
		Object[] param = new Object[]{photoPath,userId};
		baseDao.update(hql, param);
	}

	@Override
	public void updateUserPwd(String newPwd,int userId) {
		// TODO Auto-generated method stub
		String hql="update t_user set password='"+newPwd+"' where userId="+userId;
		baseDao.update(hql);
	}
}
