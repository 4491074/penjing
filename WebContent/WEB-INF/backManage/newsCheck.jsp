<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻审核</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/newsManage.js"></script>
</head>
<body style="margin: 5px;">
<table id="dg" title="新闻审核" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
	url="news/gainUnCheckNews" toolbar="#tools" fit="true" pageSize="20" >
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th field="newsId" width="10">新闻编号</th>
			<th field="newsTitle" width="20">新闻标题</th>
			<th field="newsContent" width="30">新闻内容</th>
			<th field="readNu" width="10">点击量</th>
			<th field="newsPublisher" width="10">新闻发布者</th>
			<th field="newsAssessor" width="10">新闻审批者</th>
			<th field="publishTime" width="10">发布时间</th>
			<th field="newsBoard" width="10">新闻所属板块</th>
			<th field="newsStatus" width="10">状态</th>
			<th field="remark" width="10">备注</th>
		</tr>
	</thead>
</table>

<div id="tools">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="checkNews()">审核</a>
			<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="lookfor()">查看</a>
		</div>
</div>



<div id="bt">
    	<a class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" onclick="closedialog()">关闭</a>
    </div>

 <div class="easyui-dialog" id="dialog" buttons="#bt" style="width:60%;height:100%;padding:10px" data-options="modal:true"
    	closed="true">
    	<form action="#" id="newsform">
	    	<table>
	    		<tr>
		    		<td align="right" width="70px">新闻标题：&nbsp;</td>
		   			<td id="newsTitle"></td>
		   		</tr>
		   		
		   		<tr>
		    		<td align="right">新闻类别：&nbsp;</td>
		   			<td id="newsBoard"></td>
		   		</tr>
		   		
	    		<tr>
	    			<td align="right" valign="top">新闻内容：&nbsp;</td>
	    			<td id="newsContent"></td>
	    		</tr>
	    		
	    		<tr>
	    			<td align="right" valign="top">备注：&nbsp;</td>
	    			<td id="remark"></td>
	    		</tr>
	    	</table>
	    </form>
 </div>

 
 <div class="easyui-dialog" id="dialog_1" buttons="#bt1" style="width:30%;height:40%;padding:25px" data-options="modal:true"
    	closed="true">
    	<form action="#" id="newsform1">
    		<table>
	    		<tr>
		    		<td>新闻状态：&nbsp;</td>
		   			<td>
		   				<input id="state" type="radio" name="state" value="1" checked="checked">审核通过
		   			</td>
		   		</tr>
		   		<tr>
		    		<td></td>
		   			<td>
	    				<input id="state1" type="radio" name="state" value="0" >审核不通过
		   			</td>
		   		</tr>
		 	</table>
	    </form>
 </div>
 <div id="bt1">
    	<a class="easyui-linkbutton" id="ok" iconCls="icon-save" onclick="confirm()">确认</a>
    	<a class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" onclick="cancel()">取消</a>
  </div>

</body>
</html>