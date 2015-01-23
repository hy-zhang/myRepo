{-# OPTIONS_GHC -fglasgow-exts #-}

module SybAlg where

import Data.Typeable

data Company  = C [Dept]                 deriving (Show, Typeable)
data Dept     = D Name Manager [SubUnit] deriving (Show, Typeable)
data SubUnit  = PU Employee | DU Dept    deriving (Show, Typeable)
data Employee = E Person Salary          deriving (Show, Typeable)
data Person   = P Name Address           deriving (Show, Typeable)
data Salary   = S Float                  deriving (Show, Typeable)
type Manager  = Employee
type Name     = String
type Address  = String

genCom :: Company
genCom = C [D "Research" ralf [PU joost, PU marlow], D "Strategy" blair []]

ralf, joost, marlow, blair :: Employee
ralf   = E (P "Ralf"   "Amsterdam") (S 8000)
joost  = E (P "Joost"  "Amsterdam") (S 1000)
marlow = E (P "Marlow" "Cambridge") (S 2000)
blair  = E (P "Blair"  "London")    (S 100000)

