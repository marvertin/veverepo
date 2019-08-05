{-# LANGUAGE RecordWildCards            #-}

module VodniKolo
    ( 
        Kodef,
        delkaLopatky,
        stredovyUhelSvorce,
        rotaceBod,
        zakladniKorecek,
        zakladniKolo,
        hmotnostiKorecku,
        momentyKorecku,
        celkovyMomentKola,
        ko1,
        ko2,
        ko3
    ) where

import Korecek

data Kodef = Kodef {
    pocetKorecku :: Double, -- aby se s tím líp počítalo, je to double
    prumerKola :: Double, -- celkový průměr kola k hranicím korečků
    vyskaKorecku :: Double, -- radiální výška korečků vod okraje k poddénce
    pomerSvorce :: Double,  -- poměr výšky svorce k výšce korečku (0 až 1) 0 bez svorce, 1 kolmé lopatky
    uhelLopatky:: Double,  -- odklad lopatky od radiály
    tlouskaDesky :: Double -- tloušťka desky, ze které je vyrobena lopatka a svorec
}  deriving (Show)

-- kolo sestava z korečků  
type Kolo = [Polyg]

sirkaKola  = 0.3
gg = 10.0
rovody = 1000.0

ko1 = Kodef { pocetKorecku = 16, 
             prumerKola= 0.9, 
             vyskaKorecku = 0.2,
             pomerSvorce = 0.5, 
             uhelLopatky = pi / 6,
             tlouskaDesky= 0.02
            }

ko2 = Kodef { pocetKorecku = 16, 
    prumerKola= 1000, 
    vyskaKorecku = 200,
    pomerSvorce = 0.5, 
    uhelLopatky = pi / 6,
    tlouskaDesky= 30
}

ko3 = Kodef { pocetKorecku = 16, 
             prumerKola= 0.9, 
             vyskaKorecku = 0.2,
             pomerSvorce = 0.01, 
             uhelLopatky = pi / 4,
             tlouskaDesky= 0.02
            }

-- Vypočte koreček v základní poloze dle definice kola.
-- Základní poloha korečku je taková, kdylopatka právě míjí horní úvrat
--    a do korečka by začala vtékat svislá voda (to ještě není vhodná poloha)
--    pro napuštění korečku, kolo by se vracelo)
--    kole se točí proti v kladném smyslu (proti směru hodinových ručiček)
zakladniKorecek (k@Kodef{..}) =
    let polomer = (prumerKola/2)
        odStreduKLopatce = polomer - vyskaKorecku + (vyskaKorecku * pomerSvorce) -- vzdálenost od středu kla přes prázdné místo, svorec k začátku lopatiky
        uhel = stredovyUhelSvorce k
        uhelTloustkyDesky = tlouskaDesky / (polomer - vyskaKorecku / 2) -- bereme stření tloušťku
        odklonZadni =  - (2 * pi / pocetKorecku - uhelTloustkyDesky) -- odkláníme zpět
        predniHrana = [(polomer, pi/2), (odStreduKLopatce, pi/2 + uhel), (polomer - vyskaKorecku, pi/2 + uhel)] -- v polárních souřadnicích
        zadniHrana = fmap  (fmap (+ odklonZadni)) predniHrana
    in map polar2bod (predniHrana  ++ reverse zadniHrana)

-- Vypočte celé kolo, první koreček je v základní poloze
zakladniKolo :: Kodef -> Kolo
zakladniKolo (k@Kodef{..}) = let
      zaklad = zakladniKorecek k
    in  map  (\n -> rotaceKorecek (n * (2 * pi / pocetKorecku) ) $ zaklad ) [0 .. pocetKorecku - 1]

hmotnostiKorecku ::  Kolo -> [Double]
hmotnostiKorecku = map (\kor -> obsahKorecku kor * sirkaKola * rovody)

momentyKorecku ::  Kolo -> [Double]
momentyKorecku = map (\kor -> momentovaMiraKorecku kor * sirkaKola * rovody * gg)

celkovyMomentKola :: Kolo -> Double
celkovyMomentKola = sum . momentyKorecku


delkaLopatky :: Kodef -> Double
delkaLopatky (Kodef{..}) =
    let polomer = (prumerKola/2)
        odStreduKLopatce = polomer - vyskaKorecku + (vyskaKorecku * pomerSvorce) -- vzdálenost od středu kla přes prázdné místo, svorec k začátku lopatiky
        b = - 2 * odStreduKLopatce * cos (pi - uhelLopatky)  -- koeficient kvadreaticke rovnice
        c =  odStreduKLopatce ** 2 - polomer ** 2          -- koeficient kvadreaticke rovnice
    in (- b + sqrt (b ** 2 - 4 * c)) / 2
    

-- je to úhel, který svírá svorec se svislou osou, pokud je lopatka v základním tvaru a začíná do ní téci voda
stredovyUhelSvorce :: Kodef -> Double
stredovyUhelSvorce (Kodef{..}) =
    let polomer = (prumerKola/2)
        odStreduKLopatce = polomer - vyskaKorecku + (vyskaKorecku * pomerSvorce) -- vzdálenost od středu kla přes prázdné místo, svorec k začátku lopatiky
        uhelLopatkyUVrchu = asin $ sin (pi - uhelLopatky) / polomer * odStreduKLopatce
    in  uhelLopatky - uhelLopatkyUVrchu -- zbývající úhel v trjúhelníku
        

-- rotace o určitý úhel daného bodu
rotaceBod :: Double -> Bod -> Bod    
rotaceBod u (x, y) = (x * cos u - y * sin u, x * sin u + y * cos u)

-- rotace celého korečku o daný úhel
rotaceKorecek :: Double -> Polyg -> Polyg    
rotaceKorecek fi  = map ( rotaceBod fi)

-- poláně R a FI na bod
polar2bod :: (Double, Double) -> Bod
polar2bod (r, fi) = (r * cos fi, r * sin fi)

    