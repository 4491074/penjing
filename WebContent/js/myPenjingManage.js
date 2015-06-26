var filename; 
var picturePath=[];
var pictureNu = 0;
var pictureH = 0;
var penjingId;
var penjingName;
var penjingTitle;
var penjingDescription;
var penjingType;
var remark;
var pppppp = ""; //修改盆景后盆景数据
var upSuccess = 0;

$(document).ready(
		function() {
			$("#uploadify").uploadify(
							{
								//开启调试
								'debug' : false,
								//是否自动上传
								'auto' : false,
								//支持多文件上传
								'multi' : true,
								//超时时间
								'successTimeout' : 99999,
								//flash
								'swf' : "flvPlayer/uploadify.swf",
								//不执行默认的onSelect事件
								'overrideEvents' : [ 'onDialogClose' ],
								//文件选择后的容器ID
								'queueID' : 'fileQueue',
								//服务器端脚本使用的文件对象的名称 $_FILES个['upload']
								'fileObjName' : 'pictures',
								//上传处理程序
								'uploader' : 'penjingPicture/uploadPictures',
								//浏览按钮的背景图片路径
								//'buttonImage':'${pageContext.request.contextPath}/js/jquery.uploadify/uploadify-cancel.png',
								//浏览按钮的宽度
								'width' : '70',
								//浏览按钮的高度
								'height' : '25',
								'rollover' : true,
								'removeCompleted' : false,
								'removeTimeout' : 1,
								//expressInstall.swf文件的路径。
								//'expressInstall':'expressInstall.swf',
								//在浏览窗口底部的文件类型下拉菜单中显示的文本
								'fileTypeDesc' : 'Image Files', //如果配置了以下的'fileExt'属性，那么这个属性是必须的 
								'simUploadLimit' : 6, //每次最大上传文件数
								'queueSizeLimit' : 6, //每次最大上传文件数
								'uploadLimit' : 6, //总得上传数量
								'progressData' : 'speed',//有speed和percentage两种，一个显示速度，一个显示完成百分比 
								//允许上传的文件后缀
								'fileTypeExts' : '*.jpeg;*.gif;*.png;*.jpg',
								//上传文件的大小限制
								'fileSizeLimit' : '1024*512',
								'buttonText' : '选择文件',//浏览按钮
								//每次更新上载的文件的进展
								'onUploadProgress' : function(file,
										bytesUploaded, bytesTotal,
										totalBytesUploaded,
										totalBytesTotal) {
								},
								//选择上传文件后调用
								'onSelect' : function(file) {
								},
								//返回一个错误，选择文件的时候触发
								'onSelectError' : function(file,
										errorCode, errorMsg) {
									switch (errorCode) {
									case -100:
										alert("上传的文件数量已经超出系统限制的"
												+ $('#uploadify').uploadify('settings','queueSizeLimit')
												+ "个文件！");
										break;
									case -110:
										alert("文件 ["
												+ file.name
												+ "] 大小超出系统限制的"
												+ $('#uploadify').uploadify('settings','fileSizeLimit')
												+ "大小！");
										break;
									case -120:
										alert("文件 [" + file.name
												+ "] 大小异常！");
										break;
									case -130:
										alert("文件 [" + file.name
												+ "] 类型不正确！");
										break;
									}
								},
								//检测FLASH失败调用
								'onFallback' : function() {
									alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
								},
								//上传到服务器，服务器返回相应信息到data里
								'onUploadSuccess' : function(file,
										data, response) {
									var r = $.parseJSON(data);
									if(r.result == 1){
										pictureNu ++;
										picturePath.push(r.path);
									}
								},
								//把文件名传给后台
								'onUploadStart' : function(file) {
									var typeSelect = $("#typeSelect").combobox("getValue");
									$("#uploadify").uploadify("settings",'formData',
													{
//														'fileInputFileName' : file.name,
														"penjingType" : typeSelect,
													});
								},
								'onQueueComplete':function(stats){
									afterUploadPictures();
								}
							});
			$("#uploadify_1").uploadify(
					{
						//开启调试
						'debug' : false,
						//是否自动上传
						'auto' : false,
						//支持多文件上传
						'multi' : true,
						//超时时间
						'successTimeout' : 99999,
						//flash
						'swf' : "flvPlayer/uploadify.swf",
						//不执行默认的onSelect事件
						'overrideEvents' : [ 'onDialogClose' ],
						//文件选择后的容器ID
						'queueID' : 'fileQueue_1',
						//服务器端脚本使用的文件对象的名称 $_FILES个['upload']
						'fileObjName' : 'pictures',
						//上传处理程序
						'uploader' : 'penjingPicture/updatePictures',
						//浏览按钮的背景图片路径
						//'buttonImage':'${pageContext.request.contextPath}/js/jquery.uploadify/uploadify-cancel.png',
						//浏览按钮的宽度
						'width' : '70',
						//浏览按钮的高度
						'height' : '25',
						'rollover' : true,
						'removeCompleted' : false,
						'removeTimeout' : 1,
						//expressInstall.swf文件的路径。
						//'expressInstall':'expressInstall.swf',
						//在浏览窗口底部的文件类型下拉菜单中显示的文本
						'fileTypeDesc' : 'Image Files', //如果配置了以下的'fileExt'属性，那么这个属性是必须的 
						'simUploadLimit' : 6, //每次最大上传文件数
						'queueSizeLimit' : 6, //每次最大上传文件数
						'uploadLimit' : 6, //总得上传数量
						'progressData' : 'speed',//有speed和percentage两种，一个显示速度，一个显示完成百分比 
						//允许上传的文件后缀
						'fileTypeExts' : '*.jpeg;*.gif;*.png;*.jpg',
						//上传文件的大小限制
						'fileSizeLimit' : '1024*512',
						'buttonText' : '选择文件',//浏览按钮
						//每次更新上载的文件的进展
						'onUploadProgress' : function(file,
								bytesUploaded, bytesTotal,
								totalBytesUploaded,
								totalBytesTotal) {
						},
						//选择上传文件后调用
						'onSelect' : function(file) {
						},
						//返回一个错误，选择文件的时候触发
						'onSelectError' : function(file,
								errorCode, errorMsg) {
							switch (errorCode) {
							case -100:
								alert("上传的文件数量已经超出系统限制的"
										+ $('#uploadify_1').uploadify('settings','queueSizeLimit')
										+ "个文件！");
								break;
							case -110:
								alert("文件 ["
										+ file.name
										+ "] 大小超出系统限制的"
										+ $('#uploadify_1').uploadify('settings','fileSizeLimit')
										+ "大小！");
								break;
							case -120:
								alert("文件 [" + file.name
										+ "] 大小异常！");
								break;
							case -130:
								alert("文件 [" + file.name
										+ "] 类型不正确！");
								break;
							}
						},
						//检测FLASH失败调用
						'onFallback' : function() {
							alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
						},
						//上传到服务器，服务器返回相应信息到data里
						'onUploadSuccess' : function(file,
								data, response) {
							var r = $.parseJSON(data);
							if(r.result == 1){
								upSuccess ++;
							}
						},
						//把文件名传给后台
						'onUploadStart' : function(file) {
							var typeSelect = $("#typeSelect_1").combobox("getValue");
							$("#uploadify_1").uploadify("settings",'formData',
											{
//												'fileInputFileName' : file.name,
												"penjingType" : typeSelect,
												"penjingId" : penjingId,
											});
						},
						'onQueueComplete':function(stats){
							edit();
						}
					});
			pictureH = $("#pictures").width()*0.3;
		});

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

