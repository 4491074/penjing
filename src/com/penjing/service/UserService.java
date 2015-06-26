package com.penjing.service;

import java.util.Date;
import java.util.List;

import com.penjing.entity.Role;
import com.penjing.entity.User;

public interface UserService {

public void saveUser(User user);
	
	/*
	 * 跟新用户
	 */
	public void updateUser(User user);
	
	/*
	 * 批量跟新用户角色
	 */
	public void updateUserRole(String roleId);
	
	/*
	 * 批量跟新用户角色
	 */
	public void updateUserRole(int userId,Role role);
	
	/*
	 * 跟新用户最后登录时间
	 */
	public void updateLastTime(int userId,Date time);
	
	/*
	 * 跟新用户头像
	 */
	public void updatePhoto(int userId,String photoPath);
	
	/*
	 * 用户自己修改资料
	 */
	public void update(int userId,String mail,String phone,String userDescription);
	
	/*
	 * 跟新用户角色以及用户状态
	 */
	public void update(int userId,int roleId,byte userStatus);
	
	/*
	 * 根据用户名查找用户
	 */
	public User findUserByuserName(String userName);
	
	/*
	 * 删除用户
	 */
	public void deleteUser(User user);
	
	/*
	 * 查找所有用户
	 */
	public List<User> findAllList(byte userStatus);
	
	/*
	 * 根据属性查找用户
	 * 用户名、邮箱或者电话
	 */
	public User findUserByValue(String field, String value);
	
	/*
	 * 获取用户资料
	 */
	public User getUserInfo(int userId);
	
	/*
	 * 获取用户资料
	 */
	public User getMyInfo(int userId);
	/*
	 * 分页查找所有
	 */
	public List<User> pageList(int first,int max,int userState);
	/*
	 * 查找用户数目
	 */
	public Long count(int userState);
	
	/*
	 * 修改user密码
	 */
	public void updateUserPwd(String newPwd,int userId);
}
