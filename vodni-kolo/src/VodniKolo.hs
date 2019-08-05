{-# LANGUAGE RecordWildCards            #-}

module VodniKolo
    ( 
        Kolo,
        delkaLopatky,
        stredovyUhelSvorce,
        ko1
    ) where


data Kolo = Kolo {
    pocetKorecku :: Double,
    prumerKola :: Double, -- celkový průměr kola k hranicím korečků
    vyskaKorecku :: Double, -- radiální výška korečků vod okraje k poddénce
    pomerSvorce :: Double,  -- poměr výšky svorce k výšce korečku (0 až 1) 0 bez svorce, 1 kolmé lopatky
    uhelLopatky:: Double,  -- odklad lopatky od radiály
    tlouskaDesky :: Double -- tloušťka desky, ze které je vyrobena lopatka a svorec

  }  deriving (Show)

ko1 = Kolo { pocetKorecku = 16, 
             prumerKola= 900, 
             vyskaKorecku = 200,
             pomerSvorce = 0.5, 
             uhelLopatky = pi / 6,
             tlouskaDesky= 20}
  
  -- Vypočte koreček v základní poloze dle definice kola.
  -- Základní poloha korečku je taková, kdylopatka právě míjí horní úvrat
  --    a do korečka by začala vtékat svislá voda (to ještě není vhodná poloha)
  --    pro napuštění korečku, kolo by se vracelo)
  --    kole se točí proti v kladném smyslu (proti směru hodinových ručiček)
  --zakladniKorecek :: Kolo -> Polygon


delkaLopatky :: Kolo -> Double
delkaLopatky (Kolo{..}) =
    let polomer = (prumerKola/2)
        odStreduKLopatce = polomer - vyskaKorecku + (vyskaKorecku * pomerSvorce) -- vzdálenost od středu kla přes prázdné místo, svorec k začátku lopatiky
        b = - 2 * odStreduKLopatce * cos (pi - uhelLopatky)  -- koeficient kvadreaticke rovnice
        c =  odStreduKLopatce ** 2 - polomer ** 2          -- koeficient kvadreaticke rovnice
    in (- b + sqrt (b ** 2 - 4 * c)) / 2
    

-- je to úhel, který svírá svorec se svislou osou, pokud je lopatka v základním tvaru a začíná do ní téci voda
stredovyUhelSvorce :: Kolo -> Double
stredovyUhelSvorce (Kolo{..}) =
    let polomer = (prumerKola/2)
        odStreduKLopatce = polomer - vyskaKorecku + (vyskaKorecku * pomerSvorce) -- vzdálenost od středu kla přes prázdné místo, svorec k začátku lopatiky
        uhelLopatkyUVrchu = asin $ sin (pi - uhelLopatky) / polomer * odStreduKLopatce
    in  uhelLopatky - uhelLopatkyUVrchu -- zbývající úhel v trjúhelníku
        
    
    