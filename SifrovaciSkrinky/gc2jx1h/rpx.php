<?php
session_start();
ini_set('display_errors',1);
error_reporting(E_ALL|E_STRICT);

function provedPrihlaseni($identifier) {

  //echo 'FUNKCE $identifier: "' .  $identifier . '"';
  //echo "\n";
  $link = mysql_connect("localhost", "geokuk_cz02", "geokukzphp")
      or die("Nelze se pøipojit: $php_errormsg");
  mysql_select_db("geokuk_cz_gc2jx1h", $link);
  $result = mysql_query("SELECT kac, nick, admin FROM kacer WHERE openid='$identifier' ORDER BY reftime desc");
  if (!$result) {
    die('Could not query:' . mysql_error());
  }
  if (mysql_num_rows($result) == 0) {
     $result =  mysql_query("INSERT INTO kacer (openid, portime) values ('$identifier', Now())", $link);
     if (!$result) {
       die('Could not query:' . mysql_error());
     }
     $kac = mysql_insert_id();
     $nick = "";
     $admin = 0;
  } else {
     $kac = mysql_result($result, 0, "kac");
     $nick = mysql_result($result, 0, "nick");
     $admin = mysql_result($result, 0, "admin");
  }
  $_SESSION['kac'] = $kac;
  $_SESSION['nick'] = $nick;
  $_SESSION['admin'] = $admin;
  header('Location: lusteni.php');

  //echo "nick: $nick\n";
  //echo "kac: $kac\n";
}

// Below is a very simple PHP 5 script that implements the RPX token URL processing.
// The code below assumes you have the CURL HTTP fetching library.


$rpxApiKey = 'eedbe70a3a6e5c9ae665df9c246c97f6e5b8faad';

if(isset($_POST['token'])) {

  /* STEP 1: Extract token POST parameter */
  $token = $_POST['token'];

  /* STEP 2: Use the token to make the auth_info API call */
  $post_data = array('token' => $_POST['token'],
                     'apiKey' => $rpxApiKey,
                     'format' => 'json');

  $curl = curl_init('https://rpxnow.com/api/v2/auth_info');
  curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
//  curl_setopt($curl, CURLOPT_URL, 'https://rpxnow.com/api/v2/auth_info');
  curl_setopt($curl, CURLOPT_POST, true);
  curl_setopt($curl, CURLOPT_POSTFIELDS, $post_data);
  curl_setopt($curl, CURLOPT_HEADER, false);
  curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
  $raw_json = curl_exec($curl);
  curl_close($curl);

  /* STEP 3: Parse the JSON auth_info response */
  $auth_info = json_decode($raw_json, true);

  if ($auth_info['stat'] == 'ok') {

    /* STEP 3 Continued: Extract the 'identifier' from the response */
    $profile = $auth_info['profile'];
    $identifier = $profile['identifier'];

    if (isset($profile['photo']))  {
      $photo_url = $profile['photo'];
    }

    if (isset($profile['displayName']))  {
      $displayName = $profile['displayName'];
    }

    if (isset($profile['preferredUsername']))  {
      $preferredUsername = $profile['preferredUsername'];
    }

    if (isset($profile['email']))  {
      $email = $profile['email'];
    }

    /* STEP 4: Use the identifier as the unique key to sign the user into your system.
       This will depend on your website implementation, and you should add your own
       code here.
    */
     $_SESSION['displayName'] = "$displayName";

    provedPrihlaseni($identifier);
echo '$identifier: "' .  $identifier . '"';
echo "\n";
echo '$displayName: "' .  $displayName . '"';
echo "\n";
echo '$preferredUsername: "' .  $preferredUsername . '"';
echo "\n";

/* an error occurred */
  } else {
    // gracefully handle the error.  Hook this into your native error handling system.
    echo 'Chybisko: ' . $raw_json;
    echo 'An errorek okuroval: ' . $auth_info['err']['msg'];
  }
}
echo 'Konec';
?>
</pre>



