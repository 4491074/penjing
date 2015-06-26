<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主题帖管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/topicManage.js"></script>
</head>
<body style="margin: 5px;">
<table id="dg" title="主题帖管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
	url="topic/getAllTopic" toolbar="#tools" fit="true" pageSize="20">
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th field="topicId" width="40">帖子编号</th>
			<th field="userName" width="100">楼主</th>
			<th field="title" width="200">标题</th>
			<th field="replyNu" width="40" data-options="formatter:go">回帖数</th>
			<th field="time" width="100">创建时间</th>
			<th field="readNu" width="40">阅读量</th>
			<th field="isTop" width="40">是否置顶</th>
			<th field="topTime" width="100">置顶结束时间</th>
			<th field="topicState" width="40">帖子状态</th>
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
    	<a class="easyui-linkbutton" id="ok" iconCls="icon-save" onclick="edit()">保存</a>
    	<a class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" onclick="closedialog()">关闭</a>
    </div>
	
    <div class="easyui-dialog" id="dialog" buttons="#bt" style="width:35%;height:50%;padding:10px" data-options="modal:true"
    	closed="true">
    	<form action="#" id="topicForm">
	    	<table>
		    	<tr style="margin-bottom: 5px;">
		    		<td style="width: 90px;" align="right">主题帖编号：&nbsp;</td>
		   			<td id="topicId" name="topic.topicId"></td>
		   		</tr>
	    		<tr style="margin-bottom: 5px;">
	    			<td valign="top" align="right">标题：&nbsp;</td>
	    			<td id="title"></td>
	    		</tr>
	    		<tr style="margin-bottom: 5px;">
	    			<td valign="top" align="right">是否置顶：&nbsp;</td>
	    			<td>
	    				<select id="isTop" name="topic.isTop" style="width:200px;">
	    					<option value="1">是</option>
        					<option value="0">否</option>
        				</select>
	    			</td>
	    		</tr>
	    		<tr style="margin-bottom: 5px;height: 0px;" id="topTimeTr">
	    			<td valign="top" align="right">置顶结束时间：&nbsp;</td>
	    			<td>
	    				<input class="easyui-datetimebox" name="topic.topTime" id="topTime" value="" style="width:70%;">
	    			</td>
	    		</tr>
	    		<tr style="margin-bottom: 5px;">
	    			<td valign="top" align="right">帖子状态：&nbsp;</td>
	    			<td>
	    				<input id="state" type="radio" name="topic.topicState" value="1" checked="checked">正常
	    				<input id="state1" type="radio" name="topic.topicState" value="0" >冻结（<font color="red">冻结后主题帖将不可见</font>）
	    			</td>
	    		</tr>
	    	</table>
	    	</form>
    </div>
</body>
</html>