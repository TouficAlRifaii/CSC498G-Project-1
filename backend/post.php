<?php

include("db_info.php");

$rate = doubleval($_POST["rate"]);
$amount = doubleval($_POST["amount"]);
$currency = $_POST["currency"];


$query = $mysqli -> ("INSERT INTO conversions (amount, rate, currency) Values(?, ?, ?)");
$query -> bind_param("dds", $amount , $rate , $currency);

$array_response = [];

if (strcmp($currency, "lbp") === 0) { 
    $array_response["result"] = $amount / $rate ; 
    $query->execute();
} else if (strcmp($currency , "usd") === 0 ){ 
    $array_response["result"] = $amount * $rate ; 
    $query->exceute(); 
}

echo json_encode($array_response);

?>