<?php
  ini_set('display_errors',1);
  error_reporting(E_ALL|E_STRICT);
  session_start();

  $kac = $_SESSION['kac'];

if($kac && isset($_POST['nick'])) {
  $nick = $_POST['nick'];

  if ($nick) {
    //echo 'FUNKCE $identifier: "' .  $identifier . '"';
    //echo "\n";
    $link = mysql_connect("localhost", "geokuk_cz02", "geokukzphp")
        or die("Nelze se pøipojit: $php_errormsg");
    mysql_select_db("geokuk_cz_gc2jx1h", $link);
    $sqlNick =  mysql_real_escape_string ($nick, $link);
    $result = mysql_query("UPDATE kacer SET nick = '$sqlNick' WHERE kac=$kac and nick is null");
    if (!$result) {
      die('Could not query:' . mysql_error());
    }
    $_SESSION['nick'] = $nick;
  } else {
    $_SESSION['chybisko'] = 'Nick na gc.com je povinna poloza. Nesmi byt prazdny.';
  }
}
header('Location: lusteni.php');
?>
