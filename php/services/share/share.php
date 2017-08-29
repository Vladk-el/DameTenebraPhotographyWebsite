<?php
    /* Connexion */
    include '../connection/connection.php';
    $photos = mysqli_query($con, 'SELECT * FROM photo WHERE photo_id = ' . $_GET['photo'] . ';');
	$photo = mysqli_fetch_array($photos);
	$size = getimagesize("../../../img/full/" . $photo['photo_link']);
	if(!isset($photo) || !$size) {
		header('Location: http://dametenebra.com');
		exit();
	}	
	list($width, $height) = $size;
	$photo['photo_width'] = $width;
	$photo['photo_height'] = $height;
    mysqli_close($con);
	/* Make a redirect if no photo found */
?>

	<html>

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>
			<?php echo $photo['photo_name']; ?>
		</title>
		<meta property="fb:app_id" content="698165167044112" />
		<meta property="og:url" content="http://dametenebra.com/php/services/share/share.php?photo=<?php echo $photo['photo_id']; ?>" />
		<meta property="og:title" content="<?php echo $photo['photo_name']; ?>" />
		<meta property="og:description" content="<?php echo $photo['photo_description']; ?>" />
		<meta property="og:image" content="http://dametenebra.com/img/full/<?php echo rawurlencode($photo['photo_link']); ?>" />
		<meta property="og:image:width" content="<?php echo $photo['photo_width']; ?>" />
		<meta property="og:image:height" content="<?php echo $photo['photo_height']; ?>" />
		<link rel="shortcut icon" href="../../../img/favicon.ico" type="image/x-icon" />
		<link rel="icon" href="../../../img/favicon.ico" type="image/x-icon" />
		<link type="text/css" rel="stylesheet" href="../../../css/dist.css">
	</head>

	<body>

		<div class="row">
			<div class="col-sm-12 text-center">
				<br />
				<h2><?php echo $photo['photo_name']; ?></h2>
				<br />
			</div>
		</div>

		<img class="img-fluid" src="../../../img/full/<?php echo $photo['photo_link']; ?>">

		<div class="row">
			<div class="col-sm-12 text-center">
				<br />
				<a class="btn btn-outline-secondary" href="../../../#/gallery/<?php echo $photo['category_photo']; ?>?photo_id=<?php echo $photo['photo_id']; ?>" role="button">Continue your visit</a>
				<br />
				<br />
			</div>
		</div>
	</body>

	</html>
