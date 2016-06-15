<?php 

$id = $_POST['pid'];		//user id
$room = $_POST['proom'];
//$date = $_POST['pdate'];

if(isset($_POST['pdate'])) {
	$date = $_POST['pdate'];
}else{
	$date = date('Y-m-d', mktime(0,0,0,date("m"),date("d"),date("Y")));
}


$con=mysqli_connect($db_host, $db_user, $db_password, $db_name);

if(mysqli_connect_error())
{
    echo "Fail to connect DB" . mysqli_connect_error();
}

$query = "select u_index, name from user where id='$id'";
$result = mysqli_query($con, $query);

$check = mysqli_fetch_array($result);
if(isset($check)) {
	
	//$sql = "insert into board values('', '$title', '$content', '$check[u_index]')";
	$sql = "update reservation set state='Reject' where room='$room' and want_time='$date' and fk_u='$check[u_index]'";
	$res=mysqli_query($con, $sql);
	$results = mysqli_fetch_row($res);
	
	echo "success";
	mysqli_close($con);
} else {
	echo "fail";
	mysqli_close($con);
}

?>