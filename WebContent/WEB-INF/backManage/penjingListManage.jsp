<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/penjingListManage.js"></script>
</head>
<body style="margin: 5px;">
<table id="dg" title="订单详情管理" target="${orderId }" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
	url="${page }" toolbar="#tools" fit="true" pageSize="20" singleSelect="true">
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th field="penJingId" width="10">盆景编号</th>
			<th field="penJingName" width="10">盆景名称</th>
			<th field="penJingTitle" width="10">盆景标题</th>
			<th field="publisherName" width="10">发布者</th>
			<th field="penJingTypeName" width="10">盆景类型</th>
			<th field="mainPicture" width="10" data-options="formatter:go">首页图片</th>
			<th field="penJingStatus" width="10">盆景状态</th>
			<th field="count" width="10">订货数量</th>
			<th field="listNote" width="10">备注</th>
		</tr>
	</thead>
</table>

	<div id="tools">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doremove()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openeditdialog()">修改</a>
			<input class="easyui-textbox" data-options="buttonText:'添加',buttonIcon:'icon-search',prompt:'请输入盆景编号以添加'" style="width:250px;height:22px;">
		</div>
	</div>
	
	<div id="bt">
    	<a class="easyui-linkbutton" id="ok" iconCls="icon-save" onclick="editdialog()">保存</a>
    	<a class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" onclick="closedialog()">关闭</a>
    </div>
	
	<div class="easyui-dialog" title="查找盆景" id="penjing" buttons="#bt" style="width:30%;height:85%;" modal="true"
    	closed="true">
    	<form action="#" id="addList">
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
	    			<td valign="top" align="right">首页图片：</td>
	    			<td>
	    				<img alt="盆景首页图片" id="mainPicture" src="" style="width: 100px;height: 100px;">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td align="right">盆景状态：</td>
	    			<td id="penJingStatus"></td>
	    		</tr>
	    		<tr>
		    		<td align="right">备注信息：</td>
		   			<td>
		   				<textarea id="listNote" style="width: 100%;font-size: 12px;" rows="3" class="easyui-validatebox" data-options="validType:'length[0,100]'"></textarea>
		   			</td>
		   		</tr>
	    		<tr>
		    		<td align="right">盆景数量：</td>
		   			<td><input id="count" type="text" style="width: 100%;" maxlength="5"></td>
		   		</tr>
		   		<tr>
		    		<td></td>
		   			<td>
		   				<input type="button" value="提交" onclick="subPenjing()">
		   				<input type="button" value="取消" onclick="closePenjing()">
		   			</td>
		   		</tr>
	    	</table>
	    	</form>
    </div>
	
    <div class="easyui-dialog" id="dialog" buttons="#bt" style="width:35%;height:80%;padding:10px" data-options="modal:true"
    	closed="true">
    	<form action="#" id="penjingListForm">
	    	<table>
		    	<tr>
		    		<td align="right" style="width: 80px;">盆景编号：</td>
		   			<td id="editPenJingId"></td>
		   		</tr>
	    		<tr>
	    			<td align="right">盆景名称：</td>
	    			<td id="editPenJingName"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">盆景标题：</td>
	    			<td id="editPenJingTitle"></td>
	    		</tr>
	    		<tr>
		    		<td valign="top" align="right">备注信息：</td>
		   			<td>
		   				<textarea id="editListNote" style="width: 100%;font-size: 12px;" rows="3" class="easyui-validatebox" data-options="validType:'length[0,100]'"></textarea>
		   			</td>
		   		</tr>
	    		<tr>
		    		<td align="right">盆景数量：</td>
		   			<td><input id="editCount" type="text" style="width: 100%;" maxlength="5"></td>
		   		</tr>
	    	</table>
	    	</form>
    </div>
    
    <div class="easyui-dialog" id="mPicture" style="width:300px;height:320px;" modal="true"
    	closed="true" title="首页图片">
	    	<div style="width: 100%;height: 100%;">
	    		<img alt="盆景首页图片" id="mainPic" style="width: 100%;height: 100%" src="">
	    	</div>
    </div>
</body>
</html>