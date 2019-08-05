module Main where

import Lib
import VodniKolo
import Korecek
import Text.Printf


-- seznam = zakladniKorecek ko2 
seznam = concat (zakladniKolo ko2)

main :: IO ()
main = do

 putStrLn $ 
    map (\x -> if x == '.' then ',' else x)  
      ( unlines (  "xxx; yyy" : ( map (\(x,y) -> printf "%8.0f;%8.0f" x y ) $ seznam) ))
