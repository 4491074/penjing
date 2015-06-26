<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@page import="java.util.List"%>
      <%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	.reply{list-style-type:none;}
    .reply .replyLi{padding: 10px 10px;height: 100%;}
    .reply .replyLi:after{content:".";display:block;clear:both; height:0;font-size:0;line-height:0;}
    .reply .top{background-color: #FAFAFA;padding: 5px 10px;}
    .reply .top .replyTop{float: right;}
    .reply .hr{margin: 0px 0px;}
    .reply .replyContent{float: right;width: 93%;}
    .reply .replyContent .floor{float: right;}
    .reply .replyContent .replyText{word-wrap: break-word;word-break: normal;}
    .reply .replyPhoto{width: 6%;}
    .reply .buttom{text-align: center;}

</style>
</head>
<body>
<ul class="reply">
			<li class="top">
				<div class="replyTop"><a href="JavaScript:textAreaFock()">直接回复</a></div>
				${replyNu }&nbsp;回复&nbsp;|&nbsp;最后回复时间&nbsp;${lastTime }
			</li>
			<hr class="hr">
			<c:forEach items="${replys }" var="reply">
			<li class="replyLi">
				<div class="replyContent">
					<div class="floor">#${reply.floor }-<a href="JavaScript:textAreaFock('${reply.userName }')">回复</a></div>
					<a href="../users?id=${reply.userId }" target="_top">${reply.userName }</a>&nbsp;&nbsp;${reply.timeString }
					<div class="replyText">
						${reply.content }
					</div>
				</div>
				 <a href="../users?id=${reply.userId }" target="_top">
				    <c:if test="${empty reply.photo}">
				 		<img class="replyPhoto" src="../img/photo.png" alt="头像">
				 	</c:if>
				    <c:if test="${not empty reply.photo}">
				    	<img class="replyPhoto" src="../${reply.photo }" alt="头像">
				 	</c:if>
				 </a>
			</li>
			<hr class="hr">
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
			<li class="buttom">
    					<nav>
  							<ul class="pagination" style="margin: 5px 0px;">
    							<%
    								for(String str:pages){
    									if(str.endsWith(the)){
    							%>
    								<li class="active"><a href="JavaScript:frameChange('../reply/getReply?topicId=${topicId }&replyPage=<%=str %>')"><%=str %></a></li>
    							<%			
    									}else if("...".endsWith(str)){
    							%>
    	    						<li class="disabled"><a href="JavaScript:frameChange('../reply/getReply?topicId=${topicId }&replyPage=<%=str %>')"><%=str %></a></li>
    	    					<%	
    									}else {
    							%>
    								<li><a href="JavaScript:frameChange('../reply/getReply?topicId=${topicId }&replyPage=<%=str %>')"><%=str %></a></li>
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
			<hr class="hr">
			</ul>
			<!-- jQuery -->
    <script src="../js/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/reply.js"></script>
</body>
</html>