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
	      $(".enrollButton").mouseout(blurEnroll);
	      $(".enrollButton").mouseover(focusEnroll);
	      $(".reSet").mouseout(blurReset);
	      $(".reSet").mouseover(focusReset);
	      $("#userName").blur(blurUserName);
	      $("#userName").focus(focusUserName);
	      $("#password").blur(blurPassword);
	      $("#password").focus(focusPassword);
	      $("#password1").blur(blurPassword1);
	      $("#password1").focus(focusPassword1);
	      $("#mail").blur(blurMail);
	      $("#mail").focus(focusMail);
	      $("#phone").blur(blurPhone);
	      $("#phone").focus(focusPhone);
	    });
	  }
})(jQuery);

var can = 1;

$(document).ready(function(){
	changeHeight();
	window.parent.select(-1); //将首页相选中
	window.parent.changeTitle('重庆南山盆景网-注册');
});


function changeHeight() {
	var h = $("body").height();
	if (h<430) {
		h = 430;
		$('.con').css('height',430);
	}
	window.parent.changeFrameHeight(h);  //调整页面高度
}

/*
 * 鼠标放在登录上时变色提醒
 */
function focusEnroll() {
	$(this).css('background-color','#00AB72');
}

/*
 * 鼠标离开登录上时变色提醒
 */

function blurEnroll() {
	$(this).css('background-color','#1ACDA1');
}

/*
 * 鼠标放在重置上时变色提醒
 */
function focusReset() {
	$(this).css('background-color','#FF291E');
}

/*
 * 鼠标离开重置上时变色提醒
 */

function blurReset() {
	$(this).css('background-color','#FF4040');
}
/*
 * 鼠标聚焦用户名框时出现提醒
 */
function focusUserName() {
	repl('userName','../img/tip.gif','10个字以内的汉字、数字或字母','#666');
}

/*
 * 鼠标离开用户名时验证用户输入并给出提示
 */
function blurUserName() {
	var userName = $(this).val().replace(/\ +/g,""); //去掉空格
	 $(this).val(userName);
	if (userName=="") {
		  repl('userName','../img/wrong.png','用户名不能为空','#FF4040');
		}else {
			repl('userName','../img/loading.gif','正在验证','#666');
			isHave('userName',userName);
		}
	
}

/*
 * 鼠标聚焦密码框时出现提醒
 */
function focusPassword() {
	repl('password','../img/tip.gif','6至16位密码','#666');
}

/*
 * 鼠标离开密码时验证用户输入并给出提示
 */
function blurPassword() {
	 var password = $(this).val().replace(/\ +/g,""); //去掉空格
	  $(this).val(password);
	  if (password=="") {
		  repl('password','../img/wrong.png','密码不能为空','#FF4040');
		}else if (password.length<6) {
			repl('password','../img/wrong.png','密码不能低于6位','#FF4040');
			}else{
				repl('password','../img/right.png','通过验证','#00ee00');
			}
}

/*
 * 鼠标聚焦密码确认框时出现提醒
 */
function focusPassword1() {
	repl('password1','../img/tip.gif','两次密码必须一样','#666');
}

/*
 * 鼠标离开密码确认时验证用户输入并给出提示
 */
function blurPassword1() {
	 var password =  $("#password").val();
	  var password1 = $(this).val().replace(/\ +/g,"");
	  $(this).val(password1);
	  if(password1==""){
		  repl('password1','../img/wrong.png','密码确认不能为空','#FF4040');
		}else if (password1==password) {
			repl('password1','../img/right.png','通过验证','#00ee00');
			}else{
				repl('password1','../img/wrong.png','两次密码不相同','#FF4040');
			}
}

/*
 * 鼠标聚焦邮箱框时出现提醒
 */
function focusMail() {
	repl('mail','../img/tip.gif','请输入正确的邮箱','#666');
}

/*
 * 鼠标离开邮箱框时验证用户输入并给出提示
 */
function blurMail() {
	 var mail = $(this).val().replace(/\ +/g,"");
	  $(this).val(mail);
	  if(mail==""){
		  repl('mail','../img/wrong.png','用户邮箱不能为空','#FF4040');
		}else if(!(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test(mail))){
			repl('mail','../img/wrong.png','邮箱格式错误','#FF4040');
			}else{
				repl('mail','../img/loading.gif','正在验证','#666');
				isHave('mail',mail);
			}
}

/*
 * 鼠标聚焦手机框时出现提醒
 */
function focusPhone() {
	repl('phone','../img/tip.gif','请输入正确的手机号码','#666');
}

/*
 * 鼠标离开手机号码框时验证手机号码格式并给出提示
 */
