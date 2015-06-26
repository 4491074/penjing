<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>盆景网后台管理系统</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
  	*{margin: 1px;padding: 0px;}
  	.west li{list-style: none;margin-bottom: 1px;background-color: #EFEFEF;}
	.west li p{color: #666;padding: 5px 0px;text-align: center;
								  position: relative;z-index: 2;cursor:default;cursor: pointer;}
	.west .newsParent{background-color: #514F4F;}
	.west .newsParent p{color: #fff;}
	.west .child{display: none;}
	.userInfo{float: right;width: 250px;margin-top: 45px;font-size: 12px;font-family: 微软雅黑;}
</style>
</head>
<body class="easyui-layout">
	<div region="north" style="height: 90px; background-color: #FAFAFA;">
		<div class="userInfo">
			欢迎您：<font style="color: #FF4040;">${user.userName }</font>(<a href="home">返回首页</a>/
				<a href="JavaScript:logout()">退出登录</a>)<br>
			用户分组：<font style="color: #FF4040;">${role.roleName }</font>
		</div>
		<div style="font-size: 60px; font-family: 楷体;padding: 10px 0px 0px 10%;color: #2E8B57">重庆南山盆景网后台管理</div>
	</div>
	<div region="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
		</div>
	</div>
	<div region="west" class="west" style="width: 160px;" title="导航菜单" split="true">
		<ul style="position: relative;z-index: 0;">
			<li class="newsParent" id="parent-1"><p>用户管理</p></li>
			<ul  class="child-1 child">
				<li target="personalManage"><p>个人资料</p></li>
				<c:if test="${role.manageUser==true }">
    				 <li target="userManage"><p>所有用户管理</p></li>
    			</c:if>
				
			</ul>
			<c:if test="${role.publishPenJing==true || role.auditPenJing==true || role.managePenJing==true || role.manageOrder==true}">
				<li  class="newsParent" id="parent-2"><p>盆景管理</p></li>
				<ul class="child-2 child">
					<c:if test="${role.publishPenJing==true }">
	    				 <li target="myPenjingManage"><p>我的盆景</p></li>
	    			</c:if>
	    			<c:if test="${role.auditPenJing==true }">
	    				<li target="penjingCheckManage"><p>盆景审核</p></li>
	    			</c:if>
	    			<c:if test="${role.managePenJing==true }">
	    				<li target="penJingTypeManage"><p>盆景类型管理</p></li>
	    				<li target="penJingManage"><p>盆景管理</p></li>
	    			</c:if>
	    			<c:if test="${role.manageOrder==true }">
	    				<li target="orderManage"><p>订单管理</p></li>
	    			</c:if>
				</ul>
    		</c:if>
    		
    		<c:if test="${role.publishNews==true || role.auditNews==true || role.manageNews==true}">
				<li  class="newsParent" id="parent-3"><p>新闻管理</p></li>
				<ul class="child-3 child">
					<c:if test="${role.publishNews==true }">
	    				 <li target="myNewsManage"><p>我的新闻</p></li>
	    			</c:if>
	    			<c:if test="${role.auditNews==true }">
	    				<li target="newsCheck"><p>新闻审核</p></li>
	    			</c:if>
	    			<c:if test="${role.manageNews==true }">
	    				<li target="newsBdManage"><p>新闻板块管理</p></li>
	    				<li target="newsManage"><p>新闻管理</p></li>
	    			</c:if>
				</ul>
    		</c:if>
    		
    		<c:if test="${role.manageForum==true}">
				<li  class="newsParent" id="parent-4"><p>论坛管理</p></li>
				<ul class="child-4 child">
	    			<li target="topicManage"><p>主题帖管理</p></li>
				</ul>
    		</c:if>
    		
    		<c:if test="${role.manageRole==true}">
				<li  class="newsParent" id="parent-5"><p>权限管理</p></li>
				<ul class="child-5 child">
					<li target="roleManage"><p>角色管理</p></li>
				</ul>
    		</c:if>
    		
    		<c:if test="${role.manageInfo==true}">
				<li  class="newsParent" id="parent-6"><p>页面信息管理</p></li>
				<ul class="child-6 child">
					<li  target="getHeadImgManage"><p>首页图片管理</p></li>
					<li target="forumManage"><p>论坛展示信息管理</p></li>
					<li target="remarkInfoManage"><p>备注信息管理</p></li>
				</ul>
    		</c:if>
			
		</ul>
	</div>
	<div region="south" style="height: 25px;" align="center">版权所有：<font color="red">重庆南山盆景网</font></div>
	<script src="js/back.js"></script>
</body>
</html>