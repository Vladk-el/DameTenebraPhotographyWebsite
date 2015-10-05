<?php
    /* Connexion */
    include '../connection/connection.php';

    $response = mysqli_query($con, 'SELECT * FROM photo');
    $row_count = $response->num_rows;
    $cpt = 1;

    print("[");
    while($r = mysqli_fetch_assoc($response)) {
        
        if(!empty($_GET['display'])) {
        
            list($width, $height) = getimagesize("../../../img/full/" . $r['photo_link']);
            $r['photo_width'] = $width;
            $r['photo_height'] = $height;
        
        }
        
        print json_encode($r);
        if($cpt < $row_count) {
            print(",");
        }
        $cpt++;
    }
    print("]");

    mysqli_close($con);
?>