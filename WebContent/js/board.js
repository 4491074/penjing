function toForum(id) {
	window.parent.location.href="../forum?id="+id;
	
}

$(document).ready(function(){
	window.parent.titleChange("重庆南山盆景网-社区");
	var h = $("body").height();
	if (h>700) {
		window.parent.changeHigh(h);
	}
});

function toEnr() {
	window.parent.location.href = "../enroll";
}