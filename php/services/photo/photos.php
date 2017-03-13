<?php
    /* Connexion */
    include '../connection/connection.php';

	$sql = 'SELECT * FROM photo';

	if(isset($_GET['home'])) {
		$sql = $sql . ' ORDER BY photo_date DESC LIMIT 50;';
	}

    $response = mysqli_query($con, $sql);
    $row_count = $response->num_rows;

	$photos = [];

	while($row = mysqli_fetch_assoc($response)) {

        $size = getimagesize("../../../img/full/" . $row['photo_link']);
		
        if($size) {
            list($width, $height) = $size;
            $row['photo_width'] = $width;
            $row['photo_height'] = $height;
			if(isset($_GET['home']) && $row['active'] == 1) {
				if($row['photo_width'] > $row['photo_height']) {
					array_push($photos, $row);
					if(count($photos) > 4) {
						break;
					}
				}
			} else {
				array_push($photos, $row);
			}            
        }

    }

    print json_encode($photos);

    mysqli_close($con);
?>
