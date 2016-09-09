<?php 
	
	/**
	 * �˽ӿ��ṩ�йؾ�����й���Ϣ
	 */


	require '../Util/SqlHelper.class.php';



header("Content-type: text/html; charset=utf-8");
	if(isset($_GET['start'])&&isset($_GET['end'])){
		$startNum=$_GET['start'];
		$endNum=$_GET['end'];
		
		$sqlHelper=new SqlHelper();
		$sql="select * from scenic where scenicid>='$startNum' and scenicid <='$endNum'";
		$res=$sqlHelper->execute_dql($sql);
		
		echo JSON($res);
        JSON($res[0]);
        require '../Util/JsonHelper.class.php';
        echo JsonHeper::JSON($res[0]);
	}


?>