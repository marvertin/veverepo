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
import Obracduo

kombinace2 :: [a] -> [(a,a)]
kombinace2 s = [(x,y) | (x, xi) <- sii, (y, yi) <- sii, xi < yi]
   where sii = zip s [1..]

zpracuj :: String -> String
zpracuj text =
  let
    petky = filter ((==5) . length) . lines $ text
    dvojicky = filter match (map obracduox $ kombinace2 petky)
    dvojickyStr = map show dvojicky   
  in
    unlines dvojickyStr ++ "\n" ++ show (length dvojickyStr)  ++ "\n"

main = do  
    putStrLn "--- zacinam ---"
    handle <- openFile "Czech.3-2-3.dic" ReadMode  
    hSetEncoding handle utf8
    contents <- hGetContents handle  
    putStr $ zpracuj contents
    hClose handle  
    putStrLn "--- hotovo ---"
    
    