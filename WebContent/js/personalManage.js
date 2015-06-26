var userId;
var mail;
var phone;
var userDescription;
var photo = null;
var judge=0;
var userName;

function go(value,row,index) {
	return "<a href='javascript:openPhoto();'>点击查看头像</a>";
}

function openPhoto() {
	var selected = $("#dg").datagrid('getSelected');
	if(selected.photo != null && selected.photo!=""){
		if(photo == null){
			photo = selected.photo;
		}
		$("#photo").find("img").attr("src",photo+"?d="+new Date().valueOf());
	}
	$("#photo").dialog("open");
}

function openeditdialog() {
	var selections = $("#dg").datagrid('getSelections');
	if(selections.length!=1){
		$.messager.alert("系统提示","请选择一条要修改的数据！");
	}else{
		userId = selections[0].userId;
		$("#userId").text(userId);
		$("#userName").text(selections[0].userName);
		mail = selections[0].mail;
		$("#mail").val(mail);
		phone = selections[0].phone;
		$("#phone").val(phone);
		userDescription = selections[0].userDescription;
		if(userDescription == null){
			userDescription = "";
		}
		$("#userDescription").val(userDescription);
		$("#dialog").dialog({
			title:"修改",
		});
		$("#dialog").dialog("open");
	}
}

function openPhotodialog() {
	var selections = $("#dg").datagrid('getSelections');
	if(selections.length!=1){
		$.messager.alert("系统提示","请选择一条要修改的数据！");
	}else{
		var selected = $("#dg").datagrid('getSelected');
		if(selected.photo != null && selected.photo!=""){
			if(photo == null){
				photo = selected.photo;
			}
			$("#photoEdit").find("img").attr("src",photo+"?d="+new Date().valueOf());
		}
		$("#photoEdit").dialog("open");
	}
}

function openPhotodialog1() {
	$("#photo").dialog("close");
	openPhotodialog();
}

function closedialog() {
	if(judge==1){
		$("#Pwddialog").dialog("close");
	}
	$("#dialog").dialog("close");
}

function closePhoto() {
	$("#photoEdit").dialog("close");
}

 function savedialog(){
	 var result = $("#personalForm").form("validate");
	 if(result){
		 var newPhone = $("#phone").val();
		 if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(newPhone))){
			 $.messager.alert("系统提示","电话格式错误");
			 return;
		 }
		 var newMail = $("#mail").val();
		 
		 var newUserDescription = $("#userDescription").val();
		 if (newMail == mail && newPhone == phone && newUserDescription == userDescription) {
			 closedialog();
			 return;
		}
		var params = $("#personalForm").serialize();
		params = decodeURIComponent(params,true); //解决中文编码问题
		edit(params);
	}
}
 
 function edit(params) {
	 $.ajax({
		    type: "POST",
		    url: "user/editMyInfo",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	var nu = r.nu;
		    	if(result == 1){
		    		closedialog();
		    		$.messager.alert("系统提示","修改成功");
		    		$("#dg").datagrid("reload");
		    	}else {
		    		if (result == 2) {
		    			$.messager.alert("系统提示","请重新登录后重试");
					}else if (result == 3) {
						$.messager.alert("系统提示","该邮箱已经注册");
					}else if (result == 4) {
						$.messager.alert("系统提示","该电话已经注册");
					}
		    	}
		    },
		    error: function back(data){
		    	$.messager.alert("系统提示","修改失败，系统错误");
		    }
		    });
}
 
 /*
  * ajax提交头像图片
  *
  */
 function submitPhoto() {
	// 开始上传文件时显示一个图片
     $("#photoLoading").ajaxStart(function() {
         $(this).show();
     // 文件上传完成将图片隐藏起来
     }).ajaxComplete(function() {
         $(this).hide();
     });
	 $.ajaxFileUpload({  
         url:'user/submitPhoto',  //需要链接到服务器地址  
         secureuri:false,  
         fileElementId:'image',  //文件选择框的id属性  
         dataType: 'json',  //服务器返回的格式，可以是json  
         success: function(data, status)  //相当于java中try语句块的用法  
         {     
        	 var r = eval(data);
        	 var result = r.result;
        	 if(result == 1){
        		 photo = r.photo;
        		 $("#photoEdit").find("img").attr("src",photo+"?d="+new Date().valueOf());
        	 }else {
        		if (result == 3) {
        			$.messager.alert("系统提示","您上传的照片过大，请选择1M以内的头像");
				}else{
					$.messager.alert("系统提示","请重新登录后重试");
				}
			}
         },  
         error: function (data, status, e)             //相当于java中catch语句块的用法  
         {  
        	 $.messager.alert("系统提示","系统错误");
         }  
       }  
     ); 
}
 
 
 function checkOldPwd(){
	 $("#errorMsg").text("");
	 var params={
			userName:userName,
	 		oldPwd:$("#oldPwd").val(),
	 }
	 $.ajax({
		    type: "POST",
		    url: "user/checkOldPwd",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	if(result == 0){
		    		$("#errorMsg").text("旧密码错误，请检查...");
		    	}
		    },
		    error: function back(data){
		    	$.messager.alert("系统提示","修改失败，系统错误");
		    }
		    });
	 
 }
 function openPwddialog(){
	 judge=1;
	 $("#oldPwd").val("");
	 $("#reNewPwd").val("");
	 $("#newPwd").val("");
	 var selections = $("#dg").datagrid('getSelections');
		if(selections.length==0){
			$.messager.alert("系统提示","请选择要修改的用户！");
		}else{
			userName=selections[0].userName;
			$("#Pwddialog").dialog("open");
		}
 }
 function savepwddialog(){
	 var params={
		 userName:userName,
		 oldPwd:$("#oldPwd").val(),
		 newPwd:$("#reNewPwd").val()
	 }
	 var newPwd=$("#newPwd").val();
	 var reNewPwd=$("#reNewPwd").val();
	 var result = $("#pwdform").form("validate");
	 if(newPwd.length<6||reNewPwd.length<6){
		 return;
	 }
	 if(result){
		 if(newPwd==reNewPwd){
			 updatePwd(params);
			 $("#Pwddialog").dialog("close");
		 }
	 }
 }
 function checkRePwd(){
	 var newpwd= $("#newPwd").val();
	 if(newpwd.length<6){
		 return;
	 }
	 $("#errorMsg").text("");
	 var newPwd=$("#newPwd").val();
	 var reNewPwd=$("#reNewPwd").val();
	 if(newPwd==reNewPwd){
		 
	 }else{
		 $("#errorMsg").text("请检查新密码...");
	 }
 }
 function updatePwd(params){
	 $.ajax({
		    type: "POST",
		    url: "user/setNewPwd",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	if(result==1){
		    		$.messager.alert("系统提示","修改成功");
		    		parent.location.href="login";
		    	}
		    },
		    error: function back(data){
		    	$.messager.alert("系统提示","修改失败，系统错误");
		    }
		    });
 }
 function checkNewPwd(){
	 $("#errorMsg").text(""); 
	 var newpwd= $("#newPwd").val();
	 if(newpwd.length<6){
		 $("#errorMsg").text("输入密码小于6位..."); 
	 }
 }

