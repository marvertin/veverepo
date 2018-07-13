import Data.Char
import System.IO
import GeneratorKrizovek.Krizovky
import Zdil.CeskySlovnik
import Zdil.Soubory

--
-- Program, který vygeneruje z dodaného slovníku ètvercové
-- køížovky o zadaném rozmìru a vypisuje je na standarní výstup
-- 

zpracuj :: [String] -> String
zpracuj text =
  let
    velikost = 6
    slovnik = map (map toUpper) text
    generovanci = generovatKrizovku velikost slovnik
  in
    unlines $ map show generovanci -- použij krShow pro øádkový výpis
  
main = do  
  putStrLn "--- zacinam ---"
  sl <- ceskySlovnik
  --putStr $ zpracuj sl
  ulozSoubor "output/krizovky.txt" (zpracuj sl)
  putStrLn "--- hotovo ---"

