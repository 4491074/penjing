var topicId = null;
var title = null;
var isTop = null;
var topTime = null;
var topicState = null;

function go(value,row,index) {
	return "<a href='javascript:replyManage("+row.topicId+","+row.replyNu+");'>"+value+"</a>";
}

function replyManage(id,replyNu) {
	if(replyNu<1){
		$.messager.alert("系统提示","该主题帖没有回帖");
		return;
	}
	var url = "replyManage?id="+id;
	window.parent.openTab("回帖管理",url);
}

$(document).ready(function(){  
	 $('#isTop').change(function(){  
	   var index=$(this).children('option:selected').val();//这就是selected的值 
	   if(index == 1){
		   $("#topTimeTr").css("display","table-row");
	   }else{
		   $("#topTimeTr").css("display","none");
	   }
	   })  
	})

function doremove(){
	var selections = $("#dg").datagrid('getSelections');
		if(selections.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
		}else{
			var selIds=[];
			for(var i=0;i<selections.length;i++){
				var id = selections[i].topicId;
				selIds.push(id);
			}
			var ids=selIds.join(",");
			$.messager.confirm("系统提示","确认要删除这<font color=red>"+selections.length+"</font>条数据吗？<br>删除后该主题帖下的回帖将同时删除",function(r){
				if(r){
					dele(ids);
				}	
			});
		}
	}

function dele(ids) {
	 var params = {
	           topicId : ids
	        }
	 $.ajax({
		    type: "POST",
		    url: "topic/delTopics",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	var nu = r.nu;
		    	var ru = r.ru;
		    	if(result == 1){
		    		closedialog();
		    		$.messager.alert("系统提示","删除成功,你已经删除了<font color=red>"+nu+"</font>条主题帖<br>以及<font color=red>"+ru+"</font>条回帖");
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
		topicId = selections[0].topicId;
		title = selections[0].title;
//		isTop = $("#isTop option:selected").val();
		isTop = selections[0].isTop;
		topTime = selections[0].topTime;
		topicState = selections[0].topicState;
		$("#topicId").text(topicId);
		$("#title").text(title);
		if(isTop == "置顶"){
			$("#isTop").val(1);
			$("#topTimeTr").css("display","table-row");
			$('#topTime').datetimebox('setValue', topTime);
		}else {
			$("#isTop").val(0);
			$("#topTimeTr").css("display","none");
		}
		if(topicState != "正常"){
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
	setnull();
}

 function edit() {
	 var params = $("#topicForm").serialize();
	params = decodeURIComponent(params,true); //解决中文编码问题
	 $.ajax({
		    type: "POST",
		    url: "topic/editTopic?topicId="+topicId,
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	if(result == 1){
		    		closedialog();
		    		$.messager.alert("系统提示","修改成功");
		    		$("#dg").datagrid("reload");
		    	}else {
		    		$.messager.alert("系统提示","添加失败，系统错误");
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

