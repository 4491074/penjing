
$(document).ready(function(){
	changeHeight();
	var li = getUrlParam('li')
	window.parent.select(li); //将首页相选中
	window.parent.changeTitle('页面建设');
});


function changeHeight() {
	var h = $("body").height();
	if (h<430) {
		h = 430;
		$('.con').css('height',430);
	}
	window.parent.changeFrameHeight(h);  //调整页面高度
}

//从url地址中获取参数
function getUrlParam(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return unescape(r[2]); return null; //返回参数值
}
