var count;
var listNote;
var penjingListId;
var penjingId;

/*
 * 修改已经添加进订单的盆景
 */
function openeditdialog() {
	var selections = $("#dg").datagrid('getSelections');
	if(selections.length!=1){
		$.messager.alert("系统提示","请选择一条要修改的数据！");
	}else{
		penjingListId = selections[0].penJingListId;
		count = selections[0].count;
		listNote = selections[0].listNote;
		$("#editPenJingId").text(selections[0].penJingId);
		$("#editPenJingName").text(selections[0].penJingName);
		$("#editPenJingTitle").text(selections[0].penJingTitle);
		$("#editListNote").val(listNote);
		$("#editCount").val(count);
		$("#editDialog").dialog({
			title:"修改",
		});
		$("#dialog").dialog("open");
	}
}

/*
 * 修改已经添加进订单的盆景
 */
function openeditdialog_1(r) {
		penjingListId = r.penJingListId;
		count = r.count;
		listNote = r.listNote;
		$("#editPenJingId").text(r.penJingId);
		$("#editPenJingName").text(r.penJingName);
		$("#editPenJingTitle").text(r.penJingTitle);
		$("#editListNote").val(listNote);
		$("#editCount").val(count);
		$("#editDialog").dialog({
			title:"修改",
		});
		$("#dialog").dialog("open");
}
/*
 * 根据盆景id查找盆景简单信息
 * 验证用户输入是否符合格式
 * 首先在本地数据中查找该订单是否已经添加次盆景
 * 然后后台数据库查找
 */
$(function(){
    $(".easyui-textbox").textbox({onClickButton:function(){
        var id = $(".easyui-textbox").val();
        if(/^[0-9]*$/.test(id)){
    		if (id<1) {
    			$.messager.alert("系统提示","盆景编号必需大于0!");
    		}else{
    			searchPenjing(id,$("#dg").attr('target'));
    		}
    	}else {
    		$.messager.alert("系统提示","盆景编号为纯数字!");
    	}
    }})
})

function searchPenjing(id,orderId){
	var params = {
	          "penjingId" : id,
	          "orderId" : orderId
	        }
	$.ajax({
	    type: "POST",
	    url: "penjings/searchPenjingToList",
	    data: params,
	    success: function back(data){
	    	var r = eval(data);
	    	var result = r.result;
	    	if(result == 3){
	    		openeditdialog_1(r);
	    	}
	    	if(result == 1){
	    		showPenjing(r);
	    	}else if (result == 2){
	    		$.messager.alert("系统提示","不存在该编号的盆景");
	    	}
	    },
	    error: function back(data){
	    	$.messager.alert("系统提示","系统错误");
	    }
	    });
}

function showPenjing(r) {
	var i = 0;
	$("#mainPicture").attr('src',"");
	penjingId = r.penJingId;
	$("#penJingId").text(penjingId);
	$("#penJingName").text(r.penJingName);
	$("#penJingTitle").text(r.penJingTitle);
	$("#mainPicture").attr('src',r.mainPicture);
	$("#penJingStatus").text(r.penJingStatus);
	$("#listNote").val("");
	$("#count").val("");
	$("#penjing").dialog("open");
}

/*
 * 添加订单详情
 */
function subPenjing() {
	var c = $("#count").val();
	if(/^[0-9]*$/.test(c)){
		 var params = {
				 "penjingId" : penjingId,
				  "orderId" : $("#dg").attr('target'),
				  "count" : c,
				  "listNote" : $("#listNote").val()
			 }
		 save(params);
		}else {
			$.messager.alert("系统提示","盆景数量为纯数字!");
		}
}

function save(params) {
	 $.ajax({
		    type: "POST",
		    url: "penjingList/savePenjingList",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	if(result == 1){
		    		$("#dialog").dialog("close");
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

function go(value,row,index) {
	return "<a href='javascript:seePicture();'>查看图片</a>";
}

function seePicture() {
	$('#mainPicture').attr('src',"");
	var selected = $("#dg").datagrid('getSelected');
	$('#mainPic').attr('src',selected.mainPicture);
	$("#mPicture").dialog("open");
}

function closedialog() {
	$("#dialog").dialog("close");
}

function editdialog(){
	 var result = $("#penjingForm").form("validate");
	 if(result){
		 var count1 = $("#editCount").val();
		 var listNote1 = $("#editListNote").val();
		 if(count == count1 && listNote == listNote1){
			 $("#dialog").dialog("close");
			 return;
		 }
		 if(/^[0-9]*$/.test(count1)){
			 var params = {
					  "penjingList" : penjingListId,
					  "count" : count1,
					  "listNote" : listNote1
				 }
			 edit(params);
			}else {
				$.messager.alert("系统提示","盆景数量为纯数字!");
			}
	 }
}

function edit(params) {
	 $.ajax({
		    type: "POST",
		    url: "penjingList/editPenjingList",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	if(result == 1){
		    		$("#dialog").dialog("close");
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

function doremove(){
	var selections = $("#dg").datagrid('getSelections');
		if(selections.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
		}else{
			var selIds=[];
			for(var i=0;i<selections.length;i++){
				var id = selections[i].penJingListId;
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
			 	
	           "penjingList" : ids
	        }
	 $.ajax({
		    type: "POST",
		    url: "penjingList/delPenjingList",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	if(result == 1){
		    		var nu = r.nu;
		    		$.messager.alert("系统提示","删除成功,你已经删除了<font color=red>"+nu+"</font>条数据");
		    		$("#dg").datagrid("reload");
		    	}else {
		    		if(result == 0){
						$.messager.alert("系统提示",r.error);
		    		}else {
		    			$.messager.alert("系统提示","删除失败，系统错误");
					}
		    	}
		    },
		    error: function back(data){
		    	$.messager.alert("系统提示","删除失败，系统错误");
		    }
		    });
}

