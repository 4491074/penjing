var h = 0;

$(document).ready(function(){
	window.parent.trans(1);
	var page = getUrlParam1("page");
	if (page == null) {
		page = 1;
	}
	$("#frame").attr("src","../forum/getForum?page="+page);
	h = $("body").height();
	window.parent.changeHigh(h);
});

function changeHigh(h) {
	$("#frame").height(h);
	var h1 = $("body").height();
	window.parent.changeHigh(h1);
}

function getUrlParam(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.parent.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return unescape(r[2]); return null; //返回参数值
}

function getUrlParam1(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return unescape(r[2]); return null; //返回参数值
}


function clock() {
	var h1 = $("body").height();
		if(h1!=h){
			h = h1;
			window.parent.changeHigh(h);
		}
}

function checkTopic() {
	var title = $("#topicTitle").val().trim();
	var content = ue.getContent();
	var length = content.length;
	if(title.length<5){
		$("#titleMsg").html("标题不能少于5个字");
		clock();
		var t = setTimeout("noText('titleMsg')",3000);
	}else if (length<15) {
		$("#contentMsg").html("内容不能不能少于15个字");
		clock();
		var t = setTimeout("noText('contentMsg')",3000);
	}else if (length>3000) {
		$("#contentMsg").html("内容不能不能超过3000个字");
		clock();
		var t = setTimeout("noText('contentMsg')",3000);
	}else {
		unClick();
		topicSubmit();
	}
}

function topicSubmit() {
	var params = $("#fastTopicForum").serialize();
	params = decodeURIComponent(params,true); //解决中文编码问题
	$.ajax({
	    type: "POST",
	    url: "../topic/fastCreat",
	    data: params,
	    success: function back(data){
	    	var r = eval(data);
	    	var type = r.result;
	    	if(type==1){
	    		$('#frame').attr('src', $('#frame').attr('src'));
	    		$("#topicTitle").val("");
//	    		UE.getEditor('container').execCommand('cleardoc');
	    		ue.setContent("");
	    		window.parent.showFloat("创建主题成功","middle");
	    		var t = setTimeout("window.parent.hideFloat()",3000);
	    	}else{
	    		 if (type == 2) {
	    			$("#titleMsg").html("用户获取失败，请重新登录后再试");
	    			window.parent.location.reload();
				}
	    	}
	    	canClick();
	    },
	    error: function back(data){
	    	$("#titleMsg").html("系统错误");
	    	canClick();
	    }
	    });
}

function noText(name) {
	$("#"+name).html("");
	clock();
}

/*
 * 将快速发帖表单隐藏
 */
function hideForm() {
	$("#fastTopicForum").css('display','none');
}
/*
 * 显示快速发帖
 */
function showForm() {
	$("#fastTopicForum").css('display','inline');
}

/*
 * 将loading状态文字隐藏
 */
function hideLoading() {
	$("#loading").css('display','none');
}
/*
 * 显示loading状态文字
 */
function showloading() {
	$("#loading").css('display','inline');
}


function toEnroll() {
	window.parent.location.href = "../enroll";
}

/*
 * 将发帖按钮变成不可按状态
 */
function unClick() {
	$("#topicButton").addClass("disabled ");
	$("#topicButton").attr('value','loading..');
}

/*
 * 将发帖按钮变成可按状态
 */
function canClick() {
	$("#topicButton").removeClass("disabled ");
	$("#topicButton").attr('value','提交');
}








