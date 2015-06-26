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
	      $(".west li").mouseout(blurLi);
	      $(".west li").mouseover(focusLi);
	      $(".newsParent").click(parentClick);
	      $(".west li").click(openTabs);
	    });
	  }
})(jQuery);

var u = null;
var u1 = null;

/*
 * 鼠标离开导航栏时产生动画
 * 首先消除所有动画，然后执行当前动画
 */
function blurLi() {
	if($(this).attr('class')=='newsParent'){
		return;
	}else {
		$(this).css("background-color","#EFEFEF");
		$(this).find("p").css("color","#666");
	}
}


/*
 * 导航栏有鼠标悬浮时产生动画
 * 首先消除所有动画，然后执行当前动画
 */
function focusLi() {
	if($(this).attr('class')=='newsParent'){
		return;
	}else {
		$(this).css("background-color","#006666");
		$(this).find("p").css("color","#fff");
	}
}

function parentClick() {
	var index = $(this).attr("id").substring(7, $(this).attr("class").length);
	$(".child-"+index).slideToggle(200);
}


function openTabs() {
	
	if($(this).attr('class')=='newsParent'){
		return;
	}else {
		var text = $(this).text();
		if ($("#tabs").tabs('exists',text)) {
			$("#tabs").tabs('select',text)
		}else {
			var url = $(this).attr('target');
			if (url == "penjing/getAllPenjing") {
				u1 = url;
			}
//			getManagePage(url,text);
			var content="<iframe frameborder='0' scrolling='auto' style='width: 100%;height:100%;' src='"+url+"'></iframe>";
			$('#tabs').tabs('add',{   
			     title:text,   
			     content:content,   
			      closable:true  
			  });
		}
	}
}

function openTab(text,url) {
	if ($("#tabs").tabs('exists',text)) {
		if(u == url){
			$("#tabs").tabs('select',text);
			return;
		}
		$("#tabs").tabs('close',text);
	}
	u = url;
	var content="<iframe frameborder='0' scrolling='auto' style='width: 100%;height:100%;' src='"+u+"'></iframe>";
	$('#tabs').tabs('add',{   
		  title:text,   
		  content:content,   
		  closable:true  
	});
}
//具体盆景类型的盆景管理
function openTab_1(text,url) {
	if ($("#tabs").tabs('exists',text)) {
		if(u1 == url){
			$("#tabs").tabs('select',text);
			return;
		}
		$("#tabs").tabs('close',text);
	}
	u1 = url;
	var content="<iframe frameborder='0' scrolling='auto' style='width: 100%;height:100%;' src='"+u1+"'></iframe>";
	$('#tabs').tabs('add',{   
		  title:text,   
		  content:content,   
		  closable:true  
	});
}

///*
// * ajax请求后台获取管理页面
// * 权限验证
// */
//function getManagePage(url,text) {
//	$.ajax({
//	    type: "POST",
//	    url: url,
//	    success: function back(data){
//	    	var r = $.parseJSON(data);
//	    	var type = r.result;
//	    	var page = r.page;
//	    	var content="<iframe frameborder='0' scrolling='auto' style='width: 100%;height:100%;' src='"+page+"'></iframe>";
//			$('#tabs').tabs('add',{   
//			     title:text,   
//			     content:content,   
//			      closable:true  
//			  });
//	    	
//	    },
//	    error: function back(data){
//	    	location.reload();
//	    }
//	    });
//	
//}


function logout() {
	$.ajax({
	    type: "POST",
	    url: "user/logout",
	    success: function back(data){
	    	var r = eval(data);
	    	var type = r.result;
	    	if(type==1){
	    		window.parent.location.href="home";
	    	}else{
	    		window.parent.location.href="home";
	    	}
	    },
	    error: function back(data){
	    	location.reload();
	    }
	    });
	
}



