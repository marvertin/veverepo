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


type PouziteZnaky = S.Set Char

inserto :: Obracduo -> PouziteZnaky -> PouziteZnaky
inserto (Obracduo _ (a,b)) = S.insert a . S.insert b

jePouzit :: Obracduo -> PouziteZnaky -> Bool
jePouzit (Obracduo _ (a,b)) pouz = a `S.member` pouz || b `S.member` pouz  
  
kombo :: Int -> PouziteZnaky -> [Obracduo] -> [[Obracduo]]     
kombo 12 _ _  = [[]]
kombo level pouzitci obra = 
     concatMap kom1 (tails obra)
  where 
    kom1 :: [Obracduo] -> [[Obracduo]]
    kom1 [] = []
    kom1 (x:_) | jePouzit x pouzitci = []
    kom1 (x:xs) = map (x:) $ kombo (level+1) (inserto x pouzitci) xs 
    
kombinuj :: String -> String
kombinuj text =
  let 
    odes = map obracduoFromS (lines text)
    vysledek = kombo 0 S.empty odes
  in unlines . map format $ vysledek 

main = do
    putStrLn "--- nacitame S---"
    handle <- openFile "output/dvojicex.txt" ReadMode  
    hSetEncoding handle utf8
    contents <- hGetContents handle  
    putStr $ kombinuj contents
    hClose handle  
    putStrLn "--- hotovo S---"
    