<?php 
 $con = mysqli_connect("localhost","root","","learning resource center");
 if($_SERVER['REQUEST_METHOD']=='GET')
 {
	$enrollment  = $_GET['enrollment'];
	$sql = "SELECT * FROM users WHERE enrollment='".$enrollment."'";
	$r = mysqli_query($con,$sql);
	$res = mysqli_fetch_array($r);
	$result = array();
	array_push($result,array(
		"firstname"=>$res['firstname'],
		"lastname"=>$res['lastname'],
		"password"=>$res['password'],
		"enrollment"=>$res['enrollment'],	
		"branch"=>$res['branch'],
		"semester"=>$res['semester'],
		"gender"=>$res['gender'],
		"email"=>$res['email'],
		"address"=>$res['address'],
		"pincode"=>$res['pincode'],
		"phone"=>$res['phone'],
		"dateofbirth"=>$res['dateofbirth'],)
		);
		echo json_encode($result);
		mysqli_close($con);
	}
?>