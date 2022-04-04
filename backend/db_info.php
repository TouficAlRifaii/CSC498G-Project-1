<?php

$db_host = "localhost";
$db_user = "root"; // I will keep the username and the password as they are because you will
$db_pass = null;   // change them anyway to test the application 
$db_name = "conversiondb";

$mysqli = new mysqli($db_host, $db_user, $db_pass, $db_name);

if(mysqli_connect_errno()){
    die("Conenction Failed!");
}


?> 