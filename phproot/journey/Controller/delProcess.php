<?php
	
	//处理删除用户请求
    session_start();
	if(!isset($_SESSION['username'])){
		//跳转
		$url="../View/daolian.html";
		Header ( "Location: $url" );
	}

	require '../Util/SqlHelper.class.php';
	//接收删除id
	if(isset($_GET['delId'])){
		$delId=$_GET['delId'];
		$sqlHelper=new SqlHelper();
		$sql="delete from user where userid=".$delId;
		$res=$sqlHelper->execute_dml($sql);
		
		//跳转
		$url="../View/userManage.php";
		Header ( "Location: $url" );
	}
?>