{-# OPTIONS_GHC -fglasgow-exts #-}

module Test1 where

import Data.Typeable
import SybAlg
import Core

increase :: Company -> Company
increase = everywhereT (mkT inc)

prettyPrint :: Company -> String
prettyPrint = everywhereQ (++) (mkQ2 "" info1 info2)

salaryBill :: Company -> Float
salaryBill = everywhereQ (+) (mkQ 0 bill)

-- This function is really helpful.
-- Because we have several strings (name, address, deptName).
-- We sometimes need to distinguish them.
-- This gives a good example.
mkQ2 :: (Typeable a, Typeable b, Typeable c) => String -> (a -> String) -> (b -> String) -> c -> String
mkQ2 val f1 f2 a = (mkQ val f1 a) ++ (mkQ val f2 a)

inc :: Salary -> Salary
inc (S x) = S (1.1 * x)

info1 :: Dept -> String
info1 (D n m xs) = "<" ++ n ++ ">"

info2 :: Person -> String
info2 (P n a) = "(" ++ n ++ ")"

bill :: Salary -> Float
bill (S x) = x

main = do
  print $ prettyPrint genCom
  print $ prettyPrint . increase $ genCom 
  print $ "salaryBill  = " ++ (show . salaryBill $ genCom)
  print $ "salaryBill' = " ++ (show . salaryBill . increase $ genCom)
