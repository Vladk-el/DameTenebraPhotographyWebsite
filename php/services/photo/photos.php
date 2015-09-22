<?php
    /* Connexion */
    try{
        $con = mysqli_connect('localhost', 'root', '', 'dametenebra');
    }catch (Exception $e){
      die('Erreur : ' . $e->getMessage());
    }

    mysqli_set_charset($con, "utf8");

    $response = mysqli_query($con, 'SELECT * FROM photo');
    $row_count = $response->num_rows;
    $cpt = 1;

    print("[");
    while($r = mysqli_fetch_assoc($response)) {
        
        print json_encode($r);
        if($cpt < $row_count) {
            print(",");
        }
        $cpt++;
    }
    print("]");

    mysqli_close($con);
?>