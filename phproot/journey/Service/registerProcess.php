<?php

require '../Util/sqlHelper.class.php';
header("Content-type: text/html; charset=utf-8");
$sqlHelper=new SqlHelper();


$value = $_POST['value'];

$obj=json_decode($value);

$uname = $obj->username;
$uemail= $obj->email;

$upassword = $obj->password;

$md5_pwd=md5($upassword);

date_default_timezone_set("PRC");
$ctime = date("Y/m/d");	

   $link = mysql_connect($sqlHelper->host,$sqlHelper->username,$sqlHelper->password);
	 mysql_select_db($sqlHelper->dbname,$link);
mysql_query('set names utf8');
	  
$mselect="select * from `user` where username = '".$uname."'";

$res = mysql_query($mselect);

$row   = mysql_num_rows($res);
if(empty($row)){

	$sql="INSERT INTO `user`( username, password, userTime,email) VALUES ('".$uname."','".$md5_pwd."','".$ctime."','".$uemail."');";
	$res = mysql_query($sql);

	printf("ok");
}else{

	printf("no");

}
	  
die();

