<?php
	require_once './ValidateCode.class.php';
	$checkImg=new ValidateCode();
	$checkImg->doimg();
	$checkCode=$checkImg->getCode();
	session_start();
	$_SESSION['checkCode']=$checkCode;
?>