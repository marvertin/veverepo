module Korecek
    ( 
      Polyg,
      Bod,
      obsahKorecku,
      momentovaMiraKorecku
    ) where

type Bod = (Double, Double)
type Troj = (Bod, Bod, Bod)
type Polyg = [Bod]

delka :: Bod -> Bod -> Double
delka (ax, ay) (bx, by) = sqrt ( (ax - bx) ** 2 + (ay - by) ** 2)

---------------------------------------------------------------------
-- Obecně fungující geometrické funkce

-- rozdělí úsečku od prvního bodu v daném poměru
rozdel :: Double -> Bod -> Bod -> Bod
rozdel q (ax, ay) (bx, by) = (_rozdel ax bx, _rozdel ay by)
  where _rozdel a b = q * (b - a) + a

stred :: Bod -> Bod -> Bod
stred = rozdel 0.5

obsahTroj :: Troj -> Double
obsahTroj (a@(ax, ay), b@(bx, by), c@(cx, cy)) =
  let dc =  delka a b
      da =  delka b c
      db =  delka c a
      s = (da + db + dc) /2
   in sqrt (s * (s - da) * (s - db) * (s - dc))

tezisteTroj :: Troj -> Bod
tezisteTroj (a, b, c)  =  rozdel (1.0 / 3.0) (stred a b) c

----------------------------------------------------------------------
-- Výpočty korečků
--
-- první parametr je jeden bod té přímky, dále je úsečka, výsledek je bod
prunikSVodorovnouPrimkou :: Bod -> (Bod, Bod) -> Bod
prunikSVodorovnouPrimkou (_, y) ((ax, ay), (bx, by)) = 
     (bx +  (y - by) * (ax - bx) / (ay - by)  , y)

odrizniPretikajici :: Polyg -> Polyg
odrizniPretikajici polyg@((_, yh) : rest) = 
    let (zbytek, dalsi) = span (\(_, y) -> y <= yh) polyg
    in if dalsi == [] then zbytek
                      else zbytek ++ [head dalsi]
        
-- zcela zaříne koreček vodorovnou vodou
zarizniVodou :: Polyg -> Polyg
zarizniVodou polyg =
    let (prvni : zbytek) = odrizniPretikajici polyg
        posledniUsecka = (last zbytek, last . init $ zbytek)
    in if length zbytek <= 1
        || fst prvni > 0
        || snd prvni < snd (head zbytek) then [] 
                 else (prvni : init zbytek ++ [prunikSVodorovnouPrimkou prvni posledniUsecka])
                             


polygon2Troje :: Polyg -> [Troj]
polygon2Troje [] = []
polygon2Troje (prvni: zbytek) = _polygon2Troje (zbytek ++ [prvni]) -- od lomu lopatky a svorce je nutné měřit
   where
    _polygon2Troje [] = []
    _polygon2Troje [_] = []
    _polygon2Troje [_, _] = []
    _polygon2Troje (a : b : c : rest) =  (a, b, c) : _polygon2Troje (a : c : rest)

obsahKorecku :: Polyg -> Double
obsahKorecku polyg = sum $ map obsahTroj $ polygon2Troje $ zarizniVodou polyg

-- momentová míra je v metrech krychlových (plocha krát páka)
-- aby se získal moment v newtone metrech,
-- musí se násobit šířkou kola, hustotou vody a gravitačním zrychlením
momentovaMiraKorecku :: Polyg -> Double
momentovaMiraKorecku polyg = sum $ map
    (\troj -> 
        (fst . tezisteTroj) troj * obsahTroj troj
    ) $ polygon2Troje $ zarizniVodou polyg





