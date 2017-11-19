--
--   Generuje promitivním zpsůobem ze slovníku dvojice 
--   pětipísmenných slov, které mají význam popředu i pozpátku,
--   ale liší se prostředním písmenem 
--
module Obracduo  
( 
  Obracduo (Obracduo),
  obracduo,
  obracduox,
  obracduoFromS,
  match,
  format
) where 



import System.IO
import Data.List
import Data.String
import Data.List.Split
import qualified Data.Set as S

-- typ nesoucí slova jdoucí proti sobě s různým středem a také zvlášť ty středy
-- očekává se, že bude oba řetězce liché a jejich středy budou správně vytaženy
-- třeba  "kecáš" "šálek" 'c' 'l'
data Obracduo = Obracduo (String, String) (Char, Char)   

instance Show Obracduo where
  show (Obracduo (x,y) (a,b)) = [a,b] ++ " | " ++ x ++ " | " ++ y 

obracduoFromS s =   
 let  [_,a,b] = (splitOn "|" . filter (/=' ') $  s)
 in obracduo a b
  
-- vytvářecí funkce provádějící kontroly na stejnost a lichost řetězců
obracduo :: String -> String -> Obracduo 
obracduo x y 
  | not ((odd . length) x && (odd . length) y) = error ("Řetězce nemají lichou délku:  " ++ x ++ " " ++ y)
  | length x /= length y = error ("Řetězce nejsou stejně dlouhé  " ++ x ++ " " ++ y)
  | length x /= 5 = error "Řetězce nemají délu 5"
  | otherwise =
      let stred = length x `div` 2 
      in Obracduo (x, y) (x !! stred, y !! stred)              

obracduox :: (String, String) -> Obracduo
obracduox (x, y) = obracduo x y
 
-- True, pokud oba řetěuce jdou proti sobě a sdtředy jsou různé
match :: Obracduo -> Bool
match (Obracduo ([a1,b1,c1,d1,e1], [a2,b2,c2,d2,e2]) _) = 
  a1 == e2 && e1 == a2 && 
  b1 == d2 && d1 == b2 &&
  c1 /= c2 

format :: [Obracduo] -> String
format xs =  sort (concat . map stredy $ xs) ++ "  " ++ (intercalate " , " . map fofo $ xs)
   where fofo (Obracduo (x, y) _) = x ++ "-" ++ y   
         stredy (Obracduo _ (x, y)) = [x, y]   
