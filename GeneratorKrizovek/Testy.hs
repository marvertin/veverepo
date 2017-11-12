import Data.List 
import Krizovky

--
-- Test objektu Krizovka, jeho postupné vyrábění
--
    
slova = ["a", "blb", "ptak", "osel", "kokot", "kojot", "koken", "kraken", "kulak", 
 "befel", "muk", "lepek", "lepic", "ruka", "suk", "muz", "b", "syn" ]    


--  KOPEC
--  ASTAT
--  MI
--  IK
--  LA

priklad =   map (foldl krNext (krEmpty 5)) (inits ["KOPEC", "KAMIL", "ASTAT", "OSIKA", "MIKRO", "PTKON", "IKONA", "EARNK", "LANKO", "CTOAO"])
       
main =   putStrLn $ unlines $ map show priklad                                 
 