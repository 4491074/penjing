$(document).ready(function(){
	window.parent.titleChange($("#userName").text()+"的主页");
	changeHigh();
	isHava();
});

function changeHigh() {
	var h = $("body").height();
	window.parent.changeHigh(h);
}

/*
 * 验证实际获取的帖子数和回帖数是否等于该用户的发表数目
 * 如果小于，添加获取更多链接
 * 如果等于，无链接
 * 最多获取9条记录，等于9条时添加文字提醒
 */
function isHava() {
	var topicNu = $("#userTopics li").length-1;
	var replyNu = $("#userReplys li").length-1;
	var topicNu1 = $("#topicNu").text();
	var replyNu1 = $("#replyNu").text();
	if(topicNu==topicNu1){
		$("#moreTopic").css("display","none");
	}else if(topicNu==9){
		$("#moreTopic").replaceWith("最多只能获取最近的9条主题记录");
	}
	if(replyNu==replyNu1){
		$("#moreReply").css("display","none");
	}else if (replyNu==9) {
		$("#moreReply").replaceWith("最多只能获取最近的9条回帖记录");
	}
}
/*
 * 点击获取更多时进行验证
 * 验证是获取帖子还是回帖
 * 验证是否满足获取条件（小于总数以及小于9条记录）
 */
function get(type,userId) {
	if(type == "topic"){
		var topicNu = $("#userTopics li").length-1;
		var topicNu1 = $("#topicNu").text();
		if(topicNu < topicNu1 && topicNu < 9){
			getMore("topic", topicNu,userId);
		}
	}
	if(type == "reply"){
		var replyNu = $("#userReplys li").length-1;
		var replyNu1 = $("#replyNu").text();
		if(replyNu < replyNu1 && replyNu < 9){
			getMore("reply", replyNu,userId);
		}
	}
}

/*
 * 通过ajax获取用户的帖子以及回帖信息
 * first为起始数
 * 每次最多获得3条记录
 * 返回json数据
 */
function getMore(type,first,userId) {
	$.ajax({
	    type: "POST",
	    url: "../"+type+"/"+type+"_getMore?first="+first+"&id="+userId,
	    success: function back(data){
	    	var r = eval(data);
	    	var result = r.result;
	    	if(result==1){
	    		showData(type, r.data);
	    	}else{
	    		if(result == 2){
	    			alert("最多只能获取最近的9条记录")
	    		}
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
 function showData(type,data) {
	 if(type=="reply"){
		 var replyNu = $("#userReplys li").length%2;
		 $(data).each(function(index,item){
			 if(((index+replyNu)%2)==0){
				 $("#moreReply-Li").before(
							"<li class='list-group-item listColor'>"+
					 			"<div class='media'>" +
					 				"<div>" +
					 					"<h4>" +
					 						"回复了<a href='../users?id="+item.userId+"' target='_parent'>"+item.userName+"</a>创建的主题<a href='../topics?id="+item.topicId+"' target='_parent'>"+item.topicTitle+"</a>" +
					 					"</h4>" +
					 					"<div class='replyInfo'>" +
					 						"<div style='margin-bottom: 5px;'>内容："+item.content+"</div>" +
					 						"<div>时间："+item.timeString+"</div>" +
					 					"</div>" +
					 					"<div class='replyColor'></div>" +
					 				"</div>" +
					 			"</div>" +
					 		"</li>");
			 }else {
				 $("#moreReply-Li").before(
							"<li class='list-group-item'>"+
					 			"<div class='media'>" +
					 				"<div>" +
					 					"<h4>" +
					 						"回复了<a href='../users?id="+item.userId+"' target='_parent'>"+item.userName+"</a>创建的主题<a href='../topics?id="+item.topicId+"' target='_parent'>"+item.topicTitle+"</a>" +
					 					"</h4>" +
					 					"<div class='replyInfo'>" +
					 						"<div style='margin-bottom: 5px;'>内容："+item.content+"</div>" +
					 						"<div>时间："+item.timeString+"</div>" +
					 					"</div>" +
					 					"<div class='replyColor'></div>" +
					 				"</div>" +
					 			"</div>" +
					 		"</li>");
			}
	     });
	 }else if (type=="topic") {
		var topicNu = $("#userTopics li").length%2;
		$(data).each(function(index,item){
			 if(((index+topicNu)%2)==0){
				 $("#moreTopic-Li").before(
							"<li class='list-group-item listColor'>"+
					 			"<div class='media'>" +
					 				"<div>" +
					 					"<span class='badge topicBadge' style='float: right;'>"+item.replyNu+"</span>" +
					 					"<a href='../topics?id="+item.topicId+"' target='_parent'>" +
					 						"<h4><font color='#000'>"+item.title+"</font></h4>" +
					 					"</a>" +
					 					"<font color='#666'>"+item.lastTimeString+"</font>&nbsp;•&nbsp;<font color='#666'>最后回复来自&nbsp;</font>" +
					 					"<a href='../users?id="+item.lastUserId+"' target='_parent' id='newList-'"+index+">"+item.lastUserName+"</a>" +
					 				"</div>" +
					 			"</div>" +
					 		"</li>");
			 }else {
				 $("#moreTopic-Li").before(
						 "<li class='list-group-item'>"+
				 			"<div class='media'>" +
				 				"<div>" +
				 					"<span class='badge topicBadge' style='float: right;'>"+item.replyNu+"</span>" +
				 					"<a href='../topics?id="+item.topicId+"' target='_parent'>" +
				 						"<h4><font color='#000'>"+item.title+"</font></h4>" +
				 					"</a>" +
				 					"<font color='#666'>"+item.lastTimeString+"</font>&nbsp;•&nbsp;<font color='#666'>最后回复来自&nbsp;</font>" +
				 					"<a href='../users?id="+item.lastUserId+"' target='_parent' id='newList-"+index+"'>"+item.lastUserName+"</a>" +
				 				"</div>" +
				 			"</div>" +
				 		"</li>");
			}
			if(item.lastUserId==0){
				$("#newList-"+index).replaceWith("--无回复--");
			}
	     });
	}
	 changeHigh();
	 isHava();
}
  
 function toEnroll() {
	 var url = window.top.location.pathname;
		url = url.substring(9, url.length);
		var param = window.top.location.search
		window.parent.location.href = "../enroll?from="+url+param;
	}
