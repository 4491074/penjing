<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{margin: 0px;padding: 0px;}
	body {background-color: #FAFAFA;}
	.con {width: 80%;margin-left: 10%;}
	.con .newsShow {width: 79%;float: right;}
	body:after {content:".";display:block;clear:both; height:0;font-size:0;line-height:0;}
	.con .newsType {width: 19%;background-color: #D1EEEE;}
	.con .news .newsType .type{color: white;font-weight: 700;padding: 5px 0px;text-align: center;background-color: #3CB371}
	.con .news .newsType li{list-style: none;margin-bottom: 1px;background-color: #00c896;}
	.con .news .newsType li p{color: white;font-weight: 700;padding: 8px 0px;text-align: center;
								  position: relative;z-index: 2;cursor:default;cursor: pointer;}
	.con .news .newsType #newsParent{background-color: #3CB371;}
	.con .news .newsType #newsParent p{cursor: text;}
	.con .news .newsType .typeSelect{width: 100%; background-color: #008264;position: absolute;z-index: 1;top: 0px;display: none;}
	.con .newsShow .nowType{color: white;font-weight: 700;padding: 5px 0px;background-color: #3CB371;padding-left: 10px;}
	.con .news .newsShow .newsList{width: 100%;background-color: #FAFAFA;}
	.con .news .newsShow .newsList li{list-style: none;font-size: 12px;font-family: 微软雅黑;color: #666;padding: 5px 5px;background-color: #FAFAFA;
									  border-bottom: 1px;border-bottom-style: solid;border-bottom-color: #DCDCDC;cursor: pointer;}
	.con .news .newsShow .page{width: 100%;margin-top: 5px;text-align: right;font-size: 12px;font-family: 微软雅黑;}
	.con .news .newsShow .page a{color:#000;}
	.con .news .newsShow .page a:HOVER{color:red;}
	.con .news .newsShow .page li{float: left;list-style: none;}
	.con .news .newsShow .theNews .newsTitle{float: right; text-align: center;color: white;font-weight: 700;padding: 5px 0px;
											 background-color: #3CB371;width: 92%; }
	.con .news .newsShow .theNews .newsContent{background-color: #DEFFDE;font-size: 12px;font-family: 微软雅黑;padding: 3px 10px;}
	.con .news .newsShow .theNews .newsContent .content{word-wrap: break-word;word-break: normal;}
	.con .news .newsShow .theNews .title:after {content:".";display:block;clear:both; height:0;font-size:0;line-height:0;}
	.con .news .newsShow .theNews .title .back{background-color: #67B389;padding: 5px 10px;width: 5%;color: white;font-weight: 700;
											   text-align: center;cursor: pointer;}
	.con .news .newsShow .theNews .newsContent .newsInfo{padding-bottom: 3px;}
</style>
</head>
<body>
	<div class="con">
		<div class="news">
		<!-- 盆景展示开始 -->
			<div class="newsShow">
				<div class="newsBoard">
					<div class="nowType">
						<p class="detailnowType">
							<c:out value="${newBdName}"></c:out>
						</p>
					</div>
					<input type="hidden" value="${newsBdId}" id="newsBdId"></input>
					<div style="height: 5px;background-color: #FAFAFA;"></div>
					<div class="newsList">
						<ul>
						<c:forEach var="fnews" items="${newsList}">
							<li onclick="seeNews(${fnews.newsId})">•<c:out value="${fnews.newsTitle}"></c:out></li>
						</c:forEach>
						</ul>
					</div>
					<div class="page">
						【<a target="_parent" href="news?newsPage=1&&newsBdId=${newsBdId}" style="text-decoration:none;">首页</a>】
						【<a target="_parent" href="news?newsPage=${pageNo-1}&&newsBdId=${newsBdId}" style="text-decoration:none;">上页</a>】
						第${pageNo}页/共<font id="lastPageNo">${lastPageNo}</font>页
						【<a target="_parent" href="news?newsPage=${pageNo+1}&&newsBdId=${newsBdId}" style="text-decoration:none;">下页</a>】
						【<a target="_parent" href="news?newsPage=${lastPageNo}&&newsBdId=${newsBdId}" style="text-decoration:none;">末页</a>】
						快速到第<input type="text" style="width: 30px;" onchange="fastJump(this)">页
					</div>
				</div>
				<div class="theNews" style="display: none;">
					<div class="title">
						<div class="newsTitle">
	
						</div>
						<div class="back" onclick="backNews()">返回</div>
					</div>
					<div style="height: 5px;background-color: #FAFAFA;"></div>
					<div class="newsContent">
						<div class="newsInfo"></div>
						<hr style="background-color: #F5F5F5;">
						<div class="content">
						</div>
					</div>
				</div>
			</div>
		<!-- 盆景展示结束 -->
		
		<!-- 盆景分类开始 -->
			<div class="newsType">
				<div class="type">新闻中心</div>
				<div style="height: 5px;background-color: #FAFAFA;"></div>
				<ul style="position: relative;z-index: 0;">
					<c:forEach var="pNbd" items="${pNewsBd}">
						<li id="newsParent"><p><c:out value="${pNbd.newsBoardName}"></c:out></p></li>	
						<c:forEach var="sNbd" items="${sNewsBd}">
							<c:if test="${sNbd.newsBoardparent.newsBoardId==pNbd.newsBoardId}">
								<li onclick="tiJiaoSBdId(${sNbd.newsBoardId})"><p><c:out value="${sNbd.newsBoardName}"></c:out></p></li>
							</c:if>
						</c:forEach>
					</c:forEach>
					<li class="typeSelect"></li>
				</ul>
			</div>
		<!-- 盆景分类结束 -->
		</div>
	</div>
	<script src="js/jquery-1.11.2.min.js"></script>
	<script src="js/news.js"></script>
</body>
</html>