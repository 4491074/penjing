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
	      $(".introductionType li").mouseout(blurLi);
	      $(".introductionType li").mouseover(focusLi);
	      $(".winPicture img").mouseover(scroll_end);
	      $(".winPicture img").mouseout(scroll_start);
	    });
	  }
})(jQuery);

var H = 40;
var i = 0;
var timer = null;

$(document).ready(function(){
	changeHeight();
	window.parent.select(1); //将首页相选中
	window.parent.changeTitle('重庆南山盆景非物质文化遗产');
	$(".introductionContext").find('p').eq(0).css('display','inline');
	changeTypeSelect(0);
	scroll_start();
});


function changeHeight() {
	var h = $("body").height();
	window.parent.changeFrameHeight(h);  //调整页面高度
}

/*
 * 鼠标离开导航栏时产生动画
 * 首先消除所有动画，然后执行当前动画
 */
function blurLi() {
	$(".typeSelect").stop();
	$(".typeSelect").animate({'top':X},150);
}


/*
 * 导航栏有鼠标悬浮时产生动画
 * 首先消除所有动画，然后执行当前动画
 */
function focusLi() {
	$(".typeSelect").stop();
	var X1 = $(this).index()*H;
	if($(this).index()>3){
		X1++;
	}
	$(".typeSelect").animate({'top':X1},100);
}

/*
 * 修改盆景类型选择器的位置
 */
function changeTypeSelect(index) {
	i = index;
	var l = $(".introductionType li").length-1;
	H = parseInt($(".introductionType ul").css('height'))/l;
//	var h1 = (parseInt($(".introductionType li").css('height'))-1)+"px";
	var h1 = $(".introductionType li").css('height');
	if (l > index) {
		X = index*H;
		if(index>3){
			X++;
		}
		$(".typeSelect").css({'top':X,'display':'inline','height':h1});
	}
	var type = $(".introductionType").find("li").eq(index).text();
	$(".nowType").text(type);
}

/*
 * 点击项目介绍的相应版块后跳转
 */
function introductionType(index) {
	if(index != i){
		var p = $(".introductionContext").find('p').eq(index);
		changeTypeSelect(index);
		$(".introductionContext").animate({'height':'0px'},300,function(){
			$(".introductionContext").find('p').css('display','none');
			p.css('display','inline');
			if (index == 5) {
				$(".introductionContext").animate({'height':'380px'},500);
			}else {
				$(".introductionContext").animate({'height':p.css('height')},500);
			}
		});
	}
}


/*
 * 获奖图片滚动
 */
function img_scroll(){
	if(w.scrollLeft < 2160){
		w.scrollLeft++;
	} else {
		w.scrollLeft = 0;
	}
}

/*
 * 获奖图片开始滚动
 */
function scroll_start(){
	timer = setInterval(img_scroll,10);
}

/*
 * 获奖图片停止滚动
 */
function scroll_end(){
	clearInterval(timer);
}

