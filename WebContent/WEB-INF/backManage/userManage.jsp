<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/userManage.js"></script>
</head>
<body style="margin: 5px;">
<table id="dg" title="用户管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
	url="user/getAllUser" toolbar="#tools" fit="true" pageSize="10" singleSelect="true">
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th field="userId" width="10">用户编号</th>
			<th field="userName" width="10">用户名</th>
			<th field="mail" width="10">邮箱</th>
			<th field="phone" width="10">电话号码</th>
			<th field="enrollTime" width="10">注册时间</th>
			<th field="lastTime" width="10">上一次登录时间</th>
			<th field="userDescription" width="10">个人说明</th>
			<th field="roleName" width="10">用户角色</th>
			<th field="userStatus" width="10">用户状态</th>
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
	
    <div class="easyui-dialog" id="dialog" buttons="#bt" style="width:30%;height:50%;" modal="true"
    	closed="true">
	    	<table>
	    	
		    	<tr>
		    		<td align="right">用户编号：</td>
		   			<td id="userId"></td>
		   		</tr>
		   		
	    		<tr>
	    			<td align="right">用户名：</td>
	    			<td id="userName"></td>
	    		</tr>
	    		
	    		<tr>
	    			<td align="right">用户角色：</td>
	    			<td>
	    				<input id="roleSelect" class="easyui-combobox" 
							name="language"
							data-options="
								url:'role/getUserRole',
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
	    			<td align="right">用户状态：</td>
	    			<td>
	    				<input id="state" type="radio" name="state" value="1" checked="checked">正常
	    				<input id="state1" type="radio" name="state" value="0" >冻结（<font color="red">冻结后用户不能登录</font>）
	    			</td>
	    		</tr>
	    	</table>
    </div>
</body>
</html>