<?php 
	//���ܲ���
	session_start();
	if(isset($_GET['exit'])){
		if($_GET['exit']==1){
			session_destroy();
		}
	}

?>
<html>
	<head>
		<title>��̨����ϵͳ</title>
		<meta charset="gbk">
		<link href="../Include/Css/common.css" type="text/css" rel="stylesheet"/>
		<link rel="stylesheet" type="text/css" href="../Include/dist/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../Include/dist/bootstrap-theme.css">
		<link rel="stylesheet" type="text/css" href="../Include/Css/admin.css">
		<script type="text/javascript" src="../Include/dist/bootstrap.min.js"></script>
		<script type="text/javascript" src="../Include/Js/jquery.js"></script>
		<script type="text/javascript">
			function changeImg(change){
				change.src="../Util/CheckCode.php?"+Math.random();
			}
			//��֤��
			function checkForm(){
				var flag1=true;
				var flag2=true;
				var uname=$("#name").val();
				var pwd=$("#pwd").val();
				var checkCode=$("#imgCode").val();
				if(uname==""||uname==null||pwd==null||pwd==""){
					flag1=false;
				}
				if(checkCode==""||checkCode==null){
					flag2=false;
				}
				if(!flag1){
					$("#codeInfo").html("");
					$("#unameInfo").html("�û�������Բ��ܿ�");
				}else{
					if(!flag2){
						$("#unameInfo").html("");
						$("#codeInfo").html("��֤�벻�ܿ�");
					}
				}
				return flag1&&flag2;
			}
		</script>
		
	</head>
	<style>
		span{
			font-size:16px;
			color:red;
		}
	</style>
	<body>
		<div style="margin:180px auto;width:400px;height:240px;">
			<form   onsubmit="return checkForm();" class="form-inline" action="../Controller/LoginService.php" method="POST" role="form">
				<label style="position:relative;left:60px;">��̨�����˺ŵ�½</label>
				<br/>
				<div class="form-group" style="position:relative;top:20px;">
      				<label for="name">�û���:</label>
      				<input type="text" class="form-control" id="name" 
         				placeholder="�������û���" name="username">
   				</div>
   				<span id="unameInfo" style="position:relative;top:17px;"></span>
   				<div class="form-group" style="position:relative;top:30px;">
      				<label for="pwd">��&nbsp;&nbsp;&nbsp;&nbsp;��:</label>
      				<input type="password" class="form-control" id="pwd" 
         				placeholder="����������" name="password">
   				</div>
   				<div class="form-group" style="position:relative;top:40px;">
      				<label for="pwd">��֤��:</label>
      				<input type="text" class="form-control" id="imgCode" 
         				placeholder="��֤��" name="checkCode" size="5">
      				<img id="checkCode" alt="��֤��" src="../Util/CheckCode.php" onclick="changeImg(this)">
   				</div>
   				<span id="codeInfo" style="position:relative;top:40px;left:18px;"></span>
   				<br/>
   				<div class="form-group" style="position:relative;top:50px;left:45px;">
      				<input type="submit" class="btn btn-success">
      				<input type="reset" class="btn btn-info" style="position:relative;left:40px;">
   				</div>
			</form>
		</div>
	</body>
</html>