import Data.List
import Data.Function

import Zdil.CeskySlovnik
import Presmycky.Presmycky

formatuj :: [String] -> String
formatuj xs@(x:_) =  show (length xs) ++ "|" ++ show (length x) ++ " : " ++ intercalate " "  xs

main = do
  sl <- ceskySlovnik
  writeFile "presmycky.txt" (unlines $ map formatuj (presmycky sl))

