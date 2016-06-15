<?php 

$id = $_POST['pid'];
$name = $_POST['pname'];
$content = $_POST['pcontent'];


$perm=0;

$con=mysqli_connect($db_host, $db_user, $db_password, $db_name);

if(mysqli_connect_error())
{
    echo "Fail to connect DB" . mysqli_connect_error();
}

$query = "select u_index, id from user where id='$id'";
$result = mysqli_query($con, $query);
$row = mysqli_fetch_array($result);

if($row[1] == $id){
	$sql = "insert into board values ('', '$name', '$content', '$row[0]')";
	mysqli_query($con, $sql);

	echo "success";

	
	mysqli_close($con);

} else {
	echo "fail";
	mysqli_close($con);
}



?>


