<?php

	/**
	 * ��Ӻ���?
	 * 
	 */
require '../Util/SqlHelper.class.php';


header("Content-type: text/html; charset=utf-8");

if(isset($_GET['userid'])&&isset($_GET['articlemode'])&&isset($_GET['contents'])&&isset($_GET['header'])&&isset($_GET['image'])){
	
	$userid=$_GET['userid'];
	$articlemode=$_GET['articlemode'];
	$contents=$_GET['contents'];
	$header=$_GET['header'];
	$image=$_GET['image'];
	$date=date("Y-m-d",time());
    echo "dang".$userid.$articlemode.$contents.$header.$image.$date;
	$sqlHelper = new SqlHelper ();

	$sql = "insert into article (contents,header,sendTime,articlemode,image) values('$contents','$header','$date','$articlemode','$image')";
	$s1=$sqlHelper->execute_dml($sql);
	
	
	$sql = "select * from article where sendTime = '$date' and header = '$header' and articlemode = '$articlemode'";
	$res1 = $sqlHelper->execute_dql($sql);
	$aid=$res1[0]['articleid'];
	$articleid=$res1[0]['articleid'];
	
	$sql = "select * from user where userid = '$userid'";
	$fl= $sqlHelper->execute_dql($sql);
	$fl=$fl[0]['articlelist'];
    if(!($aid==null||$aid=="")){
        if(Sfl==null||$fl==""){
            $fl.="$aid";
        }else{
            $fl.=",$aid";
        }
    }
	$sql = "update user set articlelist = '$fl' where userid = '$userid'";
	$s2=$sqlHelper->execute_dml($sql);


    $sql = "select * from user where userid = '$userid'";
    $resuser = $sqlHelper->execute_dql($sql);
    $userheader=$resuser[0]['userheader'];
	$name=$resuser[0]['name'];

    $sql = "update article set username = '$name', userimage  = '$userheader' where articleid = '$articleid'";
    $s2=$sqlHelper->execute_dml($sql);
	if($s1==1&&$s2==1) {
		echo "success";
		return;
	}else{
		echo "error";
		return;
	}
	
}


?>