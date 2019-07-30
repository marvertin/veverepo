module Kolo
    (   zakladna,
        vyres,
        Zub,
        Zoubek
    ) where

type Zoubek = (Double, Double)
type Zub = [Zoubek]
type Pastorek = Double

nPalec  = 6.0   :: Double   -- pocet palcu
rPastor = 60.0  :: Double -- polomwer pastorku (stredy palcu)
rPalec  = 16.0  :: Double-- polomer palce
krok    = 1.0   :: Double

zakladna = rPastor * 2 * sin (pi / nPalec)

nevyhloubenyZub = zip [0.0, krok .. zakladna] (repeat (rPastor +  rPalec))

-- vmáčkne pastorek na jediný zoubek
xvmackni :: Pastorek -> Zoubek -> Zoubek
xvmackni pastorek (x, y) = (x, x + 0.3)

-- vmáčkne do zubu pastorek na dané pozici
vmackni ::  Zub -> Pastorek -> Zub
vmackni zub pastorek = map (xvmackni pastorek) zub

vyres :: Zub
vyres = foldl vmackni nevyhloubenyZub [-zakladna, -zakladna+krok .. 2 * zakladna]