<?php

include("db_info.php");

$rate = doubleval($_POST["rate"]);
$amount = doubleval($_POST["amount"]);
$currency = $_POST["currency"];


$query = $mysqli -> ("INSERT INTO conversions (amount, rate, currency) Values(?, ?, ?)");
$query -> bind_param("dds", $amount , $rate , $currency);



?>