<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>盆景管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/penjingManage.js"></script>
<style type="text/css">
.p{width: 30%;margin: 0px 1.5% 10px 1.5%;float: left;margin-bottom: 10px;border-width: 1px;border-color: #333;border-style: solid;}
.editP{width: 100px; margin: 0px 3px 5px 3px;float: left;margin-bottom: 10px;border-width: 1px;border-color: #333;border-style: solid;}
.editPic{width: 100%;height: 100px;margin-bottom: 3px;}
.editPic img{width: 100%;height: 100%;}
</style>
</head>
<body style="margin: 5px;">
<%
	String url = (String)request.getAttribute("page");
	if(url == null || !url.startsWith("penjings/getAllPenjing")){
		url = "penjings/getAllPenjing";
	}
%>
<table id="dg" title="盆景管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
	url="<%=url %>" toolbar="#tools" fit="true" pageSize="10" singleSelect="true">
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th field="penJingId" width="10">盆景编号</th>
			<th field="penJingName" width="10">盆景名称</th>
			<th field="penJingTitle" width="10">盆景标题</th>
			<th field="publisherName" width="10">发布者</th>
			<th field="penJingTypeName" width="10">盆景类型</th>
			<th field="penJingDescription" width="10">盆景描述</th>
			<th field="publishTime" width="10">发布时间</th>
			<th field="pictures" width="10" data-options="formatter:go">盆景图片</th>
			<th field="penJingStatus" width="10">盆景状态</th>
			<th field="remark" width="10">备注</th>
		</tr>
	</thead>
</table>
	<div id="tools">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openeditdialog()">管理</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doremove()">删除</a>
		</div>
	</div>
	
	<div id="bt">
    	<a class="easyui-linkbutton" id="ok" iconCls="icon-save" onclick="savedialog()">保存</a>
    	<a class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" onclick="closedialog()">关闭</a>
    </div>
    
    <div class="easyui-dialog" id="pictures" title="盆景图片" style="width:95%;height:95%;" modal="true" closed="true">
	    	<hr>
    </div>
	
    <div class="easyui-dialog" title="审核" id="dialog" buttons="#bt" style="width:40%;height:95%;" modal="true"
    	closed="true">
	    	<table>
	    	
		    	<tr>
		    		<td align="right" style="width: 80px;">盆景编号：</td>
		   			<td id="penJingId"></td>
		   		</tr>
		   		
	    		<tr>
	    			<td align="right">盆景名称：</td>
	    			<td id="penJingName"></td>
	    		</tr>
	    		
	    		<tr>
	    			<td align="right">盆景标题：</td>
	    			<td id="penJingTitle"></td>
	    		</tr>
	    		
	    		<tr>
	    			<td align="right">盆景赏析：</td>
	    			<td id="penJingDescription"></td>
	    		</tr>
	    		
	    		<tr>
	    			<td align="right">盆景审核：</td>
	    			<td>
	    				<select class="editPenjing">
							 <option value ="1">通过审核</option>
							 <option value ="0">不通过审核</option>
							 <option value ="2">删除</option>
						</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td valign="top" align="right">图片审核：</td>
	    			<td>
	    				<hr id="hr">
	    			</td>
	    		</tr>
	    	</table>
    </div>
</body>
</html>