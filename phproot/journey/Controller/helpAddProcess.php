<?php 
	require '../Util/SqlHelper.class.php';
	session_start();
	// �������
	if ($_SESSION ['username'] == null) {
		$url = "./daolian.html";
		Header ( "Location: $url" );
	}
	
	/**
	 * ������������Ӳ����Ĵ���
	 */
	
	//���ܲ���
	$helpheader=$_POST['helpHeader'];
	$helpcontent=$_POST['helpContent'];
	$helptime=$_POST['helpTime'];
	$commentlist=$_POST['commentList'];

	
	$sqlHelper=new SqlHelper();//�½�sqlHelperʵ��
	$sql="insert into helparticle(helpheader,helpcontent,helptime,commentlist) values('$helpheader','$helpcontent','$helptime','$commentlist')";
	$status=$sqlHelper->execute_dml($sql);
	
	
	if($status==1){
		$url="../View/helpArticle.php";
		Header ( "Location: $url" );
	}

?>