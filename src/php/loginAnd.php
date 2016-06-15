<?php 

$id = $_POST['id'];
$pw = $_POST['pw'];



$con=mysqli_connect($db_host, $db_user, $db_password, $db_name);

if(mysqli_connect_error())
{
    echo "Fail to connect DB" . mysqli_connect_error();
}

$query = "select * from user where id='$id' and pw='$pw'";
$result = mysqli_query($con, $query);
$check = mysqli_fetch_array($result);

if(isset($check)) {
	echo "success&$check[permission]";
}else{
	echo "fail";
}

mysqli_close($con);
?>


