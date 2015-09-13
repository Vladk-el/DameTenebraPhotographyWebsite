<?php
    /* Connexion */
    try{
        $con = mysqli_connect('localhost', 'root', '', 'dametenebra');
    }catch (Exception $e){
      die('Erreur : ' . $e->getMessage());
    }

    $response = mysqli_query($con, 'SELECT * FROM category ORDER BY category_name;');
    $row_count = $response->num_rows;
    $cpt = 1;

    print("[");
    while($r = mysqli_fetch_assoc($response)) {
        if($r != null && $r != "" && strlen(json_encode($r)) > 0 ) {
            print json_encode($r);
            if($cpt < $row_count) {
                print(",");
            }   
        }
        $cpt++;
    }
    print("]");

    mysqli_close($con);
?>