<?php 
	require '../Util/SqlHelper.class.php';

	//接受参数
	session_start();
	
	// 检验盗链
	if ($_SESSION ['username'] == null) {
		$url = "./daolian.html";
		Header ( "Location: $url" );
	}
	
	
	if(isset($_GET['exit'])){
		if($_GET['exit']==1){
			session_destroy();
		}
	}
	
	if(isset($_GET['updateid'])){
		$helpid=$_GET['updateid'];

		$_SESSION['updateid']=$helpid;
		
		$sqlHelper=new SqlHelper();
		$sql="select * from helparticle where helpid='$helpid'";
		$h=$sqlHelper->execute_dql($sql);
		$_SESSION['updatehelp']=$h[0];
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
					<a class="navbar-brand" href="#">求助帖信息修改</a>
				</div>
				<div>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#"><?php echo "您好,".$_SESSION['username'];?></a></li>
						<li><a href="#"><?php echo "上次登录时间:"; if($_SESSION['lasttime']==0) echo "无";else echo date("Y-m-d",$_SESSION['lasttime']); ?></a></li>
					</ul>
				</div>
			</nav>
			<!-- 导航条结束 -->
			
			<form class="form-horizontal" role="form" method="POST" action="../Controller/updateProcess3.php">
				 <div class="form-group">
      				<label class="col-sm-2 control-label">
         			求助帖ID
      				</label>
      				<div class="col-sm-4">
         				<input class="form-control" type="text" 
            			value="<?php echo ($_SESSION['updatehelp']['helpid']) ?>" disabled>
     				</div>
   				</div>
  				<div class="form-group">
      				<label class="col-sm-2 control-label">求助帖标题</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="helpheader" id="focusedInput" type="text" 
            			value="<?php echo ($_SESSION['updatehelp']['helpheader'])?>">
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">求助帖内容</label>
      				<div class="col-sm-4">
         				<textarea cols="49" rows="3" name="helpcontent" id="focusedInput">
         				<?php echo ($_SESSION['updatehelp']['helpcontent'])?>
            			</textarea>
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">评论列表</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="commentlist" id="focusedInput" type="text"
            			value="<?php echo ($_SESSION['updatehelp']['commentlist'])?>">
      				</div>
   				</div>
   				<div class="form-group">
      				<label class="col-sm-2 control-label">帖子发表时间</label>
      				<div class="col-sm-4">
         				<input class="form-control" name="helptime" id="focusedInput" type="text" 
            			value="<?php echo ($_SESSION['updatehelp']['helptime'])?>">
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