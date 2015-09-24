<?php
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'INSERT INTO `link`(`link_id`, `link_name`, `link_link`, `link_owner_id`, `active`) VALUES (null, "' . $obj->{'link_name'} . '", "' . $obj->{'link_link'} . '", ' . $obj->{'link_owner_id'} . ', "' . $obj->{'active'} . '")'; 

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
