<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@page import="com.penjing.entity.User"%>
<title></title>
<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    *{margin: 0px;padding: 0px;}
    body {padding-top: 60px;background-color: #FAFAFA;}
    .con {width: 100%;margin: 0px auto;height: 500px;}
    .login {width: 30%;position: absolute;top: 30%;left: 35%;display: none;}
    .username {margin-top: 10px;}
    .password {margin-top: 30px;margin-bottom: 30px;}
    .check .code {float: right;margin-right: 10px;}
    .check {margin-top: 30px;margin-bottom: 30px;}
    .bo {height: 80px;background-color: #404040;width: 100%;margin-top: 10px;text-align: center;padding-top: 10px;color: white;
    	 font-size: 12px;font-family: 微软雅黑;}
    .collapse .navbar-right .navPhoto{width: 21px;}
    .collapse .navbar-right .media{margin: 0px;padding: 0px;margin-top: 15px;}
    .floatDiv{width: 0px;padding: 5px 0px;margin: 0px;text-align: center;word-wrap: break-word;word-break: normal;background-color: #D1EEEE;
    			border-radius:5px;-moz-border-radius:25px;box-shadow: 3px 3px 3px #888888;display: none;}
    </style>
</head>
<body>
<div id="m">
<!-- 导航条开始 -->
	<div class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
	  <div class="container-fluid  container">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">展开</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="home">南山盆景</a>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li><a href="forums">论坛</a></li>
	      </ul>
	      <form class="navbar-form navbar-left" role="search">
	        <div class="form-group">
	          <input type="text" class="form-control" placeholder="查找">
	        </div>
	        <button type="submit" class="btn btn-default">搜索</button>
	      </form>
	      <%
    		String userName = "";
	    	if(session.getAttribute("user")==null){
	      %>
	    	<div class="nav navbar-nav navbar-right">
	    		<li><a href="#" onclick="toEnroll()">注册</a></li>
	    		<li><a href="#" data-toggle="modal" onclick="loginPane()">登录</a></li>
	        </div>
	    		<%
	    	}else {
	    		User user = (User)session.getAttribute("user");
	    		userName = user.getUserName();
	    		String photo = user.getPhoto();
	    		if(photo == null){
	    			photo = "img/photo.png";
	    		}
	    		%>
	    	<div class="nav navbar-nav navbar-right" id="login">
          		<div class="media">
  					<a class="media-left" href="users?id=<%=user.getUserId() %>" style="padding-right: 3px;">
  							<img class="navPhoto img-circle" src=<%=photo %> alt="头像">
  					</a>
  					<div class="media-body dropdown">
    					<a href="#" data-toggle="dropdown"><%=userName %><span class="caret"></span></a>
    						<ul class="dropdown-menu" role="menu">
					            <li><a href="users?id=<%=user.getUserId() %>">个人中心</a></li>
					            <li class="divider"></li>
					            <li><a href="JavaScript:logout()">退出</a></li>
					          </ul>
  					</div>
				</div>
	      </div>
	    		<%
	    	}
			%>
	      </div>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	
	<!-- 导航条结束 -->
	
	<!-- 中间容器开始 -->
	<div class="con" id="con">
		<%
			String nowPage = (String)request.getAttribute("nowPage");
			if(nowPage==null||"".equals(nowPage)){
				nowPage = "../jsp/forum.jsp?page=1";
			}
		%>
		<iframe class="embed-responsive-item" id="frame" frameborder="0" scrolling="no" style="width: 100%;height: 100%;" src="<%=nowPage %>"></iframe>
	</div>
	<!-- 中间容器结束 -->
	<!-- 底部容器开始 -->
		<div class="bo">
			<ul style="list-style:none;">
			<li>重庆南山盆景网提供&nbsp;&nbsp;Rights Reserved</li>
			<li>客服电话：18723385990</li>
			<li>
				<a href="JavaScript:void(0);" onclick="navbar('jsp/building.jsp?li=-1')">意见反馈</a>&nbsp;&nbsp;
				<a href="JavaScript:void(0);" onclick="navbar('jsp/building.jsp?li=-1')">联系我们</a>
			</li>
		</ul>
		</div>
	<!-- 底部容器结束 -->
	</div>
	<!-- 登陆组件开始 -->
	<div class="modal fade fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  		<div class="modal-dialog modal-sm">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        			<h4 class="modal-title" id="myModalLabel">登陆</h4>
      			</div>
	      			<div class="modal-body">
	      			<form action="#" id="loginForm">
	      				<div><font color="red" id="errormsg">&nbsp;</font></div>
	        			<div class="input-group username" >
		 		 			<span class="input-group-addon">账号</span>
		  					<input type="text" class="form-control" placeholder="UserName" id="name" name="user.userName">
						</div> 
						<div class="input-group password">
							<span class="input-group-addon">密码</span>
		  					<input type="password" class="form-control" placeholder="Password" id="pass" name="user.password">
						</div>
					</form>
      			<div class="modal-footer">
        			<div class="btn-group btn-group-justified">
				  		<div class="btn-group">
				    		<button type="button" class="btn btn-primary" onclick="mainReset()">重置</button>
				  		</div>
				  		<div class="btn-group">
				    		<button type="button" class="btn btn-primary" onclick="toEnroll()">注册</button>
				 		</div>
				  		<div class="btn-group">
				    		<button type="button"class="btn btn-primary" onclick="loginCheck()">登陆</button>
				  		</div>
      				</div>
     			 </div>
    		</div>
  		</div>
	</div>
	</div>
	<!-- 登陆组件结束 -->
	<!-- 页面浮动层开始 -->
	<div class="floatDiv">
  		<button type="button" class="close" data-dismiss="alert" aria-label="Close" style="margin-right: 10px;" onclick="hideFloat()"><span aria-hidden="true">&times;</span></button>
  		<p style="font-size: 12px;margin-bottom: 0px;font-family: 微软雅黑;" id="floadContent"></p>
	</div>
	<!-- 页面浮动层结束 -->
	
	<!-- 登陆组件结束 -->
    <!-- jQuery -->
    <script src="js/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jqueryFloat.js"></script>
    <script src="js/bbsindex.js"></script>
</body>
</html>