function blurPhone() {
	var phone = $(this).val().replace(/\ +/g,"");
	  $(this).val(phone);
	  if(phone==""){
		  repl('phone','../img/wrong.png','手机号码不能为空','#FF4040');
		}else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(phone))){
			repl('phone','../img/wrong.png','手机格式错误','#FF4040');
			}else{
				repl('phone','../img/loading.gif','正在验证','#666');
				isHave('phone',phone);
			}
}


/*
 * 替换文本框后的提示信息和图片
 * name:指定替换对象
 * img:将图片替换为
 * text:将文本替换为
 * color:文字颜色
 */
function repl(name,imge,text,color) {
	var img = "#"+name+"Img";
	var t = "#"+name+"Text";
	$(img).replaceWith("<img id='"+name+"Img"+"' alt='提示' src='"+imge+"'>");
	$(t).replaceWith("<font id='"+name+"Text"+"' style='color: "+color+"'>"+text+"</font>")
}

/*
 * 重置
 */
function reSet() {
	window.parent.location.reload();
}

/*
 * ajax验证用户名、邮箱、手机是否已经注册
 */
function isHave(name,value) {
	var params = {
	           value : value,
	        }
	$.ajax({
	    type: "POST",
	    url: "../user/check_"+name,
	    data: params,
	    success: function back(data){
	    	var r = eval(data);
	    	var result = r.result;
	    	if (result == 1) {
	    		repl(name,'../img/right.png','通过验证','#00ee00');
			}else {
				repl(name,'../img/wrong.png','已被注册','#FF4040');
			}
	    },
	    error: function back(data){
	    	repl(name,"../img/wrong.png","系统错误",'#FF4040');
	    }
	    });
}
/*
 * 点击注册时验证表单
 * 验证成功后ajax提交
 */
function checkForm() {
	if(can == 1){
		document.getElementById("enrollButton").focus();
		var tip = "通过验证";
		var userNameText = $("#userNameText").text();
		var passwordText = $("#passwordText").text();
		var password1Text = $("#password1Text").text();
		var mailText = $("#mailText").text();
		var phoneText = $("#phoneText").text();
		var password = $("#password").val().replace(/\ +/g,"");
		var password1 = $("#password1").val().replace(/\ +/g,"");
		if (userNameText!=tip) {
			document.getElementById("userName").focus();
			alertNotNull(userNameText);
		}else if (passwordText!=tip) {
			document.getElementById("password").focus();
			alertNotNull(passwordText);
		}else if (password1Text!=tip) {
			document.getElementById("password1").focus();
			alertNotNull(password1Text);
		}else if (mailText!=tip) {
			document.getElementById("mail").focus();
			alertNotNull(mailText);
		}else if (phoneText!=tip) {
			document.getElementById("phone").focus();
			alertNotNull(phoneText);
		}else if (password1!=password) {
			document.getElementById("password1").focus();
			repl('password1','../img/wrong.png','两次密码不相同','#FF4040');
		}else {
			enrolling();
			enroll();
		}
	}
}

/*
 * 将按钮切换至正在注册状态
 */
function enrolling() {
	$(".enrollButton").html("注&nbsp;&nbsp;册&nbsp;&nbsp;中...");
	$('.enrollButton').unbind();
	can = 0;
}

/*
 * 将按钮切换至可按状态
 */
function canEnroll() {
	$(".enrollButton").css('background-color','#1ACDA1');
	$(".enrollButton").html("注&nbsp;&nbsp;&nbsp;&nbsp;册");
	$(".enrollButton").bind("mouseover",function(){
		$(".enrollButton").css('background-color','#00AB72');
		});
	$(".enrollButton").bind("mouseout",function(){
		$(".enrollButton").css('background-color','#1ACDA1');
		});
	can = 1;
}

//从url地址中获取参数
function getUrlParam(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.parent.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return unescape(r[2]); return null; //返回参数值
}

/*
 * ajax注册用户
 */
function enroll() {
	window.parent.showFloat("正在注册","img/loading1.gif",null,80);
	var params = $("#enrollForm").serialize();
	params = decodeURIComponent(params,true); //解决中文编码问题
	var from = getUrlParam("from");
	$.ajax({
	    type: "POST",
	    url: "../user/enroll",
	    data: params,
	    success: function back(data){
	    	var r = eval(data);
	    	var result = r.result;
	    	if(from == null || from == ""){
	    		from = "login";
	    	}
	    	if(result==1){
	    		window.parent.location.href="../"+from;
	    	}else{
	    		if(result == 2){
	    			repl('password','../img/wrong.png','系统出错','#FF4040');
	    			repl('password1','../img/wrong.png','系统出错','#FF4040');
	    			window.parent.hideFloat();
	    		}
	    		canEnroll();
	    	}
	    },
	    error: function back(data){
	    	repl('userName','../img/wrong.png','系统错误','#FF4040');
	    	window.parent.hideFloat();
	    	canEnroll();
	    }
	    });
}

//过滤掉空字符串
function alertNotNull(value) {
	if (value!="") {
		alert(value);
	}
}
