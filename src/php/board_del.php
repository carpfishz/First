<?php 

$num=$_POST['pnum'];
$date = date('Y-m-d', mktime(0,0,0,date("m"),date("d"),date("Y")));


$date=str_replace(".","-",$date);
$date=str_replace("/","-",$date);
$date = date('Y-m-d', strtotime($date));


$con=mysqli_connect($db_host, $db_user, $db_password, $db_name);

if(mysqli_connect_error())
{
    echo "Fail to connect DB" . mysqli_connect_error();
}

$quary = "select * from board where b_index='$num'";
$result = mysqli_query($con, $quary );
$check = mysqli_fetch_array($result);

if(isset($check)){
	$sql = "delete from board where b_index='$num'";
	mysqli_query($con, $sql );
	echo "success";
}else{
	echo "fail";
}

mysqli_close($con);
?>

