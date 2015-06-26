<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回帖管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/replyManage.js"></script>
</head>
<body style="margin: 5px;">
<table id="dg" title="回帖管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
	url="${page }" toolbar="#tools" fit="true" pageSize="20">
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th field="floor" width="40">楼层数</th>
			<th field="userName" width="100">楼主</th>
			<th field="content" width="200">回帖内容</th>
			<th field="time" width="100">创建时间</th>
			<th field="replyState" width="40">回帖状态</th>
		</tr>
	</thead>
</table>

	<div id="tools">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doremove()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openeditdialog()">修改</a>
		</div>
	</div>
	
	<div id="bt">
    	<a class="easyui-linkbutton" id="ok" iconCls="icon-save" onclick="savedialog()">保存</a>
    	<a class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" onclick="closedialog()">关闭</a>
    </div>
	
    <div class="easyui-dialog" id="dialog" buttons="#bt" style="width:35%;height:40%;padding:10px" data-options="modal:true"
    	closed="true">
    	<form action="#" id="replyForm">
	    	<table>
		    	<tr style="margin-bottom: 5px;">
		    		<td style="width: 70px;" align="right">回帖编号：&nbsp;</td>
		   			<td id="replyId"></td>
		   		</tr>
	    		<tr style="margin-bottom: 5px;">
	    			<td valign="top" align="right">回帖内容：&nbsp;</td>
	    			<td id="content"></td>
	    		</tr>
	    		<tr style="margin-bottom: 5px;">
	    			<td valign="top" align="right">回帖状态：&nbsp;</td>
	    			<td>
	    				<input id="state" type="radio" name="reply.replyState" value="1" checked="checked">正常
	    				<input id="state1" type="radio" name="reply.replyState" value="0" >冻结（<font color="red">冻结后回帖将不可见</font>）
	    			</td>
	    		</tr>
	    	</table>
	    	</form>
    </div>
</body>
</html>