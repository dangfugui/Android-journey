<?php

	require '../Util/SqlHelper.class.php';

	session_start();
	/**
 	* ������¾�����Ϣ������
 	*/
	//receive the parameter
	$updateid = $_SESSION['updateid'];
	$scenicname=$_POST['scenicname'];
	$scenicdes=$_POST['scenicdes'];
	$commentlist=$_POST['commentlist'];
	$star=$_POST['star'];
	$imgpath=$_POST['imgpath'];
	$locale=$_POST['locale'];
	
	//create sqlHelper ʵ��
	$sqlHelper=new sqlHelper();
	$sql="update scenic set scenicname='$scenicname',scenicdes='$scenicdes',commentlist='$commentlist',star='$star',imgpath='$imgpath',locale='$locale' where scenicid='$updateid'";

	$status=$sqlHelper->execute_dml($sql);
	if($status==1){
		$url="../View/scenicManage.php";
		Header ( "Location: $url" );
	}
	
?>