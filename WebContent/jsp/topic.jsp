<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.penjing.entity.User"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script src="../UEditor/ueditor.parse.js"></script>
<script type="text/javascript">
	uParse("#content",{});
</script>
<title></title>
<!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
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
    .con .left{width: 69%;height: 100%;background-color: #fff;border-radius: 5px;-moz-border-radius:5px;}
    .con .left .title{margin-left: 10px;padding-top: 0.1px;}
    .con .left .photo{width: 75px;float: right;margin-right: 20px;margin-top: 10px;}
    .con .left #content{height: 100%;width: 100%;word-wrap: break-word;word-break: normal;padding: 0px 10px 20px 10px;}
    .con .left .replyPane{background-color: #FAFAFA;padding-top: 30px;}
    .con .left .replyPane .panel{margin-bottom: 0px;}
    .con .left .top{background-color: #FAFAFA;padding: 5px 10px;}
    .con .left .top .replyTop{float: right;}
    .con .left .replyPane #myReply{width: 100%;padding: 0px 5px;resize: none;margin-bottom: 10px;}
    .con .left .replyPane .replyButton{float: right;}
    .con .left .replyPane .replyMsg{float: right;width: 80%;padding-top: 5px;color: red;}
    .con .right{width: 23%;height: 100%;position: absolute;right: 10%}
    .con .right #rightPhoto {width: 80px;}
    .bo {height: 80px;background-color: #404040; margin-top: 100px;width: 100%;position: absolute;}
    .bo li {list-style: none;top: 30px;font-size: 12px;font-family: 微软雅黑;left: 10%;position: relative;width: 100px;}
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
  							<img src="../<%=photo %>" id="rightPhoto" alt="头像">
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
			<div class="media">
				 <a href="../users?id=${topic.userId }" target="_parent">
				 	<c:if test="${empty topic.photo}">
				 		<img class="photo" src="../img/photo.png" alt="头像">
				 	</c:if>
				    <c:if test="${not empty topic.photo}">
				 		<img class="photo" src="../${topic.photo }" alt="头像">
				 	</c:if>
				 </a>
			</div>
			<div class="title">
				<h3 id="topicTitle"><b>${topic.title }</b></h3>
				<font color="#666">By&nbsp;<a href="../users?id=${topic.userId }" target="_parent">${topic.userName }</a>&nbsp;•&nbsp;${topic.timeString }&nbsp;•&nbsp;${topic.readNu }次阅读&nbsp;•&nbsp;<a href="#">收藏</a></font>
			</div>
			<hr>
			<div id="content">
				${topic.content }
			</div>
			<hr style="margin: 8px 0px;">
			<iframe class="embed-responsive-item" id="frame" frameborder="0" scrolling="no" style="width: 100%;height: 100px;" src="../reply/getReply?topicId=${topic.topicId }&replyPage=1"></iframe>
			<div class="replyPane">
				<div class="panel panel-default">
				    <div class="panel-heading">回复</div>
				    <% 
			    	if(session.getAttribute("user")==null){
			   	%>
			   	<div class="panel-body">
					<div class="loginButtom">
						<h4>你还没有<a href="#" onclick="window.parent.loginPane()"><font color="#000">登录</font></a>，不能发表评论！</h4>
						<button type="button" class="btn btn-default" onclick="window.parent.toEnroll()">注册</button>or
						<button type="button" class="btn btn-default" onclick="window.parent.loginPane()">登录</button>
					</div>
  				</div>
			   	<%
			    	}else{
			    %>
				    <div class="panel-body">
				         <textarea id="myReply" name="replyContent" rows="5"></textarea>
				         <input class="btn btn-success replyButton" type="button" onclick="replyCheck()" value="回复">
				         <div class="replyMsg"></div>
				         <input class="btn btn-default" type="button" value="图片/附件">
				    </div>
				<%
			    	}
  				%>
				</div>
			</div>
			</div>
	</div>
	<!-- 中间容器结束 -->
    <!-- jQuery -->
    <script src="../js/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/topic.js"></script>
</body>
</html>