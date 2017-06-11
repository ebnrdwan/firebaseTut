


<?php

$host = "localhost";
$dp_user ="root";
$dp_password="";
$dp_name="fcmtut";
$con = mysqli_connect($host,$dp_user,$dp_password,$dp_name);

if ($con) {
	
	echo "connection success";
}
else{

	echo "failed to connect";
}
?>
