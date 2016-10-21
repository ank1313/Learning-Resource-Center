<?php 
 $con = mysqli_connect("localhost","root","","learning resource center");
 if($_SERVER['REQUEST_METHOD']=='GET')
 {
	$string  = $_GET['string'];
	$sql = "SELECT * from availablebooks WHERE title LIKE '"."%".$string."%"."'" . "OR author LIKE '"."%".$string."%"."'" . "OR publisher LIKE '"."%".$string."%"."'" ;
	$r = mysqli_query($con,$sql);
//	$res = mysqli_fetch_array($r);
//	$result = array();
//	array_push($result,array(
	//	"bid"=>$res['bid'],
//		"title"=>$res['title'],)
	//	);
	
	$response=array();
	
	while($row = mysqli_fetch_array($r)){
		$result = array();
//		$result["bid"]=$row["bid"];
		$result["title"]=$row["title"];
		$result["author"]=$row["author"];
		$result["publisher"]=$row["publisher"];
		$result["available"]=$row["available"];
		array_push($response,$result);
	}
//	$result[]['data'] = $row;
 //   }
	if($response==null)
		echo "NO BOOK FOUND.";
	else
		echo json_encode($response);
	//	echo json_encode(array("result"=>$result));
		mysqli_close($con);
	}
?>