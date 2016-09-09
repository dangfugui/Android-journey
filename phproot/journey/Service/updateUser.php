<?php

	/**
	 * 更改用户的信息
	 */
	require '../Util/SqlHelper.class.php';
	require '../Util/JsonHelper.class.php';
header("Content-type: text/html; charset=utf-8");

	$sqlHelper=new SqlHelper();
    $value = $_POST['value'];
    $obj=json_decode($value);

		$userid = $obj->userid;
		$name  = $obj->name;
		$phone  = $obj->phone;
		$country  = $obj->country;
		$introduce  = $obj->introduce;
		$userheader  = $obj->userheader;

		$sql="update user set name = '$name' ,phone = '$phone' ,country = '$country',introduce = '$introduce',userheader = '$userheader' where userid = '$userid'";
		
		$res=$sqlHelper->execute_dml($sql);


    $sql = "select * from `user` where userid = '".$userid."'";
    $res1 = $sqlHelper->execute_dql ( $sql );

    echo JsonHeper::JSON ($res1);
?>