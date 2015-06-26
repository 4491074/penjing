var replyId = null;
var replyState = null;

function doremove(){
	var selections = $("#dg").datagrid('getSelections');
		if(selections.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
		}else{
			var selIds=[];
			for(var i=0;i<selections.length;i++){
				var id = selections[i].replyId;
				selIds.push(id);
			}
			var ids=selIds.join(",");
			$.messager.confirm("系统提示","确认要删除这<font color=red>"+selections.length+"</font>条数据吗？",function(r){
				if(r){
					dele(ids);
				}	
			});
		}
	}

function dele(ids) {
	 var params = {
	           replyId : ids
	        }
	 $.ajax({
		    type: "POST",
		    url: "reply/delReply",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	var nu = r.nu;
		    	if(result == 1){
		    		closedialog();
		    		$.messager.alert("系统提示","删除成功,你已经删除了<font color=red>"+nu+"</font>条回帖");
		    		$("#dg").datagrid("reload");
		    	}else {
		    		if(result == 0){
						$.messager.alert("系统提示",r.error);
		    		}else {
		    			$.messager.alert("系统提示","添加失败，系统错误");
					}
		    	}
		    },
		    error: function back(data){
		    	$.messager.alert("系统提示","删除失败，系统错误");
		    }
		    });
}

function openeditdialog() {
	var selections = $("#dg").datagrid('getSelections');
	if(selections.length!=1){
		$.messager.alert("系统提示","请选择一条要修改的数据！");
	}else{
		replyId = selections[0].replyId;
		replyState = selections[0].replyState;
		$("#replyId").text(replyId);
		$("#content").text(selections[0].content);
		if(replyState != "正常"){
			document.getElementById("state1").checked = true;
		}else {
			document.getElementById("state").checked = true;
		}
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
	 if(s == replyState){
		 $("#dialog").dialog("close");
	 }else {
		 if(s == "正常"){
			 edit(1);
		 }else{
			 edit(0);
		 }
		
	 }
}

 function edit(s) {
	 var params = {
			 replyState : s
	 }
	 $.ajax({
		    type: "POST",
		    url: "reply/editReply?replyId="+replyId,
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	if(result == 1){
		    		closedialog();
		    		$.messager.alert("系统提示","修改成功");
		    		$("#dg").datagrid("reload");
		    	}else {
		    		$.messager.alert("系统提示","修改失败，系统错误");
		    	}
		    },
		    error: function back(data){
		    	$.messager.alert("系统提示","修改失败，系统错误");
		    }
		    });
 }

