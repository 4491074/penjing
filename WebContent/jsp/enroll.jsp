<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{margin: 0px;padding: 0px;}
	body{background-color: #FAFAFA;}
	.con {width: 80%;margin-left: 10%;}
	.con .enrollPane {width: 80%;font-size: 18px;color: #666;font-family: 微软雅黑;background-color: #E2E2E3;}
	.con .enrollPane .enrollTitle{font-size: 34px;text-align: center;background-color: #FAFAFA;margin-bottom: 1px;padding: 5px 0px 20px 0px;}
	.con .enrollPane .enroll{padding: 0px 15px;background-color: #FAFAFA;margin-bottom: 1px;height: 55px;}
	.con .enrollPane .check{float: right;width: 35%;height: 20px;margin-top: 18px;font-size: 12px;font-family: 微软雅黑;}
	.con .enrollPane .check img{vertical-align:middle;}
	.con .enrollPane .input{float: right;margin-right: 15px;width: 50%}
	.con .enrollPane .input input{width: 100%;background:transparent;border: none;height: 45px;font-size: 12px;color: #333;
								  font-family: 微软雅黑;padding: 0px 5px;margin: 5px 0px;}
	.con .enrollPane .name{float: right;padding-top: 15px;margin-right: 5px;}
	.con .enrollPane .enroll .reSet{width: 20%;height: 35px;background-color: #FF4040;margin-right: 20%;float: right;color: #FFF;font-weight: 900;
									text-align: center;font-size: 20px;padding-top: 6px;cursor: pointer;}
	.con .enrollPane .enroll .enrollButton{width: 20%;height: 35px;background-color: #1ACDA1;margin-left: 20%;color: #FFF;font-weight: 900;
										  text-align: center;font-size: 20px;padding-top: 6px;cursor: pointer;}
</style>
</head>
<body>
	<div class="con">
		<div class="enrollPane">
			<div class="enrollTitle">重庆南山盆景网-注册</div>
			<form action="#" id="enrollForm">
				<div class="enroll userName">
					<div class="check userNameCheck">
						<img id="userNameImg">
						<font id="userNameText"></font>
					</div>
					<div class="input userNameInput">
						<input id="userName" type="text" name="user.userName" placeholder="请输入用户名" maxlength="10">
					</div>
					<div class="name">用户名：</div>
				</div>
				<div class="enroll password">
					<div class="check PasswordCheck">
						<img id="passwordImg">
						<font id="passwordText"></font>
					</div>
					<div class="input PasswordInput">
						<input id="password" type="password" name="user.password" placeholder="请输入密码" maxlength="16" >
					</div>
					<div class="name">密码：</div>
				</div>
				<div class="enroll password1">
					<div class="check password1Check">
						<img id="password1Img">
						<font id="password1Text"></font>
					</div>
					<div class="input password1Input">
						<input id="password1" type="password" placeholder="请确认密码" maxlength="16">
					</div>
					<div class="name">确认密码：</div>
				</div>
				<div class="enroll mail">
					<div class="check mailCheck">
						<img id="mailImg">
						<font id="mailText"></font>
					</div>
					<div class="input mailInput">
						<input id="mail" type="text" name="user.mail" placeholder="请输入邮箱" maxlength="20">
					</div>
					<div class="name">用户邮箱：</div>
				</div>
				<div class="enroll phone">
					<div class="check phoneCheck">
						<img id="phoneImg">
						<font id="phoneText"></font>
					</div>
					<div class="input phoneInput">
						<input id="phone" type="text" name="user.phone" placeholder="请输入手机号码" maxlength="20">
					</div>
					<div class="name">电话号码：</div>
				</div>
				<div class="enroll button" style="padding-top: 15px;margin-bottom: 0px;">
					<div class="reSet" onclick="reSet()">重&nbsp;&nbsp;&nbsp;&nbsp;置</div>
					<div class="enrollButton" id="enrollButton" onclick="checkForm()">注&nbsp;&nbsp;&nbsp;&nbsp;册</div>
				</div>
			</form>
		</div>
	</div>
	<script src="../js/jquery-1.11.2.min.js"></script>
	<script src="../js/enroll.js"></script>
</body>
</html>