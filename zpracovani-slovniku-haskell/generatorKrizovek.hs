import Data.Char
import System.IO
import GeneratorKrizovek.Krizovky
import Zdil.CeskySlovnik
import Zdil.Soubory

--
-- Program, kter� vygeneruje z dodan�ho slovn�ku �tvercov�
-- k��ovky o zadan�m rozm�ru a vypisuje je na standarn� v�stup
-- 

zpracuj :: [String] -> String
zpracuj text =
  let
    velikost = 6
    slovnik = map (map toUpper) text
    generovanci = generovatKrizovku velikost slovnik
  in
    unlines $ map show generovanci -- pou�ij krShow pro ��dkov� v�pis
  
main = do  
  putStrLn "--- zacinam ---"
  sl <- ceskySlovnik
  --putStr $ zpracuj sl
  ulozSoubor "output/krizovky.txt" (zpracuj sl)
  putStrLn "--- hotovo ---"