function opensavedialog() {
	picturePath=[];
	pictureNu = 0;
	 $("#penJingName").val("");
     $("#penJingTitle").val("");
     $("#remark").val("");
     $("#penJingDescription").val("");
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
		upSuccess = 0;
		penjingId = selections[0].penJingId;
		penjingName = selections[0].penJingName;
		penjingTitle = selections[0].penJingTitle;
		penjingDescription = selections[0].penJingDescription;
		penjingType = selections[0].penJingTypeId;
		remark = selections[0].remark;
		$("#penJingName_1").val(penjingName);
		$("#penJingTitle_1").val(penjingTitle);
		$("#penJingDescription_1").val(penjingDescription);
		$("#remark_1").val(remark);
		$("#typeSelect_1").combobox("select",penjingType);
		
		$(".editP").remove();
		var pictures = eval(selections[0].pictures);
		$(pictures).each(function(index,item){
			 $("#hr").after(
					 "<div class='editP'>"+
	 					"<div class='editPic'>"+
	 						"<img alt='盆景' src='"+item.pictureUrl+"'>"+
	 					"</div>"+
	 					"<select class='editS' target='"+item.penJingPictureId+"'>"+
							  "<option value ='0'>不删除</option>"+
							  "<option value ='1'>删除</option>"+
						"</select>"+
	 				"</div>"
			 );
		});
		
		$("#dialog_1").dialog({
			title:"修改",
		});
		$("#dialog_1").dialog("open");
	}
}

