<?php 
$con = mysqli_connect("localhost","root","","learning resource center");
//$con = mysqli_connect("mysql.hostinger.in","u932111573_lrc","lrclrc","u932111573_lrc");
if($_SERVER['REQUEST_METHOD']=='POST')
 {
 //Getting values 
 $enrollment=$_POST['enrollment'];
 $email = $_POST['email'];
 $address = $_POST['address'];
 $pincode = $_POST['pincode'];
 $phone = $_POST['phone'];
 
// echo "ADSA";
 
 //$enrollment="14103239";
// $email = "pragesh@gmail.com";
// $address = "noida";
// $pincode = "133223";
// $phone = "9888898988";
 
 //Creating sql query 
 $sql = "UPDATE users SET email = '$email', address = '$address', pincode = '$pincode' , phone ='$phone' WHERE enrollment = '$enrollment'";
 
 
 //Updating database table 
 if(mysqli_query($con,$sql)){
 echo 'success';
 }else{
 echo 'failure';
 }
}
 //closing connection 
 mysqli_close($con);
 ?>