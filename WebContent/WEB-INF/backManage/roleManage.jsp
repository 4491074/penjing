<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/roleManage.js"></script>
</head>
<body style="margin: 5px;">
<table id="dg" title="角色管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
	url="role/getAllRole" toolbar="#tools" fit="true" pageSize="20">
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th field="roleId" width="100">角色编号</th>
			<th field="roleName" width="100">角色名称</th>
			<th field="roleDescription" width="200">角色描述</th>
			<th field="publishPenJing" width="100">发布盆景</th>
			<th field="auditPenJing" width="100">审批盆景</th>
			<th field="managePenJing" width="100">管理盆景</th>
			<th field="manageOrder" width="100">订单管理</th>
			<th field="publishNews" width="100">发表新闻</th>
			<th field="auditNews" width="100">审批新闻</th>
			<th field="manageNews" width="100">管理新闻</th>
			<th field="creatTopic" width="100">论坛发帖</th>
			<th field="manageForum" width="100">论坛管理</th>
			<th field="manageRole" width="100">角色管理</th>
			<th field="manageUser" width="100">用户管理</th>
			<th field="manageInfo" width="150">页面信息管理</th>
			<th field="roleStatus" width="100">角色状态</th>
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
	
    <div class="easyui-dialog" id="dialog" buttons="#bt" style="width:35%;height:90%;padding:10px" data-options="modal:true"
    	closed="true">
    	<form action="#" id="roleForm">
	    	<table>
		    	<tr>
		    		<td style="width: 80px;" align="right">角色名称：&nbsp;</td>
		   			<td><input id="roleName" type="text" style="width: 100%;" maxlength="10" class="easyui-validatebox" required="true"></td>
		   		</tr>
	    		<tr>
	    			<td valign="top" align="right">角色描述：&nbsp;</td>
	    			<td>
	    				<textarea id="roleDescription" style="width: 100%;font-size: 12px;" rows="3" class="easyui-validatebox" data-options="validType:'length[0,100]'"></textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td valign="top" align="right">角色状态：&nbsp;</td>
	    			<td>
	    				<input id="state" type="radio" name="state" value="1" checked="checked">正常（该状态下用户正常使用其权限）<br>
	    				<input id="state1" type="radio" name="state" value="0" >冻结（<font color="red">冻结后该角色下的用户将变为游客状态，恢复正常后用户角色也恢复正常</font>）
	    			</td>
	    		</tr>
	    		<tr>
	    			<td valign="top" align="right">权限：&nbsp;</td>
	    			<td>
	    				<ul id="tt" class="easyui-tree" data-options="url:'json/roleManage.json',method:'get',animate:true,checkbox:true,onlyLeafCheck:true"></ul>
	    			</td>
	    		</tr>
	    	</table>
	    	</form>
    </div>
</body>
</html>