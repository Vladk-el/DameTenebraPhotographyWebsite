<?php
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'UPDATE `owner` SET 
                                `active`="0" 
            WHERE `owner_id`="' . $obj->{'owner_id'} . '"';

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
