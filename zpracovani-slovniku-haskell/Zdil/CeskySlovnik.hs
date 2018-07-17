--
-- Natažený soubor s čeyskm slovníkem
-- 

module Zdil.CeskySlovnik
(
   ceskySlovnik, -- IO akce, která načte cel slovník jako seznam slov
   -- podstatnaJmena -- jen podstatná jména v prvním pádě (není zaručeno, že je to podmnožina slovníku)
  
) where

import System.IO

nactiSlovnik :: FilePath -> IO [String]
nactiSlovnik fp = do
    putStr "--- Nacitame slovnik " 
    handle <- openFile fp ReadMode  
    hSetEncoding handle utf8
    contents <- hGetContents handle
    let slova = lines contents 
    putStr $ show $ length slova 
    hClose handle  
    putStrLn " slov. ---"
    return slova


ceskySlovnik :: IO [String]
ceskySlovnik = nactiSlovnik "Zdil/Czech.3-2-3.dic"




     