zacatek2
<?php
echo ini_get('display_errors');

if (!ini_get('display_errors')) {
    ini_set('display_errors', 1);
}

echo ini_get('display_errors');
error_reporting(E_ALL|E_STRICT);
?>


<?php
  $a = 1 + 1;
  $x = 0;
  $y = 5;
  $z = $y / $x;
  $b = $a + 1;
  print "verson: " . phpversion() . "##"; 
?>
konec
