<?php 

$id = $_POST['pid'];


$con=mysqli_connect($db_host, $db_user, $db_password, $db_name);

if(mysqli_connect_error())
{
    echo "Fail to connect DB" . mysqli_connect_error();
}

$query = "select u_index from user where id='$id'";

//echo $query . "<br />";

$result = mysqli_query($con, $query);
$check = mysqli_fetch_array($result);

//print_r($check);

//echo "-------------------------------------- <br />";

$sql = "select room, want_time, start, end, state from reservation where fk_u='$check[0]'";
//echo $sql . "<br />";

$res = mysqli_query($con, $sql);
while($finall = mysqli_fetch_array($res)) {
	echo "$finall[0]&$finall[1]&$finall[2]&$finall[3]&$finall[4]";
	// echo "$finall[0] <br />";
	// echo "$finall[1] <br />";
	// echo "$finall[2] <br />";
	// echo "$finall[3] <br />";
	// echo "$finall[4] <br />";
	//echo "----------------------------<br />";

}

//print_r($finall);


// while($finall = mysqli_fetch_array($res, MYSQL_BOTH)) {
// 	echo "$finall[0] <br />";
// 	echo "$finall[1] <br />";
// 	echo "$finall[2] <br />";
// 	echo "$finall[3] <br />";
// 	echo "$finall[4] <br />";
// 	unset($finall);
// 	echo "----------------------------";
// }
//mysqli_close($con);

mysqli_close($con);

?>