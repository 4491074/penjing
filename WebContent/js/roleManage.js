var id = null;

function searchStudent(value,name) {
	$("#dg").datagrid('load',{
		searchval:value,
		searchtype:name
	});
	$("#searchit").searchbox('setValue',null);
}

function doremove(){
	var selections = $("#dg").datagrid('getSelections');
		if(selections.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
		}else{
			var selIds=[];
			for(var i=0;i<selections.length;i++){
				var id = selections[i].roleId;
				if(id == 1){
					$.messager.alert("系统提示","<font color=red>游客</font>不能被删除");
					return;
				}else if(id == 2){
					$.messager.alert("系统提示","<font color=red>超级管理员</font>不能被删除");
					return;
				}else {
					selIds.push(id);
				}
			}
			var ids=selIds.join(",");
			$.messager.confirm("系统提示","确认要删除这<font color=red>"+selections.length+"</font>条数据吗？<br>删除后该角色下的用户将转为<font color=red>游客</font>",function(r){
				if(r){
					dele(ids);
				}	
			});
		}
	}

function dele(ids) {
	 var params = {
	           roleId : ids
	        }
	 $.ajax({
		    type: "POST",
		    url: "role/delRole",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	var nu = r.nu;
		    	if(result == 1){
		    		closedialog();
		    		$.messager.alert("系统提示","删除成功,你已经删除了<font color=red>"+nu+"</font>条数据");
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
		var node = null;
		id = selections[0].roleId;
		if(id == 1){
			$.messager.alert("系统提示","<font color=red>游客</font>不能被修改");
		}else if(id == 2){
			$.messager.alert("系统提示","<font color=red>超级管理员</font>不能被修改");
		}else {
			$("#roleName").val(selections[0].roleName);
			$("#roleDescription").val(selections[0].roleDescription);
			if(selections[0].publishPenJing == "允许"){
				node = $("#tt").tree("find","PublishPenJing");
				$("#tt").tree("check",node.target);
			}else {
				node = $("#tt").tree("find","PublishPenJing");
				$("#tt").tree("uncheck",node.target);
			}
			if(selections[0].auditPenJing == "允许"){
				node = $("#tt").tree("find","AuditPenJing");
				$("#tt").tree("check",node.target);
			}else {
				node = $("#tt").tree("find","AuditPenJing");
				$("#tt").tree("uncheck",node.target);
			}
			if(selections[0].managePenJing == "允许"){
				node = $("#tt").tree("find","ManagePenJing");
				$("#tt").tree("check",node.target);
			}else {
				node = $("#tt").tree("find","ManagePenJing");
				$("#tt").tree("uncheck",node.target);
			}
			if(selections[0].publishNews == "允许"){
				node = $("#tt").tree("find","PublishNews");
				$("#tt").tree("check",node.target);
			}else {
				node = $("#tt").tree("find","PublishNews");
				$("#tt").tree("uncheck",node.target);
			}
			if(selections[0].auditNews == "允许"){
				node = $("#tt").tree("find","AuditNews");
				$("#tt").tree("check",node.target);
			}else {
				node = $("#tt").tree("find","AuditNews");
				$("#tt").tree("uncheck",node.target);
			}
			if(selections[0].manageNews == "允许"){
				node = $("#tt").tree("find","ManageNews");
				$("#tt").tree("check",node.target);
			}else {
				node = $("#tt").tree("find","ManageNews");
				$("#tt").tree("uncheck",node.target);
			}
			if(selections[0].creatTopic == "不允许"){
				node = $("#tt").tree("find","CreatTopic");
				$("#tt").tree("uncheck",node.target);
			}else {
				node = $("#tt").tree("find","CreatTopic");
				$("#tt").tree("check",node.target);
			}
			if(selections[0].manageForum == "允许"){
				node = $("#tt").tree("find","ManageForum");
				$("#tt").tree("check",node.target);
			}else {
				node = $("#tt").tree("find","ManageForum");
				$("#tt").tree("uncheck",node.target);
			}
			if(selections[0].manageRole == "允许"){
				node = $("#tt").tree("find","ManageRole");
				$("#tt").tree("check",node.target);
			}else {
				node = $("#tt").tree("find","ManageRole");
				$("#tt").tree("uncheck",node.target);
			}
			if(selections[0].manageUser == "允许"){
				node = $("#tt").tree("find","ManageUser");
				$("#tt").tree("check",node.target);
			}else {
				node = $("#tt").tree("find","ManageUser");
				$("#tt").tree("uncheck",node.target);
			}
			if(selections[0].manageInfo == "允许"){
				node = $("#tt").tree("find","ManageInfo");
				$("#tt").tree("check",node.target);
			}else {
				node = $("#tt").tree("find","ManageInfo");
				$("#tt").tree("uncheck",node.target);
			}
			if(selections[0].roleStatus != "正常"){
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
}

function closedialog() {
	$("#dialog").dialog("close");
	setnull();
}

 function savedialog(){
	 var result = $("#roleForm").form("validate");
	 if(result){
		var selections = $("#tt").tree('getChecked');
		var selIds=[];
		for(var i=0;i<selections.length;i++){
			selIds.push(selections[i].id);
		}
		var ids=selIds.join(",");
		var state;
		 if(document.getElementById("state").checked){
			 state = 1;
		 }else {
			 state = 0;
		}
		 if(id == ""){
			 var params = {
			           roleName : $("#roleName").val(),
			           roleDescription : $("#roleDescription").val(),
			           roleAuthority : ids,
			           roleState : state,
			        }
			 save(params);
		 }else {
			 var params = {
					   roleId : id,
			           roleName : $("#roleName").val(),
			           roleDescription : $("#roleDescription").val(),
			           roleAuthority : ids,
			           roleState : state,
			        }
			 edit(params);
		}
		
	 }else{
	 }
 }
 
 function save(params) {
	 $.ajax({
		    type: "POST",
		    url: "role/saveRole",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	var id = r.id;
		    	if(result == 1){
		    		closedialog();
		    		$.messager.alert("系统提示","添加成功,该角色编号为<font color=red>"+id+"</font>");
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
		    	$.messager.alert("系统提示","添加失败，系统错误");
		    }
		    });
}
 
 function edit(params) {
	 $.ajax({
		    type: "POST",
		    url: "role/editRole",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	var id = r.id;
		    	if(result == 1){
		    		closedialog();
		    		$.messager.alert("系统提示","修改成功");
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
		    	$.messager.alert("系统提示","修改失败，系统错误");
		    }
		    });
}

function setnull() {
	$("#roleName").val("");
	document.getElementById("state").checked = true;
	$("#roleDescription").val("");
	$("#tt").tree("reload");
}

