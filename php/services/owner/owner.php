<?php
    /* Connexion */
    include '../connection/connection.php';

    $owners = mysqli_query($con, 'SELECT * FROM owner WHERE owner_id = ' . $_GET['owner'] . ';');

    while($owner = mysqli_fetch_array($owners)){
        print json_encode($owner);
    }   

    mysqli_close($con);
?>