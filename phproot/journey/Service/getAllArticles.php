<?php

/**
	 * 锟矫碉拷一锟斤拷锟剿碉拷锟斤拷锟叫猴拷锟窖的可讹拷锟斤拷志
	 *
	 */
require '../Util/SqlHelper.class.php';
require '../Util/JsonHelper.class.php';

header("Content-type: text/html; charset=utf-8");

if (isset ( $_GET ['userid'] )) {
	
	$userid = $_GET ['userid'];
	
	$sqlHelper = new SqlHelper ();
	$sql = "select friendlist from user where userid = '$userid'";
	$res1 = $sqlHelper->execute_dql ( $sql );
	$fl = $res1 [0] ['friendlist'];
	
	$list = preg_split ( '[,]', $fl );
	$arr = array ();
	
	$count = 0;
	for($i = 0; $i < sizeof ( $list ); $i ++) {
		$sql = "select articlelist from user where userid = '$list[$i]'";
		$res2 = $sqlHelper->execute_dql ( $sql );
		$al = $res2[0]['articlelist'];
		$l2 = preg_split ( '[,]', $al );
		
		for($j = 0; $j < sizeof ( $l2 ); $j ++) {
			$sql = "select * from article where articleid = '$l2[$j]' and articlemode > 0";
			$res3 = $sqlHelper->execute_dql ( $sql );
			if (! empty ( $res3 [0] ))
				$arr [$count ++] = $res3 [0];
		}
	}
	for($i = 0; $i < sizeof ( $arr ) - 1; $i ++) {
		for($j = 0; $j < sizeof ( $arr ) - 1 - $i; $j ++) {
			
			if (strtotime ( $arr [$j] ['sendTime'] ) < strtotime ( $arr [$j + 1] ['sendTime'] )) {
				
				$temp = $arr [$j];
				$arr [$j] = $arr [$j + 1];
				$arr [$j + 1] = $temp;
			}
		}
	}

	echo JsonHeper::JSON($arr);
}

?>