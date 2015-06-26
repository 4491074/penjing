<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的盆景</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/myPenjingManage.js"></script>
<link href="css/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/swfobject.js"></script>
<script type="text/javascript" src="js/jquery.uploadify.js"></script>
<style>
body {
	font: 12px/16px Arial, Helvetica, sans-serif;
}

#fileQueue {height: 100px;overflow: auto;border: 1px solid #0088CC;}
#fileQueue_1 {height: 100px;overflow: auto;border: 1px solid #0088CC;}
.p{width: 30%;margin: 0px 1.5% 10px 1.5%;float: left;margin-bottom: 10px;border-width: 1px;border-color: #333;border-style: solid;}
.editP{width: 100px; margin: 0px 3px 5px 3px;float: left;margin-bottom: 10px;border-width: 1px;border-color: #333;border-style: solid;}
.editPic{width: 100%;height: 100px;margin-bottom: 3px;}
.editPic img{width: 100%;height: 100%;}

</style>
</head>
<body style="margin: 5px;">
<table id="dg" title="我的盆景" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
	url="penjings/getMyPenjing" toolbar="#tools" fit="true" pageSize="10" singleSelect="true">
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th field="penJingId" width="10">盆景编号</th>
			<th field="penJingName" width="20">盆景名称</th>
			<th field="penJingTitle" width="20">盆景标题</th>
			<th field="penJingTypeName" width="20">盆景类型</th>
			<th field="penJingDescription" width="20">盆景赏析/描述</th>
			<th field="pictures" width="10" data-options="formatter:go">盆景图片</th>
			<th field="publishTime" width="20">发布时间</th>
			<th field="penJingStatus" width="10">盆景状态</th>
			<th field="remark" width="20">备注</th>
		</tr>
	</thead>
</table>
	<div id="tools">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="opensavedialog()">添加</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doremove()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openeditdialog()">修改</a>
		</div>
		
	</div>
	
	<div id="bt">
    	<a class="easyui-linkbutton" id="ok" iconCls="icon-save" onclick="savedialog()">保存</a>
    	<a class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" onclick="closedialog()">关闭</a>
    </div>
    
    <div id="bt_1">
    	<a class="easyui-linkbutton" id="ok" iconCls="icon-save" onclick="savedialog_1()">保存</a>
    	<a class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" onclick="closedialog_1()">关闭</a>
    </div>
	
    <div class="easyui-dialog" id="dialog" buttons="#bt" style="width:45%;height:95%;" modal="true"
    	closed="true">
	    	<form action="#" id="penjingForm">
	    	<table>
		    	<tr>
		    		<td style="width: 90px;" align="right">盆景名称：&nbsp;</td>
		   			<td><input id="penJingName" type="text" style="width: 100%;" maxlength="10" class="easyui-validatebox" required="true"></td>
		   		</tr>
		   		<tr>
		    		<td style="width: 80px;" align="right">盆景标题：&nbsp;</td>
		   			<td><input id="penJingTitle" type="text" style="width: 100%;" maxlength="10" class="easyui-validatebox" required="true"></td>
		   		</tr>
	    		<tr>
	    			<td valign="top" align="right">盆景赏析/描述：&nbsp;</td>
	    			<td>
	    				<textarea id="penJingDescription" style="width: 100%;font-size: 12px;" rows="2" class="easyui-validatebox" data-options="validType:'length[0,100]'"></textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td valign="top" align="right">盆景类型：&nbsp;</td>
	    			<td>
	    				<input id="typeSelect" class="easyui-combobox" 
							name="language"
							data-options="
								url:'penjingType/getPenjingType',
								method:'post',
								valueField:'id',
								textField:'text',
								panelHeight:'auto',
								groupField:'group',
								editable:false
						">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td valign="top" align="right">盆景图片选择(<font style="color: red;">上传失败可能是图片过大，每张图片限制512K</font>)：</td>
	    			<td>
	    				<div class="allFream" style="width: 300px;">
							<div id="fileQueue" style="width: 100%"></div>
							<div id="result"></div>
							<div style="width: 100%">
								<div style="float: left; margin-top: 5px;">
									<input type="file" name="pictures" id="uploadify" />
								</div>
								<!-- 
								<p style="float: left; margin-left: 50px; font-size: 18px;">
									<a href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a>&nbsp;
									<a href="javascript:$('#uploadify').uploadify('cancel','*')">取消上传</a>
								</p>
								 -->
							</div>
						</div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td valign="top" align="right">备注：&nbsp;</td>
	    			<td>
	    				<textarea id="remark" style="width: 100%;font-size: 12px;" rows="2" class="easyui-validatebox" data-options="validType:'length[0,100]'"></textarea>
	    			</td>
	    		</tr>
	    	</table>
	    	</form>
    </div>
    
    
    <div class="easyui-dialog" id="dialog_1" buttons="#bt_1" style="width:45%;height:95%;" modal="true"
    	closed="true">
	    	<form action="#" id="penjingForm_1">
	    	<table>
		    	<tr>
		    		<td style="width: 90px;" align="right">盆景名称：&nbsp;</td>
		   			<td><input id="penJingName_1" type="text" style="width: 100%;" maxlength="10" class="easyui-validatebox" required="true"></td>
		   		</tr>
		   		<tr>
		    		<td style="width: 80px;" align="right">盆景标题：&nbsp;</td>
		   			<td><input id="penJingTitle_1" type="text" style="width: 100%;" maxlength="10" class="easyui-validatebox" required="true"></td>
		   		</tr>
	    		<tr>
	    			<td valign="top" align="right">盆景赏析/描述：&nbsp;</td>
	    			<td>
	    				<textarea id="penJingDescription_1" style="width: 100%;font-size: 12px;" rows="2" class="easyui-validatebox" data-options="validType:'length[0,100]'"></textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td valign="top" align="right">盆景类型：&nbsp;</td>
	    			<td>
	    				<input id="typeSelect_1" class="easyui-combobox" 
							name="language"
							data-options="
								url:'penjingType/getPenjingType',
								method:'post',
								valueField:'id',
								textField:'text',
								panelHeight:'auto',
								groupField:'group',
								editable:false
						">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td valign="top" align="right">图片删除：</td>
	    			<td>
	    				<hr id="hr">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td valign="top" align="right">添加图片(<font style="color: red;">上传失败可能是图片过大，每张图片限制512K</font>)：</td>
	    			<td>
	    				<div class="allFream" style="width: 300px;">
							<div id="fileQueue_1" style="width: 100%"></div>
							<div id="result"></div>
							<div style="width: 100%">
								<div style="float: left; margin-top: 5px;">
									<input type="file" name="pictures" id="uploadify_1" />
								</div>
								<!-- 
								<p style="float: left; margin-left: 50px; font-size: 18px;">
									<a href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a>&nbsp;
									<a href="javascript:$('#uploadify').uploadify('cancel','*')">取消上传</a>
								</p>
								 -->
							</div>
						</div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td valign="top" align="right">备注：&nbsp;</td>
	    			<td>
	    				<textarea id="remark_1" style="width: 100%;font-size: 12px;" rows="2" class="easyui-validatebox" data-options="validType:'length[0,100]'"></textarea>
	    			</td>
	    		</tr>
	    	</table>
	    	</form>
    </div>
    
    <div class="easyui-dialog" id="pictures" title="盆景图片" style="width:95%;height:95%;" modal="true" closed="true">
	    	<hr>
    </div>
</body>
</html>