  <?php
  define('HOST','localhost');
  define('USER','root');
  define('PASS',"");
  define('DB','learning resource center');
  $con = mysqli_connect("localhost","root","","learning resource center");
  
  $name = $_POST['name'];
  $email = $_POST['email'];
  $password = $_POST['password'];
  
  $firstname = $_POST['firstname'];
  $lastname = $_POST['lastname'];
  $enrollment = $_POST['enrollment'];
  $password = $_POST['password'];
  $branch = $_POST['branch'];
  $semester = $_POST['semester'];
  $gender = $_POST['gender'];
  $email = $_POST['email'];
  $address = $_POST['address'];
  $pincode = $_POST['pincode'];
  $phone = $_POST['phone'];
  $dateofbirth = $_POST['dateofbirth'];
  
  echo "$password";
  
  $sql = "insert into users (firstname,lastname,enrollment,password,branch,semester,gender,email,address,pincode,phone,dateofbirth) values ('$firstname','$lastname','$enrollment','$password','$branch','$semester','$gender','$email','$address','$pincode','$phone','$dateofbirth');";
  if(mysqli_query($con,$sql)){
    echo 'success';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>