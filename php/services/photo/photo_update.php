<?php
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'UPDATE `photo` SET 
                                `photo_name`="' . $obj->{'photo_name'} . '",
                                `photo_description`="' . $obj->{'photo_description'} . '",
                                `photo_link`="' . $obj->{'photo_link'} . '",
                                `photo_mini_link`="' . $obj->{'photo_mini_link'} . '",
                                `appareil`="' . $obj->{'appareil'} . '",
                                `obturation`="' . $obj->{'obturation'} . '",
                                `ouverture`="' . $obj->{'ouverture'} . '",
                                `longueur_focale`="' . $obj->{'longueur_focale'} . '",
                                `vitesse_ISO`="' . $obj->{'vitesse_ISO'} . '",
                                `category_photo`="' . $obj->{'category_photo'} . '" 
            WHERE `photo_id`="' . $obj->{'photo_id'} . '"';

    //print $sql;
    
    try{
        $con = mysqli_connect('localhost', 'root', '', 'dametenebra');
        mysqli_set_charset($con, "utf8");
    }catch (Exception $e){
        die('Erreur : ' . $e->getMessage());
    }

    if (mysqli_query($con, $sql)) {
        echo "success";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($con);
    }

    mysqli_close($con);  

?>
