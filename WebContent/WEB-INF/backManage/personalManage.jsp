<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人资料</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript" src="js/personalManage.js"></script>
</head>
<body style="margin: 5px;">
<table id="dg" title="个人资料" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
	url="user/getMyInfo" toolbar="#tools" fit="true" pageSize="10" singleSelect="true">
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th field="userId" width="10">用户编号</th>
			<th field="userName" width="10">用户名</th>
			<th field="photo" width="10" data-options="formatter:go">头像</th>
			<th field="mail" width="10">邮箱</th>
			<th field="phone" width="10">电话号码</th>
			<th field="enrollTime" width="10">注册时间</th>
			<th field="userDescription" width="10">个人说明</th>
		</tr>
	</thead>
</table>
	<div id="tools">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openeditdialog()">修改资料</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openPhotodialog()">修改头像</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openPwddialog()">修改密码</a>
		</div>
	</div>
	
	<div id="bt">
    	<a class="easyui-linkbutton" id="ok" iconCls="icon-save" onclick="savedialog()">保存</a>
    	<a class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" onclick="closedialog()">关闭</a>
    </div>
    
    <div id="bt1">
    	<a class="easyui-linkbutton" id="cpb" onclick="openPhotodialog1()">更换头像</a>
    </div>
    
    <div id="bt2">
    	<a class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" onclick="closePhoto()">关闭</a>
    </div>
    
    
    <div id="bt3">
    	<a class="easyui-linkbutton" id="ok" iconCls="icon-save" onclick="savepwddialog()">保存</a>
    	<a class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" onclick="closedialog()">关闭</a>
    </div>
    
	
    <div class="easyui-dialog" id="dialog" buttons="#bt" style="width:30%;height:70%;" modal="true"
    	closed="true">
    	<form action="#" id="personalForm">
	    	<table>
		    	<tr>
		    		<td align="right">用户编号：&nbsp;</td>
		   			<td id="userId"></td>
		   		</tr>
		   		
	    		<tr>
	    			<td align="right">用户名：&nbsp;</td>
	    			<td id="userName"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">邮箱：&nbsp;</td>
	    			<td><input type="text" id="mail" name="user.mail" style="width: 100%;" maxlength="20" class="easyui-validatebox" required="true" validType="email"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">电话号码：&nbsp;</td>
	    			<td><input type="text" id="phone" name="user.phone" style="width: 100%;" maxlength="20" class="easyui-validatebox" required="true" validType="phone"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">个人说明：&nbsp;</td>
	    			<td>
	    				<textarea id="userDescription" name="user.userDescription" style="width: 100%;font-size: 12px;" rows="3" class="easyui-validatebox" data-options="validType:'length[0,100]'"></textarea>
	    			</td>
	    		</tr>
	    		
	    	</table>
	    </form>
    </div>
    
    <div class="easyui-dialog" buttons="#bt1" id="photo" style="width:160px;height:200px;" modal="true"
    	closed="true" title="头像">
	    	<div style="width: 100%;height: 100%;">
	    		<img alt="头像" style="width: 100%;height: 100%" src="img/photo.png">
	    	</div>
    </div>
    
    <div class="easyui-dialog" buttons="#bt2" id="photoEdit" style="width:30%;height:70%;" modal="true"
    	closed="true" title="修改头像">
    		<div style="width: 160px;height: 160px;">
	    		<img alt="头像" style="width: 100%;height: 100%" src="img/photo.png">
	    	</div>
	    	<div style="width: 100%;">
	    		请选择256KB以内的头像:<input type="file" id="image" name="photo" accept=".jpeg,.gif,.png,.jpg">
	    		<img alt="loading" src="img/loading.gif" style="display: none;" id="photoLoading"><br>
	    		<input type="submit" value="提交" onclick="submitPhoto()">
	    	</div>
    </div>
    
    
    <div class="easyui-dialog" id="Pwddialog" buttons="#bt3" style="width:25%;height:40%;" modal="true"
    	closed="true" title="修改密码">
    	<form action="" id="pwdform">
	    <table>
		    <tr>
		    	<td>原密码：</td>
		    	<td><input id="oldPwd" type="password" onchange="checkOldPwd()"/></td>
		    </tr>
		    <tr>
		    	<td>新密码：</td>
		    	<td><input id="newPwd" type="password" onchange="checkNewPwd()"/></td>
		    </tr>
		    <tr>
		    	<td>确认密码：</td>
		    	<td><input id="reNewPwd" type="password" onchange="checkRePwd()"/></td>
		    </tr>
		     <tr>
		    	<td colspan="2"><font color="red" id="errorMsg"></font></td>
		    </tr>
	    </table>
	    </form>
    </div>
    
</body>
</html>