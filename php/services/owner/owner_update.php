<?php

    /* Connexion */
    include '../connection/connection.php';
        
    $json = file_get_contents_utf8('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'UPDATE `owner` SET 
                                `owner_name`="' . encodeToDB($obj->{'owner_name'}) . '",
                                `owner_description`="' . encodeToDB($obj->{'owner_description'}) . '",
                                `active`="' . $obj->{'active'} . '" 
            WHERE `owner_id`="' . $obj->{'owner_id'} . '"';

    //print $sql;

    if (mysqli_query($con, $sql)) {
        echo "success";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($con);
    }

    mysqli_close($con);  

?>
