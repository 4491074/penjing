$(function(){
		$(".navbar-li").changeCSS({});
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
	      $(".navbar-li li").mouseout(blurLi);
	      $(".navbar-li li").mouseover(focusLi);
	    });
	  }
})(jQuery);

/*
 * 导航栏选中框的Y轴坐标
 */
var Y = 0;
var he = 0;

$(document).ready(function(){
	he = $(window).height()-100;
});

/*
 * 导航栏有鼠标悬浮时产生动画
 * 首先消除所有动画，然后执行当前动画
 */
function focusLi() {
	var i = $("#selectDiv").offset().left;
	if(Y==0 && i==0){
		$("#selectDiv").css({'left':0,'display':'inline'});
	}
	$("#selectDiv").stop();
	var Y1 = $(this).offset().left;
	$("#selectDiv").animate({'left':Y1},300);
}

/*
 * 鼠标离开导航栏时产生动画
 * 首先消除所有动画，然后执行当前动画
 */
function blurLi() {
	$("#selectDiv").stop();
	$("#selectDiv").animate({'left':Y},200,function(){
		$("#selectDiv").stop();
		if(Y == 0){
			$("#selectDiv").css({'display':'none'});
		}
	});
}

/*
 * 设置导航栏选中项目
 * 也就是当前页面
 * 如果当前页面不属于导航栏的项目时，index<0
 */
function select(index) {
	$("#selectDiv").stop();
	if(index < 0){
		Y = 0;
		$("#selectDiv").css({'left':Y,'display':'none'});
	}else {
		var li = $(".navbar-li").find("li").eq(index);
		Y = li.offset().left;
		$("#selectDiv").css({'left':Y,'display':'inline'});
		li.css('color','#333');
	}
}

/*
 * 设置iframe的高度
 * 子页面加载完成时通过此函数调整高度
 */
function changeFrameHeight(height) {
	if(height > he){
		$("#frame").css('height',height);
	}else {
		$("#frame").css('height',he);
	}
	
}

/*
 * 设置该页面窗口的title
 * 子页面加载完成时通过此函数修改
 */
function changeTitle(title) {
	document.title=title;
}

/*
 * 导航栏跳转
 */
function navbar(src) {
	if(src != null){
		if(src == "enroll"){
			var url = window.top.location.pathname;
			url = url.substring(9, url.length);
			var param = window.top.location.search
			window.parent.location.href = "enroll?from="+url+param;
		}else {
			window.parent.location.href=src;
		}
	}
}

/*
 * 将浮动提醒显示出来
 * content为文字内容
 * img为图片地址，没有图片时为null
 * location为浮动层相对于屏幕的出现位置
 * length为浮动提醒的宽度，需要自己慢慢调整
 */
function showFloat(content,img,location,length) {
	var loca = location;
	if(loca == null){
		loca = "middletop";
	}
	if (img == null) {
		$("#loadingImg").replaceWith("<img id='loadingImg'>");
	}else {
		$("#loadingImg").replaceWith("<img id='loadingImg' alt='loading' src='"+img+"'>");
	}
	$("#floadContent").text(content);
	
	$(".floatDiv").css("width",length+"px");
	$(".floatDiv").floatdiv(loca);
	$(".floatDiv").show();
}
/*
 * 修改浮动提醒上的文字
 * content为文字内容
 * img为图片地址，没有图片时为null
 * length为浮动提醒的宽度，需要调整
 */
function changeFloatContent(content,img,length) {
	if (img == null) {
		$("#loadingImg").replaceWith("<img id='loadingImg'>");
	}else {
		$("#loadingImg").replaceWith("<img id='loadingImg' alt='loading' src='"+img+"'>");
	}
	$("#floadContent").text(content);
	$(".floatDiv").css("width",length+"px");
}

/*
 * 浮动提醒退出
 */
function hideFloat() {
	$(".floatDiv").fadeOut(300);
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

