<?php

	/**
	 * �õ�һ�����ӵ�ȫ������
	 */

	require '../Util/SqlHelper.class.php';



header("Content-type: text/html; charset=utf-8");
	if(isset($_GET['articleid'])){
		$articleid=$_GET['articleid'];
		
		$sqlHelper=new SqlHelper();
		$sql="select commentlist from article where articleid='$articleid'";
		$res1=$sqlHelper->execute_dql($sql);
		
		$str=$res1[0]['commentlist'];
	    $list= preg_split('[,]', $str);  
	    $arr=array();
	    for($i=0;$i<sizeof($list);$i++){
	    	$sql="select * from comment where commentid ='$list[$i]'";
	    	$res=$sqlHelper->execute_dql($sql);

	    	$arr[$i]=$res[0];
	    }
        require '../Util/JsonHelper.class.php';
        echo JsonHeper::JSON ($arr);

	}

?>