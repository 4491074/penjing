$(document).ready(function(){
	var h = $("body").height();
	window.parent.changeHigh(h);
});

function frameChange(src) {
	window.parent.frameChange(src);
}

function textAreaFock(userName) {
	window.parent.textAreaFock(userName);
}
