<?php
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'UPDATE `category` SET 
                                `category_name`="' . utf8_encode($obj->{'category_name'}) . '",
                                `category_description`="' . utf8_encode($obj->{'category_description'}) . '",
                                `active`="' . $obj->{'active'} . '" 
            WHERE `category_id`="' . $obj->{'category_id'} . '"';

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
