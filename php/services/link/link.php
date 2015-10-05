<?php
    /* Connexion */
    include '../connection/connection.php';

    $links = mysqli_query($con, 'SELECT * FROM link WHERE link_id = ' . $_GET['link'] . ';');

    while($link = mysqli_fetch_array($links)){
        print json_encode($link);
    }   

    mysqli_close($con);
?>