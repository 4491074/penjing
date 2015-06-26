$(document).ready(function(){
	window.parent.titleChange($("#topicTitle").text());
});

function changeHigh(h) {
	$("#frame").height(h);
	var h1 = $("body").height();
	window.parent.changeHigh(h1);
}

function getUrlParam(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return unescape(r[2]); return null; //返回参数值
}

function getUrlParam1(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.top.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return unescape(r[2]); return null; //返回参数值
}

function replyCheck() {
	var value = $("#myReply").val();
	if (value.length<5) {
		$(".replyMsg").html("回复内容不能少于5个字");
		var t = setTimeout("noText()",3000);
	}else {
		topicSubmit();
	}
}

function topicSubmit() {
	var topicId = getUrlParam("topicId");
	var params = $("#myReply").serialize();
	params = decodeURIComponent(params,true); //解决中文编码问题
	$.ajax({
	    type: "POST",
	    url: "../reply/replyCreat?topicId="+topicId,
	    data: params,
	    success: function back(data){
	    	var r = eval(data);
	    	var type = r.result;
	    	if(type==1){
	    		$('#frame').attr('src', $('#frame').attr('src'));
	    		$("#myReply").val("");
	    		window.parent.showFloat("回复成功","middle");
	    		var t = setTimeout("window.parent.hideFloat()",3000);
	    	}else{
	    		if(type == 2){
	    			$(".replyMsg").html("请先登录");
	    			var t = setTimeout("noText()",3000);
	    		}else if (type == 3) {
	    			$("。replyMsg").html("系统错误，请重试");
	    			window.parent.location.reload();
				}
	    	}
	    },
	    error: function back(data){
	    	$(".replyMsg").html("系统错误");
			var t = setTimeout("noText()",3000);
	    }
	    });
}


function noText() {
	$(".replyMsg").html("");
}

function frameChange(src) {
	$('#frame').attr('src',src);
	$("html,body",window.parent.document).animate({scrollTop: $("#frame").offset().top}, 500); 
}

function textAreaFock(userName) {
	if(userName!=null){
		var text = $("#myReply").text();
		$("#myReply").text(text+"@"+userName+" ");
	}
	$("html,body",window.parent.document).animate({scrollTop: $(".replyPane").offset().top}, 500);
	var r =  document.getElementById("myReply")
	if(r!=null){
		r.focus();
	}
	
}

function toEnroll() {
	var url = window.top.location.pathname;
	url = url.substring(9, url.length);
	var param = window.top.location.search
	window.parent.location.href = "../enroll?from="+url+param;
}

