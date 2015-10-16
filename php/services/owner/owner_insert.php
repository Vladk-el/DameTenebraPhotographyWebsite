<?php

    /* Connexion */
    include '../connection/connection.php';
        
    $json = file_get_contents_utf8('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'INSERT INTO `owner`
                            (`owner_id`, `owner_name`, `owner_description`, `active`) VALUES 
                                (null, "' . 
                                encodeToDB($obj->{'owner_name'}) . '", "' . 
                                encodeToDB($obj->{'owner_description'}) . '", "' . 
                                $obj->{'active'} . '")'; 

    //print $sql;

    if (mysqli_query($con, $sql)) {
        echo "success";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($con);
    }

    mysqli_close($con);   

?>
