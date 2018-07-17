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
   "vní", "vního"]

-- vytvoří všechny možné kombinace seznamů
-- porozdeluj "abc" = [("", "abc"),("a", "bc"), ("ab", "c"),("abc","")]
porozdeluj :: [a] -> [([a], [a])]
porozdeluj xs = ([], xs) : porox [] xs
  where 
    porox :: [a] -> [a] -> [([a], [a])]
    porox _ [] = []       
    porox prefix (x:xs) = (prpr, xs) : porox prpr xs 
      where prpr = prefix ++ [x]
   

porozdelujBezPrazdnych :: [a] -> [([a], [a])]
porozdelujBezPrazdnych = filter neprazdny . porozdeluj 
   where neprazdny (_, []) = False
         neprazdny ([], _) = False
         neprazdny _ = True
         
porozdelujDelsiNez :: Int -> [a] -> [([a], [a])]       
porozdelujDelsiNez n = filter delsinez . porozdeluj
   where 
      delsinez (u, v) = length u > n && length v > n
   
hledej2v1 :: [String] -> [(String,String)]
hledej2v1 kmen = let
   setkmen = S.fromList (map (map toLower) kmen) S.\\ ignorovat
   --setkmen = S.fromList kmen
   mocdvojic = kmen >>=  porozdelujDelsiNez 2
   in filter (\(u,v) ->  (S.member $ map toLower u) setkmen  && (S.member $ map toLower v) setkmen) mocdvojic
  

main = do
  sl <- ceskySlovnikZakladniTvary
  ulozSoubor "output/2v1.txt" $ unlines $ sort ( map (\(u,v) -> u ++ "-" ++ v) (hledej2v1 sl)) 

