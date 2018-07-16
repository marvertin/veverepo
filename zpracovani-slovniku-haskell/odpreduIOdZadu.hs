import Data.List
import Data.Function
import qualified Data.Set as S

import Zdil.CeskySlovnik
import Zdil.Soubory
                            

filtrujOdpreduIOdZadu :: Ord a => [[a]] -> [[a]]
filtrujOdpreduIOdZadu kmen = filter (flip S.member setkmen . reverse) kmen
  where
    setkmen = S.fromList kmen

main = do
  sl <- ceskySlovnik
  ulozSoubor "output/odpreduIOdZadu.txt" (unlines $ sortBy (flip compare `on` length) (filtrujOdpreduIOdZadu sl))

