<?php

	require '../Util/SqlHelper.class.php';

	session_start();
	/**
 	* ��������û���Ϣ������
 	*/
	//receive the parameter
	$updateid = $_SESSION['updateid'];
	$uname=$_POST['uname'];
	$friendlist=$_POST['friendList'];
	$grades=$_POST['grades'];
	$articlelist=$_POST['articlelist'];
	$phone=$_POST['phone'];
	$country=$_POST['country'];
	$email=$_POST['email'];
	
	//create sqlHelper ʵ��
	$sqlHelper=new sqlHelper();
	$sql="update user set name='$uname',friendlist='$friendlist',grades='$grades',articlelist='$articlelist',phone='$phone',country='$country',email='$email' where userid='$updateid'";

	$status=$sqlHelper->execute_dml($sql);
	if($status==1){
		$url="../View/userManage.php";
		Header ( "Location: $url" );
	}
	
?>