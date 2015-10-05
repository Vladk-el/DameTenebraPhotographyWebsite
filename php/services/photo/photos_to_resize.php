<?php
    /* Connexion */
    include '../connection/connection.php';

    $response = mysqli_query($con, 'SELECT * FROM photo');
    $row_count = $response->num_rows;
    $cpt = 1;

    print("[");
    while($r = mysqli_fetch_assoc($response)) {
        
        
        list($width, $height) = getimagesize("../../../img/full/" . $r['photo_link']);
        $r['photo_width'] = $width;
        $r['photo_height'] = $height;
        
        if($width != 600 && $width != 900 || $height != 600 && $height != 900) {
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