<?php
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'UPDATE `link` SET 
                                `active`="0" 
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
