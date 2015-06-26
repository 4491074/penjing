var pictureH;
var penjingId;


$(document).ready(function(){
	pictureH = $("#pictures").width()*0.3;
})

$(document).ready(function(){
	$('.editPenjing').change(function(){  
		var index=$(this).children('option:selected').val();//这就是selected的值 
		if(index == 1){
			$(".editS").val("1");
			$(".editS").removeAttr("disabled");
			$(".main").attr("disabled", false);
		}else if (index == 2){
			$(".editS").val("2");
			$(".editS").attr("disabled","disabled");
			$(".main").attr("checked",false);
			$(".main").attr("disabled", true);
		}else{
			$(".editS").val("0");
			$(".editS").attr("disabled","disabled");
			$(".main").attr("checked",false);
			$(".main").attr("disabled", true);
		}
		   })  
})

function change(index) {
	var i=$(".editS_"+index).children('option:selected').val();//这就是selected的值
	if(i == 1){
		$(".main_"+index).attr("disabled", false);
	}else if(i == 2){
		$(".main_"+index).attr("checked",false);
		$(".main_"+index).attr("disabled", true);
	}else{
		$(".main_"+index).attr("checked",false);
		$(".main_"+index).attr("disabled", true);
	}
}

function go(value,row,index) {
	return "<a href='javascript:seePicture();'>查看图片</a>";
}

function seePicture() {
	$(".p").remove();
	var selected = $("#dg").datagrid('getSelected');
	var pictures = eval(selected.pictures);
	$(pictures).each(function(index,item){
		 $("#pictures hr").after(
			"<div class='p pic_"+index+"'>"+
		    	"<img alt='盆景' class='picture_"+index+"' src='"+item.pictureUrl+"'>"+
		    "</div>"
		 );
		 $(".pic_"+index).css('height',pictureH);
		 $(".picture_"+index).load(function(){
			 picCenter($(this));
		});
	});
	
	$("#pictures").dialog("open");
}

function picCenter(img) {
	var height = img.height();
	var width = img.width();
	var scale = "100%";
	if (width >= height) {
		scale = (0.5-height/(2*width))*100+"%";
		img.css({"width":"100%","margin-top":scale});
	}else {
		scale = (0.5-width/(2*height))*100+"%";
		img.css({"height":"100%","margin-left":scale});
	}
}

function openeditdialog() {
	var selections = $("#dg").datagrid('getSelections');
	if(selections.length!=1){
		$.messager.alert("系统提示","请选择一条要修改的数据！");
	}else{
		penjingId = selections[0].penJingId;
		$("#penJingId").text(penjingId);
		$("#penJingName").text(selections[0].penJingName);
		$("#penJingTitle").text(selections[0].penJingTitle);
		$("#penJingDescription").text(selections[0].penJingDescription);
		$(".editP").remove();
		var pictures = eval(selections[0].pictures);
		var m = selections[0].mainPicture;
		$(pictures).each(function(index,item){
			var id = item.penJingPictureId;
			var url = item.pictureUrl;
			var s = item.pictureStatus;
			if (s == 1) {
				if (m == url) {
					$("#hr").after(
							 "<div class='editP'>"+
			 					"<div class='editPic'>"+
			 						"<img alt='盆景' src='"+item.pictureUrl+"'>"+
			 					"</div>"+
			 					"<input class='main main_"+id+"' type='radio' name='mainPicture' checked='checked' target='"+url+"' value="+id+">首页展示"+
			 					"<select class='editS editS_"+id+"' name='"+item.pictureUrl+"' onchange='change("+id+")' target='"+id+"'>"+
									  "<option selected = 'selected' value ='1'>通过审核</option>"+
									  "<option value ='0'>不通过审核</option>"+
									  "<option value ='2'>删除</option>"+
								"</select>"+
			 				"</div>"
					 );
				}else {
					$("#hr").after(
							 "<div class='editP'>"+
			 					"<div class='editPic'>"+
			 						"<img alt='盆景' src='"+item.pictureUrl+"'>"+
			 					"</div>"+
			 					"<input class='main main_"+id+"' type='radio' name='mainPicture' target='"+url+"' value="+id+">首页展示"+
			 					"<select class='editS editS_"+id+"' name='"+item.pictureUrl+"' onchange='change("+id+")' target='"+id+"'>"+
									  "<option selected = 'selected' value ='1'>通过审核</option>"+
									  "<option value ='0'>不通过审核</option>"+
									  "<option value ='2'>删除</option>"+
								"</select>"+
			 				"</div>"
					 );
				}
			}else {
				$("#hr").after(
						 "<div class='editP'>"+
		 					"<div class='editPic'>"+
		 						"<img alt='盆景' src='"+item.pictureUrl+"'>"+
		 					"</div>"+
		 					"<input class='main main_"+id+"' type='radio' name='mainPicture' disabled=true target='"+url+"' value="+id+">首页展示"+
		 					"<select class='editS editS_"+id+"' name='"+item.pictureUrl+"' onchange='change("+id+")' target='"+id+"'>"+
								  "<option value ='1'>通过审核</option>"+
								  "<option selected = 'selected' value ='0'>不通过审核</option>"+
								  "<option value ='2'>删除</option>"+
							"</select>"+
		 				"</div>"
				 );
			}
		});
		var state = selections[0].penJingStatus;
		if (state == "通过审核") {
			$(".editPenjing").val("1");
		}else {
			$(".editPenjing").val("0");
			$(".editS").val("0");
			$(".editS").attr("disabled","disabled");
			$(".main").attr("checked",false);
			$(".main").attr("disabled", true);
		}
		
		$("#dialog_1").dialog({
			title:"修改",
		});
		$("#dialog").dialog("open");
	}
}

