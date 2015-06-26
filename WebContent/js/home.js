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
	      $(".penJingType li").mouseout(blurLi);
	      $(".penJingType li").mouseover(focusLi);
	      $(".penJingShow li .penJingImg").mouseover(getTip);
	      $(".penJingShow li .penJingImg").mouseout(delTip);
	      $(".carousel").mouseover(stopCarousel);
	      $(".carousel").mouseout(startCarousel);
	    });
	  }
})(jQuery);

var X = 0;
var H = 40;
var T;
var time = 2;
var TextH = '52px';
var id = 0;
var h1;
var h2;

$(document).ready(function(){
	setPenJingHeight();
	var h = $("body").height();
	window.parent.changeFrameHeight(h);  //调整页面高度
	window.parent.select(0); //将首页相选中
	window.parent.changeTitle('欢迎进入重庆南山盆景网');
	getId();
	pictureCen();
	T = self.setInterval("clock()",5000);
});

function getUrlParam(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return unescape(r[2]); return null; //返回参数值
}
/*
 * 获取盆景类型Id
 */
function getId() {
	id = getUrlParam("penjingType");
	if(id == null || id == 0){
		changeTypeSelect(0);
		return;
	}else{
		$(".penJingType li").each(function(){
			if ($(this).attr('target')==id) {
				changeTypeSelect($(this).index());
				$(".nowType").text($(this).text())
				return;
			}
		});
	}
}

/*
 * 修改盆景类型选择器的位置
 */
function changeTypeSelect(index) {
	var l = $(".penJingType li").length-1;
	H = parseInt($(".penJingType ul").css('height'))/l;
	var h1 = $(".penJingType li").css('height');
	if (l > index) {
		X = index*H;
		$(".typeSelect").css({'top':X,'display':'inline','height':h1});
	}
//	$("html,body",window.parent.document).animate({scrollTop: $(".penJingShow").offset().top}, 800);
}

function pictureCen() {
	var nu = 0;
	$(".img").each(function(){
		picCenter($(this));
		nu++;
	});
	if(nu<6){
		$(".loadMore").css('display','none');
	}
}

/*
 * 图片居中显示
 */
