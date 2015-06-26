<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{ font-size:14px;}
input{ vertical-align:middle; margin:0; padding:0}
.file-box{ position:relative;width:160px;margin-left: 66%;}
.btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;}
.file{ position:absolute; top:0; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px }
</style>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/metro-gray/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/HeadImageManage.js"></script>
<%
Date time=new Date();
%>
</head>
<body>
<!-- 第一张图片 -->
<div class="headfont"><font size="+4" style="margin-left: 38%;color: gray">首页图片操作</font></div>
	 <div id="firstImg1" style="width:650px; margin: 5px auto 5px">
		<img src="headImg/carousel1.png?num=<%=time %>" width="650px"></img>
 </div>  
<div class="file-box">
  <form action="updateImg" method="post" enctype="multipart/form-data" onsubmit="return check()">  
     <input type="hidden" name="imgName" value="carousel1"/>
     <input type='button' class='btn' value='浏览...' />
     <input type="file" name="file" class="file" id="fileField1" size="28"  />
     <input type="submit" name="submit" class="btn" value="上传" />
  </form>
</div>

<!-- 第二张图片 -->

 <div id="firstImg1" style="width:650px; margin: 5px auto 5px">
		<img src="headImg/carousel2.png?num=<%=time %>" width="650px"></img>
 </div>  
<div class="file-box">
  <form action="updateImg" method="post" enctype="multipart/form-data" onsubmit="return check()">  
     <input type="hidden" name="imgName" value="carousel2"/>
     <input type='button' class='btn' value='浏览...' />
     <input type="file" name="file" class="file" id="fileField2" size="28" />
     <input type="submit" name="submit" class="btn" value="上传" />
  </form>
</div>

<!-- 第三张图片 -->
<div id="firstImg1" style="width:650px; margin: 5px auto 5px">
		<img src="headImg/carousel3.png?num=<%=time %>" width="650px"></img>
 </div>  
<div class="file-box">
  <form action="updateImg" method="post" enctype="multipart/form-data" onsubmit="return check()">  
     <input type="hidden" name="imgName" value="carousel3"/>
     <input type='button' class='btn' value='浏览...' />
     <input type="file" name="file" class="file" id="fileField3" size="28"  />
     <input type="submit" name="submit" class="btn" value="上传" />
  </form>
</div>
</body>
</html>