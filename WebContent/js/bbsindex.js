var windowH = 500;

$(document).ready(function(){
	$("#frame").focus();
	windowH = $(window).height();
});

//从url地址中获取参数
function getUrlParam(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return unescape(r[2]); return null; //返回参数值
}

function changeHigh(h) {
	if (h>windowH) {
		$("#con").height(h);
	}else {
		$("#con").height(windowH);
	}
}

function trans(index) {
	if(index==1){
		$(".navbar-nav").find("li").eq(0).addClass("active").siblings().removeClass("active");
	}else if(index==2){
		$(".navbar-nav").find("li").eq(1).addClass("active").siblings().removeClass("active");
	}else{
		$(".navbar-nav").find("li").siblings().removeClass("active");
	}
	
}

/*
 * 登录面板信息显示
 * 控制颜色和内容
 */
function showMsg(color,content) {
	$("#errormsg").css("color",color);
	$("#errormsg").html(content);
}

function titleChange(title) {
	document.title=title;
}
//登录前简单验证用户名、密码
function loginCheck() {
	var userName = $("#name").val();
	if (userName == "") {
		showMsg("red","请输入用户名");
		document.getElementById("name").focus();
		return;
	}
	var password = $("#pass").val();
	if (password.length<6) {
		showMsg("red","请输正确密码");
		document.getElementById("pass").focus();
		return;
	}
	login();
}

//登陆提交
function login() {
	showMsg("#666", "正在登陆....")
	var params = $("#loginForm").serialize();
	params = decodeURIComponent(params,true); //解决中文编码问题
	$.ajax({
	    type: "POST",
	    url: "user/login",
	    data: params,
	    success: function back(data){
	    	var r = eval(data);
	    	var type = r.result;
	    	if(type==1){
	    		location.reload();
	    	}else{
	    		if(type == 2){
	    			showMsg("red","该用户不存在");
	    			var t = setTimeout("disappear()",4000);
	    		}else if (type == 3) {
	    			showMsg("red","密码错误");
	    			var t = setTimeout("disappear()",4000);
				}else if (type == 4) {
					showMsg("red","该用户已冻结，请联系管理员");
	    			var t = setTimeout("disappear()",4000);
				}
	    	}
	    	loginButton();
	    },
	    error: function back(data){
	    	showMsg("red","系统错误，请重试。。。");
	    	loginButton();
	    }
	    });
}
//消除错误提醒
function disappear() {
	showMsg("red","&nbsp;");
}
//登录面板重置
function mainReset() {
	$("#name").val("");
	$("#pass").val("");
	$("#authCode").val("");
}

//切换登录面板
function loginPane() {
	$('#myModal').modal('toggle');
}

function toEnroll() {
	var url = window.top.location.pathname;
	url = url.substring(9, url.length);
	var param = window.top.location.search
	location.href = "enroll?from="+url+param;
}

function logout() {
	$.ajax({
	    type: "POST",
	    url: "user/logout",
	    success: function back(data){
	    	var r = eval(data);
	    	var type = r.result;
	    	if(type==1){
	    		location.reload();
	    	}else{
	    		location.reload();
	    	}
	    },
	    error: function back(data){
	    	location.reload();
	    }
	    });
	
}

function showFloat(content,location) {
	var loca = location;
	var l = content.length;
	var w = 100;
	if(loca == null){
		loca = "middletop";
	}
	$("#floadContent").text(content);
	if (l<5) {
		w = l*20;
	}else if (l<10) {
		w = l*17;
	}else {
		w = l*13;
	}
	
	$(".floatDiv").css("width",w+"px");
	$(".floatDiv").floatdiv(loca);
	$(".floatDiv").show();
}

function changeFloatContent(content) {
	var l = content.length;
	var w = 100;
	if (l<5) {
		w = l*20;
	}else if (l<10) {
		w = l*17;
	}else {
		w = l*13;
	}
	$("#floadContent").text(content);
	var w = content.length*20;
	$(".floatDiv").css("width",w+"px");
}

function hideFloat() {
	$(".floatDiv").fadeOut(300);
}
