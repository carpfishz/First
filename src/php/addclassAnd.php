<?php 

$room = $_POST['proom'];



$con=mysqli_connect($db_host, $db_user, $db_password, $db_name);

if(mysqli_connect_error())
{
    echo "Fail to connect DB" . mysqli_connect_error();
}


if(isset($room)) {
	$query = "insert into room values ('', '$room')";
	mysqli_query($con, $query);

	echo "success";
	mysqli_close($con);
} else {
	echo "fail";
	mysqli_close($con);
}


// $id = $_POST['id'];
// $room = $_POST['proom'];
// $db_host = "chanwoo.hust.net";
// $db_user = "carpfish";
// $db_password = "carpfish";
// $db_name = "test";
// $con=mysqli_connect($db_host, $db_user, $db_password, $db_name);
// if(mysqli_connect_error())
// {
//     echo "Fail to connect DB" . mysqli_connect_error();
// }
// $query = "select permission from user where id='$id'";
// $result = mysqli_query($con, $query);
// $check = mysqli_fetch_array($result);
// if(isset($check[permission]) == 2) {
// 	$sql = "insert into room values ('', '$room')";
// 	mysqli_query($con, $sql);
// 	echo "success";
// 	mysqli_close($con);
// } else {
// 	mysqli_close($con);
// }

?>