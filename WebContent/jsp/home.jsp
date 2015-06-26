<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.penjing.entity.PenJingType"%>
    <%@page import="java.util.List"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重庆南山盆景</title>
<style type="text/css">
	*{margin: 0px;padding: 0px;}
	body {background-color: #FAFAFA;}
	.con {width: 80%;margin-left: 10%;}
	.con .carousel {width: 100%;height: 300px;background-color: #2E8B57;margin-bottom: 15px;position: relative;z-index: 0}
	.con .carousel .b_img{position: absolute;width: 100%;height: 100%;}
	.con .carousel .b_img img{width: 100%;height: 100%}
	.con .carousel #carouselInfo{width: 100%;position: absolute;z-index: 1;background-color: #292929;bottom: 0px;color: white;height: 0px;
								 background:rgba(029, 029, 029, 0.5)!important;
								 font-size: 12px;font-family: 微软雅黑;display: none;
	}
	.con .penJingShow {width: 79%;float: right;}
	body:after {content:".";display:block;clear:both; height:0;font-size:0;line-height:0;}
	.con .penJingType {width: 19%;background-color: #00c896;}
	.con .penJing .penJingType .type{color: white;font-weight: 700;padding: 10px 0px;text-align: center;background-color: #3CB371}
	.con .penJing .penJingType li{list-style: none;color: white;font-weight: 700;padding: 10px 0px;text-align: center;
								  position: relative;z-index: 2;cursor:default;cursor: pointer;}
	.con .penJing .penJingType .typeSelect{width: 100%; background-color: #008264;position: absolute;z-index: 1;top: 0px;display: none;}
	.con .penJingShow .nowType{color: white;font-weight: 700;padding: 10px 0px;background-color: #3CB371;padding-left: 10px;}
	.con .penJingShow li{list-style: none;float: left;width: 32%;margin: 0px 1% 20px 1%;}
	.con .penJingShow .penJingFir{margin-left: 0px;}
	.con .penJingShow .penJingLas{margin-right: 0px;}
	.con .penJingShow .penJingBorder{border-color: #666;border-width: 1px;border-style: solid;}
	.con .penJingShow .penJingBorder .info:after {content:".";display:block;clear:both; height:0;font-size:0;line-height:0;}
	.con .penJingShow .penJingImg{width: 95%;margin: 5px 2.5%;cursor: pointer;}
	.con .penJingShow .penJingInfo{float: right;width: 92%;font-size: 12px;color: #666;position: relative;z-index: 0;}
	.con .penJingShow .penJingInfo .phone{margin-top: 3px;margin-bottom: 5px;}
	.con .penJingShow .infoColor{width: 5px;background-color: #2E8B57;margin-left: 0.5%;position: fixed;z-index: 1;}
	.con .penJingShow .infoColor .clickTip{position: absolute;top: 7px;left: 20px;color: #E0FFFF;font-size: 15px;display: none;}
	.con .penJingShow .penJingHr{margin-bottom: 5px;color: #fff;}
</style>
</head>
<body>
	<div class="con">
		<!-- 图片滚动开始 -->
		<div class="carousel">
			<div class="b_img"><img src="../headImg/carousel1.png"></div>
      		<div class="b_img"><img src="../headImg/carousel2.png"></div>
      		<div class="b_img"><img src="../headImg/carousel3.png"></div>
      		<div id="carouselInfo">
      			<p id="carouselText" style="padding: 10px 10px;">
      				南山风景区是招隐山、黄鹤山、夹山、九华山等诸山的统称，由招隐景区、竹林景区、鹤林景区和文苑组成。景区内山峦起伏，竹林掩映，花鸟相谐，堪称美景。
      				 南山在今天似乎不及金山、北固山出名，但自南北朝起，却是无数文士名流的居住和游览胜地，因为相较于前两者的喧闹与繁华，幽雅宁静是它最大的特色。
      			</p>
      		</div>
		</div>
		<!-- 图片滚动结束 -->
		<div class="penJing">
		<!-- 盆景展示开始 -->
			<div class="penJingShow">
				<div class="nowType">最新盆景</div>
				<div style="height: 5px;background-color: #FAFAFA;"></div>
				<ul style="position: relative;z-index: 0;">
					<c:forEach items="${penjings }" var="penjing" varStatus="i">
						<li class="
						<c:if test="${(i.index)%3==0 }">
    				 		penJingFir
    					</c:if>
    					<c:if test="${(i.index)%3==2 }">
    				 		penJingLas
    					</c:if>
						 ">
							<div class="penJingBorder">
								<div class="penJingImg">
									<a href="../penjingInfo?id=${penjing.penJingId }" target="_parent">
										<img class="img" alt="盆景" src="../${penjing.mainPicture }">
									</a>
								</div>
								<hr class="penJingHr">
								<div class="info">
									<div class="penJingInfo">
										<div>${penjing.penJingName }(编号：${penjing.penJingId })</div>
										<div class="phone">联系方式：${managerTel }（村委）</div>
									</div>
									<div class="infoColor">
										<p class="clickTip">点解了解详情</p>
									</div>
								</div>
							</div>
						</li>
					</c:forEach>
					<li id="mark" style="display: none;"></li>
				</ul>
				<div class="loadMore" style="font-size: 12px;bottom: 0px;position: absolute;left: 56%">
					<a href="JavaScript:get()">查看更多</a>
				</div>
			</div>
		<!-- 盆景展示结束 -->
		
		<!-- 盆景分类开始 -->
			<div class="penJingType">
				<div class="type">盆景分类</div>
				<div style="height: 5px;background-color: #FAFAFA;"></div>
				<ul style="position: relative;z-index: 0;">
					<li id="type_0" target="0" onclick="changeType(0)">最新盆景</li>
					<%
						@SuppressWarnings("unchecked")
						List<PenJingType> types = (List<PenJingType>)application.getAttribute("penjingType");
						if(types != null){
							for(PenJingType pjt:types){
					%>
						<li onclick="changeType(<%=pjt.getPenJingTypeId() %>)" id="type_<%=pjt.getPenJingTypeId() %>" target="<%=pjt.getPenJingTypeId() %>"><%=pjt.getPenJingTypeName() %></li>
					<%
							}
						}
					%>	
					<li class="typeSelect"></li>
				</ul>
				
			</div>
		<!-- 盆景分类结束 -->
		</div>
	</div>
	<script src="../js/jquery-1.11.2.min.js"></script>
	<script src="../js/home.js"></script>
</body>
</html>