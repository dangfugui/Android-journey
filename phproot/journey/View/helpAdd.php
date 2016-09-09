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
					<a class="navbar-brand" href="#">帖子的添加</a>
				</div>
				<div>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#"><?php echo "您好,".$_SESSION['username'];?></a></li>
						<li><a href="#"><?php echo "上次登录时间:"; if($_SESSION['lasttime']==0) echo "无";else echo date("Y-m-d",$_SESSION['lasttime']); ?></a></li>
					</ul>
				</div>
			</nav>
			<!-- 导航条结束 -->
			
			<form class="form-horizontal" role="form" method="POST" action="../Controller/helpAddProcess.php">
				 <div class="form-group">
      				<label class="col-sm-2 control-label">
         			帖子标题
      				</label>
      				<div class="col-sm-4">
         				<input class="form-control" type="text" name="helpHeader" value="">
     				</div>
   				</div>
  				<div class="form-group">
      				<label class="col-sm-2 control-label">帖子内容</label>
      				<div class="col-sm-4">
         				<textarea rows="3" cols="49" name="helpContent">
						</textarea>
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">求助帖发送时间（格式:如1970-02-55）</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="helpTime" id="focusedInput" type="text" 
            			value="">
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">回复列表</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="commentList" id="focusedInput" type="text" 
            			value="">
      				</div>
   				</div>
   				
   				<button type="submit" class="btn btn-warning" style="position:relative;left:180px;">确认保存</button>
   				<a class="btn btn-warning" href="./helpArticle.php" style="position:relative;left:290px;">返回主界面</a>
   			</form>
			
		</div>
	</head>
	
	<body>
		
	</body>
</html>