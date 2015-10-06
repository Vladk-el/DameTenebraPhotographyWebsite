<?php

    /* Connexion */
    include '../connection/connection.php';
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'INSERT INTO `link`
                            (`link_id`, `link_name`, `link_link`, `link_owner_id`, `active`) VALUES 
                                (null, "' . 
                                encodeToDB($obj->{'link_name'}) . '", "' . 
                                encodeToDB($obj->{'link_link'}) . '", ' . 
                                $obj->{'link_owner_id'} . ', "' . 
                                $obj->{'active'} . '")'; 

    //print $sql;

    if (mysqli_query($con, $sql)) {
        echo "success";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($con);
    }

    mysqli_close($con);  

?>
