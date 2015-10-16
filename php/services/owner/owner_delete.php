<?php
    
    /* Connexion */
    include '../connection/connection.php';
        
    $json = file_get_contents_utf8('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'UPDATE `owner` SET 
                                `active`="0" 
            WHERE `owner_id`="' . $obj->{'owner_id'} . '"';

    //print $sql;

    if (mysqli_query($con, $sql)) {
        echo "success";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($con);
    }

    mysqli_close($con);  

?>
