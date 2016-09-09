<?php
session_start ();
// 检验盗链
if ($_SESSION ['username'] == null) {
	$url = "./daolian.html";
	Header ( "Location: $url" );
}
?>
<html>
<head>
<title></title>
<meta charset="gbk">
<link href="../Include/Css/common.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="../Include/dist/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="../Include/dist/bootstrap-theme.css">
<script type="text/javascript" src="../Include/dist/bootstrap.min.js"></script>
<script type="text/javascript" src="../Include/Js/jquery.js"></script>
</head>
<style>
.update{
	width:82px;height:30px;background:#f90;display:block;float:right;margin:13px 20px 0 0;text-align:center;text-decoration:none;line-height:30px;font-size:14px;color:#fff;border-radius:5px;
}
.delete{
	width:82px;height:30px;background:#f63;display:block;float:right;margin:13px 20px 0 0;text-align:center;text-decoration:none;line-height:30px;font-size:14px;color:#fff;border-radius:5px;
}
.add{
	width:82px;height:30px;background:#36f;display:block;float:right;margin:13px 20px 0 0;text-align:center;text-decoration:none;line-height:30px;font-size:14px;color:#fff;border-radius:5px;
}
</style>
<script type="text/javascript">
	function del(url){
		var flag=confirm("确定要删除吗?");
		if(flag){
			window.location.href=url;
		}
	}
</script>

<body>
	<div class="container">
		<!-- 导航条 -->
		<nav class="navbar navbar-inverse " role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">后台管理系统</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">用户管理</a></li>
					<li><a href="./scenicManage.php">景点管理</a></li>
					<li><a href="helpArticle.php">求助帖管理</a></li>
					<li><a href="./admin.php?exit=1">退出</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><?php echo "您好,".$_SESSION['username'];?></a></li>
					<li><a href="#"><?php echo "上次登录时间:"; if($_SESSION['lasttime']==0) echo "无";else echo date("Y-m-d",$_SESSION['lasttime']); ?></a></li>
				</ul>
			</div>
		</nav>
		<!-- 导航条结束 -->
		<form style="float:left;" action="../Controller/searchProcess.php" method="post">
			<div class="input-append">
    			<input type="text" class="span2 search-query" placeholder="请输入要查找的用户名" name="search">
        		<button type="submit" class="btn btn-info">查找</button>
			</div>
		</form>
		<a href="./userAdd.php" class="add" style="text-decoration:none;">添加用户</a>
		<!-- 搜索框结束 -->
		<br/><br/>
		<div class="panel panel-success">
   			<div class="panel-heading">
      			<a href="./userManage.php" style="text-decoration:none;"><h3 class="panel-title">用户管理列表</h3></a>
   			</div>
   			<div class="panel-body">
     			<table class="table table-striped">
     				 <thead>
      					<tr>
      						<th>用户id</th>
         					<th>用户昵称</th>
         					<th>登陆账号</th>
         					<th>用户积分</th>
         					<th>好友列表</th>
         					<th>发布文章列表</th>
         					<th>手机号</th>
         					<th>国籍</th>
         					<th>邮箱</th>
         					<th>是否进行修改</th>
         					<th>是否删除用户</th>
     					</tr>
   					</thead>
   					<tbody>
      					<!-- tr td填充body内容 -->
      					<?php

      						header("Content-type: text/html; charset=gbk");
      						//读取数据库用户信息来进行填充
      						require '../Util/FenyePage.class.php';
      						require '../Util/SqlHelper.class.php';
      						//require '../Model/User.class.php';
      						
      						if(isset($_GET['selectedId'])){
      							if($_GET['selectedId']==6){
      								if(isset($_SESSION['searchObj'])){
										$user=$_SESSION['searchObj'];
      									echo "<tr>";
      									echo "<td>$user[userid]</td><td>$user[name]</td><td>$user[username]</td><td>$user[grades]</td><td>$user[friendlist]</td><td>$user[articlelist]</td><td>$user[phone]</td><td>$user[country]</td><td>$user[email]</td>";
      									echo "<td><a class='update' href='./updateUser.php?updateid=$user[userid]'>修改</a></td>";
      									echo "<td><a class='delete' href='javascript:del(".'"'."../Controller/delProcess.php?delId=$user[userid]".'"'.")'>删除</a></td>";
      									echo "</tr>";
      									echo "</tbody></table>";
      								}else{
										echo "</tbody></table>";
      									echo "<h3>查无此人</h3>";
      								}
      								return;
      							}
      						}
      						
      						
      						if(!isset($_GET['pageNow'])){
      							$pageNow=1;
      						}else{
      							$pageNow=$_GET['pageNow'];
      						}
      						//新建分页对象
      						$fenye=new FenyePage();
      						$fenye->pageNow=$pageNow;
      						//新建SqlHelper对象
      						$sqlHelper=new sqlHelper();
      						$sql1="select * from user limit ".($fenye->pageNow-1)*$fenye->pageSize.",".$fenye->pageSize;
      						$sql2="select count(*) from user";
      						$sqlHelper->execute_dql_fenye($sql1, $sql2, $fenye);
      						for($i=0;$i<count($fenye->res_array);$i++){
								$user=$fenye->res_array[$i];
								echo "<tr>";
								echo "<td>$user[userid]</td><td>$user[name]</td><td>$user[username]</td><td>$user[grades]</td><td>$user[friendlist]</td><td>$user[articlelist]</td><td>$user[phone]</td><td>$user[country]</td><td>$user[email]</td>";
								echo "<td><a class='update' href='./updateUser.php?updateid=$user[userid]'>修改</a></td>";
								echo "<td><a class='delete' href='javascript:del(".'"'."../Controller/delProcess.php?delId=$user[userid]".'"'.")'>删除</a></td>";
								echo "</tr>";
      						}
      						
      					?>
   					</tbody>
     			</table>
     			<?php 
     				echo $fenye->navigate;
     			?>
   			</div>
		</div>
	</div>
	
</body>
</html>