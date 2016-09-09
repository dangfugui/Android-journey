<?php

	/**
	 * �õ�һ���˵�ȫ��������Ϣ
	 */

	require '../Util/SqlHelper.class.php';
    require '../Util/JsonHelper.class.php';


header("Content-type: text/html; charset=utf-8");
	if(isset($_GET['userid'])){
		$userid=$_GET['userid'];
		
		$sqlHelper=new SqlHelper();
		$sql="select friendlist from user where userid='$userid'";
		$res1=$sqlHelper->execute_dql($sql);
		
		$str=$res1[0]['friendlist'];
	    $list= preg_split('[,]', $str);  
	    $arr=array();
	    for($i=0;$i<sizeof($list);$i++){
	    	$sql="select * from user where userid ='$list[$i]'";
	    	$res=$sqlHelper->execute_dql($sql);
	    	$arr[$i]=$res[0];
	    }
	    echo JsonHeper::JSON($arr);
	}

?>