<?php

    /* Connexion */
    include '../connection/connection.php';
        
    $json = file_get_contents_utf8('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'INSERT INTO `photo`
                            (`photo_id`, `photo_name`, `photo_description`, `photo_date`, `photo_link`, `photo_mini_link`, `appareil`, `obturation`, `ouverture`, `longueur_focale`, `vitesse_ISO`, `category_photo`, `active`) VALUES 
                            (null, "' . 
                            encodeToDB($obj->{'photo_name'}) . '", "' . 
                            encodeToDB($obj->{'photo_description'}) . '", "' . 
                            $obj->{'photo_date'} . '", "' . 
                            encodeToDB($obj->{'photo_link'}) . '", "' . 
                            encodeToDB($obj->{'photo_mini_link'}) . '", "' . 
                            encodeToDB($obj->{'appareil'}) . '", "' . 
                            encodeToDB($obj->{'obturation'}) . '", "' . 
                            encodeToDB($obj->{'ouverture'}) . '", "' . 
                            encodeToDB($obj->{'longueur_focale'}) . '", "' . 
                            encodeToDB($obj->{'vitesse_ISO'}) . '", ' . 
                            $obj->{'category_photo'} . ', "' . 
                            $obj->{'active'} . '")'; 

    //print $sql;

    if (mysqli_query($con, $sql)) {
        echo "success";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($con);
    }

    mysqli_close($con);  

?>
