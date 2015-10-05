<?php
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'INSERT INTO `link`
                            (`link_id`, `link_name`, `link_link`, `link_owner_id`, `active`) VALUES 
                                (null, "' . 
                                utf8_encode($obj->{'link_name'}) . '", "' . 
                                utf8_encode($obj->{'link_link'}) . '", ' . 
                                $obj->{'link_owner_id'} . ', "' . 
                                $obj->{'active'} . '")'; 

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
