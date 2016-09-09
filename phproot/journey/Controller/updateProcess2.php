<?php

	require '../Util/SqlHelper.class.php';

	session_start();
	/**
 	* 处理更新景点信息的请求
 	*/
	//receive the parameter
	$updateid = $_SESSION['updateid'];
	$scenicname=$_POST['scenicname'];
	$scenicdes=$_POST['scenicdes'];
	$commentlist=$_POST['commentlist'];
	$star=$_POST['star'];
	$imgpath=$_POST['imgpath'];
	$locale=$_POST['locale'];
	
	//create sqlHelper 实例
	$sqlHelper=new sqlHelper();
	$sql="update scenic set scenicname='$scenicname',scenicdes='$scenicdes',commentlist='$commentlist',star='$star',imgpath='$imgpath',locale='$locale' where scenicid='$updateid'";

	$status=$sqlHelper->execute_dml($sql);
	if($status==1){
		$url="../View/scenicManage.php";
		Header ( "Location: $url" );
	}
	
?>