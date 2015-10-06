<?php
    function encodeToDB($str)
    {
        return str_replace('"', "''", utf8_encode($str));
    }
?>
