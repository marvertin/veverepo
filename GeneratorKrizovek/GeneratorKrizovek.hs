import Data.Char
import System.IO
import Krizovky

--
-- Program, kter� vygeneruje z dodan�ho slovn�ku �tvercov�
-- k��ovky o zadan�m rozm�ru a vypisuje je na standarn� v�stup
-- 

zpracuj :: String -> String
zpracuj text =
  let
    velikost = 6
    slovnik = lines $ map toUpper text
    generovanci = generovatKrizovku velikost slovnik
  in
    unlines $ map show generovanci -- pou�ij krShow pro ��dkov� v�pis
  
main = do  
    putStrLn "--- zacinam ---"
    handle <- openFile "Czech.3-2-3.dic" ReadMode  
    hSetEncoding handle utf8
    contents <- hGetContents handle  
    putStr $ zpracuj contents
    hClose handle  
    putStrLn "--- hotovo ---"

