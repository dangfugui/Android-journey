<?php

	require '../Util/SqlHelper.class.php';

	session_start();
	/**
 	* 处理更新求助帖信息的请求
 	*/
	//receive the parameter
	$updateid = $_SESSION['updateid'];
	$helpheader=$_POST['helpheader'];
	$helpcontent=$_POST['helpcontent'];
	$commentlist=$_POST['commentlist'];
	$helptime=$_POST['helptime'];
	
	//create sqlHelper 实例
	$sqlHelper=new sqlHelper();
	$sql="update helparticle set helpheader='$helpheader',helpcontent='$helpcontent',helptime='$helptime',commentlist='$commentlist' where helpid='$updateid'";

	$status=$sqlHelper->execute_dml($sql);
	if($status==1){
		$url="../View/helpArticle.php";
		Header ( "Location: $url" );
	}
	
?>