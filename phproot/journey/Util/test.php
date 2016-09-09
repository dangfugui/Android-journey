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
		
	</head>
	<body>
		
	</body>
</html>
<?php
require './FenyePage.class.php';
require './SqlHelper.class.php';

$fenye = new FenyePage ();
$fenye->pageNow = 1;
$sql1 = "select * from user";
$sql2 = "select count(userid) from user";
$sql = new SqlHelper ();
$sql->execute_dql_fenye ( $sql1, $sql2, $fenye );
echo $fenye->navigate;
?>