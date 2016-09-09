<?php 
	require '../Util/SqlHelper.class.php';

	//接受参数
	session_start();
	if(isset($_GET['exit'])){
		if($_GET['exit']==1){
			session_destroy();
		}
	}
	
	
	if(isset($_GET['updateid'])){
		$userid=$_GET['updateid'];

		$_SESSION['updateid']=$userid;
		
		$sqlHelper=new SqlHelper();
		$sql="select * from user where userid='$userid'";
		$u=$sqlHelper->execute_dql($sql);
		$_SESSION['updateuser']=$u[0];
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
					<a class="navbar-brand" href="#">用户信息修改</a>
				</div>
				<div>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#"><?php echo "您好,".$_SESSION['username'];?></a></li>
						<li><a href="#"><?php echo "上次登录时间:"; if($_SESSION['lasttime']==0) echo "无";else echo date("Y-m-d",$_SESSION['lasttime']); ?></a></li>
					</ul>
				</div>
			</nav>
			<!-- 导航条结束 -->
			
			<form class="form-horizontal" role="form" method="POST" action="../Controller/updateProcess.php">
				 <div class="form-group">
      				<label class="col-sm-2 control-label">
         			用户ID
      				</label>
      				<div class="col-sm-4">
         				<input class="form-control" type="text" 
            			value="<?php echo ($_SESSION['updateuser']['userid']) ?>" disabled>
     				</div>
   				</div>
  				<div class="form-group">
      				<label class="col-sm-2 control-label">昵称</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="uname" id="focusedInput" type="text" 
            			value="<?php echo ($_SESSION['updateuser']['username'])?>">
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">朋友列表</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="friendList" id="focusedInput" type="text" 
            			value="<?php echo ($_SESSION['updateuser']['friendlist'])?>">
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">用户积分</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="grades" id="focusedInput" type="text" 
            			value="<?php echo ($_SESSION['updateuser']['grades'])?>">
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">文章列表</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="articlelist" id="focusedInput" type="text" 
            			value="<?php echo ($_SESSION['updateuser']['articlelist'])?>">
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">手机号</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="phone" id="focusedInput" type="text" 
            			value="<?php echo ($_SESSION['updateuser']['phone'])?>">
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">国籍</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="country" id="focusedInput" type="text" 
            			value="<?php echo ($_SESSION['updateuser']['country'])?>">
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">邮箱</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="email" id="focusedInput" type="text" 
            			value="<?php echo ($_SESSION['updateuser']['email'])?>">
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