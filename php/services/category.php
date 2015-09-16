<?php
    /* Connexion */
    try{
        $con = mysqli_connect('localhost', 'root', '', 'dametenebra');
    }catch (Exception $e){
      die('Erreur : ' . $e->getMessage());
    }

    mysqli_set_charset($con, "utf8");
    //printf("Jeu de caractères courant : %s\n", mysqli_character_set_name($con));

    $categories = mysqli_query($con, 'SELECT * FROM category WHERE category_id = ' . $_GET['category'] . ';');

    while($category = mysqli_fetch_array($categories)){
        print json_encode($category);
    }   

    mysqli_close($con);
?>