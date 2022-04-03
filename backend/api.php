<?php 
$url = "https://lirarate.org/wp-json/lirarate/v2/omt?currency=LBP&_ver=t20224317";

$curl = curl_init($url);
curl_setopt($curl , CURLOPT_URL , $url);
curl_setopt($curl , CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curl);
curl_close($curl);
echo $response;

?>