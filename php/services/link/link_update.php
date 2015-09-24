<?php
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'UPDATE `link` SET 
                                `link_name`="' . $obj->{'link_name'} . '",
                                `link_link`="' . $obj->{'link_link'} . '",
                                `link_owner_id`="' . $obj->{'link_owner_id'} . '",
                                `active`="' . $obj->{'active'} . '" 
            WHERE `link_id`="' . $obj->{'link_id'} . '"';

    //print $sql;
    
    try{
        $con = mysqli_connect('localhost', 'root', '', 'dametenebra');
        mysqli_set_charset($con, "utf8");
    }catch (Exception $e){
        die('Erreur : ' . $e->getMessage());
    }

    if (mysqli_query($con, $sql)) {
        echo "success";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($con);
    }

    mysqli_close($con);  

?>
