<?php

    /* Connexion */
    include '../connection/connection.php';
        
    $json = file_get_contents_utf8('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'UPDATE `photo` SET 
                                `photo_name`="' . encodeToDB($obj->{'photo_name'}) . '",
                                `photo_description`="' . encodeToDB($obj->{'photo_description'}) . '",
                                `photo_link`="' . encodeToDB($obj->{'photo_link'}) . '",
                                `photo_mini_link`="' . encodeToDB($obj->{'photo_mini_link'}) . '",
                                `appareil`="' . encodeToDB($obj->{'appareil'}) . '",
                                `obturation`="' . encodeToDB($obj->{'obturation'}) . '",
                                `ouverture`="' . encodeToDB($obj->{'ouverture'}) . '",
                                `longueur_focale`="' . encodeToDB($obj->{'longueur_focale'}) . '",
                                `vitesse_ISO`="' . encodeToDB($obj->{'vitesse_ISO'}) . '",
                                `category_photo`="' . $obj->{'category_photo'} . '",
                                `active`="' . $obj->{'active'} . '" 
            WHERE `photo_id`="' . $obj->{'photo_id'} . '"';

    //print $sql;

    if (mysqli_query($con, $sql)) {
        echo "success";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($con);
    }

    mysqli_close($con);  

?>
