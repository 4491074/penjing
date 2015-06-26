<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/orderManage.js"></script>
</head>
<body style="margin: 5px;">
<table id="dg" title="订单管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
	url="order/getAllOrder" toolbar="#tools" fit="true" pageSize="10">
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th field="orderId" width="10">订单编号</th>
			<th field="customerName" width="10">用户姓名</th>
			<th field="customerPhone" width="10">用户电话</th>
			<th field="customerAdd" width="10">用户地址</th>
			<th field="distributionTime" width="10">货物配送时间</th>
			<th field="orderInfo" width="10" data-options="formatter:go">订单详情</th>
			<th field="time" width="10">订单生成时间</th>
			<th field="orderState" width="10">订单状态</th>
			<th field="note" width="10">备注</th>
		</tr>
	</thead>
</table>
	<div id="tools">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="opensavedialog()">添加</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openeditdialog()">修改</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doremove()">删除</a>
		</div>
	</div>
	
	<div id="bt">
    	<a class="easyui-linkbutton" id="ok" iconCls="icon-save" onclick="savedialog()">保存</a>
    	<a class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" onclick="closedialog()">关闭</a>
    </div>
	
	<div id="bt1">
    	<a class="easyui-linkbutton" id="ok1" iconCls="icon-save" onclick="savedialog1()">保存</a>
    	<a class="easyui-linkbutton" id="cancel1" iconCls="icon-cancel" onclick="closedialog1()">关闭</a>
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
    
    <div class="easyui-dialog" title="修改盆景" id="editPenjing" buttons="#bt" style="width:30%;height:65%;" modal="true"
    	closed="true">
    	<form action="#" id="editForm">
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
		    		<td align="right">备注信息：</td>
		   			<td>
		   				<textarea id="editListNote" style="width: 100%;font-size: 12px;" rows="3" class="easyui-validatebox" data-options="validType:'length[0,100]'"></textarea>
		   			</td>
		   		</tr>
	    		<tr>
		    		<td align="right">盆景数量：</td>
		   			<td><input id="editCount" type="text" style="width: 100%;" maxlength="5"></td>
		   		</tr>
		   		<tr>
		    		<td></td>
		   			<td>
		   				<input type="button" value="修改" onclick="editIt()">
		   				<input type="button" value="删除" onclick="removeIt()">
		   			</td>
		   		</tr>
	    	</table>
	    	</form>
    </div>
	
    <div class="easyui-dialog" id="dialog" buttons="#bt" style="width:50%;height:90%;" modal="true"
    	closed="true">
    	<form action="#" id="penjingForm">
	    	<table>
		    	<tr>
		    		<td align="right" style="width: 100px;">客户姓名：</td>
		   			<td><input id="customerName" type="text" style="width: 100%;" maxlength="10"></td>
		   		</tr>
		   		
	    		<tr>
	    			<td align="right">客户联系方式：</td>
		   			<td><input id="customerPhone" type="text" style="width: 100%;" maxlength="20"></td>
	    		</tr>
	    		
	    		<tr>
	    			<td valign="top" align="right">客户地址：</td>
	    			<td>
		   				<textarea id="customerAdd" style="width: 100%;font-size: 12px;" rows="3" class="easyui-validatebox" data-options="validType:'length[0,100]'"></textarea>
		   			</td>
	    		</tr>
	    		
	    		<tr>
	    			<td align="right">货物配送时间：</td>
	    			<td>
	    				<input class="easyui-datetimebox" id="distributionTime" value="" style="width:100%;">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td valign="top" align="right">订单详情：</td>
	    			<td>
	    				<div style="margin-bottom: 10px;">
	    					<table style="BORDER-COLLAPSE: collapse" borderColor=#000000 cellSpacing=0 width=300 align=center bgColor=#ffffff border=1>
	    						<tr>
	    							<td style="width: 30px;">编号</td>
	    							<td>盆景名称</td>
	    							<td>盆景标题</td>
	    							<td>备注信息</td>
	    							<td style="width: 30px;">数量</td>
	    							<td style="width: 30px;">修改</td>
	    						</tr>
	    						<tr id="lastTr">
	    						</tr>
	    					</table>
	    				</div>
	    				<div>
	    					<input type="button" value="查找" style="float: right;" onclick="penjingSearch()">
	    					<input type="text" id="penjingSearck"  placeholder="请输入盆景编号以添加">
	    				</div>
		   			</td>
	    		</tr>
	    		<tr>
	    			<td valign="top" align="right">备注：</td>
	    			<td>
		   				<textarea id="note" style="width: 100%;font-size: 12px;" rows="3" class="easyui-validatebox" data-options="validType:'length[0,100]'"></textarea>
		   			</td>
	    		</tr>
	    		<tr>
	    			<td align="right">订单状态：</td>
	    			<td>
	    				<select class="orderState">
							 <option value ="1">交易中</option>
							 <option value ="2">交易完成</option>
							 <option value ="3">交易取消</option>
							 <option value ="0">其他</option>
						</select>
	    			</td>
	    		</tr>
	    	</table>
	    	</form>
    </div>
    
    <div class="easyui-dialog" id="editDialog" buttons="#bt1" style="width:40%;height:90%;" modal="true"
    	closed="true">
    	<form action="#" id="editPenjingForm">
	    	<table>
		    	<tr>
		    		<td align="right" style="width: 100px;">客户姓名：</td>
		   			<td><input id="editCustomerName" type="text" style="width: 100%;" maxlength="10"></td>
		   		</tr>
	    		<tr>
	    			<td align="right">客户联系方式：</td>
		   			<td><input id="editCustomerPhone" type="text" style="width: 100%;" maxlength="20"></td>
	    		</tr>
	    		<tr>
	    			<td valign="top" align="right">客户地址：</td>
	    			<td>
		   				<textarea id="editCustomerAdd" style="width: 100%;font-size: 12px;" rows="3" class="easyui-validatebox" data-options="validType:'length[0,100]'"></textarea>
		   			</td>
	    		</tr>
	    		<tr>
	    			<td align="right">货物配送时间：</td>
	    			<td>
	    				<input class="easyui-datetimebox" id="editDistributionTime" value="" style="width:100%;">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td valign="top" align="right">备注：</td>
	    			<td>
		   				<textarea id="editNote" style="width: 100%;font-size: 12px;" rows="3" class="easyui-validatebox" data-options="validType:'length[0,100]'"></textarea>
		   			</td>
	    		</tr>
	    		<tr>
	    			<td align="right">订单状态：</td>
	    			<td>
	    				<select class="editOrderState">
							 <option value ="1">交易中</option>
							 <option value ="2">交易完成</option>
							 <option value ="3">交易取消</option>
							 <option value ="0">其他</option>
						</select>
	    			</td>
	    		</tr>
	    	</table>
	    	</form>
    </div>
</body>
</html>