<?php
        
    $json = file_get_contents('php://input');
    $obj = json_decode($json);
    
    //var_dump($obj);
    
    $sql = 'INSERT INTO `message`
                                (`id`, `name`, `website`, `email`, `subject`, `message`, `date`) VALUES 
                                    (null, "' . 
                                    $obj->{'name'} . '", "' . 
                                    $obj->{'website'} . '", "' . 
                                    $obj->{'email'} . '", "' . 
                                    $obj->{'subject'} . '", "' . 
                                    $obj->{'message'} . '", "' . 
                                    $obj->{'date'} . '")'; 
    
    //print $sql;

    /* Email */

    $mailToContact = "contact@dametenebra.com"; 
	$mailToError = "dev@dametenebra.com";
	$br = "\n";
	$subject = "Mail from dametenebra.com";
	$subjectError = "Query PROBLEM from dametenebra.com";
	$body = "Pseudo : " . $obj->{'name'} . $br . 
			 "Subject : " . $obj->{'subject'} . $br . 
			 "Site : " . $obj->{'website'} . $br . 
			 "Mail : " . $obj->{'email'} . $br . 
			 "Message : " . $obj->{'message'} . $br;
	$headers = 'From: contact@dametenebra.com' . $br;
    $headers .= 'Reply-To: contact@dametenebra.com' . $br;
    $headers .= 'Content-Type: text/plain; charset="utf-8"' . $br;
    $headers .= 'Content-Transfer-Encoding: 8bit';

    mail($mailToContact, $subject, $body, $headers);

    /* /Email */
    
    /* Connexion */
    include '../connection/connection.php';

    if (mysqli_query($con, $sql)) {
        echo "success";
    } else {
        echo "Error when adding message to db : " . $sql . "<br />" . mysqli_error($con);
    }

    mysqli_close($con);  

?>