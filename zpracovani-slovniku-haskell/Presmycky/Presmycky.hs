
module Presmycky.Presmycky (
  presmycky
) where
           
import Data.List
import Data.Function
import Zdil.CeskySlovnik

presmycky :: Ord a => [[a]] -> [[[a]]]
presmycky  = 
    sortBy porovnani                           -- seřadíme skupiny dle velkosti, nejdříve největší 
  . map (map snd)                       -- odstraníme seřazence, aby zbyla jen původní slova
  . filter (\x -> length x > 1)
  . groupBy ((==) `on` fst)             -- rozdělíme na skupiny podle první z dvojic, tedy seřazené
  . sort                                -- seřadíme dvojice dle první z položek tedy sežazené
  . (map (\x -> (sort x, x)) )          -- vytvoříme dvojice seřazeno neseřazeno ("acijz", "zajic")

  where
     porovnani :: Ord a => [[a]] -> [[a]] -> Ordering
     porovnani xs@(x:_) ys@(y:_) = (length ys `compare` length xs) `mappend`  
                                   (length y `compare` length x) `mappend` 
                                   (xs `compare` ys)

formatuj :: [String] -> String
formatuj xs@(x:_) =  show (length xs) ++ "|" ++ show (length x) ++ " : " ++ intercalate " "  xs

main = do
  sl <- ceskySlovnik
  putStrLn $ unlines $ map formatuj (presmycky sl)

