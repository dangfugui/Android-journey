<?php
	
	/**
	 * ����search��������Ϣ��Request
	 */
	
	require '../Util/SqlHelper.class.php';

	session_start();
	if(!isset($_SESSION['username'])){
		//��ת
		$url="../View/daolian.html";
		Header ( "Location: $url" );
	}
	
	//���������������
	if(isset($_POST['search'])&&$_POST['search']!=null){
		$header=$_POST['search'];
		$sqlHelper=new SqlHelper();
		$sql="select * from helparticle where helpheader='$header'";
		$res=$sqlHelper->execute_dql($sql);
		//����ѯ���Ķ������session��
		$_SESSION['searchObj']=$res[0];
	
	
	}else{
		$_SESSION['searchObj']=null;
	}
	
	//��ת
	$url="../View/helpArticle.php?selectedId=6";
	Header ( "Location: $url" );

?>