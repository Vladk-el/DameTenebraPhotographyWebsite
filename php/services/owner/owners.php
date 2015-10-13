<?php
    /* Connexion */
    include '../connection/connection.php';

    $response = mysqli_query($con, 'SELECT * FROM owner');
    $row_count = $response->num_rows;
    $cpt = 1;

    print("[");
    while($r = mysqli_fetch_assoc($response)) {
        if($r != null && $r != "" && strlen(json_encode($r)) > 0 ) {
            
            if(!empty($_GET['display'])) {
                $links = mysqli_query($con, 'SELECT * FROM link WHERE link_owner_id = ' . $r['owner_id'] . ';');  
                $r['links'] = array();
                while($link = mysqli_fetch_array($links)){
                    array_push($r['links'], $link);
                }
            }
                        
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
