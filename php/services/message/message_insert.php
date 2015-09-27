<?php
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    var_dump($obj);
    
    $sql = 'INSERT INTO `message`(`id`, `pseudo`, `site`, `mail`, `subject`, `message`, `date`) VALUES (null, "' . $obj->{'pseudo'} . '", "' . $obj->{'site'} . '", "' . $obj->{'mail'} . '", "' . $obj->{'subject'} . '", "' . $obj->{'message'} . '", "' . $obj->{'date'} . '")'; 

    print $sql;
    
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