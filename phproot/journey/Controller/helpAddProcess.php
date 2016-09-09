<?php 
	require '../Util/SqlHelper.class.php';
	session_start();
	// 检验盗链
	if ($_SESSION ['username'] == null) {
		$url = "./daolian.html";
		Header ( "Location: $url" );
	}
	
	/**
	 * 进行求助帖添加操作的处理
	 */
	
	//接受参数
	$helpheader=$_POST['helpHeader'];
	$helpcontent=$_POST['helpContent'];
	$helptime=$_POST['helpTime'];
	$commentlist=$_POST['commentList'];

	
	$sqlHelper=new SqlHelper();//新建sqlHelper实例
	$sql="insert into helparticle(helpheader,helpcontent,helptime,commentlist) values('$helpheader','$helpcontent','$helptime','$commentlist')";
	$status=$sqlHelper->execute_dml($sql);
	
	
	if($status==1){
		$url="../View/helpArticle.php";
		Header ( "Location: $url" );
	}

?>