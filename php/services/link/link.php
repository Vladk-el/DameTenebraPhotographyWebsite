<?php
    /* Connexion */
    try{
        $con = mysqli_connect('localhost', 'root', '', 'dametenebra');
    }catch (Exception $e){
        die('Erreur : ' . $e->getMessage());
    }

    mysqli_set_charset($con, "utf8");

    $links = mysqli_query($con, 'SELECT * FROM link WHERE link_id = ' . $_GET['link'] . ';');

    while($link = mysqli_fetch_array($links)){
        print json_encode($link);
    }   

    mysqli_close($con);
?>