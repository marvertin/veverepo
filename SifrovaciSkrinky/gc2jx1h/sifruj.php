<?php
  define("SPRAVNE_AAA", "49163791627136");
  define("SPRAVNE_BBB", "31323143413234233211213223323");
  define("SPRAVNE_VVV", "UTOMKEVRCEKARBAROMTENEKAMOSIS");

  session_start();
  ini_set('display_errors',1);
  error_reporting(E_ALL|E_STRICT);
  $kac = $_SESSION['kac'];

?>
<? include "sifrator.php"; ?>
<?php
   $link = mysql_connect("localhost", "geokuk_cz02", "geokukzphp")
      or die("Nelze se pøipojit: $php_errormsg");
   mysql_select_db("geokuk_cz_gc2jx1h", $link);


  function zapis($kac, $aaa, $bbb, $bbbopr, $vvv, $ipadresa) {

    $result = mysql_query("SELECT pocpokusu, blok FROM kacer WHERE kac=$kac");
    if (!$result) {
      die('Could not query:' . mysql_error());
    }
    $blok = mysql_result($result, 0, "blok");
    if (!$blok) {   // muzeme to zapocitat
      $pocpokusu = mysql_result($result, 0, "pocpokusu");
      $pocpokusu ++;
      $result = mysql_query("INSERT INTO pokus (kac, aaa, bbb, bbbopr, vvv, poradi, ipadresa) VALUES ($kac, '$aaa', '$bbb', $bbbopr, '$vvv', $pocpokusu, '$ipadresa')");
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
  $bbbopr = '';

  $vvv = '';
  $chybisko = '';
  $aaapattern = '/^[0-9]+$/';
  $bbbpattern = '/^[1-4]*$/';

  if (! preg_match($aaapattern, $aaa)) 
    $chybisko = "Retezec 'c' smi obsahovat pouze cislice, nejmene jednu.";
  elseif (! preg_match($bbbpattern, $bbb)) 
    $chybisko = "Retezec 'k' smi obsahovat pouze cislice 1 az 4.";
  else {
    $bbbopr = opravKlic($bbb, strlen($aaa) * 5);
    $vvv = sifruj($aaa, $bbbopr);
    $stejnejakminule =   @$_SESSION['poslednipokus'] == "$aaa-$bbb-$bbbopr-$vvv";
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
        $blok = zapis($kac, $aaa, $bbb, $bbbopr, $vvv, $_SERVER['REMOTE_ADDR']);
        if ($blok) {
           $chybisko = "Tvuj ucet byl administratorem zablokovan.";
           $vvv = "";
        }
      }
    }
  }

  $_SESSION['bbbopr'] = $bbbopr;
  $_SESSION['aaa'] = $aaa;
  $_SESSION['bbb'] = $bbb;
  $_SESSION['vvv'] = $vvv;
  $_SESSION['chybisko'] = $chybisko;

header('Location: lusteni.php');
?>
