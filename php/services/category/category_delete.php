<?php
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'UPDATE `category` SET 
                                `active`="0" 
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
