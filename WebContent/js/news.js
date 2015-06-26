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
	      $(".newsType li").mouseout(blurLi);
	      $(".newsType li").mouseover(focusLi);
	      $(".newsList li").mouseover(focusNews);
	      $(".newsList li").mouseout(blurNews);
	      $(".back").mouseover(focusBack);
	      $(".back").mouseout(blurBack);
	    });
	  }
})(jQuery);

var H = 40;
var L = 0;
var H1 = 0;

$(document).ready(function(){
	changeHeight();
	window.parent.select(5); //将首页相选中
	window.parent.changeTitle('重庆南山盆景非物质文化遗产');
	L = $(".newsType li").length-1;
	H = parseInt($(".newsType ul").css('height'))/L;
	H1 = $(".newsType li").css('height');
	changeTypeSelect();
});

/*
 * 点击新闻版块
 */

function tiJiaoSBdId(newsBdId){
	backNews();
	window.parent.location.href="news?newsBdId="+newsBdId;
}
function fastJump(a){
	var lastPageNo=document.getElementById("lastPageNo").innerHTML;
	var newsBdId=document.getElementById("newsBdId").value;
	if(a.value.length!=0){
		if(a.value>lastPageNo){
			alert("输入超出范围");
		}else{
			window.parent.location.href="news?newsPage="+a.value+"&&newsBdId="+newsBdId;
		}
	}	
}




function changeHeight() {
	var h = $("body").height();
	if (h<430) {
		h = 430;
		$('.con').css('height',430);
	}
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
	if($(this).attr('id')=='newsParent'){
		blurLi();
	}else {
		var X1 = $(this).index()*H;
		if($(this).index()>3){
			X1++;
		}
		$(".typeSelect").animate({'top':X1},100);
	}
}

/*
 * 修改盆景类型选择器的位置
 */
function changeTypeSelect() {
	var type = $(".detailnowType").text().trim();
	for (var int = 0; int < L; int++) {
		var type1 = $(".newsType").find("li").eq(int).text();
		var id = $(".newsType").find("li").eq(int).attr('id');
		if(type == type1 && id != "newsParent"){
			X = int*H;
			$(".typeSelect").css({'top':X,'display':'inline','height':H1});
			break;
		}
	}
}

/*
 * 鼠标放在新闻上时，背景变色
 */
function focusNews() {
	var text = $(this).text();
	if (text.length>1) {
		$(this).css('background-color','#C7C7C7');
	}
}

/*
 * 鼠标离开新闻时，背景变回原来颜色
 */
function blurNews() {
	$(this).css('background-color','#FAFAFA');
}

/*
 * 鼠标放在返回上时，背景变色
 */
function focusBack() {
	$(this).css('background-color','#3CB371');
}

/*
 * 鼠标离开返回时，背景变回原来颜色
 */
function blurBack() {
	$(this).css('background-color','#67B389');
}

/*
 * 查看新闻
 */
function seeNews(newsId) {
	$.ajax({
		url:"getNewsById",
	   data:{"newsId":newsId},
	   type:"get",
	   dataType:"text",
	   success:function(data){
		   var arr=data.split(",");
		   //添加新闻标题
		   $("#detailnewstitle").remove();
		   var title="<p id='detailnewstitle'>"+arr[0]+"</p>";
		   $(".newsTitle").html(title);
		   //添加新闻内容
		   $("#detailcontent").remove();
		   var content="<p id='detailcontent'>"+arr[1]+"</p>";
		   $(".content").html(content);
		   //添加新闻发布时间和阅读量
		   $("#detailnewsinfo").remove();
		   var newsinfo="<p id='detailnewsinfo'>"+"发布日期:"+arr[2]+"&nbsp;•&nbsp;点击量:"+arr[3]+"</p>";
		   $(".newsInfo").html(newsinfo); 
	   },
	   cache:false,
	   timeout:5000,
	   error:function(){
		   alert("超时");
	   }
	});
	$(".newsBoard").animate({'height':'0px'},300,function(){
		$(".newsBoard").css('display','none');
		$(".theNews").css({'display':'inline','height':'0','width':'100%'});
		$(".theNews").animate({'height':'100%'},500);
		changeHeight();
	});
	
}


/*
 * 从新闻内容返回新闻列表
 */
function backNews() {
	$(".theNews").animate({'height':'0px'},300,function(){
		$(".theNews").css('display','none');
		$(".newsBoard").css({'display':'inline','height':'0px','width':'100%'});
		$(".newsBoard").animate({'height':'100%'},500);
		changeHeight();
	});
}

