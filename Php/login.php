 <?php
 
  $con = mysqli_connect("localhost","root","","learning resource center");
// $con = mysqli_connect("mysql.hostinger.in","u932111573_lrc","lrclrc","u932111573_lrc");
 
 $enrollment = $_POST['enrollment'];
  $password = $_POST['password'];
 
 //$enrollment = "14103239"
  //$password = "aaa";
 
 //Creating sql query
 $sql = "SELECT * FROM users WHERE enrollment='$enrollment' AND password='$password'";
  
 //executing query
 $result = mysqli_query($con,$sql);
 
 //fetching result
 $check = mysqli_fetch_array($result);
 
 //if we got some result 
 if(isset($check)){
 //displaying success 
 echo "success";
 }else{
 //displaying failure
 echo "failure";
 }
 mysqli_close($con);
 

 ?>