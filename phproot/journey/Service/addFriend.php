<?php

/**
	 * 添加好友
	 *
	 */
require '../Util/SqlHelper.class.php';
require '../Util/JsonHelper.class.php';
header("Content-type: text/html; charset=utf-8");

if(isset($_GET['userid'])&&isset($_GET['friendid'])){

	$userid=$_GET['userid'];
	$friendid=$_GET['friendid'];

	$sqlHelper = new SqlHelper ();
	$sql = "select friendlist from user where userid = '$userid'";
	$res1 = $sqlHelper->execute_dql ( $sql );
	$fl=$res1[0]['friendlist'];
	/**
	 * 首先判断是否已添加该好友
	 */
	$list= preg_split('[,]', $fl);
	if(in_array($friendid, $list)){
		echo "error2";
		return;
	}else {
        if ($fl == null) {
            $fl = "$friendid";
        } else {
            $fl = $fl.",$friendid";
        }
		$sql = "update user set friendlist = '$fl' where userid = '$userid'";
		$status=$sqlHelper->execute_dml($sql);
		if($status == 1) {
			echo "success";	
		}else{
			echo "error";
		}
		return;
	}
	
}


?>