function closedialog() {
	$('#uploadify').uploadify('cancel','*');
	$("#dialog").dialog("close");
}

function closedialog_1() {
	$('#uploadify_1').uploadify('cancel','*');
	$("#dialog_1").dialog("close");
}

 function savedialog(){
	 var result = $("#penjingForm").form("validate");
	 if(result){
		var typeSelect = $("#typeSelect").combobox("getValue");
		var num =  $("#uploadify").data('uploadify').queueData.queueLength; //添加图片的数目
		if(typeSelect == null || typeSelect == ""){
			$.messager.alert("系统提示","请选择盆景类型");
		}else{
			if(num > 0){
				$('#uploadify').uploadify('upload','*');
			}else {
				afterUploadPictures();
			}
			
		}
	}
 }
 
 function savedialog_1(){
	 var result = $("#penjingForm_1").form("validate");
	 if(result){
		 var penjingName_1 = $("#penJingName_1").val();
		 var penjingTitle_1 = $("#penJingTitle_1").val();
		 var penjingDescription_1 = $("#penJingDescription_1").val();
		 var remark_1 = $("#remark_1").val();
		 var typeSelect_1 = $("#typeSelect_1").combobox("getValue");
		 var num =  $("#uploadify_1").data('uploadify').queueData.queueLength; //添加图片的数目
		 var pNu = 0; //删除图片的数目
		 var editIds=[]; //删除的图片的id
		 $(".editS").each(function(){
			 if($(this).val() == 1){
				 pNu++;
				 editIds.push($(this).attr('target'));
			 }
		 });
		 if(penjingName_1 == penjingName && penjingTitle_1 == penjingTitle && penjingDescription_1 == penjingDescription && remark_1 == remark 
				 && typeSelect_1 == penjingType && num == 0 && pNu == 0
		 ){
			 closedialog_1();
		}else{
			$.messager.confirm("系统提示","确认要修改吗？<br><font color=red>修改后盆景将变成未审核状态</font>",function(r){
				if(r){
					var ids = "";
					if(pNu > 0){
						ids = editIds.join(",");
					}
					pppppp = {
					           "penJingName" : penjingName_1,
					           "penJingTitle" : penjingTitle_1,
					           "remark" : remark_1,
					           "penJingDescription" : penjingDescription_1,
					           "penjingType" : typeSelect_1,
					           "deletePenjingIds" : ids,
					           "penjingId" : penjingId
					        }
					if(num > 0){  //如果有图片需要增加，需要先上传图片并写入数据库，然后再提交其他数据
						$('#uploadify_1').uploadify('upload','*');
					}else{
						edit();
					}
				}	
			});
		}
	}
 }
 
 function afterUploadPictures() {
	 var path=picturePath.join(",");
	 var typeSelect = $("#typeSelect").combobox("getValue");
	 var params = {
	           "penJingName" : $("#penJingName").val(),
	           "penJingTitle" : $("#penJingTitle").val(),
	           "remark" : $("#remark").val(),
	           "penJingDescription" : $("#penJingDescription").val(),
	           "picturePath" : path,
	           "penjingType" : typeSelect
	        }
	 save(params);
}
 
function save(params) {
	 $.ajax({
		    type: "POST",
		    url: "penjings/addPenjing",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	if(result == 1){
		    		closedialog();
		    		$.messager.alert("系统提示","添加成功成功，盆景编号为<font color=red>"+r.penjingId+"</font>");
		    		$("#dg").datagrid("reload");
		    	}else {
		    		$.messager.alert("系统提示","系统错误，添加失败");
		    	}
		    },
		    error: function back(data){
		    	$.messager.alert("系统提示","系统错误，添加失败");
		    }
		    });
}
 
 function edit() {
	 $.ajax({
		    type: "POST",
		    url: "penjings/editPenjing",
		    data: pppppp,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	var delNu = r.delNu;
		    	if(result == 1){
		    		closedialog_1();
		    		$.messager.alert("系统提示","修改成功<br>上传<font color=red>"+upSuccess+"</font>张图片<br>删除<font color=red>"+delNu+"</font>张图片");
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
		    url: "penjings/delPenjing",
		    data: params,
		    success: function back(data){
		    	var r = eval(data);
		    	var result = r.result;
		    	if(result == 1){
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