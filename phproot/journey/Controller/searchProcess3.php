<?php
	
	/**
	 * 处理search求助帖信息的Request
	 */
	
	require '../Util/SqlHelper.class.php';

	session_start();
	if(!isset($_SESSION['username'])){
		//跳转
		$url="../View/daolian.html";
		Header ( "Location: $url" );
	}
	
	//处理搜索框的请求
	if(isset($_POST['search'])&&$_POST['search']!=null){
		$header=$_POST['search'];
		$sqlHelper=new SqlHelper();
		$sql="select * from helparticle where helpheader='$header'";
		$res=$sqlHelper->execute_dql($sql);
		//将查询到的对象存入session中
		$_SESSION['searchObj']=$res[0];
	
	
	}else{
		$_SESSION['searchObj']=null;
	}
	
	//跳转
	$url="../View/helpArticle.php?selectedId=6";
	Header ( "Location: $url" );

?>