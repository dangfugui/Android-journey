<?php
	
	/**
	 * ����search������Ϣ��Request
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
		$scenicname=$_POST['search'];
		$sqlHelper=new SqlHelper();
		$sql="select * from scenic where scenicname='$scenicname'";
		$res=$sqlHelper->execute_dql($sql);
		//����ѯ���Ķ������session��
		$_SESSION['searchObj']=$res[0];
	
	
	}else{
		$_SESSION['searchObj']=null;
	}
	
	//��ת
	$url="../View/scenicManage.php?selectedId=6";
	Header ( "Location: $url" );

?>