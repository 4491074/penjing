<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎进入重庆南山盆景网</title>

<style type="text/css">
	*{margin: 0px;padding: 0px;}
	body{background-color: #FAFAFA;}
	.topLogo{width: 100%;height: 90px;}
	.topLogo .login{float: right;width: 200px;margin-top: 60px;font-size: 12px;font-family: 微软雅黑;}
	.navbar{width: 100%;background-color: #00c896;height: 40px;margin-bottom: 5px;z-index: 0;position: relative;}
	.navbar .navbar-li{height: 100%;margin-left: 10%;list-style:none;text-align: center;width: 80%;z-index: 2;position: relative;}
	.navbar .navbar-li li{color: white;font-weight: 900;float: left;height: 25px;width: 12.5%;padding-top: 12px;text-align: center;cursor: pointer;}
	.bottom{width: 100%;background-color: #43CD80;height: 90px;margin-top: 10px;text-align: center;}
	#selectDiv{width: 10%;height: 40px;background-color: #008264;position: absolute;top: 0px;display: none;z-index: 1;}
	.floatDiv{width: 0px;padding: 5px 0px;margin: 0px;text-align: center;word-wrap: break-word;word-break: normal;background-color: #D1EEEE;
    			border-radius:5px;-moz-border-radius:25px;box-shadow: 3px 3px 3px #888888;display: none;}
    .floatDiv font{float: right;margin-right: 10px;font-size: 12px;margin-bottom: 0px;font-family: 微软雅黑;font-weight: 700;}
</style>
</head>
<body>
	<!-- 顶部logo开始 -->
	<div class="topLogo">
		<div class="login">
			<%
				if(session.getAttribute("user")==null){
			%>
				<a href="JavaScript:void(0);" onclick="navbar('login')">登录</a>/
				<a href="JavaScript:void(0);" onclick="navbar('enroll')">注册</a>   	
			<%		
				}else {
			%>
				<a href="manage">进入后台管理</a>/
				<a href="JavaScript:void(0)" onclick="logout()">退出</a> 	
			<%		
				}
			%>
			
		</div>
		<div style="font-size: 60px; font-family: 楷体;padding: 10px 0px 0px 10%;">
			<a href="home" style="text-decoration:none;color: #2E8B57;">重庆南山盆景网</a>
		</div>
	</div>
	<!-- 顶部logo结束 -->
	
	<!-- 顶部导航条开始 -->
	<div class="navbar">
		<ul class="navbar-li">
			<li onclick="navbar('home')">盆景首页</li>
			<li onclick="navbar('cultural')">非物质文化遗产</li>
			<li onclick="navbar('lead')">领导关怀</li>
			<li onclick="navbar('activity')">活动纪实</li>
			<li onclick="navbar('knowledge')">盆景知识</li>
			<li onclick="navbar('news')">新闻列表</li>
			<li onclick="navbar('enterprise')">企业</li>
			<li onclick="navbar('forums')">论坛</li>
		</ul>
		<div id="selectDiv"></div>
	</div>
	<!-- 顶部导航条结束-->
	
	<div class="con" id="con">
		<%
			String nowPage = (String)request.getAttribute("nowPage");
			if(nowPage == null || nowPage == ""){
				nowPage = "penjings/homePenjing?penjingType=0";
			}
		%>
		<iframe id="frame" frameborder="0" scrolling="no" style="width: 100%;height: 500px;" src="<%=nowPage %>"></iframe>
	</div>
	
	<!-- 底部信息栏开始 -->
	<div class="bottom" style="padding-top: 20px;font-size: 12px;font-family: 微软雅黑;color: #121212">
		<ul style="list-style:none;">
			<li>重庆南山盆景网提供&nbsp;&nbsp;Rights Reserved</li>
			<li>渝ICP备15001446号</li>
			<li>客服电话：${managerTel }</li>
			<li>
				<a href="JavaScript:void(0);" onclick="navbar('jsp/building.jsp?li=-1')">意见反馈</a>&nbsp;&nbsp;
				<a href="JavaScript:void(0);" onclick="navbar('jsp/building.jsp?li=-1')">联系我们</a>
			</li>
		</ul>
		<span style="float: right;margin-right: 10px;">访问量：<%=request.getServletContext().getAttribute("visitNum") %></span>
	</div>
	<!-- 底部信息栏结束 -->
	
	<div class="floatDiv">
		<font id="floadContent"></font>
  		<img id="loadingImg" alt="loading">
	</div>
	<script src="js/jquery-1.11.2.min.js"></script>
	<script src="js/jqueryFloat.js"></script>
	<script src="js/index.js"></script>
</body>
</html>