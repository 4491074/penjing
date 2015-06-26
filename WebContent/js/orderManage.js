var penjingId;
var penjingName;
var penjingTitle;
var orderId;
var customerName;
var customerPhone;
var customerAdd;
var distributionTime;
var orderState;
var note;

function opensavedialog() {
	$(".penjingTab").remove();
	$("#customerName").val("");
	$("#customerPhone").val("");
	$("#customerAdd").val("");
	$('#distributionTime').datetimebox('setValue', '');
	$("#note").val("");
	$(".orderState").val("1");
	$("#penjingSearck").val("");
	$("#dialog").dialog({
		title:"添加",
	});
	$("#dialog").dialog("open");
}

function go(value,row,index) {
	return "<a href='javascript:penjingListManage("+row.orderId+");'>点击查看详情</a>";
}

function penjingListManage(id) {
	var url = "penjingListManage?id="+id;
	window.parent.openTab("订单详情管理（编号："+id+"）",url);
}

/*
 * 根据盆景id查找盆景简单信息
 * 验证用户输入是否符合格式
 */
function penjingSearch() {
	var id = $("#penjingSearck").val();
	if(/^[0-9]*$/.test(id)){
		if (id<1) {
			$.messager.alert("系统提示","盆景编号必需大于0!");
		}else{
			searchPenjing(id);
		}
	}else {
		$.messager.alert("系统提示","盆景编号为纯数字!");
	}
}

