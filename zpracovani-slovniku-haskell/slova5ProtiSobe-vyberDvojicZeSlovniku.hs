--
--   Generuje promitivním zpsůobem ze slovníku dvojice 
--   pětipísmenných slov, které mají význam popředu i pozpátku,
--   ale liší se prostředním písmenem 
--
import System.IO
import Data.List
import Data.String
import Data.List.Split
import qualified Data.Set as S
import Slova5ProtiSobe.Obracduo  
import Zdil.CeskySlovnik
import Zdil.Soubory

kombinace2 :: [a] -> [(a,a)]
kombinace2 s = [(x,y) | (x, xi) <- sii, (y, yi) <- sii, xi < yi]
   where sii = zip s [1..]

zpracuj :: [String] -> String
zpracuj text =
  let
    petky = filter ((==5) . length) $ text
    dvojicky = filter match (map obracduox $ kombinace2 petky)
    dvojickyStr = map show dvojicky   
  in
    unlines dvojickyStr 

main = do  
  putStrLn "--- zacinam ---"
  sl <- ceskySlovnik
  ulozSoubor "output/dvojice.txt" (zpracuj sl)
  putStrLn "--- hotovo ---"
    
    