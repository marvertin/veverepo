<?php
    session_start();

    define ("POCET_POKUSU_V_OKNE", 25);
    define ("CASOVA_VELIKOST_OKNA", 300);
    $kac = $_SESSION['kac'];
    $nick = $_SESSION['nick'];
    $chybisko = $_SESSION['chybisko'];
    $aaa = $_SESSION['aaa'];
    $bbb = $_SESSION['bbb'];
    $bbbopr = $_SESSION['bbbopr'];
    $vvv = $_SESSION['vvv'];
    $admin = $_SESSION['admin'];
    if (! $admin) $admin = 0;
    $displayName = $_SESSION['displayName'];

    ini_set('display_errors',1);
    error_reporting(E_ALL|E_STRICT);
    $link = mysql_connect("localhost", "geokuk_cz02", "geokukzphp")
        or die("Nelze se připojit: $php_errormsg");
    mysql_select_db("geokuk_cz_gc2jx1h", $link);

    function casek($cas) {
       $rozdil = time() - $cas;
       if (! $cas) {
         return "&nbsp;";
       }
       if ($rozdil > 86400) {
          return StrFTime("%Y-%m-%d %H:%M", $cas);
       } else {
          $hodina = intval($rozdil / 3600);
          $minuta = intval(($rozdil % 3600) / 60);
          $sekunda = $rozdil %  60;
          if ($hodina == 0) {
            if ($minuta == 0) {
              return "-$sekunda sec";
            } else {
              return "-$minuta min";
            }
          } else {
            return sprintf("-%d:%02d hod", $hodina, $minuta);
          }
       }
    }
    
    function rozdily($old, $new) {
      $result = '';
      $i = 0;
      for ($i = 0; $i < strlen($old) && $i <strlen($new); $i++) {
        if ($old[$i] != $new[$i]) break;
        $result .= $old[$i];
      }
      if ($i < strlen($old)) {
        $result .= '<strike class="bbbopr">' . substr($old, $i) . '</strike>';
      }
      if ($i < strlen($new)) {
        $result .= '<u class="bbbopr">' . substr($new, $i) . '</u>';
      }
      return $result;
    }

?>
<head><? include "hlavicka.html"; ?></head>
<body>
<div class="content">
<div class="prvniradek">
  <div class="GC2JX1H">GC2JX1H</div> -
