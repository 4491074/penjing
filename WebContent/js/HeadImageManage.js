function check(){
	var a1=document.getElementById('fileField1').value;
	var a2=document.getElementById('fileField2').value;
	var a3=document.getElementById('fileField3').value;
	
	if(a1.length==0&&a2.length==0&&a3.length==0){
		$.messager.alert("系统提示","请选择图片！");
		return false;
	}else{
		return true;
	}
}