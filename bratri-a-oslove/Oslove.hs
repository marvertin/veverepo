
--
-- Otec zemřel, zanechal svým třem snům 19 oslů a závěť:
--   Nejstarší syn dostane polobinu
--   Prostřední syn dostane čtvrtinu oslů
--   Nejmladší syn dostane pštinu oslů.
--
-- Bratři si ze závětí nevěděli rady. Kolem jel mudrc a pomohl jim.
-- K bratrovým oslům přidal svého osla, takže bylo 20 oslů a přišlo řešení:
--   Nejstarší syn si vzal polovinu, tedy 10 oslů.
--   Protřední syn si vzal čtvrtinu, tedy 5 oslů.
--   Nejmladší syn si vzal pětinu, tedy 4 osly.
-- Dohromady rozdělili 19 oslů a zbyl jen mudrcův osel, ten si osla vzal a bratry obdarován odešel.
--
-- A my zde hledáme všechny možnosti, pro které má úloha řešení.
-- Podmínky:
--     * libovolný počet bratrů.
--     * každý bratr si odnáší různé poměry
--     * je dán minimální počet oslů, které si bratr může odvést
--     * je dán minimální dělící poměr 

-- Pro implicitní nastavení 3/3 je úloha řešitelná pro nejmenší počet oslů 59.
--
minimalniPocetOslu :: Integral a => a
minimalniPocetOslu = 3

minimalniPodil :: Integral a => a
minimalniPodil = 3

komb :: [a] -> [[a]]
komb [] = [[]]
komb (x : xs) = let y = komb xs
                in y ++ ((x:) <$> y)

delitele :: Integral a => a -> [a]
delitele n = [x | x <- [minimalniPodil .. div n minimalniPocetOslu], mod n x == 0]

-- Vezme počet oslů k rozdělení (mimo mudrcova)
-- Vrací seznam kombinací poměrů, pro kterých má úloha řešení
oslove :: Integral a => a -> [[a]]
oslove n = 
    let m = n + 1  -- jeden osel přidán rádcem
    in  filter (\x -> sum (div m <$> x) == n) $
           komb . delitele $ m

-- Hledá řešení pro každý počet oslů až do zadaného
osloveDo :: Integral a => a -> [(a, [[a]])]
osloveDo n = [(x, osl) | x <- [1..n], let osl = oslove x, not $ null osl ]
