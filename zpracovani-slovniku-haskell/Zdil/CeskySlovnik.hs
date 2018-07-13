--
-- Natažený soubor s čeyskm slovníkem
-- 

module Zdil.CeskySlovnik
(
   ceskySlovnik -- IO akce, která načte cel slovník jako seznam slov
  
) where

import System.IO

ceskySlovnik :: IO [String]
ceskySlovnik = do
    putStr "--- Nacitame slovnik "
    handle <- openFile "Zdil/Czech.3-2-3.dic" ReadMode  
    hSetEncoding handle utf8
    contents <- hGetContents handle
    let slova = lines contents 
    putStr $ show $ length slova 
    hClose handle  
    putStrLn " slov. ---"
    return slova

     