<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.penjing.entity.User"%>
    <%@page import="com.penjing.entity.Forum"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
    .con .left{width: 69%;height: 100%;background-color: #fff;}
    .con .right{width: 23%;height: 100%;position: absolute;right: 10%}
    .con .right #rightPhoto {width: 80px;}
    .panel-body:after {clear: both;}
    .con .left .userInfo .info{float: right;width: 80%;}
    .con .left .userInfo .photo{width: 100px;}
    .con .left .userInfo:after {content:".";display:block;clear:both; height:0;font-size:0;line-height:0;}
    .con .left .topics{background-color: #FAFAFA;padding-top: 20px;}
    .con .left .replys{background-color: #FAFAFA;padding-top: 20px;}
    .con .left .replys .replyInfo{float: right;width: 98%;}
    .con .left .replys .list-group-item:after {content:".";display:block;clear:both; height:0;font-size:0;line-height:0;}
    .con .left .replys .replyColor{background-color: #666;height: 46px;width: 4px;}
    .con .left .listColor{background-color: #FAFAFA}
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
			<div class="userInfo">
				<div class="info">
					<div id="userName" style="font-size: 20px;"><b>${user.userName }</b></div>
					<ul style="font-size: 12px;font-family: 微软雅黑;">
						<li>会员ID：${user.userId }</li>
						<li>加入时间：${user.enrollTimeString }</li>
						<li>上一次登录：${user.lastTimeString }</li>
					</ul>
				</div>
				<div class="media" style="margin-top: 0px;margin-left: 10px;">
					<c:if test="${empty user.photo}">
				 		<img class="photo" src="../img/photo.png" alt="头像">
				 	</c:if>
				    <c:if test="${not empty user.photo}">
				 		<img class="photo" src="../${user.photo }" alt="头像">
				 	</c:if>
				</div>
			</div>
			<hr style="margin: 5px 0px 10px 0px;">
			<div class="topics">
				<div class="panel panel-default" style="margin-bottom: 0px;">
				<span class="badge" id="topicNu" style="float: right;margin: 11px 15px 0px 0px;">${user.topicNum }</span>
				  <!-- Default panel contents -->
				  <div class="panel-heading">${user.userName }&nbsp;创建的主题</div>
				  <!-- List group -->
				  <ul class="list-group" id="userTopics">
				  <c:forEach items="${ts }" var="topic" varStatus="i">
				    <li class="list-group-item 
					    <c:if test="${(i.index)%2==1 }">
	    					 listColor
	    				</c:if>
				    ">
    					 <div class="media">
  							<div>
  								<span class="badge topicBadge" style="float: right;">${topic.replyNu }</span>
   			 					<a href="../topics?id=${topic.topicId }" target="_parent">
   			 						<h4>
   			 							<font color="#000">${topic.title }</font>
   			 						</h4>
   			 					</a>
   			 					<font color="#666">${topic.lastTimeString }</font>&nbsp;•&nbsp;<font color="#666">最后回复来自&nbsp;</font>
   			 					<c:if test="${topic.lastReplyUser.userId==0 }">
	    					 		--无回复--
	    						</c:if>
	    						
	    						<c:if test="${topic.lastReplyUser.userId!=0 }">
	    					 		<a href="../users?id=${topic.lastReplyUser.userId }" target="_parent" id="lastReplyUser">${topic.lastReplyUser.userName }</a>
	    						</c:if>
   			 					
  							</div>
						</div>
						
    				</li>
    				</c:forEach>
    				<li class="list-group-item" id="moreTopic-Li" style="padding: 5px 15px; text-align: center;">
    					<a href="JavaScript:get('topic',${user.userId })" id="moreTopic">查看更多</a>
    				</li>
				  </ul>
				</div>
			</div>
			
			<div class="replys">
				<div class="panel panel-default" style="margin-bottom: 0px;">
				  <!-- Default panel contents -->
				  <span class="badge" id="replyNu" style="float: right;margin: 11px 15px 0px 0px;">${user.repliesNum }</span>
				  <div class="panel-heading">${user.userName }&nbsp;最近回复了</div>
				  <!-- List group -->
				  <ul class="list-group" id="userReplys">
				  <c:forEach items="${replys }" var="reply" varStatus="i">
				    <li class="list-group-item 
				    	<c:if test="${(i.index)%2==1 }">
	    					 listColor
	    				</c:if>
				    ">
    					 <div class="media">
  							<div>
   			 					<h4>
   			 						回复了<a href="../users?id=${reply.userId }" target="_parent">${reply.userName }</a>创建的主题<a href="../topics?id=${reply.topicId }" target="_parent">${reply.topicTitle }</a>
   			 					</h4>
   			 					<div class="replyInfo">
	   			 					<div style="margin-bottom: 5px;">内容：${reply.content }</div>
	   			 					<div>时间：${reply.timeString }</div>
   			 					</div>
   			 					<div class="replyColor"></div>
  							</div>
						</div>
    				</li>
    				</c:forEach>
    				<li class="list-group-item" id="moreReply-Li" style="padding: 5px 15px; text-align: center;">
    					<a href="JavaScript:get('reply',${user.userId })" id="moreReply">查看更多</a>
    				</li>
				  </ul>
				</div>
			</div>
		</div>
	</div>
	<!-- 中间容器结束 -->
    <!-- jQuery -->
    <script src="../js/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/userInfo.js"></script>
</body>
</html>