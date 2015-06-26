

var Id;
function go(value,row,index) {
	return "<a href='javascript:pjTypeManage("+row.penJingTypeId+","+row.penJingNum+");'>"+value+"</a>";
}

function pjTypeManage(id,replyNu) {
	if(replyNu<1){
		$.messager.alert("系统提示","该盆景类型下没有盆景");
		return;
	}
	var url = "penJingManage?id="+id;
	window.parent.openTab_1("盆景管理",url);
}


function opensavedialog() {
	Id = "";
	$("#dialog").dialog({
		title:"添加",
	});
	$("#dialog").dialog("open");
	$("#penJingTypeName").val("");
	$("#penJingTypeDescription").val("");
}
function closedialog() {
	$("#dialog").dialog("close");
}


/*
 * 保存一个PenJingType
 */
function savedialog(){
	 var stats=1;
	 if(document.getElementById("state1").checked){
		 var stats=0; 
	 }
	 var result = $("#pjTypeform").form("validate");
	 if(result){
		 if(Id == ""){
		 var params = {
				   penJingTypeName : $("#penJingTypeName").val(),
				   penJingTypeDescription :$("#penJingTypeDescription").val(),
				   penJingTypeStatus:stats,
		        }
		 save(params);
		 }else{
			 var params = {
					   penJingTypeId:Id,
					   penJingTypeName : $("#penJingTypeName").val(),
					   penJingTypeDescription :$("#penJingTypeDescription").val(),
					   penJingTypeStatus:stats,
			        }
		 update(params);
		 }
	 }
}

function  save(params){
$.ajax({
    type: "POST",
    url: "penjings/AddPenJingType",
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
	 * 修改盆景类型
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
		}
		if(selections.length==1){
			Id=selections[0].penJingTypeId;
			$("#penJingTypeName").val(selections[0].penJingTypeName);
			$("#penJingTypeDescription").val(selections[0].penJingTypeDescription);
			var penJingTypeStatus = selections[0].penJingTypeStatus;
			if(penJingTypeStatus != "正常"){
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
		    url: "penjings/updatePenJingType",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	if(r.result==1){
		    		$.messager.alert("系统提示","修改盆景类型成功！");
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
 * 删除一个盆景类型
 */
function doremove(){
		var selections = $("#dg").datagrid('getSelections');
			if(selections.length==0){
				$.messager.alert("系统提示","请选择要删除的数据！");
			}else{
				var selIds=[];
				for(var i=0;i<selections.length;i++){
					if(selections[i].penJingNum>0){
						$.messager.alert("系统提示","不能删除有盆景的盆景类型");
						return;
					}
					var id = selections[i].penJingTypeId;
					selIds.push(id);
				}
				var ids=selIds.join(",");
				$.messager.confirm("系统提示","确认要删除这<font color=red>"+selections.length+"</font>个盆景类型吗？",function(r){
					if(r){
						del(ids,selections.length);
					}	
				});
			}
		}
	function del(ids,length){
		 var params = {
				penJingTypeIds : ids
		        }
		$.ajax({
		    type: "POST",
		    url: "penjings/DelPenJingType",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var errormsg = r.errormsg;
		    	var msg=r.msg;
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





