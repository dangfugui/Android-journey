<?php

	require '../Util/SqlHelper.class.php';

	session_start();
	/**
 	* ���������������Ϣ������
 	*/
	//receive the parameter
	$updateid = $_SESSION['updateid'];
	$helpheader=$_POST['helpheader'];
	$helpcontent=$_POST['helpcontent'];
	$commentlist=$_POST['commentlist'];
	$helptime=$_POST['helptime'];
	
	//create sqlHelper ʵ��
	$sqlHelper=new sqlHelper();
	$sql="update helparticle set helpheader='$helpheader',helpcontent='$helpcontent',helptime='$helptime',commentlist='$commentlist' where helpid='$updateid'";

	$status=$sqlHelper->execute_dml($sql);
	if($status==1){
		$url="../View/helpArticle.php";
		Header ( "Location: $url" );
	}
	
?>