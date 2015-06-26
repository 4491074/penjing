

function confirm(){
	 var result = $("#newsform1").form("validate");
	 var selections = $("#dg").datagrid('getSelections');
	 var selIds=[];
	 for(var i=0;i<selections.length;i++){
			//if(selections[i].newsStatus=="未审核"){
				var id = selections[i].newsId;
				selIds.push(id);
			//}
		}
	var ids=selIds.join(",");var status;
	if(document.getElementById("state").checked){
		status=1;
	}else{
		status=2;
	}
	check(ids,status);
	cancel();
}

function cancel(){
	$("#dialog_1").dialog("close");
}
/*
 * 新闻审核
 */	
function checkNews(){
		var selections = $("#dg").datagrid('getSelections');
 		if(selections.length==0){
 			$.messager.alert("系统提示","请选择一条要审核的数据！");
 		}else{
 			 for(var i=0;i<selections.length;i++){
 				if(selections[i].newsStatus!="未审核"){
 					$.messager.alert("系统提示","已审核");
 					return;
 				}		
 			 }
 				$("#dialog_1").dialog({
 					title:"新闻审核",
 				});
 				$("#dialog_1").dialog("open");
 		}
	}
	function check(ids,status){
		var params = {
		           newsIds : ids,
		           newsStatus :status,
		        }
		 $.ajax({
			    type: "POST",
			    url: "news/checkNews",
			    data: params,
			    success: function back(data){
			    	var r = eval(data);
			    	var msg=r.msg;
			    	if(msg != null){
			    		$("#dg").datagrid("reload");
			    		$.messager.alert("系统提示",msg);
			    	}
			    },
			    error: function back(data){
			    	$.messager.alert("系统提示","删除失败，系统错误");
			    }
			    });
	}
 	
	
 	function lookfor(){
 		var selections = $("#dg").datagrid('getSelections');
 		if(selections.length==0){
 			$.messager.alert("系统提示","请选择一条要查看的数据！");
 		}
 		if(selections.length>1){
 			$.messager.alert("系统提示","不能进行批量查看！");
 		}else{
 			$("#newsTitle").text(selections[0].newsTitle);
 			var newsBoard = selections[0].newsBoard;
 			$("#newsBoard").text(newsBoard);
 			$("#newsContent").html(selections[0].newsContent);
 			$("#remark").text(selections[0].remark);
 			$("#dialog").dialog({
				title:"查看新闻",
				
			});
 		
			$("#dialog").dialog("open");
 		}
 	}
 		
 		
 		
 	