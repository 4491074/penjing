/**
 * 
 */
var Id;
function opensavedialog() {
	Id = "";
	$("#dialog").dialog({
		title:"添加",
	});
	$("#dialog").dialog("open");
	$("#newsTitle").val("");
	ue.setContent("");
	$("#remark").val("");
}
/*
 * 关闭按钮
 */
function closedialog() {
	$("#dialog").dialog("close");
}
/*
 * 保存按钮
 */
function savedialog(){
	 var result = $("#newsform").form("validate");
	 var content = ue.getContent();
	 if(result){
			 if(Id == ""){
				 var params = {
						 newsTitle : $("#newsTitle").val(),
						 newsBoard : $("#newsBoard").combobox("getValue"),
						 newsContent :content,
						 remark:$("#remark").val(),
				 }
				 save(params);
			 }else{
				 var params = {
						 newsId:Id,
						 newsTitle : $("#newsTitle").val(),
						 newsBoard : $("#newsBoard").combobox("getValue"),
						 newsContent :content,
						 remark:$("#remark").val(),
				 }
				 update(params);
			 }
	 }
}
function save(params) {
	 $.ajax({
		    type: "POST",
		    url: "news/publishNews",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	if(r.result==1){
		    		$("#dg").datagrid("reload");
		    		closedialog();
		    	}
		    },
		    error: function back(data){
		    	$.messager.alert("系统提示","添加失败，系统错误");
		    }
		    });
}

/*
 * 删除新闻
 */

function doremove(){
		var selections = $("#dg").datagrid('getSelections');
			if(selections.length==0){
				$.messager.alert("系统提示","请选择要删除的数据！");
			}else{
				var selIds=[];
				for(var i=0;i<selections.length;i++){
					var id = selections[i].newsId;
					selIds.push(id);
				}
				var ids=selIds.join(",");
				$.messager.confirm("系统提示","确认要删除这<font color=red>"+selections.length+"</font>条数据吗？",function(r){
					if(r){
						del(ids,selections.length);
					}	
				});
			}
		}

	function del(ids,length){
		 var params = {
				newsIds : ids
		        }
		$.ajax({
		    type: "POST",
		    url: "news/deleteNews",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var msg=r.msg;
		    	if(msg!=null){
		    		$.messager.alert("系统提示",msg);
		    	}
		    	$("#dg").datagrid("reload");
		    },
		    error: function back(data){
		    	$.messager.alert("系统提示","删除失败，系统错误");
		    }
		    });
	}
	
	/*
	 * 修改我的新闻
	 */	
	function openeditdialog(){
 		var selections = $("#dg").datagrid('getSelections');
 		if(selections.length==0){
 			$.messager.alert("系统提示","请选择一条要修改的数据！");
 		}
 		if(selections.length>1){
 			$.messager.alert("系统提示","不能进行批量修改！");
 		}else{
 			if(selections[0].newsStatus=="审核通过"){
 				$.messager.alert("系统提示","新闻已发布，不能进行修改！");
 			}else{
 				Id=selections[0].newsId;
 				$("#newsTitle").val(selections[0].newsTitle);
 				var newsBoard = selections[0].newsBoard;
 				$("#newsBoard").combobox("select",newsBoard);
 				ue.setContent(selections[0].newsContent);
 				$("#remark").val(selections[0].remark);
 				$("#dialog").dialog({
 					title:"修改",
 				});
 				$("#dialog").dialog("open");
 			}
 		}
 	}
 	function update(params){
 		 $.ajax({
 		    type: "POST",
 		    url: "news/updateMyNews",
 		    data: params,
 		    success: function back(data){
 		    	var r = eval(data);
 		    	if(r.result==1){
 		    		$("#dg").datagrid("reload");
 		    		closedialog();
 		    	}
 		    },
 		    error: function back(data){
 		    	$.messager.alert("系统提示","添加失败，系统错误");
 		    }
 		    });
 	}
