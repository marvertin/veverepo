<?php
   session_start();
  ini_set('display_errors',1);
  error_reporting(E_ALL|E_STRICT);
  $kac = $_SESSION['kac'];
  $pok = $_GET['pok'];
  $link = mysql_connect("localhost", "geokuk_cz02", "geokukzphp")
      or die("Nelze se pøipojit: $php_errormsg");
  mysql_select_db("geokuk_cz_gc2jx1h", $link);
  mysql_query("DELETE FROM pokus WHERE pok = $pok AND kac = $kac");
  header('Location: lusteni.php');

?>
