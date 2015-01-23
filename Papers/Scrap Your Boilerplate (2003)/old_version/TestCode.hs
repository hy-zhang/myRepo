{-# OPTIONS_GHC -fglasgow-exts #-}

-- The first part, for generic transformations, not queries.

import Data.Typeable
import Data.Char

-- mkT: make a transformation for matched types.
mkT :: (Typeable a, Typeable b) => (b -> b) -> (a -> a)
mkT f = case cast f of 
	Just g -> g
	Nothing -> id

-- everywhere: one-layer traversal.
everywhere :: Term a => (forall b. Term b => b -> b) -> a -> a
everywhere f x = f (gmapT (everywhere f) x)

class Typeable a => Term a where
	gmapT :: (forall b. Term b => b -> b) -> a -> a
	
data Company = C [Dept] deriving (Show, Typeable)
data Dept = D Name Manager [SubUnit] deriving (Show, Typeable)
data SubUnit = PU Employee | DU Dept deriving (Show, Typeable)
data Employee = E Person Salary deriving (Show, Typeable)
data Person = P Name Address deriving (Show, Typeable)
data Salary = S Float deriving (Show, Typeable)
type Manager = Employee
type Name = String
type Address = String

-- gmapT: apply f to the immediate children. When it has no children, gmapT has no effect.

instance Term Char where
	gmapT f s = s
	
instance Term Float where
	gmapT f s = s
	
instance Term Salary where
	gmapT f (S x) = S (f x)

instance Term a => Term [a] where
	gmapT f [] = []
	gmapT f (x:xs) = f x : f xs

instance Term Person where
	gmapT f (P x y) = P (f x) (f y)
	
instance Term Employee where
	gmapT f (E x y) = E (f x) (f y)

instance Term SubUnit where
	gmapT f (PU x) = PU (f x)
	gmapT f (DU x) = DU (f x)
	
instance Term Dept where
	gmapT f (D x y xs) = D (f x) (f y) (f xs)

instance Term Company where
	gmapT f (C xs) = C (f xs)
	
incS :: Float -> Salary -> Salary
incS k (S s) = S $ s * (1 + k)

nameAppend :: String -> Person -> Person
nameAppend str (P x y) = P (str ++ x) y

increase :: Float -> Company -> Company
increase k = everywhere (mkT (incS k))

nameChanging :: String -> Company -> Company
nameChanging str = everywhere (mkT (nameAppend str))

-- Below are for testing.
	
genCom = C [D "Research" ralf [PU joost, PU marlow], D "Strategy" blair []]

ralf = E (P "Ralf" "Amsterdam") (S 8000)
joost = E (P "Joost" "Amsterdam") (S 1000)
marlow = E (P "Marlow" "Cambridge") (S 2000)
blair = E (P "Blair" "London") (S 100000)

test1 = genCom
test2 = increase 0.1 genCom
test3 = nameChanging "___" genCom