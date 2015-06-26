<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.penjing.entity.User"%>
     <%@page import="java.util.List"%>
      <%@page import="java.util.ArrayList"%>
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
    body {background-color: #FAFAFA;font-size: 12px;font-family: 微软雅黑;}
    .panel-body:after {clear: both;}
    .topicPhoto {width: 40px;}
    .topicBadge {right: 10px;position: absolute;}
    .topicNu {float: right;}
    .pag {text-align: center;}
    .listColor {background-color: #FAFAFA;}
    </style>
</head>
<body>
		<div class="left">
			<div class="panel panel-primary">
			    <div class="panel-heading">
			    	<div class="topicNu">话题<span class="badge">${forum.topicNu }</span></div>
			    	<div id="forumTitle">${forum.forumName }</div>
			    </div>
			    <div class="panel-body">
    				<p>通知：${forum.notice }</p>
  				</div>
			    <ul class="list-group">
			    	<c:forEach items="${ts }" var="topic" varStatus="i">
			    	<li class="list-group-item
			    	<c:if test="${(i.index)%2==0 }">
    				 	listColor
    				</c:if>
    				">
    					 <div class="media">
  							<div class="media-left">
    							<a href="../users?id=${topic.userId }" target="_top">
      								
      								<c:if test="${empty topic.photo}">
				 						<img class="media-object topicPhoto" src="../img/photo.png" alt="头像">
								 	</c:if>
								    <c:if test="${not empty topic.photo}">
								    	<img class="media-object topicPhoto" src="../${topic.photo }" alt="头像">
								 	</c:if>
    							</a>
  							</div>
  							<div class="media-body">
   			 					<a href="../topics?id=${topic.topicId }" target="_top">
   			 						<h4 class="media-heading">
   			 							<font color="#000">${topic.title }</font>
   			 							<c:if test="${topic.isTop==1 }">
					    				 	<span class="label label-default" style="font-size: 10px;">置顶</span>
					    				</c:if>
   			 						</h4>
   			 						<span class="badge topicBadge">${topic.replyNu }</span>
   			 					</a>
   			 					<a href="../users?id=${topic.userId }" target="_top">${topic.userName }</a>&nbsp;•&nbsp;
   			 					<font color="#666">${topic.lastTimeString }</font>&nbsp;•&nbsp;<font color="#666">最后回复来自&nbsp;</font>
   			 					<c:if test="${topic.lastReplyUser.userId==0 }">
	    					 		--无回复--
	    						</c:if>
	    						
	    						<c:if test="${topic.lastReplyUser.userId!=0 }">
	    					 		<a href="../users?id=${topic.lastReplyUser.userId }" target="_top" id="lastReplyUser">${topic.lastReplyUser.userName }</a>
	    						</c:if>
  							</div>
						</div>
    				</li>
    				</c:forEach>
			    
    				<%
    						@SuppressWarnings("unchecked")
    						List<String> pages = (ArrayList<String>)request.getAttribute("pages");
    						String the = (String)request.getAttribute("page");
    						if(the == null){
    							the = 1+"";
    						}
    						if(pages!=null&&pages.size()>1){
    						%>
    				<li class="list-group-item pag">
    					<nav>
  							<ul class="pagination" style="margin: 0px 0px;">
    							<%
    								for(String str:pages){
    									if(str.endsWith(the)){
    							%>
    								<li class="active"><a href="../forums?page=<%=str %>" target="_top"><%=str %></a></li>
    							<%			
    									}else if("...".endsWith(str)){
    							%>
    	    						<li class="disabled"><a href="../forums?page=<%=str %>" target="_top"><%=str %></a></li>
    	    					<%	
    									}else {
    							%>
    								<li><a href="../forums?page=<%=str %>" target="_top"><%=str %></a></li>
    							<%
    									}
    								}
    							%>
							</ul>
						</nav>
    				</li>
    				<%
    								}
    				%>
  				</ul>
			</div>
		</div>
	<!-- 中间容器结束 -->
    <!-- jQuery -->
    <script src="../js/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/forumTopics.js"></script>
</body>
</html>