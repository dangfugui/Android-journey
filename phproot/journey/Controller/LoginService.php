<?php
/**
 * 处理登陆请求
 * @author yy
 *
 */
	require_once '../Util/SqlHelper.class.php';	

	session_start();

	$username = $_POST ['username'];
	$password = $_POST ['password'];
	$checkCode = $_POST ['checkCode'];
	
	//errorType参数说明   2验证码错误 1用户名或者密码错误
	$url = "../View/admin.php?errorType=";
	if(!($checkCode==$_SESSION['checkCode'])){
		$url.='2';
		Header ( "Location: $url" );
	}
	
	//查询登陆的用户名和密码是否正确
	$sqlHelper=new SqlHelper();
	$md5_pwd=md5($password);
	$sql="select * from adminuser where username='$username' and password='$md5_pwd'";
	$res=$sqlHelper->execute_dql($sql);
	
	if(empty($res)){
		$url.='1';
		Header ( "Location: $url" );
	}else{
		$_SESSION['username']=$username;
		$_SESSION['lasttime']=$res[0]['lasttime'];
		$currentTime=time();
		
		//将当前登陆时间戳添加到数据库中
		$sql1="update adminuser set lasttime='$currentTime' where username='$username'";
		$sqlHelper->execute_dml($sql1);
		$sqlHelper->close_connect();
		
		//页面的跳转
		$locale="../View/userManage.php";
		Header ( "Location: $locale" );
	}
?>