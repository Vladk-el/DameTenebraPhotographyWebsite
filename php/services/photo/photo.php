<?php
    /* Connexion */
    include '../connection/connection.php';

    $photos = mysqli_query($con, 'SELECT * FROM photo WHERE photo_id = ' . $_GET['photo'] . ';');

    while($photo = mysqli_fetch_array($photos)){
        print json_encode($photo);
    }   

    mysqli_close($con);
?>