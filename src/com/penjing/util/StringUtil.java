package com.penjing.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.penjing.entity.Role;
import com.penjing.entity.User;

@Component("stringUtil")
public class StringUtil{


	//判断是否为数字
	public static boolean isNumber(String str){
		 Pattern pattern = Pattern.compile("[0-9]*"); 
		 return pattern.matcher(str).matches();
	}
	
	//将首字母转换为大写
	public static String firsetoUpperCase(String str){
		str = str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
		return str;
	}
	
	public static void main(String[] args) throws ParseException {
		StringUtil su = new StringUtil();
		@SuppressWarnings("static-access")
		String r = su.date2DateSubtract(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-02-15 09:40:00"));
		System.out.println(r);
	}
	//去除字符串空格
	public static void removeBlank(User user){
		user.setUserName(user.getUserName().replaceAll(" ", ""));
		user.setPassword(user.getPassword().replaceAll(" ", ""));
		user.setMail(user.getMail().replaceAll(" ", ""));
		user.setPhone(user.getPhone().replaceAll(" ", ""));
	}
	
	//将时间转化为时间差
	public static String date2DateSubtract(Date date) throws ParseException{
		Date now = new Date();
		long diff = now.getTime() - date.getTime();
		long days = diff / (1000 * 60 * 60 * 24);
		long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
		long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
		if(days == 0){
			if(hours == 0){
				if(minutes == 0){
					return "小于1分钟";
				}
				return ""+minutes+"分钟之前";
			}
			return ""+hours+"小时之前";
		}else{
			if(days<4){
				return days+"天之前";
			}else if(days<366){
				return (new SimpleDateFormat("MM-dd").format(date)).toString();
			}
		}
		return (new SimpleDateFormat("yyyy-MM-dd").format(date)).toString();
	}
	
	//去除字符串后面的#
	public static String removeChar(String str){
		if(str.endsWith("#")){
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
	
	/*
	 * 计算分页栏所显示的页码
	 */
	public static void setPages(String page,String maxPage,List<String> pages){
		int p = Integer.parseInt(page);
		int mp = Integer.parseInt(maxPage);
		if(p>mp || p<1){
		}else{
			if(p>4){
				pages.add("...");
				pages.add(p-2+"");
				pages.add(p-1+"");
				pages.add(p+"");
			}else{
				for(int i = 2;i<=p;i++){
					pages.add(i+"");
				}
			}
			if(mp-p>3){
				pages.add(p+1+"");
				pages.add(p+2+"");
				pages.add("...");
				pages.add(mp+"");
			}else{
				for(int i = p+1;i<=mp;i++){
					pages.add(i+"");
				}
			}
		}
	}
	
	/*
	 * 将前端页面传过来的String转换为role中的权限
	 */
	public static void String2RoleAuthority(String roleAuthority,Role role){
		if(roleAuthority != null && !"".endsWith(roleAuthority)){
			String[] authority = roleAuthority.split(",");
			Method method = null;
			Class<Role> roleClass = Role.class;
			for(String str:authority){
				try {
					method = roleClass.getMethod("set"+str,Boolean.class);
					method.invoke(role, true);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	/*
	 * 将用户上传的头像文件命名为用户id的文件
	 */
	public static String photoName(int id,String name){
		int i=name.lastIndexOf(".");//原名称里倒数第一个"."在哪里 
		String ext=name.substring(i+1);//取得后缀，及"."后面的字符 
		name=id+"."+ext;//拼凑而成 
		return name;
	}
	
	/*
	 * 将用户上传的盆景图片按照时间，随机数命名
	 */
	public static String penjingName(String name){
		int i=name.lastIndexOf(".");//原名称里倒数第一个"."在哪里 
		String ext=name.substring(i+1);//取得后缀，及"."后面的字符
		long time = System.currentTimeMillis();
		int random = (int)(Math.random()*100);
		name=time+random+"."+ext;//拼凑而成 
		return name;
	}
}
