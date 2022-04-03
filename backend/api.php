<?php 
$url = "https://lirarate.org/wp-json/lirarate/v2/omt?currency=LBP&_ver=t20224320";

$curl = curl_init($url);
curl_setopt($curl , CURLOPT_URL , $url);
curl_setopt($curl , CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curl);

curl_close($curl);
$stdClass = (json_decode($response));
$arr = json_decode(json_encode($stdClass) , true) ;
$rate = [] ;
$rate["rate"] = $arr['omt'][count($arr['omt']) -1][1]; 

$json_rate = json_encode($rate);
echo $json_rate;

?>