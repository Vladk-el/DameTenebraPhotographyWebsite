<?php
    /* Connexion */
    try{
        $con = mysqli_connect('localhost', 'root', '', 'dametenebra');
    }catch (Exception $e){
      die('Erreur : ' . $e->getMessage());
    }

    mysqli_set_charset($con, "utf8");
    //printf("Jeu de caractères courant : %s\n", mysqli_character_set_name($con));

    $response = mysqli_query($con, 'SELECT * FROM photo WHERE category_photo = ' . $_GET['category'] . ';');

    if($response->num_rows > 0){
        $imgs = array();
        while($img = mysqli_fetch_array($response)){
            array_unshift($imgs, $img);
        }
    }
    
    print json_encode($imgs[count($imgs) - 1]);

    mysqli_close($con);
?>