<?php

	/**
	 * �õ������û�����Ϣ
	 *
	 */
require '../Util/SqlHelper.class.php';
require '../Util/JsonHelper.class.php';


//header ( "content-type:text/html; charset=gbk" );
header("Content-type: text/html; charset=utf-8");

$sqlHelper = new SqlHelper ();
$sql = "select * from user";
$res1 = $sqlHelper->execute_dql ( $sql );

echo JsonHeper::JSON ($res1);

?>