<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{margin: 0px;padding: 0px;}
	body{background-image: url("../img/login.jpg");-moz-background-size:100% 100%; /* 老版本的 Firefox */background-size:100% 100%;
		  background-repeat:no-repeat;}
	.con{width: 100%;height: 100%;background:rgba(255, 255, 255, 0.1)!important;position: absolute;}
	.con .login{width: 26%;margin-right: 16%;float: right;}
	.con .title{width: 100%;background:rgba(51, 51, 51, 0.7)!important;padding: 10px 0px;text-align: center;
				color: #C4C4C4;font-weight: 700;font-size: 28px;margin-bottom: 45px;margin-top: 60px;}
	.con .loginPane{width: 96%;background:rgba(51, 51, 51, 0.7)!important;padding: 15px 2%;}
	.con .loginPane #userName{height: 35px;padding: 2px 5px;background:transparent;border:1px solid #ffffff;width: 96%;
							  margin-bottom: 10px;color: white;autocomplete: off;}
	.con .loginPane #password{height: 35px;padding: 2px 5px;background:transparent;border:1px solid #ffffff;width: 96%;
							  margin-bottom: 15px;color: white;autocomplete: off;}
	.con .loginPane .middle{font-size: 12px;font-family: 微软雅黑;margin-bottom: 20px;color: #BFBFBF;}
	.con .loginPane .findPassword{float: right;}
	.con .loginPane #loginTip{float: right;text-align: left;width: 60%;color: #FF4040;}
	.con .loginPane .loginButton{width: 100%;height: 30px;background-color: #1ACDA1;padding-top: 6px;color: white;font-weight: 900;
								 text-align: center;font-size: 20px;margin-bottom: 12px;cursor: pointer;}
	.con .loginPane .enroll{font-size: 12px;font-family: 微软雅黑;color: #BFBFBF;}
	.con .loginTip{margin-left: 16%;width: 40%;margin-top: 80px;height: 100px;color: #D9D9D9;font-weight: 700;
				   font-size: 12px;padding-top: 50px;font-family: 微软雅黑;line-height: 150%;}
</style>
</head>
<body>
		<div class="con">
			<div class="login">
				<div class="title">
					欢迎登录重庆南山盆景网
				</div>
				<div class="loginPane">
					<form action="" id="loginForm">
						<input id="userName" type="text" name="user.userName" placeholder="请输入用户名" maxlength="10">
						<input id="password" type="password" name="user.password" placeholder="请输入密码" maxlength="20">
						<div class="middle">
							<div class="findPassword"><a href="#" style="text-decoration:none;color: #BFBFBF;">忘记密码？</a></div>
							<div id="loginTip"></div>
							<input type="checkbox" style="vertical-align:middle;zoom: 120%">记住密码
							
						</div>
						<div class="loginButton" onclick="loginCheck()">登&nbsp;&nbsp;&nbsp;&nbsp;录</div>
						<div class="enroll">还没有盆景网账号？<a href="../enroll" target="_parent" style="text-decoration:none;color: #61C2F2">立即注册</a></div>
					</form>
				</div>
			</div>
			<div class="loginTip">
				<div style="margin-bottom: 40px;"><font style="font-size: 36px;">致力于培育全国最好的盆景</font></div><br>
				•购买盆景请联系盆景下方的联系电话<br>
				•发布盆景请注册盆景网账号并联系管理员
			</div>
		</div>
	<script src="../js/jquery-1.11.2.min.js"></script>
	<script src="../js/login.js"></script>
</body>
</html>