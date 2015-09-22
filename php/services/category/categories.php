<?php
    /* Connexion */
    try{
        $con = mysqli_connect('localhost', 'root', '', 'dametenebra');
    }catch (Exception $e){
      die('Erreur : ' . $e->getMessage());
    }

    mysqli_set_charset($con, "utf8");
    //printf("Jeu de caractères courant : %s\n", mysqli_character_set_name($con));

    $response = mysqli_query($con, 'SELECT * FROM category ORDER BY category_name;');
    $row_count = $response->num_rows;
    $cpt = 1;

    print("[");
    while($r = mysqli_fetch_assoc($response)) {
        if($r != null && $r != "" && strlen(json_encode($r)) > 0 ) {
            
            if($_GET['display'] != null) {
                $photo = mysqli_query($con, 'SELECT * FROM photo WHERE category_photo = ' . $r['category_id'] . ';');
                if($photo->num_rows > 0){
                    $imgs = array();
                    while($img = mysqli_fetch_array($photo)){
                        array_unshift($imgs, $img);
                    }
                }            

                list($width, $height) = getimagesize("../../../img/full/" . $imgs[count($imgs) - 1]['photo_link']);

                $r['photo_width'] = $width;
                $r['photo_height'] = $height;

                $r['photo_link'] = $imgs[count($imgs) - 1]['photo_link'];
                
            }
            
            print json_encode($r);
            if($cpt < $row_count) {
                print(",");
            }   
        }
        $cpt++;
    }
    print("]");

    mysqli_close($con);
?>