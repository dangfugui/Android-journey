<?php

require '../Util/SqlHelper.class.php';
header("Content-type: text/html; charset=utf-8");
$sqlHelper=new SqlHelper();
    
$value = $_POST['value'];
	  
$obj=json_decode($value);
	  
$uname = $obj->username;
	
$upassword = $obj->password;
	
 //  $link = mysql_connect('localhost','root','root'); 
	// mysql_select_db('recommend',$link);
  
$md5_pwd=md5($upassword);
mysql_query('set names utf8');
	  
$mselect="select * from `user` where username = '".$uname."'";
  
$res = mysql_query($mselect);

   
$row   = mysql_num_rows($res); 
if(!empty($row)){

	$mselect="select * from `user` where username = '".$uname."' and password = '".$md5_pwd."'";
  
	$res = mysql_query($mselect);
   
	$row   = mysql_num_rows($res); 
	if(!empty($row)){
		$arr = array();
		 while($row = mysql_fetch_assoc($res)){
			$arr[] = $row;
		 }
		 die(json_encode($arr));
	}else{
		printf("nopass");
	}
}else{

	printf("nouser");
	  
}
	  
die();

