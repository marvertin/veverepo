<?php
  define("SPRAVNE_AAA", "49163791627136");
  define("SPRAVNE_BBB", "93541749861");
  define("SPRAVNE_VVV", "IÜUTNÜNAXADMIÖEEGEWVTMUZTMDZ");

  session_start();
  ini_set('display_errors',1);
  error_reporting(E_ALL|E_STRICT);
  $kac = $_SESSION['kac'];

?>
<? include "sifrator.php"; ?>
<?php
   $link = mysql_connect("localhost", "geokuk_cz01", "geokukzphp")
      or die("Nelze se pøipojit: $php_errormsg");
   mysql_select_db("geokuk_cz_gc2h28r", $link);


  function zapis($kac, $aaa, $bbb, $vvv, $ipadresa) {
    
    $result = mysql_query("SELECT pocpokusu, blok FROM kacer WHERE kac=$kac");
    if (!$result) {
      die('Could not query:' . mysql_error());
    }     
    $blok = mysql_result($result, 0, "blok");
    if (!$blok) {   // muzeme to zapocitat
      $pocpokusu = mysql_result($result, 0, "pocpokusu");
      $pocpokusu ++;
      $result = mysql_query("INSERT INTO pokus (kac, aaa, bbb, vvv, poradi, ipadresa) VALUES ($kac, '$aaa', '$bbb', '$vvv', $pocpokusu, '$ipadresa')");
      if (!$result) {
        die('Could not query:' . mysql_error());
      }     
      mysql_query("UPDATE kacer SET pocpokusu = pocpokusu + 1 WHERE kac = $kac");
      if (!$result) {
        die('Could not query:' . mysql_error());
      }     
      if ($aaa == SPRAVNE_AAA) {
        mysql_query("UPDATE kacer SET hovo1time = Now() WHERE kac=$kac and hovo1time is null");
      }
      if ($aaa == SPRAVNE_AAA && $bbb == SPRAVNE_BBB) {
        mysql_query("UPDATE kacer SET hovo2time = Now() WHERE kac=$kac and hovo2time is null");
      }
    }
    return $blok;
  }
  
  function zvedniPocetZbrklounu($kac) {
    mysql_query("UPDATE kacer SET poczbrklych = poczbrklych + 1 WHERE kac=$kac");
    mysql_query("UPDATE kacer SET blok = 1 WHERE poczbrklych > 1000 and kac=$kac");
  }

  //////////////////////////////////////////////////////////////////////////////////
  $aaa = trim($_POST['aaa']);
  $bbb = trim($_POST['bbb']);
  
  $vvv = '';
  $chybisko = '';
  $pattern = '/^[0-9]+$/';
   
  if (preg_match($pattern, $aaa) && preg_match($pattern, $bbb)) {
    $bbb +=0; // udelat cislo
    $vvv = sifruj($aaa, $bbb);
    $stejnejakminule =   @$_SESSION['poslednipokus'] == "$aaa-$bbb-$vvv";
    if (! $stejnejakminule) {
      $casKdySmimPriste = $_SESSION['casKdySmimPriste'];
      $kolikMusimCekat =  $casKdySmimPriste - time();
      if ($kolikMusimCekat > 0) {
        zvedniPocetZbrklounu($kac);
        $chybisko = "Ne tak zhurta, chvili take premyslej nad tim, co ti ze sifrovace leze. Pristi pokus mozny za $kolikMusimCekat sekund.";
        $vvv = "";
      } else { // mohu zapsat sifru
        if ($aaa == SPRAVNE_AAA && $bbb == SPRAVNE_BBB) {
          $vvv = "DOBRYTAKMASVSECOPOTREBUJESGRATULACE";
        }
        $blok = zapis($kac, $aaa, $bbb, $vvv, $_SERVER['REMOTE_ADDR']);
        if ($blok) {
           $chybisko = "Tvuj ucet byl administratorem zablokovan.";
           $vvv = "";
        }
      }
    }
  } else {
    $chybisko = "Vstupni retezce smeji obsahovat pouze cislice.";
  }
  
  $_SESSION['aaa'] = $aaa;
  $_SESSION['bbb'] = $bbb;
  $_SESSION['vvv'] = $vvv;
  $_SESSION['chybisko'] = $chybisko;

header('Location: lusteni.php');
?>
