<?php
	
	//����ɾ���û�����
    session_start();
	if(!isset($_SESSION['username'])){
		//��ת
		$url="../View/daolian.html";
		Header ( "Location: $url" );
	}

	require '../Util/SqlHelper.class.php';
	//����ɾ��id
	if(isset($_GET['delId'])){
		$delId=$_GET['delId'];
		$sqlHelper=new SqlHelper();
		$sql="delete from user where userid=".$delId;
		$res=$sqlHelper->execute_dml($sql);
		
		//��ת
		$url="../View/userManage.php";
		Header ( "Location: $url" );
	}
?>