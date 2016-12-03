<?php
    /* Connexion */
    include '../connection/connection.php';

    $response = mysqli_query($con, 'SELECT * FROM photo');
    $row_count = $response->num_rows;

	$photos = [];

	while($row = mysqli_fetch_assoc($response)) {

        $size = getimagesize("../../../img/full/" . $row['photo_link']);

        if($size) {
            list($width, $height) = $size;
            $row['photo_width'] = $width;
            $row['photo_height'] = $height;
            array_push($photos, $row);
        }

    }

    print json_encode($photos);

    mysqli_close($con);
?>