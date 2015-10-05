<?php
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'UPDATE `photo` SET 
                                `photo_name`="' . utf8_encode($obj->{'photo_name'}) . '",
                                `photo_description`="' . utf8_encode($obj->{'photo_description'}) . '",
                                `photo_link`="' . utf8_encode($obj->{'photo_link'}) . '",
                                `photo_mini_link`="' . utf8_encode($obj->{'photo_mini_link'}) . '",
                                `appareil`="' . utf8_encode($obj->{'appareil'}) . '",
                                `obturation`="' . utf8_encode($obj->{'obturation'}) . '",
                                `ouverture`="' . utf8_encode($obj->{'ouverture'}) . '",
                                `longueur_focale`="' . utf8_encode($obj->{'longueur_focale'}) . '",
                                `vitesse_ISO`="' . utf8_encode($obj->{'vitesse_ISO'}) . '",
                                `category_photo`="' . $obj->{'category_photo'} . '",
                                `active`="' . $obj->{'active'} . '" 
            WHERE `photo_id`="' . $obj->{'photo_id'} . '"';

    //print $sql;
    
    /* Connexion */
    include '../connection/connection.php';

    if (mysqli_query($con, $sql)) {
        echo "success";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($con);
    }

    mysqli_close($con);  

?>
