
var id;
function closedialog() {
	$("#dialog").dialog("close");
	setnull();
}

function openeditdialog(){
	var selections = $("#dg").datagrid('getSelections');
		if(selections.length==0){
			$.messager.alert("系统提示","请选择一条要修改的数据！");
		}else{
			if(selections[0].newsStatus=="审核通过"){
 				$.messager.alert("系统提示","新闻已发布，不能进行修改！");
 			}else{
 				id=selections[0].id;
 				$("#managerName").val(selections[0].managerName);
 				$("#managerTel").val(selections[0].managerTel);
 				$("#address").val(selections[0].address); 				
 				$("#dialog").dialog({
 					title:"修改",
 				});
 				$("#dialog").dialog("open");
 			}
		}
}


function savedialog(){
	var managerName=$("#managerName").val();
	var managerTel=$("#managerTel").val();
	var address=$("#address").val();
	if(managerName==""||managerTel==""||address==""){
		return;
	}else{
		var params={
				Id:id,
				managerName:managerName,
				managerTel:managerTel,
				address:address,
		};
		update(params);
	}
}
function update(params){
	 $.ajax({
		    type: "POST",
		    url: "remark/updateReInfo",
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