function closedialog() {
	$("#dialog").dialog("close");
}

function savedialog() {
	var penjingState=$('.editPenjing').children('option:selected').val();//这就是selected的值
	if (penjingState == 1) {
		var passPicture = [];
		var unPassPicture = [];
		var delPicture = [];
		var delPicturePath = [];
		var main = $("input[name='mainPicture']:checked").val();
		if(main == null){
			$.messager.alert("系统提示","请选择一张首页展示图片");
			return;
		}
		$(".editS").each(function(){
			var i = $(this).children('option:selected').val();
			if (i == 1) {
				passPicture.push($(this).attr('target'));
			}else if (i == 2){
				delPicture.push($(this).attr('target'));
				delPicturePath.push($(this).attr('name'));
			}else{
				unPassPicture.push($(this).attr('target'));
			}
		})
		var params = {
			"penjingId" : penjingId,
			"penjingState" : 1,
			"passPicture" : passPicture.join(","),
			"unPassPicture" : unPassPicture.join(","),
			"deletePenjingIds" : delPicture.join(","),
			"picturePath" : delPicturePath.join(","),
			"mainPicture" : $("input[name='mainPicture']:checked").attr('target')
		}
		edit(params);
	}else if (penjingState == 2){
		var delPicturePath = [];
		$(".editS").each(function(){
			delPicturePath.push($(this).attr('name'));
		})
		var params = {
				"penjingId" : penjingId,
				"picturePath" : delPicturePath.join(",")
			}
			dele(params);
	}else {
		var params = {
				"penjingId" : penjingId,
				"penjingState" : 2
			}
			edit(params);
	}
}

function doremove(){
	var selections = $("#dg").datagrid('getSelections');
		if(selections.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
		}else{
			$.messager.confirm("系统提示","确认要删除这条数据吗？",function(r){
				if(r){
					var ps = eval(selections[0].pictures);
					var paths=[];
					$(ps).each(function(index,item){
						paths.push(item.pictureUrl);
					});
					var params = {
					           "picturePath" : paths.join(","),
					           "penjingId" : selections[0].penJingId
					        }
					dele(params);
				}	
			});
		}
	}

function dele(params) {
	 $.ajax({
		    type: "POST",
		    url: "penjings/manDelPenjing",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	if(result == 1){
		    		closedialog();
		    		$.messager.alert("系统提示","删除成功");
		    		$("#dg").datagrid("reload");
		    	}else {
		    		if(result == 0){
						$.messager.alert("系统提示",r.error);
		    		}else {
		    			$.messager.alert("系统提示","删除失败");
					}
		    	}
		    },
		    error: function back(data){
		    	$.messager.alert("系统提示","删除失败，系统错误");
		    }
		    });
}
 
 function edit(params) {
	 $.ajax({
		    type: "POST",
		    url: "penjings/checkPenjing_1",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	var nu = r.nu;
		    	if(result == 1){
		    		closedialog();
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

