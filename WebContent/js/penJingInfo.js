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
	      $(".li").mouseover(focusLi);
	    });
	  }
})(jQuery);

var nu = 0;
var leftOffset = 0;
var h1 = 0;

$(document).ready(function(){
	changeH();
	picCenter($(".bigPic img"));
	$(".action").css("height",parseInt($(".action").css("height"))-4+"px");
	var h = $("body").height()+50;
	window.parent.changeFrameHeight(h);  //调整页面高度
});

function changeH() {
	$(".bigPic").css("height",$(".bigPic").css("width"));
	h1 = (parseInt($(".pics").css("width"))-50)/6;
	var h = h1+"px";
	$(".smallPics").css("height",h);
	$(".li").css("width",h);
	nu = $(".picsList .li").length;
	if(nu > 6){
		leftOffset = (nu-6)*h1+10*(nu-7);
	}else {
		$(".right").css("display","none");
		$(".left").css("display","none");
	}
	$(".picsList").css("width",(nu*h1+10*(nu+1))+"px");
	for (var int = 0; int < nu; int++) {
		picCenter($(".li").eq(int).find("img"));
	}
}

//向左滑动
function scrollToRight() {
	var left = $(".pics").scrollLeft();
	if (left < leftOffset) {
		$(".pics").animate({scrollLeft:left+h1+10},100);
	}
}

//向左滑动
function scrollToLeft() {
	var left = $(".pics").scrollLeft();
	if (left > 0) {
		$(".pics").animate({scrollLeft:left-h1-10},100);
	}
}

function picCenter(img) {
	img.removeAttr("style");
	var height = img.height();
	var width = img.width();
	var scale = "100%";
	if (width >= height) {
		scale = (0.5-height/(2*width))*100+"%";
		img.css({"width":"100%","margin-top":scale});
	}else {
		scale = (0.5-width/(2*height))*100+"%";
		img.css({"height":"100%","margin-left":scale});
	}
}

function focusLi() {
	var id = $(this).attr("class");
	if (id != "li action") {
		$(".action").css("height",parseInt($(".action").css("height"))+4+"px");
		$(this).addClass("action").siblings().removeClass("action");
		$(this).css("height",parseInt($(this).css("height"))-4+"px");
		$(".bigPic img").attr("src",$(this).find("img").attr("src"));
		picCenter($(".bigPic img"));
	}
	
}



