<?php
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'INSERT INTO `photo`(`photo_id`, `photo_name`, `photo_description`, `photo_date`, `photo_link`, `photo_mini_link`, `appareil`, `obturation`, `ouverture`, `longueur_focale`, `vitesse_ISO`, `category_photo`) VALUES (null, "' . $obj->{'photo_name'} . '", "' . $obj->{'photo_description'} . '", "' . $obj->{'photo_date'} . '", "' . $obj->{'photo_link'} . '", "' . $obj->{'photo_mini_link'} . '", "' . $obj->{'appareil'} . '", "' . $obj->{'obturation'} . '", "' . $obj->{'ouverture'} . '", "' . $obj->{'longueur_focale'} . '", "' . $obj->{'vitesse_ISO'} . '", ' . $obj->{'category_photo'} . ')'; 

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
