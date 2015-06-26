<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻版块管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/newsBdManage.js"></script>
</head>
<body style="margin: 5px;">
<table id="dg" title="新闻版块管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
	url="getAllNewsBd" toolbar="#tools" fit="true" pageSize="20" data-options="
	singleSelect: false,
				iconCls: 'icon-save',
				method: 'get',
				rowStyler: function(index,row){
					if (row.newsBoardparent=='无'){
						return 'background-color:#6293BB;color:#fff;font-weight:bold;';
					}
				}
	">
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th field="newsBoardId" width="10">新闻版块编号</th>
			<th field="newsBoardName" width="20">新闻版块名称</th>
			<th field="newsBoardparent" width="20">新闻版块父类</th>
			<th field="newsBoardStatus" width="10">新闻版块状态</th>
			<th field="newsBoardDescription" width="30">新闻版块描述</th>
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
	
	 <div class="easyui-dialog" id="dialog" buttons="#bt" style="width:40%;height:70%;padding:10px" data-options="modal:true"
    	closed="true">
    	<form action="#" id="newsbdform">
	    	<table>
	    		<tr>
		    		<td>新闻版块名称：&nbsp;</td>
		   			<td><input id="newsBoardName" type="text"  name="newsBoardName" maxlength="10" class="easyui-validatebox" required="true"></td>
		   		</tr>
		   		
		   		<tr>
		    		<td>新闻版块父类：&nbsp;</td>
		   			<td>
		   			<input id="newsBoardparent" class="easyui-combobox" 
							name="language" required="true" 
							data-options="
								url:'newsBd/pnewsBdName',
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
	    			<td valign="top">新闻版块描述：&nbsp;</td>
	    			<td>
	    				<textarea id="newsBoardDescription" style="width: 150%;font-size: 12px;" name="newsBoardDescription" rows="5"></textarea>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<td>新闻版块状态：&nbsp;</td>
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