<?php

require "init.php";
$fcm_token = $_POST["fcm_token"];

// $sql = "insert into 'fcm_joke' ('fcm_token','joke') values('".fcm_token"',"im jokes hehehe")"
$sql = "insert into fcm_joke values ('".$fcm_token"');" ;
mysql_query($con,$sql);
mysql_close($con);


