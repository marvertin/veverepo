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

#function substituceKazdyDruhy($ss) {
#  $s = $ss;
#  for ($i =0; $i<strlen($s); $i++) {
#    if ($i % 4 >= 2) {
#      $s[$i] = $s[$i] == '.' ? '-' : '.';
#    }
#  }
#  return $s;
#}

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
  $j = 0;
  $delka = 0;
  $lens = strlen($s);
  $lklic = strlen($klic);
  while ($i < $lens) {
    $zbyva = $lens - $i;
    if ($j == $lklic) $result .= '-';                  
    $delka = $j < $lklic ? $klic[$j] - '0' : nahodne();
    if ($delka < 1 || $delka > 4) $delka = 1;
    //echo "$s*$i*-$delka"
    $znak = $zmorse[substr($s, $i, $delka)];
    $result .= $znak;
    $i += $delka;
    $j ++;
  } 
  return $result;
}

// Poscita cislice 1 az 4, pokud tam bude neco jineho, vrati -1
function poscitejKlic($klic) {
  $suma = 0;
  for ($i =0; $i<strlen($klic); $i++) {
    $delka = $klic[$i] - '0';
    if ($delka < 1 || $delka > 4) return -1;
    $suma += $delka;
  }
  return $suma;
}

function nahodne() {
  $r = rand(0, 9);
  if ($r < 4) return 4;
  if ($r < 7) return 3;
  if ($r < 9) return 2;
  return 1;  
}

function platnychZnakuKlice($klic, $oceksuma) {
  $pocet = 0;
  $zbyva = $oceksuma;;
  for ($i =0; $i<strlen($klic) && $zbyva > 0; $i++) {
    $delka = $klic[$i] - '0';
    if ($delka < 1 || $delka > 4) $delka = 1;
    if ($delka > $zbyva) break;
    $pocet++;
    $zbyva -= $delka;
  }
  return $pocet;
}

function opravKlic($klic, $oceksuma) {
  // pocitame delku klice a urcujeme pozici, kde klic prebyva
  $result = '';
  $zbyva = $oceksuma;;
  for ($i =0; $i<strlen($klic) && $zbyva > 0; $i++) {
    $delka = $klic[$i] - '0';
    if ($delka < 1 || $delka > 4) $delka = 1;
    if ($delka > $zbyva) $delka = $zbyva;
    $result .= ('0' + $delka);
    $zbyva -= $delka;
  }
  $zbyva = $oceksuma - poscitejKlic($klic);
  // kdyz je klic kratsi
  while ($zbyva > 0) {
    $delka = nahodne();
    if ($delka > $zbyva) $delka = $zbyva;
    $result .= ('0' + $delka);
    $zbyva -= $delka;
  }
  return $result;
}


function sifruj($aaa, $bbb) {
  $morseCisla = doMorseCisla($aaa);
  $morsePismena =  transpoziceTam($morseCisla);
  $result =   zMorsePismenaDleKlice($morsePismena, $bbb);
  return $result;
}

  
function testuj() {  
  echo sifruj("55555555555555555", "");
  echo "\n";
  echo sifruj("00", "4444444444444444444444444444444444444444");
  echo "\n";
  
  echo poscitejKlic("12344321");
  echo "\n";
  echo poscitejKlic("12344a321");
  echo "\n";
  echo poscitejKlic("02344321");
  echo "\n";
  echo poscitejKlic("23443215");
  echo "\n";
  echo zMorsePismenaDleKlice("..--.-.-..--.--.....","6421");
  echo "\n";

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
  echo sifruj("7123747277797901264790", "444444444433333333332222222222222222222222");
  echo "\n";
  echo sifruj("46", 127);
  echo "\n";
  echo opravKlic("444", 20);
  echo "\n";
  echo opravKlic("4443333", 50);
  echo "\n";
  echo opravKlic("444444", 110);
  echo "\n";
  echo opravKlic("3333333", 9);
  echo "\n";
  echo platnychZnakuKlice("33211222111114443", 15);
  echo "\n";
}

//testuj();
  
?>
