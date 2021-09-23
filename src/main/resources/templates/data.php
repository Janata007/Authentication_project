<?php
// database connection code
// $con = mysqli_connect('localhost', 'database_user', 'database_password','database');
$con = mysqli_connect('localhost', 'root', 'root','ib_security');

// get the post records
$id = $_POST['ocenaId'];
$ocena = $_POST['ocena'];
$predmet = $_POST['predmet'];
$student_indeks= $_POST['studenIndeks'];

//get indeks id
$sql = "SELECT id FROM student WHERE indeks=$student_indeks";
$result = $con->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $student_id = $row["id"];
    }} else {
    echo "0 results";
}
// database insert SQL code
$sql = "INSERT INTO `ib_security.oceni` (`Id`, `ocena`, `predmet`, `student_id`) VALUES ('$id', '$ocena', '$predmet', '$student_id')";

// insert in database
$rs = mysqli_query($con, $sql);

if($rs)
{
    echo "Ocenka Inserted";
}

?>