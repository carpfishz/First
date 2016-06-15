<?php 

$id = $_POST['pid'];
$pw = $_POST['ppw'];
$major = $_POST['pmajor'];
$name = $_POST['pname'];
$pos = $_POST['ppos'];
$phone= $_POST['pphone'];

$perm=0;

$con=mysqli_connect($db_host, $db_user, $db_password, $db_name);

if(mysqli_connect_error())
{
    echo "Fail to connect DB" . mysqli_connect_error();
}

if( $pos == "student"){
	$perm=3;
	//echo $perm;
} elseif($pos == "admin") {
	$perm=2;
	//echo $perm;
} else {
	$perm=1;
}
$query = "select * from user where id='$id'";
$result = mysqli_query($con, $query);
$row = mysqli_fetch_array($result);

if($row[id] == $id){
	echo "fail";
	mysqli_close($con);

} else {
	$sql = "insert into user values ('', '$id', '$pw', '$major', '$name', '$pos', '$phone', '$perm');";
	mysqli_query($con, $sql);

	echo "success";

	mysqli_close($con);
}



?>


