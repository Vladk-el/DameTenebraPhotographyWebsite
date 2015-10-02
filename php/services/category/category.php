<?php
    /* Connexion */
    include '../connection/connection.php';

    mysqli_set_charset($con, "utf8");

    $categories = mysqli_query($con, 'SELECT * FROM category WHERE category_id = ' . $_GET['category'] . ';');

    while($category = mysqli_fetch_array($categories)){
        print json_encode($category);
    }   

    mysqli_close($con);
?>