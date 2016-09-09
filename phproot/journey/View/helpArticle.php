<?php
session_start ();
// �������
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
		var flag=confirm("ȷ��Ҫɾ����?");
		if(flag){
			window.location.href=url;
		}
	}
</script>

<body>
	<div class="container">
		<!-- ������ -->
		<nav class="navbar navbar-inverse " role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">��̨����ϵͳ</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li><a href="userManage.php">�û�����</a></li>
					<li><a href="scenicManage.php">�������</a></li>
					<li class="active"><a href="#">����������</a></li>
					<li><a href="./admin.php?exit=1">�˳�</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><?php echo "����,".$_SESSION['username'];?></a></li>
					<li><a href="#"><?php echo "�ϴε�¼ʱ��:"; if($_SESSION['lasttime']==0) echo "��";else echo date("Y-m-d",$_SESSION['lasttime']); ?></a></li>
				</ul>
			</div>
		</nav>
		<!-- ���������� -->
		<form style="float:left;" action="../Controller/searchProcess3.php" method="post">
			<div class="input-append">
    			<input type="text" class="span2 search-query" placeholder="������Ҫ���ҵ�����������" name="search">
        		<button type="submit" class="btn btn-info">����</button>
			</div>
		</form>
		<a href="./helpAdd.php" class="add" style="text-decoration:none;">�������</a>
		<!-- ��������� -->
		<br/><br/>
		<div class="panel panel-success">
   			<div class="panel-heading">
      			<a href="./helpArticle.php" style="text-decoration:none;"><h3 class="panel-title">���ӹ����б�</h3></a>
   			</div>
   			<div class="panel-body">
     			<table class="table table-striped">
     				 <thead>
      					<tr>
      						<th>������ID</th>
         					<th>����������</th>
         					<th>����������</th>
         					<th>����ʱ��</th>
         					<th>����б�</th>
         					<th>�Ƿ�����޸�</th>
         					<th>�Ƿ����ɾ��</th>
         				</tr>
   					</thead>
   					<tbody>
      					<!-- tr td���body���� -->
      					<?php

      						header("Content-type: text/html; charset=gbk");
      						//��ȡ���ݿ��û���Ϣ���������
      						require '../Util/FenyePage.class.php';
      						require '../Util/SqlHelper.class.php';
      						
      						if(isset($_GET['selectedId'])){
      							if($_GET['selectedId']==6){
      								if(isset($_SESSION['searchObj'])){
										$help=$_SESSION['searchObj'];
      									echo "<tr>";
      									echo "<td>$help[helpid]</td><td>$help[helpheader]</td><td>$help[helpcontent]</td><td>$help[helptime]</td><td>$help[commentlist]</td>";
										echo "<td><a class='update' href='./updateHelp.php?updateid=$help[helpid]'>�޸�</a></td>";
										echo "<td><a class='delete' href='javascript:del(".'"'."../Controller/delProcess3.php?delId=$help[helpid]".'"'.")'>ɾ��</a></td>";
      									echo "</tr>";
      									echo "</tbody></table>";
      								}else{
										echo "</tbody></table>";
      									echo "<h3>���޴�����</h3>";
      								}
      								return;
      							}
      						}
      						
      						
      						if(!isset($_GET['pageNow'])){
      							$pageNow=1;
      						}else{
      							$pageNow=$_GET['pageNow'];
      						}
      						//�½���ҳ����
      						$fenye=new FenyePage();
      						$fenye->pageNow=$pageNow;
      						//�½�SqlHelper����
      						$sqlHelper=new sqlHelper();
      						$sql1="select * from helparticle limit ".($fenye->pageNow-1)*$fenye->pageSize.",".$fenye->pageSize;
      						$sql2="select count(*) from helparticle";
      						$sqlHelper->execute_dql_fenye($sql1, $sql2, $fenye);
      						for($i=0;$i<count($fenye->res_array);$i++){
								$help=$fenye->res_array[$i];
								echo "<tr>";
								echo "<td>$help[helpid]</td><td>$help[helpheader]</td><td>$help[helpcontent]</td><td>$help[helptime]</td><td>$help[commentlist]</td>";
								echo "<td><a class='update' href='./updateHelp.php?updateid=$help[helpid]'>�޸�</a></td>";
								echo "<td><a class='delete' href='javascript:del(".'"'."../Controller/delProcess3.php?delId=$help[helpid]".'"'.")'>ɾ��</a></td>";
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