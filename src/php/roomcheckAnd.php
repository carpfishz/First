<?php 


$date = date('Y-m-d', mktime(0,0,0,date("m"),date("d"),date("Y")));


$date=str_replace(".","-",$date);
$date=str_replace("/","-",$date);
$date = date('Y-m-d', strtotime($date));

$con=mysqli_connect($db_host, $db_user, $db_password, $db_name);

if(mysqli_connect_error())
{
    echo "Fail to connect DB" . mysqli_connect_error();
}

$query = "select count(distinct room_num) from room";
$result = mysqli_query($con, $query);
$check = mysqli_fetch_array($result);

//ì¤‘ë³µ ?œê±° ë°©ì˜ ê°?ˆ˜
$num = $check['count(distinct room_num)'];
//echo "$num" . "<br />";

$query1 = "select room_num from room";
$result1 = mysqli_query($con, $query1);

$item = array();

while($check1 = mysqli_fetch_array($result1, MYSQL_BOTH)) {
	array_push($item, $check1[0]);
	// echo "ROOM : " . $check1[0];
	// echo "<br />";
}

//print_r($item);

$x = 0;
$i=0;
// echo "array[0]" . $item[0] . "<br />";
// echo "array[1]" . $item[1] . "<br />";
// echo "array[2]" . $item[2] . "<br />";
// echo "array[3]" . $item[3] . "<br />";

if(isset($_POST['proom'])) {
	$room = $_POST['proom'];
	$sql = "select room, start, end, state from reservation where want_time='$date' and room='$room'";
	$res = mysqli_query($con, $sql);
	if(mysqli_data_seek($res, 0)) {
		while($finall = mysqli_fetch_row($res)) {
			echo "$finall[0] $finall[1] $finall[2] $finall[3]&";
		}
	}


	// $finall = mysqli_fetch_row($res);
	// while($i<=count($finall){
	// 	echo "$finall[i]  $finall[i+1]  $finall[i+2]  $finall[i+3]&";
	// 	i=i+4;
		
	// }
	
	// $room = $_POST['proom'];
	// $sql = "select room, count(CASE room WHEN '$room' THEN 1 END) AS room_1 from reservation where want_time='$date'";
	// $res = mysqli_query($con, $sql);
	// $finall = mysqli_fetch_row($res);
	// echo "$finall[1]&$finall[2]";

}else{
	//$room = '*';
	
	while($x < $num) {
		//$sql = "select room, count(CASE room WHEN '$item[$x]' THEN 1 END) AS room_{$x} from reservation where want_time='$date' and room='$item[$x]'";
		$sql = "select count(CASE room WHEN '$item[$x]' THEN 1 END) AS room_{$x} from reservation where want_time='$date' and room='$item[$x]'";
		// echo $sql . "<br />";
		//mysqli_query($con, $sql);

		$res = mysqli_query($con, $sql);
		$finall = mysqli_fetch_row($res);
		//print_r($finall);
		//echo "$item[$x]" . "<br>######################<br>";
		//echo "$finall[0] $finall[1]&";
		echo "$item[$x] $finall[0]&";
		unset($res);
		unset($finall);
		$x = $x + 1;
		//echo "<br>";
	}

	//echo "success";
}






mysqli_close($con);



?>