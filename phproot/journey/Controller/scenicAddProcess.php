<?php 
	require '../Util/SqlHelper.class.php';
	session_start();
	// 检验盗链
	if ($_SESSION ['username'] == null) {
		$url = "./daolian.html";
		Header ( "Location: $url" );
	}
	
	/**
	 * 进行景点添加操作的处理
	 */
	
	//接受参数
	$scenicname=$_POST['scenicname'];
	$scenicdes=$_POST['scenicdes'];
	$commentlist=$_POST['commentlist'];
	$star=$_POST['star'];
	$imgpath=$_POST['imgpath'];
	$locale=$_POST['$locale'];

	
	$sqlHelper=new SqlHelper();//新建sqlHelper实例
	$sql="insert into scenic(scenicname,scenicdes,commentlist,star,imgpath,locale) values('$scenicname','$scenicdes','$commentlist','$star','$imgpath','$locale')";
	$status=$sqlHelper->execute_dml($sql);
	if($status==1){
		$url="../View/scenicManage.php";
		Header ( "Location: $url" );
	}

?>