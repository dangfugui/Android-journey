<?php
/**
 * �����½����
 * @author yy
 *
 */
	require_once '../Util/SqlHelper.class.php';	

	session_start();

	$username = $_POST ['username'];
	$password = $_POST ['password'];
	$checkCode = $_POST ['checkCode'];
	
	//errorType����˵��   2��֤����� 1�û��������������
	$url = "../View/admin.php?errorType=";
	if(!($checkCode==$_SESSION['checkCode'])){
		$url.='2';
		Header ( "Location: $url" );
	}
	
	//��ѯ��½���û����������Ƿ���ȷ
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
		
		//����ǰ��½ʱ�����ӵ����ݿ���
		$sql1="update adminuser set lasttime='$currentTime' where username='$username'";
		$sqlHelper->execute_dml($sql1);
		$sqlHelper->close_connect();
		
		//ҳ�����ת
		$locale="../View/userManage.php";
		Header ( "Location: $locale" );
	}
?>