$(function(){
		$("body").changeCSS({});
	}); 

/*
	   * 导航栏动画效果
	   * mouseout，鼠标移开时失去焦点
	   * 用mouseover方法，鼠标移动到其上方时
	   * */
(function($) {
	  $.fn.changeCSS = function(options) {
	    var settings = $.extend();
	    return this.each(function() {
	      $(".loginButton").mouseout(blurLogin);
	      $(".loginButton").mouseover(focusLogin);
	      $(".loginButton").mouseup(focusLogin);
	      $(".loginButton").mousedown(blurLogin);
	      $("#userName").blur(blurName);
	      $("#userName").focus(focusName);
	      $("#password").blur(blurPassword);
	      $("#password").focus(focusPassword);
	    });
	  }
})(jQuery);

var can = 1;

$(document).ready(function(){
	changeHeight();
	window.parent.select(-1); //将首页相选中
	window.parent.changeTitle('重庆南山盆景网-登录');
	$('.title').animate({'margin-top':'80px','margin-bottom':'5px'},800);
});


function changeHeight() {
	var h = $(window).width()/2.8;
	$("body").height(h);
	if (h<430) {
		h = 430;
		$('.con').css('height',430);
	}
	window.parent.changeFrameHeight(h);  //调整页面高度
}

/*
 * 鼠标放在登录上时变色提醒
 */
function focusLogin() {
	$(".loginButton").css('background-color','#00AB72');
}

/*
 * 鼠标离开登录上时变色提醒
 */

function blurLogin() {
	$(".loginButton").css('background-color','#1ACDA1');
}



/*
 * 聚焦用户名时边框改变
 */
function focusName() {
	$("#userName").css({'border-color':'#3E6498','border-width':'1px'});
}

/*
 * 用户名输入框失去焦点时边框恢复
 */
function blurName() {
	$("#userName").css({'border-color':'#ffffff','border-width':'1px'});
}

/*
 * 聚焦密码时边框改变
 */
function focusPassword() {
	$("#password").css({'border-color':'#3E6498','border-width':'1px'});
}

/*
 * 用户名输入框失去焦点时边框恢复
 */
function blurPassword() {
	$("#password").css({'border-color':'#ffffff','border-width':'1px'});
}

/*
 * 登录前验证用户名密码是否填写
 */
function loginCheck() {
	if(can == 1){
		var userName = $("#userName").val();
		if (userName == "") {
			$("#loginTip").text("用户名不能为空");
			setTimeout("clearLoginTip()",4000);
			document.getElementById("userName").focus();
			return;
		}else {
			var password = $("#password").val();
			if (password.length<6) {
				$("#loginTip").text("请输入正确的密码");
				setTimeout("clearLoginTip()",4000);
				document.getElementById("password").focus();
			}else {
				loging();
				login();
			}
		}
	}
	
}

/*
 * ajax登录
 */
function login() {
	var params = $("#loginForm").serialize();
	params = decodeURIComponent(params,true); //解决中文编码问题
	$.ajax({
	    type: "POST",
	    url: "../user/login",
	    data: params,
	    success: function back(data){
	    	var r = eval(data);
	    	var type = r.result;
	    	if(type==1){
	    		window.parent.location.href="../manage";
	    	}else{
	    		if(type == 2){
	    			$("#loginTip").text("该用户不存在");
	    			setTimeout("clearLoginTip()",4000);
	    		}else if (type == 3) {
	    			$("#loginTip").text("密码错误");
	    			setTimeout("clearLoginTip()",4000);
				}else if (type == 4) {
	    			$("#loginTip").text("该用户已冻结，请联系管理员");
	    			setTimeout("clearLoginTip()",4000);
				}
	    		loginButton();
	    	}
	    	
	    },
	    error: function back(data){
	    	showMsg("red","系统错误，请重试。。。");
	    	loginButton();
	    }
	    });
}

/*
 * 将按钮修改至登录状态
 */
function loging() {
	$(".loginButton").text("登    录    中  ...");
	$('.loginButton').unbind();
	can = 0;
}

/*
 * 将按钮修改至正常状态
 */
function loginButton() {
	$(".loginButton").css('background-color','#1ACDA1');
	$(".loginButton").html("登&nbsp;&nbsp;&nbsp;&nbsp;录");
	$(".loginButton").bind("mouseover mouseup",function(){
		$(".loginButton").css('background-color','#00AB72');
		});
	$(".loginButton").bind("mouseout mousedown",function(){
		$(".loginButton").css('background-color','#1ACDA1');
		});
	can = 1;
}

/*
 * 清除登录提醒
 */
function clearLoginTip() {
	$("#loginTip").text("");
}