function searchPenjing(id){
	var params = {
	          "penjingId" : id
	        }
	$.ajax({
	    type: "POST",
	    url: "penjings/searchPenjing",
	    data: params,
	    success: function back(data){
	    	var r = eval(data);
	    	var result = r.result;
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

/*
 * 获取盆景后用户输入盆景的数量添加入表格
 */
function subPenjing() {
	var result = $("#addList").form("validate");
	if(result){
		var count = $("#count").val();
		var listNote = $("#listNote").val();
		if(/^[0-9]*$/.test(count)){
			var i = 0;
			$(".penjingTab").each(function(){
				var id = $(this).attr('target')
				if(id == penjingId){
					$(".count_"+penjingId).text(count);
					$(".listNote_"+penjingId).html(listNote);
					i=1;
					return;
				}
			});
			if(i == 0){
				$("#lastTr").before(
					"<tr class='penjingTab penjingTab_"+penjingId+"' target='"+penjingId+"'>"+
						"<td class='penjingId_"+penjingId+"'>"+penjingId+"</td>"+
						"<td class='penjingName_"+penjingId+"'>"+penjingName+"</td>"+
						"<td class='penjingTitle_"+penjingId+"'>"+penjingTitle+"</td>"+
						"<td class='listNote_"+penjingId+"'>"+listNote+"</td>"+
						"<td class='count_"+penjingId+"'>"+count+"</td>"+
						"<td><a href='JavaScript:editPenjing("+penjingId+")'>修改</a></td>"+
					"</tr>"
				);
			}
			closePenjing();
		}else {
			$.messager.alert("系统提示","请输入正确的盆景数目!");
		}
	}
}

/*
 * 修改已经添加进订单的盆景
 */
function editPenjing(id) {
		penjingId = id;
		$("#editPenJingId").text($(".penjingId_"+id).text());
		$("#editPenJingName").text($(".penjingName_"+id).text());
		$("#editPenJingTitle").text($(".penjingTitle_"+id).text());
		$("#editListNote").text($(".listNote_"+id).text());
		$("#editCount").val($(".count_"+id).text());
		$("#editPenjing").dialog("open");
}

/*
 * 修改成功后重新显示
 */
function editIt() {
	var result = $("#editForm").form("validate");
	if(result){
		var count = $("#editCount").val();
		var listNote = $("#editListNote").val();
		if(/^[0-9]*$/.test(count)){
			$(".count_"+penjingId).text(count);
			$(".listNote_"+penjingId).text(listNote);
			$("#editPenjing").dialog("close");
		}else {
			$.messager.alert("系统提示","盆景编号为纯数字!");
		}
	}
}

/*
 * 关闭盆景选择窗口
 */
function closePenjing() {
	$("#penjing").dialog("close");
}

function showPenjing(r) {
	var i = 0;
	$("#mainPicture").attr('src',"");
	penjingId = r.penJingId;
	penjingName = r.penJingName;
	penjingTitle = r.penJingTitle;
	$("#penJingId").text(penjingId);
	$("#penJingName").text(penjingName);
	$("#penJingTitle").text(penjingTitle);
	$("#mainPicture").attr('src',r.mainPicture);
	$("#penJingStatus").text(r.penJingStatus);
	$(".penjingTab").each(function(){
		var id = $(this).attr('target');
		if(id == penjingId){
			$("#listNote").val($(".listNote_"+penjingId).text());
			$("#count").val($(".count_"+penjingId).text());
			i = 1;
			return;
		}
	});
	if (i == 0) {
		$("#listNote").val("");
		$("#count").val("");
	}
	$("#penjing").dialog("open");
}

/*
 * 移除订单中的盆景
 */
function removeIt() {
	$(".penjingTab_"+penjingId).remove();
	$("#editPenjing").dialog("close");
	
}

function openeditdialog() {
	var selections = $("#dg").datagrid('getSelections');
	if(selections.length!=1){
		$.messager.alert("系统提示","请选择一条要修改的数据！");
	}else{
		orderId = selections[0].orderId;
		customerName = selections[0].customerName;
		customerPhone = selections[0].customerPhone;
		customerAdd = selections[0].customerAdd;
		note = selections[0].note;
		distributionTime = selections[0].distributionTime;
		orderState = selections[0].orderState;
		$("#editCustomerName").val(customerName);
		$("#editCustomerPhone").val(customerPhone);
		$("#editCustomerAdd").val(customerAdd);
		$("#editNote").val(note);
		$('#editDistributionTime').datetimebox('setValue', distributionTime);
		if (orderState == "交易中") {
			$(".editOrderState").val("1");
		}else if(orderState == "其他"){
			$(".editOrderState").val("0");
		}else if(orderState == "交易完成"){
			$(".editOrderState").val("2");
		}else if(orderState == "交易取消"){
			$(".editOrderState").val("3");
		}
		$("#editDialog").dialog({
			title:"修改",
		});
		$("#editDialog").dialog("open");
	}
}

function closedialog() {
	$.messager.confirm("系统提示","确认退出订单吗？",function(r){
		if(r){
			$("#dialog").dialog("close");
		}	
	});
}

function closedialog1() {
	$("#editDialog").dialog("close");
}

 function savedialog(){
	 var result = $("#penjingForm").form("validate");
	 if(result){
		 var orderList = [];
		 $(".penjingTab").each(function(){
				var id = $(this).attr('target')
				var list = "{\"penjingId\":"+$(".penjingId_"+id).text()+",\"count\":"+$(".count_"+id).text()+",\"listNote\":\""+$(".listNote_"+id).text()+"\"}";
				orderList.push(list);
			});
		 var params = {
			  "order.customerName" : $("#customerName").val(),
			  "order.customerPhone" : $("#customerPhone").val(),
			  "order.customerAdd" : $("#customerAdd").val(),
			  "order.distributionTime" : $('#distributionTime').datetimebox('getValue'),
			  "order.note" : $("#note").val(),
			  "order.orderState" : $(".orderState").children('option:selected').val(),
			  "penjingList" : "["+orderList.join(",")+"]"
		 }
		 save(params);
	 }
 }
 
 function savedialog1(){
	 var result = $("#editPenjingForm").form("validate");
	 if(result){
		var customerName1 = $("#editCustomerName").val();
		var customerPhone1 = $("#editCustomerPhone").val();
		var customerAdd1 = $("#editCustomerAdd").val();
		var note1 = $("#editNote").val();
		var distributionTime1 = $('#editDistributionTime').datetimebox('getValue');
		var orderState1=$(".editOrderState").children('option:selected').val();//这就是selected的值
		if (customerName1 == customerName && customerPhone == customerPhone1 && customerAdd==customerAdd1 && note1==note && 
				distributionTime1 == distributionTime && ((orderState1==0&&orderState=="其他")||(orderState1==1&&orderState=="交易中")||(orderState1==2&&orderState=="交易完成")||(orderState1==3&&orderState=="交易取消"))) {
			$("#editDialog").dialog("close");
			return;
		}
		 var params = {
			  "order.orderId" : orderId,
			  "order.customerName" : customerName1,
			  "order.customerPhone" : customerPhone1,
			  "order.customerAdd" : customerAdd1,
			  "order.distributionTime" : distributionTime1,
			  "order.note" : note1,
			  "order.orderState" : orderState1
		 }
		 editOrder(params);
	 }
 }
 
 function save(params) {
	 $.ajax({
		    type: "POST",
		    url: "order/addOrder",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	if(result == 1){
		    		$("#dialog").dialog("close");
		    		$.messager.alert("系统提示","添加成功");
		    		$("#dg").datagrid("reload");
		    	}else {
		    		$.messager.alert("系统提示","添加失败，系统错误");
		    	}
		    },
		    error: function back(data){
		    	$.messager.alert("系统提示","添加失败，系统错误");
		    }
		    });
}
 
 function editOrder(params) {
	 $.ajax({
		    type: "POST",
		    url: "order/editOrder",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	if(result == 1){
		    		$("#editDialog").dialog("close");
		    		$.messager.alert("系统提示","更新成功");
		    		$("#dg").datagrid("reload");
		    	}else {
		    		$.messager.alert("系统提示","更新失败");
		    	}
		    },
		    error: function back(data){
		    	$.messager.alert("系统提示","更新失败");
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
					var id = selections[i].orderId;
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
		           "orderId" : ids
		        }
		 $.ajax({
			    type: "POST",
			    url: "penjingList/delOrder",
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

