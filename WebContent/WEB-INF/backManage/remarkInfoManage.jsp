<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>备注信息管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/remarkInfo.js"></script>
</head>
<body style="margin: 5px;">
<table id="dg" title="备注信息管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
	url="remark/getRemarkInfo" toolbar="#tools" fit="true" pageSize="20" >
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th field="id" width="10">备注编号</th>
			<th field="managerName" width="15">管理员姓名</th>
			<th field="managerTel" width="15">管理员电话</th>
			<th field="address" width="30">地址</th>
			<th field="visitNum" width="10">网站访问量</th>
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
	
	 <div class="easyui-dialog" id="dialog" buttons="#bt" style="width:30%;height:50%;padding:10px" data-options="modal:true"
    	closed="true">
    	<form action="#" id="reinfo">
    			<table>
	    		<tr>
		    		<td>管理员姓名：&nbsp;</td>
		   			<td><input id="managerName" type="text" name="managerName" maxlength="10" class="easyui-validatebox" required="true"></td>
		   		</tr>
		   		
	    		<tr>
	    			<td>管理员电话：&nbsp;</td>
	    			<td><input id="managerTel" type="text" name="managerTel" maxlength="11" class="easyui-validatebox" required="true"></td>
	    		</tr>
	    	
	    		<tr>
		    		<td>地址：&nbsp;</td>
		   			<td><input id="address" type="text" name="address" maxlength="20" class="easyui-validatebox" required="true"></td>
		   		</tr>
	    	</table>
	    </form>
    </div>



</body>
</html>