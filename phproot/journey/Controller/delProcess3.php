<?php

	/**
	 * 处理景点删除请求
	 */
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
		$sql="delete from helparticle where helpid=".$delId;
		$res=$sqlHelper->execute_dml($sql);

		//跳转
		$url="../View/helpArticle.php";
		Header ( "Location: $url" );
	}

?>