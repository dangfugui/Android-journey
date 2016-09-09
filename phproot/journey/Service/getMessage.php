<?php 
	
	/**
	 * �˽ӿ��ṩ�йؾ������ʳ�����Ϣ
	 */


	require '../Util/SqlHelper.class.php';
    require '../Util/JsonHelper.class.php';

header("Content-type: text/html; charset=utf-8");
	
	$sqlHelper=new SqlHelper();//�½�sql������ʵ��
	
	if(isset($_GET['type'])) {

        $type = $_GET['type'];
        $all='all';
        if($type==$all){
            $sql = "select * from scenic";
            $res = $sqlHelper->execute_dql($sql);
            echo JsonHeper::JSON($res);
        }else {
            $sql = "select * from scenic where type='$type'";
            $res = $sqlHelper->execute_dql($sql);
            echo JsonHeper::JSON($res);
        }
    }else {
        $sql = "select * from scenic";
        $res = $sqlHelper->execute_dql($sql);
        echo JsonHeper::JSON($res);
    }


		/*if($type=="scenery"){
			//������?
			if(isset($_GET['start'])&&isset($_GET['end'])){
				//���ֲ�ѯ
				$startNum=$_GET['start'];
				$endNum=$_GET['end'];
				$sql="select * from scenic where scenicid>='$startNum' and scenicid <='$endNum'";
				$res=$sqlHelper->execute_dql($sql);


                echo JsonHeper::JSON($res);
			}else if(!isset($_GET['start'])&&!isset($_GET['end'])){
				//ȫ����ѯ
				$sql="select * from scenic";
				$res=$sqlHelper->execute_dql($sql);

                echo JsonHeper::JSON($res);
			}
		}else if($type=="food"){
			//ʳ��Ĳ��?
			if(isset($_GET['start'])&&isset($_GET['end'])){
				//���ֲ�ѯ
				$startNum=$_GET['start'];
				$endNum=$_GET['end'];
				$sql="select * from food where foodid>='$startNum' and foodid <='$endNum'";
				$res=$sqlHelper->execute_dql($sql);

                echo JsonHeper::JSON($res);
			}else if(!isset($_GET['start'])&&!isset($_GET['end'])){
				//ȫ����ѯ
				$sql="select * from food";
				$res=$sqlHelper->execute_dql($sql);

                echo JsonHeper::JSON($res);
			}
		}else if($type=="all"){
			//ʳ��;���?
			if(isset($_GET['start'])&&isset($_GET['end'])){
				//���ֲ�ѯ
				$startNum=$_GET['start'];
				$endNum=$_GET['end'];
				$sql1="select * from scenic where scenicid >= '$startNum' and scenicid<='$endNum'";
				$res1=$sqlHelper->execute_dql($sql1);
				
				$sql2="select * from food where foodid >= '$startNum' and foodid<='$endNum'";
				$res2=$sqlHelper->execute_dql($sql2);
				
				$arr=array();
				for($i=0;$i<sizeof($res1);$i++){
					$arr[$i]=$res1[$i];
				}
				for($j=sizeof($res1);$j<sizeof($res1)+sizeof($res2);$j++){
					$arr[$j]=$res2[$j-sizeof($res1)];
				}

                echo JsonHeper::JSON($arr);
			}else if(!isset($_GET['start'])&&!isset($_GET['end'])){
				//���ֲ�ѯ
				$sql1="select * from scenic ";
				$res1=$sqlHelper->execute_dql($sql1);
				
				$sql2="select * from food";
				$res2=$sqlHelper->execute_dql($sql2);
				
				$arr=array();
				for($i=0;$i<sizeof($res1);$i++){
					$arr[$i]=$res1[$i];
				}
				for($j=sizeof($res1);$j<sizeof($res1)+sizeof($res2);$j++){
					$arr[$j]=$res2[$j-sizeof($res1)];
				}
                echo JsonHeper::JSON($arr);
			}
		}
	}else{
		echo "<h3>�������?/h3>";*/

	
	
	


?>