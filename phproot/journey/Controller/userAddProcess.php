<?php
	require '../Util/SqlHelper.class.php';
	session_start();

	/**
	 * 进行用户添加操作的处理
	 */
	
	//接受参数
	$name=$_POST['name'];
	$username=$_POST['username'];
	$password=$_POST['password'];
	$grades=$_POST['grades'];
	$phone=$_POST['phone'];
	$country=$_POST['country'];
	$email=$_POST['email'];
	
	//md5加密
	$md5_pwd=md5($password);
	
	$sqlHelper=new SqlHelper();
	$sql="insert into user(name,username,password,grades,phone,country,email) values('$name','$username','$md5_pwd','$grades','$phone','$country','$email')";
	$status=$sqlHelper->execute_dml($sql);
	if($status==1){
		$url="../View/userManage.php";
		Header ( "Location: $url" );
	}
	
?>