module Kolo
    (   zakladna,
        vyres
    ) where

nPalec  = 6.0   :: Double   -- pocet palcu
rPastor = 60.0  :: Double -- polomwer pastorku (stredy palcu)
rPalec  = 16.0  :: Double-- polomer palce
krok    = 1.0   :: Double

zakladna = rPastor * 2 * sin (pi / nPalec)

nevyhloubenyZub = zip [0.0, krok .. zakladna] (repeat (rPastor +  rPalec))


vyres :: [(Double, Double)]
vyres = nevyhloubenyZub