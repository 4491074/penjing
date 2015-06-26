<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{margin: 0px;padding: 0px;}
	body {background-color: #FAFAFA;}
	.con {width: 80%;margin-left: 10%;background-color: #FAFAFA;height: 500px;}
	.con .penJingInfo{float: right;width: 59%;background-color: #FAFAFA;height: 500px;}
	.con .penJingPic{width: 40%;background-color: #FAFAFA;padding-top: 10px;border-width: 1px;border-color: #333;border-style: solid;padding-bottom: 5px;}
	.con .penJingPic .bigPic{width: 96%;background-color: #FAFAFA;margin: 0% 2% 2% 2%;align:center;}
	.con .penJingPic .smallPics{width: 100%;background-color: #FAFAFA;margin-top: 5px;}
	.con .penJingPic .smallPics .right{width: 3%;height: 100%;background:rgba(51, 51, 51, 0.7)!important;float: right;}
	.con .penJingPic .smallPics .pics{height: 100%;width: 94%;background-color: #FAFAFA;float: right;overflow:hidden;}
	.con .penJingPic .smallPics .left{width: 3%;height: 100%;background:rgba(51, 51, 51, 0.7)!important;}
	.con .penJingPic .smallPics .pics .picsList{height: 100%;}
	.con .penJingPic .smallPics .pics .picsList .li{float: left;background-color: #FAFAFA;height: 100%;margin-right: 10px;cursor: pointer;}
	.con .penJingPic .smallPics .pics .picsList .action{border-width: 1px;border-color: #333;border-style: solid;}
	
</style>
</head>
<body>
	<div class="con">
		<div class="penJingInfo">
			<b>盆景名称：</b><div>${penjing.penJingName }</div><br><hr>
			<b>盆景标题：</b><div>${penjing.penJingTitle }</div><br><hr>
			<b>盆景赏析：</b><div>${penjing.penJingDescription }</div><br><hr>
			<b>发布时间：</b><div>${penjing.publishTime }</div><br><hr>
			若有意向请记住编号<font color="red">${penjing.penJingId }</font>,拨打电话18723385990
		</div>
		<div class="penJingPic">
			<div class="bigPic">
				<c:forEach items="${pics }" var="picture" varStatus="i">
					<c:if test="${i.index==0 }">
						<img alt="盆景展示" src="../${picture.pictureUrl }">
	    			</c:if>
				</c:forEach>
				
			</div>
			<hr>
			<div class="smallPics">
				<div class="right" onclick="scrollToRight()"></div>
				<div class="pics">
					<div class="picsList">
						<c:forEach items="${pics }" var="picture" varStatus="i">
							<div class="li
							<c:if test="${i.index==0 }">
	    				 		 action
	    					</c:if>
						 ">
							<img alt="盆景展示" src="../${picture.pictureUrl }">
						</div>
						</c:forEach>
					</div>
				</div>
				<div class="left" onclick="scrollToLeft()"></div>
			</div>
		</div>
	</div>
	<script src="../js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="../js/jquery.scrollto.js"></script>
	<script src="../js/penJingInfo.js"></script>
</body>
</html>