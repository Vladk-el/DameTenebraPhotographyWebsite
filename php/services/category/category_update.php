<?php

    /* Connexion */
    include '../connection/connection.php';
        
    $json = file_get_contents_utf8('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'UPDATE `category` SET 
                                `category_name`="' . encodeToDB($obj->{'category_name'}) . '",
                                `category_description`="' . encodeToDB($obj->{'category_description'}) . '",
                                `active`="' . $obj->{'active'} . '" 
            WHERE `category_id`="' . $obj->{'category_id'} . '"';

    //print $sql;

    if (mysqli_query($con, $sql)) {
        echo "success";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($con);
    }

    mysqli_close($con);  

?>
