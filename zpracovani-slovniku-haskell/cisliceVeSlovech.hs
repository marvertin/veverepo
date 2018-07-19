import Data.List
import Data.Function

import Zdil.CeskySlovnik
import Zdil.Soubory
                          
pocetTrhanychPodretezcu :: Eq a => [a] -> [a] -> Int
pocetTrhanychPodretezcu [] _ = 1
pocetTrhanychPodretezcu _ [] = 0
pocetTrhanychPodretezcu xx@(x:xs) (y:ys) = pocetTrhanychPodretezcu xx ys
  + if x == y then pocetTrhanychPodretezcu xs ys else 0

trhanychRozdil :: Eq a => [a] -> [a] -> [[a]]
trhanychRozdil [] s = [s]
trhanychRozdil _ [] = []
trhanychRozdil xx@(x:xs) (y:ys) = map (y:) (trhanychRozdil xx ys)
  ++ if x == y then trhanychRozdil xs ys else []
                              

-- 1. seznam seznamů, které se bude hledat
-- 2. seznam prohledávaný
-- Výsledek, pokdu obsahuje
obsahujeTrhaneNecoJednou :: Eq a => [[a]] -> [a] -> Bool
obsahujeTrhaneNecoJednou hledanci zdroj = any (\x -> pocetTrhanychPodretezcu x zdroj == 1) hledanci

-- 1. seznam seznamů, které se bude hledat
-- 2. seznam prohledávaný
-- Výsledek, pokdu obsahuje
ktereTrhaneObsahujeJednou :: Eq a => [[a]] -> [a] -> [[a]]
ktereTrhaneObsahujeJednou hledanci zdroj = filter (\x -> pocetTrhanychPodretezcu x zdroj == 1) hledanci

cislice = ["nula", "jedna", "dva", "tři", "čtyři", "pět", "šest", "sedm", "osm", "devět", "deset"] 
obsahujeCisliceJednou = obsahujeTrhaneNecoJednou cislice

ktereTrhaneCisliceObsahujeJednou = ktereTrhaneObsahujeJednou cislice

hledejCislice :: [String] -> [(String, [String])]
hledejCislice kmen = map (\x -> (x, ktereTrhaneCisliceObsahujeJednou x)) (filter obsahujeCisliceJednou kmen)

formatuj :: (String, [String]) -> String
formatuj (x, s) = x ++ " | " ++ intercalate " "  s
 

main = do
  sl <- ceskySlovnikZakladniTvary
  ulozSoubor "output/cisliceVeSlovech.txt" (unlines $ map formatuj (hledejCislice sl))