<? if ($kac) { ?>
  <div class="nick"><? echo $nick ?></div>
  <div class="displayName">(<? echo $displayName ?>)</div> -
<? } ?>
<? if (! $kac) { ?>
  <div class="odhlasit"><a href='prihlasit.php'>Prihlasit/registrovat</a></div>
<? } else { ?>
  <div class="odhlasit"><a href='odhlasit.php'><? echo "Odhlasit" ?></a></div>
<? } ?>
</div>
<?php
if ($chybisko) {
   printf ("<div class='chybisko'><i>Mas tam CHYBISKO:</i><div class='textchybiska'>$chybisko</div></div>");
   $chybisko='';
   $_SESSION['chybisko'] = $chybisko;
}
?>
<? if ($kac && ! $nick) { ?>
<div class="nastavitnick">
<form action="nastavnick.php" method="POST">
<div class="upozorneninicku">
Pred odeslanim peclive zkontroluj, ze je tvuj nick <b>presne</b> shodny s nickem
na gc.com, vcetne mezer a velikosti pismen. NICK POZDEJI NELZE ZMENIT!
</div>
  <b>Nick na gc.com:</b>
  <input size="30" type="text" name="nick" />
  <input type="submit" name="cudl" value="Nastavit nick" />
</form>
</div>
<? } ?>
<? if ($kac && $nick) { ?>
<hr>
<div>
<form action="sifruj.php" method="POST">

  c: <input size="20" id="aaa" type="text" name="aaa" autocomplete="off" value="<?php echo $aaa?>" maxlength=20 />
  k: <input size="40" id="bbb" type="text" name="bbb" autocomplete="off" value="<?php echo $bbb?>" maxlength=70 />
  <input type="submit" name="cudl" value="Sifrovat" />
<?php
      if ($vvv) {
         printf ("<div class='vvv'>$vvv</div>");
      }
?>
<?php
      if ($bbb != $bbbopr) {
         printf ("<div class='nespravnebbb'>Nespravne <b>\"k\"</b>, automaticky opraveno: $bbb -> $bbbopr</div>");
      }
?>
</form>
</div>
<hr>
<h4 class="nadpistabulky">Moje posledni pokusy</h4>
<?
      $cisloPoslednihoPokusu = 0;
      $casKdySmimPriste = 0;
      $result = mysql_query("SELECT poradi, aaa, bbb,  vvv,  UNIX_TIMESTAMP(reftime), pok, ipadresa, bbbopr FROM pokus WHERE kac = $kac ORDER BY reftime desc",$link);
      if (!$result) {
        die('Could not query:' . mysql_error());
      }
      printf("<div><table border=1>");
      printf("<tr class='zahlavi'><th class='poradi'>%s</th><th class='aaa'>%s</th><th class='bbb'>%s</th><th class='vvv'>%s</th><th class='reftime'>%s</th><th class='zrus'>%s</th>\n", "por", "c", "k", "p", "cas","&nbsp;");
      if ($admin) {
        printf("<th>IP</th>");
      }
      printf("</tr>\n");
      $cisradku = 0;
      while ($row = mysql_fetch_array($result, MYSQL_NUM)) {
        $poradi = $row[0];
        $aaa = $row[1];
        $bbb = $row[2];
        $vvv = $row[3];
        $reftime = $row[4];
        $pok =  $row[5];
        $ipadresa = $row[6];
        $bbbopr = $row[7];
        if (!$cisloPoslednihoPokusu) {
          $cisloPoslednihoPokusu = $poradi;
          $_SESSION['poslednipokus'] = "$aaa-$bbb-$bbbopr-$vvv";
        }
        if ($casKdySmimPriste == 0 && $poradi + POCET_POKUSU_V_OKNE == $cisloPoslednihoPokusu) {
          $casKdySmimPriste = $reftime + CASOVA_VELIKOST_OKNA;
        }
        $zobrazitRusiciOdkaz =  $reftime + CASOVA_VELIKOST_OKNA < time();
        $sudolich = $cisradku % 2 ? "lichy" : "sudy";
        $bbbhtml =  rozdily($bbb, $bbbopr);
        printf("<tr class='$sudolich'><td class='poradi'>%s</td><td class='aaa'>%s</td><td class='bbb'>%s</td><td class='vvv'>%s</td><td class='reftime'>%s</td><td class='zrus'>%s</td>\n",
         $poradi, $aaa, $bbbhtml , $vvv, casek($reftime), $zobrazitRusiciOdkaz ? "<a href='zruspok.php?pok=$pok'>Zrus</a>" : "-");
        if ($admin) {
          printf("<td>$ipadresa</td>");
        }
        printf("</tr>\n");
        $cisradku++;
      }
      printf("</div></table>");
      $_SESSION['casKdySmimPriste'] = $casKdySmimPriste;

      mysql_free_result($result);
    }
?>


<h4 class="nadpistabulky">Naposled lustici kaceri</h4>
<?php

    $result = mysql_query("SELECT kac, nick,	 UNIX_TIMESTAMP(portime),  UNIX_TIMESTAMP(reftime),	pocpokusu, UNIX_TIMESTAMP(hovo1time), UNIX_TIMESTAMP(hovo2time), admin FROM kacer WHERE nick is not null and admin=0 OR $admin=1 ORDER BY reftime desc", $link);
    if (!$result) {
      die('Could not query:' . mysql_error());
    }
    printf("<table border=1>");
    printf("<tr><th>%s</th><th>%s</th><th>%s</th><th class='pocpokusu'>%s</th>\n", "nick", "poprve", "naposled", "pokusu");
    if ($admin) {
      printf("<th>hovo1</th><th>hovo2</th><th>admin</th>");
    }
    printf("</tr>\n");
    $cisradku = 0;
    while ($row = mysql_fetch_array($result, MYSQL_NUM)) {
      $xkac = $row[0];
      $xnick = $row[1];
      $portime = $row[2];
      $reftime = $row[3];

      $sudolich = $cisradku % 2 ? "lichy" : "sudy";
      printf("<tr class='$sudolich'><td>%s</td><td>%s</td><td>%s</td><td class='pocpokusu'>%s</td>\n",
       $admin ? "<a href='jakoadmin.php?kac=$xkac'>$xnick</a>" :  $xnick,
       casek($portime), casek($reftime), $row[4]);
      if ($admin) {
        printf("<td>%s</td><td>%s</td><td>%s</td>\n", casek($row[5]), casek($row[6]), $row[7] ? "ano" : "ne");
      }
      printf("</tr>\n");
      $cisradku++;
    }
    printf("</table>");

    mysql_free_result($result);

    mysql_close($link);
?>
<? include "paticka.html"; ?>
</div>
</body>
