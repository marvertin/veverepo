--
-- Natažený soubor s čeyskm slovníkem
-- 

module Zdil.Soubory
(
   ulozSoubor -- zápis správě do filu v UTF-8 -- IO akce, která načte cel slovník jako seznam slov
  
) where

import System.IO

ulozSoubor :: FilePath -> String -> IO ()
ulozSoubor fileName ss = do
    putStrLn $ "--- Ukladame do souboru " ++ fileName   
    handle <- openFile fileName WriteMode  
    hSetEncoding handle utf8
    hPutStr handle ss
    hClose handle  

     