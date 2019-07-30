module Main where

import Lib
import Kolo
import Text.Printf

mrize :: (Double, Double) -> String
mrize (x, n) = printf "%6.1f: %6.1f - %s" x n (take (round n) $ repeat '#')

zobraz :: [(Double, Double)] -> String
zobraz list =  unlines $ map mrize list
    

main :: IO ()
main = do
    print zakladna
    putStrLn (zobraz vyres)
