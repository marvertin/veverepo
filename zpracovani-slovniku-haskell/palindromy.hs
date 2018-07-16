import Data.List
import Data.Function

import Zdil.CeskySlovnik
import Zdil.Soubory
                            
filtrujPalindromy :: Ord a => [[a]] -> [[a]]
filtrujPalindromy = filter (\x -> x == reverse x)

main = do
  sl <- ceskySlovnik
  ulozSoubor "output/palindromy.txt" (unlines $ sortBy (flip compare `on` length) (filtrujPalindromy sl))

