<?php

$zmorse = array (

  ".-"   => 'A',
  "-..." => 'B',
  "-.-." => 'C',
  "-.."  => 'D',
  "."    => 'E',
  "..-." => 'F',
  "--."  => 'G',
  "...." => 'H',
  "----" => 'ch',
  ".."   => 'I',
  ".---" => 'J',
  "-.-" =>  'K',
  ".-.." => 'L',
  "--" =>   'M',
  "-." =>   'N',
  "---" =>  'O',
  ".--." => 'P',
  "--.-" => 'Q',
  ".-." =>  'R',
  "..." =>  'S',
  "-" =>    'T',
  "..-" =>  'U',
  "...-" => 'V',
  ".--" =>  'W',
  "-..-" => 'X',
  "-.--" => 'Y',
  "--.." => 'Z',

  ".-.-" => 'Ä',
  "---." => 'Ö',
  "..--" => 'Ü',
    

);

$morcisla = array (
'-----', '.----', '..---', '...--', '....-', '.....', 
         '-....', '--...', '---..', '----.');


function doMorseCisla($s) {
  global $morcisla;
  $result = '';
  for ($i =0; $i<strlen($s); $i++) {
    $x = $s[$i] + 0;
    $result .= $morcisla[$x];
  }
  return $result;
}

function substituceKazdyDruhy($ss) {
  $s = $ss;
  for ($i =0; $i<strlen($s); $i++) {
    if ($i % 4 >= 2) {
      $s[$i] = $s[$i] == '.' ? '-' : '.';
    }
  }
  return $s;
}

function transpoziceTam($s) {
    $result = "";
    $i = intval((strlen($s) - 1) / 2);
    $krok = 0;
    while ($i >= 0 && $i < strlen($s)) {
      $result .= $s[$i];
      if ($krok <= 0) $krok = - $krok + 1;
                 else $krok = - $krok - 1;
      $i += $krok;
    }
    return $result;
}

function zMorsePismenaDleKlice($ss, $klic) {
  global $zmorse;
  $s = $ss;
  $result = "";

  $i = 0;
  $delka = 0;
  $lens = strlen($s);
  while ($i < $lens) {
    $zbyva = $lens - $i;
    if($zbyva <= 4) {  //už se dávyjádřit jediným znakem
      $delka = $zbyva;
    } else {
      $delka = ($delka + $klic & 3) % 4 + 1;
      $klic = $klic >> 2;
    }
    //echo "$s*$i*-$delka"
    $znak = $zmorse[substr($s, $i, $delka)];
    $result .= $znak;
    $i += $delka;
  } 
  return $result;
}

function sifruj($aaa, $bbb) {
  $morseCisla = doMorseCisla($aaa);
  $morseMezi =  transpoziceTam($morseCisla);
  $morsePismena = substituceKazdyDruhy($morseMezi);
  $result =   zMorsePismenaDleKlice($morsePismena, $bbb);
  return $result;
}

  
function testuj() {  
  $aaa = "14565";
  $bbb = "1289936";                                     
  $vvv = sifruj($aaa, $bbb);
  echo $vvv;
  echo "\n";
  //var_dump ($vvv);
  //echo "\n";
  //var_dump ($mor);
  echo doMorseCisla("0123456789");
  echo "\n";
  echo substituceKazdyDruhy(".....................");
  echo "\n";
  echo substituceKazdyDruhy(".-.-.-.-.-.-.-.-.-.-.");
  echo "\n";
  echo substituceKazdyDruhy("---------------------");
  echo "\n";
  echo transpoziceTam("abcdefghijklmnopqrstuvwxyz");
  echo "\n";
  echo transpoziceTam("abcdefghijklmnopqrstuvwxy");
  echo "\n";
  echo transpoziceTam("a");
  echo "\n";
  echo transpoziceTam("ab");
  echo "\n";
  echo transpoziceTam("abc");
  echo "\n";
  echo zMorsePismenaDleKlice("..--.-.-..--.--.....",3);
  echo "\n";
  echo sifruj("7123747277797901264790", 0xFFFFFFFFFFF);
  echo "\n";
  echo sifruj("46", 127);
}

//testuj();
  
?>
