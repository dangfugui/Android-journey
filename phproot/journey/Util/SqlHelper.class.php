<?php
/**
 * ���Ƕ���ݿ�����Ĺ�����
 * @author yy
 *
 */
class SqlHelper {
	public $conn;
	public $dbname = "journey";
	public $username = "root";
	public $password = "dang";
	public $host = "127.0.0.1:3306";

		/*public $dbname = "a0628212034";
		public $username = "a0628212034";
		public $password = "dangnew";
		public $host = "61.146.152.100:3306";*/

	/**
	 * ���췽�������һЩ��ʼ������
	 */
	public function __construct() {
		$this->conn = mysql_connect ( $this->host, $this->username, $this->password );
		
		if (! $this->conn) {
			
			die ( "����ʧ�ܣ�" . mysql_errno () );
		}
		//mysql_query("set names gbk");
        mysql_query("set names 'utf8'");
		mysql_select_db ( $this->dbname );//ѡ��һ��Ҫ��������ݿ�
	}
	
	// ִ��dql���
	public function execute_dql_a($sql) {
		$res = mysql_query ( $sql, $this->conn ) or die ( mysql_error () );
		
		return $res;
	}
	
	// ִ��dql��䣬���ص�������
	public function execute_dql($sql) {
		$arr = array ();
		$res = mysql_query ( $sql, $this->conn ) or die ( mysql_error () );
		// ��res����ݼ����뵽��������ȥ
		while ( $row = mysql_fetch_assoc ( $res ) ) {
			$arr [] = $row;
		}
		mysql_free_result ( $res );
		return $arr;
	}
	
	// ��ҳ������ѯ���
	// $sql1="select * from ... where ... limit 0,6";
	// $sql2="select count(id) from ��";
	public function execute_dql_fenye($sql1, $sql2, $fenyePage) {
		// �����ѯ����Ҫ��ʾ�ķ�ҳ���
		$res = mysql_query ( $sql1 ) or die ( mysql_error () );
		// $ress=>array();
		$arr = array ();
		while ( $row = mysql_fetch_assoc ( $res ) ) {
			$arr [] = $row;
		}
		// ��$arr����$fenyePage
		$fenyePage->res_array = $arr;
		mysql_free_result ( $res );
		$res2 = mysql_query ( $sql2, $this->conn ) or die ( mysql_error () );
		
		if ($row = mysql_fetch_row ( $res2 )) {
			$fenyePage->pageCount = ceil ( $row [0] / $fenyePage->pageSize );
			$fenyePage->rowCount = $row [0];
		}
		$fenyePage->navigate="<ul class='pagination'>";
		// navigate��ҳ��Ϣ��ʼ��
		$page_to = 10;
		if ($fenyePage->pageNow > $fenyePage->pageCount - 10 && $fenyePage->pageNow <= $fenyePage->pageCount)
			$page_to = $fenyePage->pageCount - $fenyePage->pageNow;
		else if ($fenyePage->pageNow > $fenyePage->pageCount)
			$fenyePage->pageNow = $fenyePage->pageCount;
		if ($fenyePage->pageNow > 1) {
			$prepage = $fenyePage->pageNow - 1;
			$fenyePage->navigate .= "<li><a href='?pageNow=$prepage'>��һҳ</a></li>&nbsp";
		}
		if ($fenyePage->pageNow > 10) {
			// ���Ϸ�ʮҳ
			$_big_a = (floor ( ($fenyePage->pageNow - 1) / 10 ) - 1) * 10 + 1;
			$fenyePage->navigate .= "<li><a href='?pageNow=$_big_a'><<</a></li>";
		}
		$start = floor ( ($fenyePage->pageNow - 1) / 10 ) * 10 + 1;
		$index = $start;
		for(; $start < $index + $page_to; $start ++) {
			$fenyePage->navigate .= "<li><a href='?pageNow=$start'>[$start]</a></li>";
		}
		if ($fenyePage->pageNow < $fenyePage->pageCount) {
			// ���·�ʮҳ
			$_big_b = (floor ( ($fenyePage->pageNow - 1) / 10 ) + 1) * 10 + 1;
			$fenyePage->navigate .= "<li><a href='?pageNow=$_big_b'>>></a></li>";
		}
		
		if ($fenyePage->pageNow < $fenyePage->pageCount) {
			$prepage = $fenyePage->pageNow + 1;
			$fenyePage->navigate .= "<li><a href='?pageNow=" . $prepage . "'>��һҳ</a></li>";
		}
		// navigate��ҳ��Ϣ������
		$fenyePage->navigate.="</ul>";
	}
	
	// ִ��dml���
	public function execute_dml($sql) {
		$dml_sql = mysql_query ( $sql, $this->conn );
		if (! $dml_sql) {
			return 0;
		} else {
			if (mysql_affected_rows ( $this->conn ) > 0) {
				return 1; // ��ʾִ�гɹ���
			} else {
				return 2; // ��ʾû�����ܵ�Ӱ��
			}
		}
	}
	public function close_connect() {
		if (! empty ( $this->conn )) {
			mysql_close ( $this->conn );
		}
	}
}
?>