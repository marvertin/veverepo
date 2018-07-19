--
--  Najede všechna slova, která lze vyrobit spojením dvou slov
-- ale délky nejméně tři znaky
--
import Data.List
import Data.Function
import Data.Char
import qualified Data.Set as S

import Zdil.CeskySlovnik
import Zdil.Soubory
                            

ignorovat :: S.Set String
ignorovat = S.fromList [
   "ách",
   "cích", "cím", "čině",
   "ech", "kou", "ního", "ních", "ním", "ními",
   "jící", "jícího", "ujících", "jících", "jícím",
   "ové", "ových", "ově", "oví", "ostí",
   "ská", "ské", "skému", "ského", "ském", "skou", "ský", "ských", "ským", "skýma", "skými",
   "stí",
   "vání", "ování", "dem", "des", "ovitý", "tví", "vitý", "nad", "pod",
   "vní", "vního"
   ]


type Sendvic a = ([a], a, [a])

-- vytvoří všechny možné kombinace seznamů se znakem uprostřed
-- porozdeluj "abcd" = [("", 'a', "bcd"),("a", 'b' "cd"), ("ab", 'c' "d"),("abc", 'd', "")]
porozdeluj :: [a] -> [Sendvic a]
porozdeluj sez = let 
       dvojicky = zip [0..] (map (const sez) sez)
       natri (xx, (y:ys)) = (xx, y, ys)
   in map (\(n,s) -> natri (splitAt n s)) dvojicky


         
porozdelujDelsiNez :: Int -> [a] -> [Sendvic a]       
porozdelujDelsiNez n = filter delsinez . porozdeluj
   where 
      delsinez (u ,_ , v) = length u > n && length v > n
   
hledejSPisemenemUprostred :: [String] -> [(String, Char, String)]
hledejSPisemenemUprostred kmen = let
   setkmen = S.fromList (map (map toLower) kmen) S.\\ ignorovat
   moctrojic = kmen >>=  porozdelujDelsiNez 2
   in filter (\(u, _, v) ->  (S.member $ map toLower u) setkmen  && (S.member $ map toLower v) setkmen) moctrojic
  

main = do
  sl <- ceskySlovnikZakladniTvary
  ulozSoubor "output/mezipismeno.txt" $ unlines $ sort ( map (\(u,x,v) -> u ++ "-" ++ [toUpper x] ++ "-" ++ v) (hledejSPisemenemUprostred sl)) 

