<?php 
$con = mysqli_connect("localhost","root","","learning resource center");
//$con = mysqli_connect("mysql.hostinger.in","u932111573_lrc","lrclrc","u932111573_lrc");
if($_SERVER['REQUEST_METHOD']=='POST')
 {
 //Getting values 
 $enrollment=$_POST['enrollment'];
 $passwordold = $_POST['passwordold'];
 $passwordnew = $_POST['passwordnew'];
 
 //Creating sql query
 $sql = "UPDATE users SET password = '$passwordnew' WHERE enrollment = '$enrollment' AND password='$passwordold'";
 
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