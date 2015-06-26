<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.penjing.entity.User"%>
    <%@page import="com.penjing.entity.Forum"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
     <!-- jQuery -->
    <script src="../js/jquery-1.11.2.min.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    *{margin: 0px;padding: 0px;}
    body {background-color: #FAFAFA;}
    .con {width: 80%;margin: 0px auto;}
    .con .left{width: 69%;height: 100%;}
    .con .right{width: 23%;height: 100%;position: absolute;right: 10%}
    .bo {height: 80px;background-color: #404040; margin-top: 100px;width: 100%;position: absolute;}
    .bo li {list-style: none;top: 30px;font-size: 12px;font-family: 微软雅黑;left: 10%;position: relative;width: 100px;}
    .panel-body:after {clear: both;}
    #topicButton {width: 100px;}
    .codeText {float: right;margin-right: 0px;margin-left: 10px;margin-top: 8px;}
    .code {float: right;margin-right: 0px;margin-left: 10px;margin-top: 2px;}
    .loginButtom {width: 100%;height: 100px;background-color: #EBEBEB;text-align: center;padding-top: 10px;}
    </style>
</head>
<body>
		<div class="con">
		<div class="right">
			<div class="panel panel-default">
			<%
				if(session.getAttribute("user")==null){
			%>
  				<div class="panel-body">
   					<button type="button" class="btn btn-default" onclick="toEnroll()">注册</button>
   					已有账号<a href="#" onclick="window.parent.loginPane()">登录</a>
 			    </div>
  				<div class="panel-footer">你还没有<a href="#" onclick="window.parent.loginPane()">登录</a></div>
			<%
				}else {
				    		User user = (User)session.getAttribute("user");
				    		String photo = user.getPhoto();
				    		if(photo == null){
				    			photo = "img/photo.png";
				    		}
			%>
  				<div class="panel-body">
   					<div class="media">
  						<a class="media-left" href="../users?id=<%=user.getUserId() %>" target="_parent">
  							<img src="../<%=photo %>" alt="头像" style="width: 80px;">
  						</a>
  						<div class="media-body">
   							 <a href="../users?id=<%=user.getUserId() %>" target="_parent">
   							 	<h4 class="media-heading"><%=user.getUserName()%></h4>
   							 </a>
    							会员号：<%=user.getUserId()%><br>
  						</div>
					</div>
 			    </div>
  				<div class="panel-footer"><a href="#">我的消息</a><span class="badge">0</span></div>
  				<%
  					}
  				%>
			</div>
			
		</div>
		<div class="left">
			<div id="loading" style="text-align: center;">
				<p>正在加载...</p>
			</div>
			<iframe class="embed-responsive-item" id="frame" frameborder="0" scrolling="no" style="width: 100%;height: 100%;" src=""></iframe>
			<form action="#" method="post" id="fastTopicForum" style="display: none;">
				<div class="panel panel-primary" style="margin-top: 30px;">
				    <div class="panel-heading">
				    	快速发帖：
				    </div>
				    <% 
				    	if(session.getAttribute("user")==null){
				   	%>
				   	<div class="panel-body">
						<div class="loginButtom">
							<h4>你还没有<a href="#" onclick="window.parent.loginPane()"><font color="#000">登录</font></a>，不能发帖！</h4>
							<button type="button" class="btn btn-default" onclick="toEnroll()">注册</button>or
							<button type="button" class="btn btn-default" onclick="window.parent.loginPane()">登录</button>
						</div>
	  				</div>
				   	<%
				    	}else if(!(Boolean)session.getAttribute("topic")){
				    %>
					<div class="panel-body">
						<div class="loginButtom">
							你没有权限进行发帖
						</div>
			  		</div>
						   	<%		
				    	}else{
				    %>
				    <div class="panel-body">
				   		<div id="titleMsg" style="color: red;"></div>
						<div class="input-group">
							  <span class="input-group-addon" id="basic-addon1">标题</span>
							  <input type="text" name="topic.title" id="topicTitle" maxlength="30" class="form-control" placeholder="请输入30字以内标题" aria-describedby="basic-addon1">
						</div>
	  				</div>
				    <ul class="list-group">
	    					<li class="list-group-item" id="textArea" >
	    					<div id="contentMsg" style="color: red;"></div>
	    				<script id="container" name="topic.content" type="text/plain">
    					</script>
	    				</li>
	    				<li class="list-group-item">
	    					<input type="button" class="btn btn-primary" id="topicButton" onclick="checkTopic()" value="提交">
	    				</li>
	  				</ul>
	  				<!-- 配置文件 -->
				    <script type="text/javascript" src="../ueditor.config.js"></script>
				    <!-- 编辑器源码文件 -->
				    <script type="text/javascript" src="../ueditor.all.js"></script>
				    <!-- 实例化编辑器 -->
				    <script type="text/javascript">
				        var ue = UE.getEditor('container');
				        var t = null;
				        ue.addListener("focus blur ready",function(type,event){
				        	if (type == "focus") {
				        		t = self.setInterval("clock()",10);
							}if (type == "ready") {
								clock();
							}if (type == "blur") {
								window.clearInterval(t);
							}
				        	})
				    </script>
	  				<%
				    	}
	  				%>
				</div>
			</form>
		</div>
	</div>
	<!-- 中间容器结束 -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/forum.js"></script>
</body>
</html>