<?php 

$id = $_POST['id'];



$con=mysqli_connect($db_host, $db_user, $db_password, $db_name);

if(mysqli_connect_error())
{
    echo "Fail to connect DB" . mysqli_connect_error();
}

$query = "select u_index, name from user where id='$id'";
$result = mysqli_query($con, $query);

$check = mysqli_fetch_array($result);
if(isset($check)) {
	$sql = "delete from reservation where fk_u=$check[u_index]";
	$res=mysqli_query($con, $sql);
	$results = mysqli_fetch_row($res);
	//echo "$results[1]&$results[2]&$results[3]&$results[4]&$check[name]&$results[5]";
	echo "success";
	mysqli_close($con);
} else {
	echo "fail";
	mysqli_close($con);
}

?>