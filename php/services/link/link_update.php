<?php

    /* Connexion */
    include '../connection/connection.php';
        
    $json = file_get_contents_utf8('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'UPDATE `link` SET 
                                `link_name`="' . encodeToDB($obj->{'link_name'}) . '",
                                `link_link`="' . encodeToDB($obj->{'link_link'}) . '",
                                `link_owner_id`="' . $obj->{'link_owner_id'} . '",
                                `active`="' . $obj->{'active'} . '" 
            WHERE `link_id`="' . $obj->{'link_id'} . '"';

    //print $sql;

    if (mysqli_query($con, $sql)) {
        echo "success";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($con);
    }

    mysqli_close($con);  

?>
