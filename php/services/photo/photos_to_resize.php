<?php
    /* Connexion */
    include '../connection/connection.php';

    $response = mysqli_query($con, 'SELECT * FROM photo');
    $row_count = $response->num_rows;

	$resize = [];

    while($row = mysqli_fetch_assoc($response)) {
        
        list($width, $height) = getimagesize("../../../img/full/" . $row['photo_link']);
        $row['photo_width'] = $width;
        $row['photo_height'] = $height;
        
        if($width != 600 && $width != 900 || $height != 600 && $height != 900) {
            array_push($resize, $row);
        }
        
    }

	print json_encode($resize);

    mysqli_close($con);
?>
