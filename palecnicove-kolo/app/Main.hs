module Main where

import Lib
import Kolo
import Text.Printf

mrize :: Zoubek -> String
mrize (x, n) = printf "%6.1f: %6.1f - %s" x n (take (round $ n * 2.0) $ repeat '#')

zobraz :: Zub -> String
zobraz list =  unlines $ map mrize list
    

main :: IO ()
main = do
    print zakladna
    putStrLn (zobraz vyres)
