<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的新闻</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/myNews.js"></script>
</head>
<body style="margin: 5px;">
<table id="dg" title="我的新闻" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
	url="news/getMyNews" toolbar="#tools" fit="true" pageSize="20" >
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
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="opensavedialog()">添加</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doremove()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openeditdialog()">修改</a>
		</div>
</div>

<div id="bt">
    	<a class="easyui-linkbutton" id="ok" iconCls="icon-save" onclick="savedialog()">保存</a>
    	<a class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" onclick="closedialog()">关闭</a>
    </div>
	
	 <div class="easyui-dialog" id="dialog" buttons="#bt" style="width:50%;height:80%;padding:10px" data-options="modal:true"
    	closed="true">
    	<form action="#" id="newsform">
    			<table>
	    		<tr>
		    		<td width="70px">新闻标题：&nbsp;</td>
		   			<td><input id="newsTitle" type="text" name="newsBoardName" maxlength="10" class="easyui-validatebox" required="true"></td>
		   		</tr>
		   		
		   		<tr>
		    		<td>新闻类别：&nbsp;</td>
		   			<td>
		   			<input id="newsBoard" class="easyui-combobox" 
							name="language" required="true"
							data-options="
								url:'newsBd/snewsBdName',
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
	    			<td colspan="2">
	    				<script id="container" name="content" type="text/plain"></script>
	  					<!-- 配置文件 -->
						<script type="text/javascript" src="ueditor.config.js"></script>
						<!-- 编辑器源码文件 -->
						<script type="text/javascript" src="ueditor.all.js"></script>
				   		 <!-- 实例化编辑器 -->
						<script type="text/javascript">
				     		  var ue = UE.getEditor('container');
						</script>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<td valign="top" align="right">备注：&nbsp;</td>
	    			<td>
	    				<textarea id="remark" style="width: 80%;font-size: 12px;" name="newsBoardDescription" rows="2" ></textarea>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
    </div>

</body>

</html>