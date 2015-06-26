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
		$(pictures).each(function(index,item){
			 $("#hr").after(
					 "<div class='editP'>"+
	 					"<div class='editPic'>"+
	 						"<img alt='盆景' src='"+item.pictureUrl+"'>"+
	 					"</div>"+
	 					"<input class='main main_"+item.penJingPictureId+"' type='radio' name='mainPicture' target='"+item.pictureUrl+"' value="+item.penJingPictureId+">首页展示"+
	 					"<select class='editS editS_"+item.penJingPictureId+"' onchange='change("+item.penJingPictureId+")' target='"+item.penJingPictureId+"'>"+
							  "<option value ='1'>通过审核</option>"+
							  "<option value ='0'>不通过审核</option>"+
						"</select>"+
	 				"</div>"
			 );
		});
		
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
		var main = $("input[name='mainPicture']:checked").val();
		if(main == null){
			$.messager.alert("系统提示","请选择一张首页展示图片");
			return;
		}
		$(".editS").each(function(){
			var i = $(this).children('option:selected').val();
			if (i == 1) {
				passPicture.push($(this).attr('target'));
			}else{
				unPassPicture.push($(this).attr('target'));
			}
		})
		var params = {
			"penjingId" : penjingId,
			"penjingState" : 1,
			"passPicture" : passPicture.join(","),
			"unPassPicture" : unPassPicture.join(","),
			"mainPicture" : $("input[name='mainPicture']:checked").attr('target')
		}
		edit(params);
	}else {
		var params = {
				"penjingId" : penjingId,
				"penjingState" : 2
			}
			edit(params);
	}
}
 
 function edit(params) {
	 $.ajax({
		    type: "POST",
		    url: "penjings/checkPenjing",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	var nu = r.nu;
		    	if(result == 1){
		    		closedialog();
		    		$.messager.alert("系统提示","修改成功");
		    		$("#dg").datagrid("reload");
		    	}else {
		    		$.messager.alert("系统提示","修改失败，系统错误");
		    	}
		    },
		    error: function back(data){
		    	$.messager.alert("系统提示","修改失败，系统错误");
		    }
		    });
}

