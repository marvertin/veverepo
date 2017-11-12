import Data.Char
import System.IO
import Krizovky

--
-- Program, který vygeneruje z dodaného slovníku ètvercové
-- køížovky o zadaném rozmìru a vypisuje je na standarní výstup
-- 

zpracuj :: String -> String
zpracuj text =
  let
    velikost = 6
    slovnik = lines $ map toUpper text
    generovanci = generovatKrizovku velikost slovnik
  in
    unlines $ map show generovanci -- použij krShow pro øádkový výpis
  
main = do  
    putStrLn "--- zacinam ---"
    handle <- openFile "Czech.3-2-3.dic" ReadMode  
    hSetEncoding handle utf8
    contents <- hGetContents handle  
    putStr $ zpracuj contents
    hClose handle  
    putStrLn "--- hotovo ---"

