<?php
header("Content-type: text/html; charset=utf-8");
	move_uploaded_file($_FILES['file']['tmp_name'], "../image/".$_FILES["file"]["name"]);

?>