<?php
    /* Connexion */
    try {
        $con = mysqli_connect('localhost', 'dametenebra', 'dametenebra', 'dametenebra');
        mysqli_set_charset($con, "utf8");
        //printf("Jeu de caractères courant : %s\n", mysqli_character_set_name($con));
    } catch (Exception $e) {
      die('Erreur : ' . $e->getMessage());
    }

    /* Utils */
    include '../utils/utils.php';
?>
