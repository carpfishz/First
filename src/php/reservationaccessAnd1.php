<?php 

//$date = $_POST['pdate'];
//echo "test";

$date = date('Y-m-d', mktime(0,0,0,date("m"),date("d"),date("Y")));



$con=mysqli_connect($db_host, $db_user, $db_password, $db_name);

if(mysqli_connect_error())
{
    echo "Fail to connect DB" . mysqli_connect_error();
}
#id, name, room, want_time, start, end, state ??출력
$query = "select reservation.room, reservation.want_time, reservation.start, reservation.end,  user.id , reservation.r_time from reservation inner join user on reservation.fk_u = user.u_index where reservation.want_time='$date' and reservation.state='Wait'";
$result = mysqli_query($con, $query);

$I = 0;
while($check = mysqli_fetch_array($result, MYSQL_BOTH)) {
	//print_r($check);
//	echo "check[0]" . $check[0] . "<br />"; //id
//	echo "check[1]" . $check[1] . "<br />"; //name
//	echo "check[2]" . $check[2] . "<br />"; //room
//	echo "check[3]" . $check[3] . "<br />"; //want_time
//	echo "check[4]" . $check[4] . "<br />"; //start
//	echo "check[5]" . $check[5] . "<br />"; //end
//	echo "check[6]" . $check[6] . "<br />"; //state
	//echo "<br />--------------------------------------------------------------------<br />";
	echo "$check[0]&$check[5]&$check[2]&$check[3]&$check[4]&$check[1]&"; 
	unset($check);
	// echo "check[0]" . $check[$I];
	// echo "<br />";
	// echo "check[1]" . $check[$I+1];
	// echo "<br />";
	// echo "check[2]" . $check[$I+2];
	// echo "<br />";
	// echo "check[3]" . $check[$I+3];
	// echo "<br />";
	// echo "check[4]" . $check[$I+4];
	// echo "<br />";
	// echo "check[5]" . $check[$I+5];
	// echo "<br />";
	// echo "check[6]" . $check[$I+6];
	// echo "<br />";
	// echo "--------------------------------------------------------------------------------";
	// echo "<br />";
	// $I += 7;
}

mysqli_close($con);

//$check = mysqli_fetch_array($result);

//print_r($check);
//$acount = count($check);

// if(isset($check)) {
	
// 	//$sql = "insert into board values('', '$title', '$content', '$check[u_index]')";
// 	//$sql = "update reservation set state='S' where room='$room' and want_time='$date' and fk_u='$check[u_index]'";
// 	//$res=mysqli_query($con, $sql);
// 	//$results = mysqli_fetch_row($res);
// 	for($x=0; $x<$acount;$x+=7){
// 		for($i=x; $i< $acount; $i++){
// 			echo "check[$i]" . $check[$i];
// 		}	
// 	}
// 	echo "success";
// 	mysqli_close($con);
// } else {
// 	echo "fail";
// 	mysqli_close($con);
// }

?>