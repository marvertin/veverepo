--
--   Generuje promitivním zpsůobem ze slovníku dvojice 
--   pětipísmenných slov, které mají význam popředu i pozpátku,
--   ale liší se prostředním písmenem 
--
import System.IO
import Data.List
import Data.String
import Zdil.CeskySlovnik
import Zdil.Soubory

jeVan :: Char -> Bool
jeVan x = x == 'v' || x == 'a' || x == 'n'

je3Van :: String -> Bool
je3Van (x : y : z: _) = jeVan x && jeVan y && jeVan z


vyhovuje :: String -> Bool
vyhovuje x 
    | length x < 3 = False
    | not (je3Van x) = False
    | not (je3Van (reverse x)) = False
vyhovuje _ = True

zpracuj :: [String] -> String
zpracuj text =
  let
    vv = filter vyhovuje text
  in
    unlines vv 

main = do  
  putStrLn "--- zacinam ---"
  sl <- ceskySlovnik
  putStrLn (zpracuj sl)
  putStrLn "--- hotovo ---"
    
    