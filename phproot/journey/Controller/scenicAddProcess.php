<?php 
	require '../Util/SqlHelper.class.php';
	session_start();
	// �������
	if ($_SESSION ['username'] == null) {
		$url = "./daolian.html";
		Header ( "Location: $url" );
	}
	
	/**
	 * ���о�����Ӳ����Ĵ���
	 */
	
	//���ܲ���
	$scenicname=$_POST['scenicname'];
	$scenicdes=$_POST['scenicdes'];
	$commentlist=$_POST['commentlist'];
	$star=$_POST['star'];
	$imgpath=$_POST['imgpath'];
	$locale=$_POST['$locale'];

	
	$sqlHelper=new SqlHelper();//�½�sqlHelperʵ��
	$sql="insert into scenic(scenicname,scenicdes,commentlist,star,imgpath,locale) values('$scenicname','$scenicdes','$commentlist','$star','$imgpath','$locale')";
	$status=$sqlHelper->execute_dml($sql);
	if($status==1){
		$url="../View/scenicManage.php";
		Header ( "Location: $url" );
	}

?>