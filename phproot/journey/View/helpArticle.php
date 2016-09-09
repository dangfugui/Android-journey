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
					<li><a href="userManage.php">用户管理</a></li>
					<li><a href="scenicManage.php">景点管理</a></li>
					<li class="active"><a href="#">求助帖管理</a></li>
					<li><a href="./admin.php?exit=1">退出</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><?php echo "您好,".$_SESSION['username'];?></a></li>
					<li><a href="#"><?php echo "上次登录时间:"; if($_SESSION['lasttime']==0) echo "无";else echo date("Y-m-d",$_SESSION['lasttime']); ?></a></li>
				</ul>
			</div>
		</nav>
		<!-- 导航条结束 -->
		<form style="float:left;" action="../Controller/searchProcess3.php" method="post">
			<div class="input-append">
    			<input type="text" class="span2 search-query" placeholder="请输入要查找的求助帖标题" name="search">
        		<button type="submit" class="btn btn-info">查找</button>
			</div>
		</form>
		<a href="./helpAdd.php" class="add" style="text-decoration:none;">添加帖子</a>
		<!-- 搜索框结束 -->
		<br/><br/>
		<div class="panel panel-success">
   			<div class="panel-heading">
      			<a href="./helpArticle.php" style="text-decoration:none;"><h3 class="panel-title">帖子管理列表</h3></a>
   			</div>
   			<div class="panel-body">
     			<table class="table table-striped">
     				 <thead>
      					<tr>
      						<th>求助帖ID</th>
         					<th>求助帖标题</th>
         					<th>求助帖内容</th>
         					<th>提问时间</th>
         					<th>解答列表</th>
         					<th>是否进行修改</th>
         					<th>是否进行删除</th>
         				</tr>
   					</thead>
   					<tbody>
      					<!-- tr td填充body内容 -->
      					<?php

      						header("Content-type: text/html; charset=gbk");
      						//读取数据库用户信息来进行填充
      						require '../Util/FenyePage.class.php';
      						require '../Util/SqlHelper.class.php';
      						
      						if(isset($_GET['selectedId'])){
      							if($_GET['selectedId']==6){
      								if(isset($_SESSION['searchObj'])){
										$help=$_SESSION['searchObj'];
      									echo "<tr>";
      									echo "<td>$help[helpid]</td><td>$help[helpheader]</td><td>$help[helpcontent]</td><td>$help[helptime]</td><td>$help[commentlist]</td>";
										echo "<td><a class='update' href='./updateHelp.php?updateid=$help[helpid]'>修改</a></td>";
										echo "<td><a class='delete' href='javascript:del(".'"'."../Controller/delProcess3.php?delId=$help[helpid]".'"'.")'>删除</a></td>";
      									echo "</tr>";
      									echo "</tbody></table>";
      								}else{
										echo "</tbody></table>";
      									echo "<h3>查无此帖子</h3>";
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
      						$sql1="select * from helparticle limit ".($fenye->pageNow-1)*$fenye->pageSize.",".$fenye->pageSize;
      						$sql2="select count(*) from helparticle";
      						$sqlHelper->execute_dql_fenye($sql1, $sql2, $fenye);
      						for($i=0;$i<count($fenye->res_array);$i++){
								$help=$fenye->res_array[$i];
								echo "<tr>";
								echo "<td>$help[helpid]</td><td>$help[helpheader]</td><td>$help[helpcontent]</td><td>$help[helptime]</td><td>$help[commentlist]</td>";
								echo "<td><a class='update' href='./updateHelp.php?updateid=$help[helpid]'>修改</a></td>";
								echo "<td><a class='delete' href='javascript:del(".'"'."../Controller/delProcess3.php?delId=$help[helpid]".'"'.")'>删除</a></td>";
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