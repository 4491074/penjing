var userStatus;
var roleId;
var userId;

function opensavedialog() {
	id = "";
	$("#dialog").dialog({
		title:"添加",
	});
	$("#dialog").dialog("open");
}

function openeditdialog() {
	var selections = $("#dg").datagrid('getSelections');
	if(selections.length!=1){
		$.messager.alert("系统提示","请选择一条要修改的数据！");
	}else{
		userId = selections[0].userId;
		$("#userId").text(userId);
		$("#userName").text(selections[0].userName);
		userStatus = selections[0].userStatus;
		if(userStatus != "正常"){
			document.getElementById("state1").checked = true;
		}else {
			document.getElementById("state").checked = true;
		}
		roleId = selections[0].roleId;
		$("#roleSelect").combobox("select",roleId);
		$("#dialog").dialog({
			title:"修改",
		});
		$("#dialog").dialog("open");
	}
}

function closedialog() {
	$("#dialog").dialog("close");
}

 function savedialog(){
	 var s = "正常";
	 if(document.getElementById("state1").checked){
		 s = "冻结";
	 }
	 var name = $("#roleSelect").combobox("getValue");
	 if(s == userStatus && name == roleId){
		 $.messager.confirm("系统提示","你没有进行修改，是否退出",function(r){
				if(r){
					closedialog();
				}	
			});
	 }else {
		 var status = 1;
		 if (s == "冻结") {
			status = 0;
		}
		var params = {
		          userId : userId,
		          userStatus : status,
		          roleId : name
		        }
		edit(params);
	}
 }
 
 function edit(params) {
	 $.ajax({
		    type: "POST",
		    url: "user/editUsers",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	var nu = r.nu;
		    	if(result == 1){
		    		closedialog();
		    		$.messager.alert("系统提示","更新成功");
		    		$("#dg").datagrid("reload");
		    	}else {
		    		$.messager.alert("系统提示","更新失败，系统错误");
		    	}
		    },
		    error: function back(data){
		    	$.messager.alert("系统提示","更新失败，系统错误");
		    }
		    });
}

