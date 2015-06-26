package com.penjing.service;

import java.util.List;

import com.penjing.entity.Role;

public interface RoleService {

	public int saveRole(Role role);
	
	/*
	 * 跟新角色
	 */
	public void updateRole(Role role);
	
	
	/*
	 * 删除角色
	 */
	public void deleteRole(Role role);
	
	/*
	 * 批量删除角色
	 */
	public int deleteRole(String roleIds);
	
	/*
	 * 查找所有角色
	 */
	public List<Role> findAllList(byte roleStatus);
	/*
	 * 查找所有角色菜单
	 */
	public List<Role> findAllListForBox(byte roleStatus);
	/*
	 * 根据角色id查找角色
	 */
	public Role find(int roleId);
	/*
	 * 分页查找所有
	 */
	public List<Role> pageList(int first,int max,int roleState);
	/*
	 * 查找角色数目
	 */
	public Long count(int roleState);
}
