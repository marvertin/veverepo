<?php
  session_start();
  ini_set('display_errors',1);
  error_reporting(E_ALL|E_STRICT);
  $_SESSION['kac'] = 0;
  $_SESSION['nick'] = "";
  $_SESSION['aaa'] = "";
  $_SESSION['bbb'] = "";
  $_SESSION['vvv'] = "";
  $_SESSION['chybisko'] = "";
  
  header('Location: lusteni.php');
?>
