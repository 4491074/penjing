
$(document).ready(function(){
	changeHeight();
	window.parent.select(6); //将首页相选中
	window.parent.changeTitle('企业简介');
	changeH();
});


function changeHeight() {
	var h = $("body").height();
	if (h<430) {
		h = 430;
		$('.con').css('height',430);
	}
	window.parent.changeFrameHeight(h);  //调整页面高度
}

function changeH() {
	var h = $(".enterpriseCon").css('height');
	var h1 = $(".enterpriseImg").css('height');
	if(h<h1){
		$(".enterpriseCon").css('height',h1);
	}
}

