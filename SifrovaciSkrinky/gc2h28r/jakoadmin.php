<?php
  session_start();
  ini_set('display_errors',1);
  error_reporting(E_ALL|E_STRICT);
  if ($_SESSION['admin'])  {
    $kac = $_GET['kac'];
    
    $link = mysql_connect("localhost", "geokuk_cz01", "geokukzphp")
      or die("Nelze se pøipojit: $php_errormsg");
    mysql_select_db("geokuk_cz_gc2h28r", $link);
    $result = mysql_query("SELECT nick FROM kacer WHERE kac=$kac");
    $nick = mysql_result($result, 0, "nick");
    $_SESSION['kac'] = $kac;
    $_SESSION['nick'] = $nick;
    $_SESSION['aaa'] = "";
    $_SESSION['bbb'] = "";
    $_SESSION['vvv'] = "";
    $_SESSION['chybisko'] = "";
    $_SESSION['displayName'] = "jako admin";
  }
  
  header('Location: lusteni.php');
?>
