<?php
    /* Connexion */
    include '../connection/connection.php';

    $response = mysqli_query($con, 'SELECT * FROM photo WHERE category_photo = ' . $_GET['category'] . ' ORDER BY photo_date DESC;');
    $row_count = $response->num_rows;

	$h = [];
	$v = [];

    while($row = mysqli_fetch_assoc($response)) {
        
        list($width, $height) = getimagesize("../../../img/full/" . $row['photo_link']);
        $row['photo_width'] = $width;
        $row['photo_height'] = $height;
		
		if($width > $height) {
			array_push($h, $row);
		} else {
			array_push($v, $row);
		}
		
    }

	print json_encode(array_merge($h, $v));

    mysqli_close($con);
?>
