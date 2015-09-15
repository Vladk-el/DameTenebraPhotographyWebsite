<?php
    /* Connexion */
    try{
        $con = mysqli_connect('localhost', 'root', '', 'dametenebra');
    }catch (Exception $e){
      die('Erreur : ' . $e->getMessage());
    }

    $categories = mysqli_query($con, 'SELECT * FROM category WHERE category_id = ' . $_GET['category'] . ';');

    while($category = mysqli_fetch_array($categories)){
        print json_encode($category);
    }

    mysqli_close($con);
?>