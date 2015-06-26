<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>论坛管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/forumManage.js"></script>
</head>
<body style="margin: 5px;">
<table id="dg" title="论坛管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
	url="forum/getAllForum" toolbar="#tools" fit="true" pageSize="20" >
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th field="forumName" width="12">论坛名称</th>
			<th field="notice" width="30">论坛公告</th>
			<th field="pageNu" width="10">帖子数量/页</th>
			<th field="forumStatus" width="10">论坛状态</th>
		</tr>
	</thead>
</table>

<div id="tools">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openeditdialog()">修改</a>
		</div>
</div>

<div id="bt">
    	<a class="easyui-linkbutton" id="ok" iconCls="icon-save" onclick="savedialog()">保存</a>
    	<a class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" onclick="closedialog()">关闭</a>
    </div>
	
	 <div class="easyui-dialog" id="dialog" buttons="#bt" style="width:40%;height:70%;padding:10px" data-options="modal:true"
    	closed="true">
    	<form action="#" id="newsbdform">
	    	<table>
	    		<tr>
		    		<td>论坛名称：&nbsp;</td>
		   			<td><input id="forumName" type="text"  name="forumName" maxlength="10" class="easyui-validatebox" required="true"></td>
		   		</tr>
		   		
		   		
		   		
	    		<tr>
	    			<td valign="top">论坛公告：&nbsp;</td>
	    			<td>
	    				<textarea id="notice" style="width: 150%;font-size: 12px;" name="notice" rows="7"></textarea>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<td>帖子数量：&nbsp;</td>
		   			<td><input id="pageNu" type="text"  name="pageNu" maxlength="10" class="easyui-validatebox" required="true"></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>论坛状态：&nbsp;</td>
	    			<td>
	    				<input id="state" type="radio" name="state" value="1" checked="checked">正常
	    				<input id="state1" type="radio" name="state" value="0" >冻结（<font color="red">冻结后不能使用</font>）
	    			</td>
	    		</tr>
	    	</table>
	    </form>
    </div>






</body>
</html>