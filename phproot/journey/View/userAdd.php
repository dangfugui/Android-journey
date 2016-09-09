<?php 
	session_start();
	// 检验盗链
	if ($_SESSION ['username'] == null) {
		$url = "./daolian.html";
		Header ( "Location: $url" );
	}
?>
<html>
	<head>
		<title>后台管理系统</title>
		<meta charset="gbk">
		<link href="../Include/Css/common.css" type="text/css" rel="stylesheet"/>
		<link rel="stylesheet" type="text/css" href="../Include/dist/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../Include/dist/bootstrap-theme.css">
		<link rel="stylesheet" type="text/css" href="../Include/Css/admin.css">
		<script type="text/javascript" src="../Include/dist/bootstrap.min.js"></script>
		<script type="text/javascript" src="../Include/Js/jquery.js"></script>
		<div class="container">
			<!-- 导航条 -->
			<nav class="navbar navbar-inverse " role="navigation">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">用户添加</a>
				</div>
				<div>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#"><?php echo "您好,".$_SESSION['username'];?></a></li>
						<li><a href="#"><?php echo "上次登录时间:"; if($_SESSION['lasttime']==0) echo "无";else echo date("Y-m-d",$_SESSION['lasttime']); ?></a></li>
					</ul>
				</div>
			</nav>
			<!-- 导航条结束 -->
			
			<form class="form-horizontal" role="form" method="POST" action="../Controller/userAddProcess.php">
				 <div class="form-group">
      				<label class="col-sm-2 control-label">
         			昵称
      				</label>
      				<div class="col-sm-4">
         				<input class="form-control" type="text" name="name" value="">
     				</div>
   				</div>
  				<div class="form-group">
      				<label class="col-sm-2 control-label">登陆名</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="username" id="focusedInput" type="text" 
            			value="">
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">密码</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="password" id="focusedInput" type="text" 
            			value="">
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">积分</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="grades" id="focusedInput" type="text" 
            			value="">
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">手机</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="phone" id="focusedInput" type="text" 
            			value="">
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">国籍</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="country" id="focusedInput" type="text" 
            			value="">
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">邮箱</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="email" id="focusedInput" type="text" 
            			value="">
      				</div>
   				</div>
   				
   				<button type="submit" class="btn btn-warning" style="position:relative;left:180px;">确认保存</button>
   				<a class="btn btn-warning" href="./userManage.php" style="position:relative;left:290px;">返回主界面</a>
   			</form>
			
		</div>
	</head>
	
	<body>
		
	</body>
</html>