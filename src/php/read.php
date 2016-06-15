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

$sql = "select b_index, name, content from board where b_index='$num'";
$res = mysqli_query($con, $sql);
if(mysqli_data_seek($res, 0)) {
	while($finall = mysqli_fetch_row($res)) {
		echo "$finall[0]&$finall[1]&$finall[2]";
	}
}


mysqli_close($con);



?>