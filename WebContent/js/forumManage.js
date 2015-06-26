/*
 	 * 修改论坛
 	 */
	var forumId;
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
 			forumId=selections[0].forumId;
 			$("#forumName").val(selections[0].forumName);
			$("#notice").val(selections[0].notice);
			$("#pageNu").val(selections[0].pageNu);
			var forumStatus = selections[0].forumStatus;
			if(forumStatus != "正常"){
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
 	
 	
 	/*
 	 * 修改按钮
 	 */
 	 function savedialog(){
 		 var stats=1;
 		 if(document.getElementById("state1").checked){
 			 var stats=0; 
 		 }
 		 var result = $("#newsbdform").form("validate");
 		 if(result){
 				 var params = {
 						   forumId:forumId,
 						   forumName : $("#forumName").val(),
 						   notice : $("#notice").val(),
 						   pageNu :$("#pageNu").val(),
 						  forumStatus:stats,
 				        }
 			 update(params);
 		 }
 	 }
 	 
 	 function update(params){
 		$.ajax({
		    type: "POST",
		    url: "forum/updateForum",
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
 	 
 	
 	
 	function closedialog() {
 		$("#dialog").dialog("close");
 	}
