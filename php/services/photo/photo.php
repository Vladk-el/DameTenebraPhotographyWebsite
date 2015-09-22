<?php
    /* Connexion */
    try{
        $con = mysqli_connect('localhost', 'root', '', 'dametenebra');
    }catch (Exception $e){
      die('Erreur : ' . $e->getMessage());
    }

    mysqli_set_charset($con, "utf8");

    $photos = mysqli_query($con, 'SELECT * FROM photo WHERE photo_id = ' . $_GET['photo'] . ';');

    while($photo = mysqli_fetch_array($photos)){
        print json_encode($photo);
    }   

    mysqli_close($con);
?>