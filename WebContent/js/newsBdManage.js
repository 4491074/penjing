/**
 * 
 */
var Id=null;
function opensavedialog() {
	Id = "";
	$("#dialog").dialog({
		title:"添加",
	});
	$("#dialog").dialog("open");
	$("#newsBoardName").val("");
	$("#newsBoardDescription").val("");
}
function closedialog() {
	$("#dialog").dialog("close");
}
/*
 * 保存一个newsBoard
 */
 function savedialog(){
	 var stats=1;
	 if(document.getElementById("state1").checked){
		 var stats=0; 
	 }
	 var result = $("#newsbdform").form("validate");
	 if(result){
		 if(Id == ""){
		 var params = {
				   newsBoardName : $("#newsBoardName").val(),
				   newsBoardparent : $("#newsBoardparent").combobox("getValue"),
				   newsBoardDescription :$("#newsBoardDescription").val(),
				   newsBoardStatus:stats,
		        }
		 save(params);
		 }else{
			 var params = {
					   newsBoardId:Id,
					   newsBoardName : $("#newsBoardName").val(),
					   newsBoardparent : $("#newsBoardparent").combobox("getValue"),
					   newsBoardDescription :$("#newsBoardDescription").val(),
					   newsBoardStatus:stats,
			        }
		 update(params);
		 }
	 }
 }
 
 function save(params) {
	 $.ajax({
		    type: "POST",
		    url: "newsBd/saveNewsBd",
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
  * 删除一个newsBoard
  */
 
 function doremove(){
		var selections = $("#dg").datagrid('getSelections');
			if(selections.length==0){
				$.messager.alert("系统提示","请选择要删除的数据！");
			}else{
				var selIds=[];
				for(var i=0;i<selections.length;i++){
					var id = selections[i].newsBoardId;
					selIds.push(id);
				}
				var ids=selIds.join(",");
				$.messager.confirm("系统提示","确认要删除这<font color=red>"+selections.length+"</font>个新闻版块吗？<br>删除新闻板块时，会将对应新闻版块下的所有新闻删除",function(r){
					if(r){
						del(ids,selections.length);
					}	
				});
			}
		}
 
 /*
  * 检查是不是删除的父类版块
  */
 	function del(ids,length){
 		 var params = {
 				newsBoardId : ids
		        }
 		$.ajax({
		    type: "POST",
		    url: "newsBd/delNewsBd",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var errormsg = r.errormsg;var msg=r.msg;
		    	if(errormsg!=null){
		    		$.messager.alert("系统提示",errormsg);
		    	}
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
 	 * 修改新闻版块
 	 */
 	function openeditdialog(){
 		var selections = $("#dg").datagrid('getSelections');
 		if(selections.length==0){
 			$.messager.alert("系统提示","请选择一条要修改的数据！");
 			return;
 		}
 		if(selections.length>1){
 			$.messager.alert("系统提示","不能进行批量修改！");
 			return;
 		}else{
 			Id=selections[0].newsBoardId;
 			$("#newsBoardName").val(selections[0].newsBoardName);
 			var newsBoardparent = selections[0].newsBoardparent;
 			$("#newsBoardparent").combobox("select",newsBoardparent);
			$("#newsBoardDescription").val(selections[0].newsBoardDescription);
			var newsBdStatus = selections[0].newsBoardStatus;
			if(newsBdStatus != "正常"){
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
 	function update(params){
 		 $.ajax({
 		    type: "POST",
 		    url: "newsBd/updateNewsBd",
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