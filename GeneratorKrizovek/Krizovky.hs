--
-- Generátor ètvervových køížovek. 
-- Vstupem je:
--    1. velikost ètvercové køížovky
--    slovník [String]  výstupem
--  Výstupem je
--    seznam køížovek [Krizovka]  
-- 
-- 
--
module Krizovky  
( Krizovka           -- objekt køížovky jak hotové, tak èásteèné                                
, generovatKrizovku  -- hlavní funkce pro generování

--  pomocné funkce exportované kvùli testùm a podobnì 

, MapaPrefixuSlov  
, mapaPrefixuSlov  
, krResit
, krNext
, krEmpty
, krShow
) where  
  

import Data.List
import Data.Char
import System.IO
import qualified Data.Set as S
import qualified Data.Map as M
import Data.Function

type MapaPrefixuSlov =  M.Map String [String]

mapaPrefixuSlov :: Int -> [String] -> MapaPrefixuSlov
mapaPrefixuSlov nn xs = M.fromList $ concatMap dlabej [0..nn]
  where
    dlabej m =  map (\s -> (take m (head s), s ) ) ( groupBy (on (==) (take m) ) $ sort xs   )

hledej :: String -> MapaPrefixuSlov -> [String]
hledej prefix mapa =
   case (M.lookup prefix mapa) of
      Just slova -> slova
      Nothing -> []
      
data Smer = Hor | Ver

data Krizovka  = Krizovka Int Smer [String] [String]
    
krEmpty :: Int -> Krizovka    
krEmpty n = Krizovka n Hor [] []

krNext :: Krizovka -> String -> Krizovka
krNext (Krizovka n Hor h v) s = Krizovka n Ver (s:h) v     
krNext (Krizovka n Ver h v) s = Krizovka n Hor  h (s:v)

krJeReseni :: Krizovka -> Bool
krJeReseni (Krizovka n _ h v) = n == length h && n == length v

krPocatky :: Krizovka -> [String]
krPocatky (Krizovka n Hor h v) = map (\ii -> (reverse . map (!! ii)) $ v) [(length h) .. n-1]     
krPocatky (Krizovka n Ver h v) = map (\ii -> (reverse . map (!! ii)) $ h) [(length v) .. n-1] 

krPrefix :: Krizovka -> String
krPrefix k 
  | krJeReseni k = ""
krPrefix (Krizovka _ Hor h v) = reverse . map (!! (length h)) $ v     
krPrefix (Krizovka _ Ver h v) = reverse . map (!! (length v)) $ h

krResit :: Krizovka -> MapaPrefixuSlov -> [Krizovka]
krResit kr _
  | krJeReseni kr = [kr]
krResit kr mapa =
  if (any (\pref -> hledej pref mapa == []) (krPocatky kr) ) 
   then []
  else
  concatMap (\nasl -> krResit nasl mapa) naslednici   
  where
    prefix = krPrefix kr
    vhodnaSlova = hledej prefix mapa
    naslednici = map (krNext kr) vhodnaSlova  

krShow :: Krizovka -> String
krShow (Krizovka _ _ h v) = unwords h ++ " | " ++ unwords v

instance Show Krizovka where
  show (Krizovka n _ h v) = unlines $
      oddelovac ++
      map zarovnej (
        (reverse h) ++   
        (drop (length h)  . transpose . reverse $ v)
      ) ++                                              
      oddelovac
    where 
       oddelovac = [ "+" ++ take (n * 2 + 3) (repeat '-') ++ "+"]    
       zarovnej s = "|  " ++   (intersperse ' ' . take n) ( s ++ repeat ' ') ++ "  |"
       
 
generovatKrizovku :: Int -> [String] -> [Krizovka]
generovatKrizovku velikost slovnik =
    let
        slovnikStejnychDelek = filter ((==velikost) . length) slovnik
        mapa = mapaPrefixuSlov velikost slovnikStejnychDelek
    in    
        krResit (krEmpty velikost) mapa 
 
 