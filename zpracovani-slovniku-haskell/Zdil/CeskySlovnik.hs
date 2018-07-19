--
-- Natažený soubor s čeyskm slovníkem
-- 

module Zdil.CeskySlovnik
(
   ceskySlovnik, -- IO akce, která načte cel slovník jako seznam slov
   -- podstatnaJmena -- jen podstatná jména v prvním pádě (není zaručeno, že je to podmnožina slovníku)
   
   ceskySlovnikZakladniTvary -- jen základní tvary jmen a slove.
                             -- Přídavná jména jen v mužském rodě
                             -- Je bez vlastních jmen
) where

import System.IO
import Data.Char

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

vyhazZaLomitkem :: String -> String
vyhazZaLomitkem = takeWhile (/= '/')

ceskySlovnik :: IO [String]
ceskySlovnik = nactiSlovnik "Zdil/Czech.3-2-3.dic"

jeVlastniJmeno :: String -> Bool
jeVlastniJmeno [] = False
jeVlastniJmeno (x:_) = isUpper x

ceskySlovnikZakladniTvary :: IO [String]
ceskySlovnikZakladniTvary = fmap ((fmap vyhazZaLomitkem) . filter (not . jeVlastniJmeno))  $ nactiSlovnik "Zdil/dict-cs-2.0/cs_CZ.dic"



     