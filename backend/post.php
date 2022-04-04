<?php

include("db_info.php");

$usd = "usd";
$lbp = "lbp";
$amount = doubleval($_POST["amount"]);
$rate = doubleval($_POST["rate"]);  
$currency = $_POST["currency"];


$query = $mysqli->prepare("INSERT INTO conversions (amount, rate, currency) VALUES (?, ?, ?)");
$query -> bind_param("dds", $amount , $rate , $currency);


$array_response = [];

if (strcmp($currency, $lbp) === 0) { 
    $array_response["result"] = $amount / $rate ; 
    $query->execute();
} else if (strcmp($currency , $usd) === 0 ){ 
    $array_response["result"] = $amount * $rate;
    $query -> execute();     
}

echo json_encode($array_response);

?>