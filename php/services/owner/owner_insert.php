<?php
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    var_dump($obj);
    
    $sql = 'INSERT INTO `owner`(`owner_id`, `owner_name`, `owner_description`, `active`) VALUES (null, "' . $obj->{'owner_name'} . '", "' . $obj->{'owner_description'} . '", "' . $obj->{'active'} . '")'; 

    print $sql;
    
    /* Connexion */
    include '../connection/connection.php';

    if (mysqli_query($con, $sql)) {
        echo "success";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($con);
    }

    mysqli_close($con);   

?>
