<?php
    /* Connexion */
    try{
        $con = mysqli_connect('localhost', 'root', '', 'dametenebra');
    }catch (Exception $e){
      die('Erreur : ' . $e->getMessage());
    }

    mysqli_set_charset($con, "utf8");
    //printf("Jeu de caractères courant : %s\n", mysqli_character_set_name($con));

    $response = mysqli_query($con, 'SELECT * FROM photo WHERE category_photo = ' . $_GET['category'] . ' ORDER BY photo_date;');
    $row_count = $response->num_rows;
    $cpt = 1;

    print("[");
    while($r = mysqli_fetch_assoc($response)) {
        
        list($width, $height) = getimagesize("../../../img/full/" . $r['photo_link']);
        $r['photo_width'] = $width;
        $r['photo_height'] = $height;
        
        print json_encode($r);
        if($cpt < $row_count) {
            print(",");
        }
        $cpt++;
    }
    print("]");

    mysqli_close($con);
?>