<?php 

$id = $_POST['id'];
$room = $_POST['proom'];
$starttime = $_POST['pstarttime'];
$endtime = $_POST['pendtime'];
$date = $_POST['restade'];
//if(isset($_POST['pdate'])) {
//	$date = $_POST['pdate'];
//}else{
//	$date = date('Y-m-d', mktime(0,0,0,date("m"),date("d"),date("Y")));
//}

//$date=str_replace(".","-",$date);
//$date=str_replace("/","-",$date);
//$date = date('Y-m-d', strtotime($date));






$con=mysqli_connect($db_host, $db_user, $db_password, $db_name);

if(mysqli_connect_error())
{
    echo "Fail to connect DB" . mysqli_connect_error();
}

$query = "select u_index from user where id='$id'";
$result = mysqli_query($con, $query);
$check = mysqli_fetch_array($result);
if(isset($check)) {
	$sql = "insert into reservation values ('', '$room', '$date', '$starttime', '$endtime', 'Wait', '$check[u_index]', now())";
	mysqli_query($con, $sql);

	echo "success";

	mysqli_close($con);
} else {
	echo "fail";
	mysqli_close($con);
}

?>