function picCenter(img) {
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

/*
 * 导航栏有鼠标悬浮时产生动画
 * 首先消除所有动画，然后执行当前动画
 */
function focusLi() {
	$(".typeSelect").stop();
	var X1 = $(this).index()*H;
	$(".typeSelect").animate({'top':X1},300);
}

/*
 * 鼠标离开导航栏时产生动画
 * 首先消除所有动画，然后执行当前动画
 */
function blurLi() {
	$(".typeSelect").stop();
	$(".typeSelect").animate({'top':X},150);
}

function changeType(id) {
	if(id == null){
		id = 0;
	}
	window.top.location.href = "../home?id="+id;
}

/*
 * 点击获取更多时进行验证
 */
function get() {
	var nu = $(".penJingShow li").length-1;
	if(nu%6 == 0){
		getMore(nu);
	}else {
		$(".loadMore").css('display','none');
	}
//	getMore( nu,penjingType);
}

/*
 * 通过ajax获取用户的帖子以及回帖信息
 * first为起始数
 * 每次最多获得3条记录
 * 返回json数据
 */
function getMore(first) {
	$.ajax({
	    type: "POST",
	    url: "../penjings/getMore?first="+first+"&penjingType="+id,
	    success: function back(data){
	    	var r = eval(data);
	    	var result = r.result;
	    	if(result==1){
	    		showData(r.penjing);
	    	}else if(result == 2){
	    		alert("获取数据失败");
	    	}else if(result == 3){
	    		$(".loadMore").css('display','none');
	    	}
	    },
	    error: function back(data){
	    	alert("系统错误")
	    }
	    });
}

/*
 * ajax获取数据成功后用jquery遍历JSON数据
 * 将数据循环输出在页面
 * type对应topic或reply类型数据
 * index指下标
 * item指代对应元素内容
 */
 function showData(data) {
	 var i = 0;
		 $(data).each(function(index,item){
			 if((index%3)==0){
				 $("#mark").before(
						 "<li class='penJingFir'>"+
									"<div class='penJingBorder'>"+
										"<div class='penJingImg penJingImg_"+index+"' style='height: "+h1+"'>"+
											"<a href='../penjingInfo?id="+item.penJingId+"' target='_parent'>"+
												"<img class='img img_"+index+"' alt='盆景' src='../"+item.mainPicture+"'>"+
											"</a>"+
										"</div>"+
										"<hr class='penJingHr'>"+
										"<div class='info'>"+
											"<div class='penJingInfo'>"+
												"<div>"+item.penJingName+"(编号："+item.penJingId+")</div>"+
												"<div class='phone'>联系方式：18723385990（村委）</div>"+
											"</div>"+
											"<div class='infoColor' style='height: "+h2+"'>"+
												"<p class='clickTip'>点解了解详情</p>"+
											"</div>"+
										"</div>"+
									"</div>"+
								"</li>");
			 }else if((index%3)==2){
				 $("#mark").before(
						 "<li class='penJingLas'>"+
							"<div class='penJingBorder'>"+
								"<div class='penJingImg penJingImg_"+index+"' style='height: "+h1+"'>"+
									"<a href='../penjingInfo?id="+item.penJingId+"' target='_parent'>"+
										"<img class='img img_"+index+"' alt='盆景' src='../"+item.mainPicture+"'>"+
									"</a>"+
								"</div>"+
								"<hr class='penJingHr'>"+
								"<div class='info'>"+
									"<div class='penJingInfo'>"+
										"<div>"+item.penJingName+"(编号："+item.penJingId+")</div>"+
										"<div class='phone'>联系方式：18723385990（村委）</div>"+
									"</div>"+
									"<div class='infoColor' style='height: "+h2+"'>"+
										"<p class='clickTip'>点解了解详情</p>"+
									"</div>"+
								"</div>"+
							"</div>"+
						"</li>");
			}else {
				$("#mark").before(
						"<li>"+
						"<div class='penJingBorder'>"+
							"<div class='penJingImg penJingImg_"+index+"' style='height: "+h1+"'>"+
								"<a href='../penjingInfo?id="+item.penJingId+"' target='_parent'>"+
									"<img class='img img_"+index+"' alt='盆景' src='../"+item.mainPicture+"'>"+
								"</a>"+
							"</div>"+
							"<hr class='penJingHr'>"+
							"<div class='info'>"+
								"<div class='penJingInfo'>"+
									"<div>"+item.penJingName+"(编号："+item.penJingId+")</div>"+
									"<div class='phone'>联系方式：18723385990（村委）</div>"+
								"</div>"+
								"<div class='infoColor' style='height: "+h2+"'>"+
									"<p class='clickTip'>点解了解详情</p>"+
								"</div>"+
							"</div>"+
						"</div>"+
					"</li>");
			}
			 $(".img_"+index).load(function(){
				 picCenter($(this));
			});
			$(".penJingImg_"+index).bind("mouseover",getTip);
			$(".penJingImg_"+index).bind("mouseout",delTip);
			i++;
	     });
		 if(i<6){
			 $(".loadMore").css('display','none');
		 }
		 $(".img").load(function(){
			 var h = $("body").height();
			 window.parent.changeFrameHeight(h);
		 });
		 
}

/*
 * 通过脚本动态修改图片框的高度
 */
function setPenJingHeight() {
	h1 = $(".penJingImg").css('width');
	h2 = parseInt($(".penJingInfo").css('height'))-5+"px";
	$(".penJingImg").css('height',h1);
	$(".infoColor").css('height',h2);
}

/*
 * 
 */
function getTip() {
	$(this).parent().parent('li').css('background-color','#F5F5F5');
	var index = $(this).parent().parent('li');
	var c = index.find('.infoColor');
	c.stop();
	c.animate({'width':'18%'},100,function(){
		index.find('.clickTip').css('display','inline');
	});
}

/*
 * 
 */
function delTip() {
	var index = $(this).parent().parent('li');
	var c = index.find('.infoColor');
	c.stop();
	index.find('.clickTip').css('display','none');
	c.animate({'width':'5px'},150);
	$(this).parent().parent('li').css('background-color','#fff');
}



function clock() {
	time = time+1;
	var time1 = time%3;
	 $(".btn li").eq(time1).addClass("on").siblings().removeClass("on");
	 $(".b_img").css({"display":'none'});
	 $(".b_img").eq(time1).fadeIn(300);
}

function stopCarousel() {
	$("#carouselInfo").stop();
	$("#carouselInfo").css({'display':'inline'});
	TextH = (parseInt($("#carouselText").css('height'))+20)+'px';
	if ($("#carouselInfo").css('height') != TextH) {
		$("#carouselInfo").animate({'height':TextH},100);
	}
}


function startCarousel() {
	$("#carouselInfo").stop();
		$("#carouselInfo").animate({'height':'0px'},100,function(){
			$("#carouselInfo").css('display','none');
		});
}

