<?php
    /* Connexion */
    include '../connection/connection.php';

    mysqli_set_charset($con, "utf8");

    $response = mysqli_query($con, 'SELECT * FROM link');
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