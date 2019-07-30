module Kolo
    (   zakladna,
        vyres,
        Zub,
        Zoubek,
        urciVysku
    ) where

import Data.Maybe
import Lib
import Debug.Trace
import Text.Printf


type Zoubek = (Double, Double)
type Zub = [Zoubek]
type Pastorek = Double
type CisloPalce = Int

nPalec  = 6     :: Int   -- pocet palcu
rPastor = 25.0  :: Double -- polomwer pastorku (stredy palcu)
rPalec  = 8.0  :: Double -- polomer palce
krok    = 1.0   :: Double

zakladna = rPastor * 2 * sin (pi / fromIntegral nPalec)
obvod = zakladna * fromIntegral nPalec
uhelPalecni = 2 * pi / fromIntegral nPalec

nevyhloubenyZub = zip [0.0, krok .. obvod] (repeat (rPastor +  rPalec))


-- určí výšku danéhé pozice pastorku a daného palce na dané x pozici. (palec nemusí omezovat)
urciVysku ::  Double -> Pastorek -> CisloPalce -> Maybe Double
urciVysku xxx pastorek cisloPalce = 
     let fiPastorek = pastorek / zakladna * 2 * pi / fromIntegral nPalec
         fiPalec = fiPastorek +  uhelPalecni * fromIntegral cisloPalce
         xPalec = pastorek - rPastor * sin fiPalec
         yPalec = rPastor + rPalec - rPastor * cos fiPalec
         ydiff2 = rPalec ** 2 - (xxx - xPalec) ** 2
     in if ydiff2 < 0 then Nothing else Just (yPalec - sqrt ydiff2)

-- vmáčkne pastorek na jediný zoubek
xvmackni :: Pastorek -> Zoubek -> Zoubek
xvmackni pastorek (x, y) = (x,
    fromJust $foldl1 minMaybe $ Just y : map (urciVysku x pastorek) [ 0 .. fromIntegral nPalec -1 ]
 )

-- vmáčkne do zubu pastorek na dané pozici
vmackni ::  Zub -> Pastorek -> Zub
vmackni zub pastorek = map (xvmackni pastorek) zub

-- řeší se postupným vmačkáváním všech pastorků do jediného zubu
vyres :: Zub
vyres = foldl vmackni nevyhloubenyZub [-obvod, -obvod+krok .. 2 * obvod]