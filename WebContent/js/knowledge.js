
$(document).ready(function(){
	changeHeight();
	window.parent.select(4); //将首页相选中
	window.parent.changeTitle('盆景知识');
	changeWidth();
});


function changeHeight() {
	var h = $("body").height();
	window.parent.changeFrameHeight(h);  //调整页面高度
}

function changeWidth() {
	$("#introductionTitle-1").css("width",$("#title-1").css('width'));
	$("#introductionTitle-2").css("width",$("#title-2").css('width'));
}

