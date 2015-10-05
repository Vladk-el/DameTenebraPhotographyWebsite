<?php
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    var_dump($obj);
    
    $sql = 'INSERT INTO `category`
                                (`category_id`, `category_name`, `category_description`, `category_date`, `active`) VALUES 
                                    (null, "' . 
                                    utf8_encode($obj->{'category_name'}) . '", "' . 
                                    utf8_encode($obj->{'category_description'}) . '", "' . 
                                    $obj->{'category_date'} . '", "' . 
                                    $obj->{'active'} . '")'; 

    //print $sql;
    
    /* Connexion */
    include '../connection/connection.php';

    print $sql;

    if (mysqli_query($con, $sql)) {
        echo "success";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($con);
    }

    mysqli_close($con);

?>
