<?php
    /* Connexion */
    include '../connection/connection.php';

    if(!empty($_GET['display'])) {
        $response = mysqli_query($con, 'SELECT * FROM category ORDER BY category_name;');
    } else {
        $response = mysqli_query($con, 'SELECT * FROM category;');
    }
    $row_count = $response->num_rows;
    $cpt = 1;

    print("[");
    while($r = mysqli_fetch_assoc($response)) {
        if($r != null && $r != "" && strlen(json_encode($r)) > 0 ) {
            
            if(!empty($_GET['display'])) {
                $photos = mysqli_query($con, 'SELECT * FROM photo WHERE category_photo = ' . $r['category_id'] . ' ORDER BY photo_date;');
                if($photos->num_rows > 0){
                    $imgs = array();
                    while($img = mysqli_fetch_array($photos)){
                        array_unshift($imgs, $img);
                    }
                }            

                list($width, $height) = getimagesize("../../../img/full/" . $imgs[0]['photo_link']);

                $r['photo_width'] = $width;
                $r['photo_height'] = $height;

                $r['photo_link'] = $imgs[0]['photo_link'];
                
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