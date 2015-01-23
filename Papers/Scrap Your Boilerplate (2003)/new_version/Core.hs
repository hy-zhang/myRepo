{-# OPTIONS_GHC -fglasgow-exts #-}

module Core where 

import Data.Typeable
import SybAlg

mkT :: (Typeable a, Typeable b) => (b -> b) -> a -> a
mkT f = case cast f of
          Just g  -> g
          Nothing -> id

mkQ :: (Typeable a, Typeable b) => r -> (b -> r) -> a -> r
mkQ defaultVal f a = case cast a of
                       Just b  -> f b
                       Nothing -> defaultVal

-- Bottom-up
everywhereT :: Term a => (forall b. Term b => b -> b) -> a -> a
everywhereT f x = f $ gmapT (everywhereT f) x

-- Top-down
everywhereQ :: Term a => (r -> r -> r) -> (forall a. Term a => a -> r) -> a -> r
everywhereQ k f x = foldl k (f x) (gmapQ (everywhereQ k f) x)

-- Top-down
everywhereT' :: Term a => (forall b. Term b => b -> b) -> a -> a
everywhereT' f x = gmapT (everywhereT' f) (f x)

class Typeable a => Term a where
  gmapT :: (forall b. Term b => b -> b) -> a -> a
  gmapQ :: (forall b. Term b => b -> r) -> a -> [r]

instance Term Company where
  gmapT f (C xs) = C (f xs)
  gmapQ f (C xs) = [f xs]

instance Term Dept where
  gmapT f (D n m xs) = D (f n) (f m) (f xs)
  gmapQ f (D n m xs) = [f n, f m, f xs]

instance Term SubUnit where
  gmapT f (PU e) = PU (f e)
  gmapT f (DU e) = DU (f e)
  gmapQ f (PU e) = [f e]
  gmapQ f (DU e) = [f e]

instance Term Employee where
  gmapT f (E p s) = E (f p) (f s)
  gmapQ f (E p s) = [f p, f s]

instance Term Person where
  gmapT f (P n a) = P (f n) (f a)
  gmapQ f (P n a) = [f n, f a]

instance Term Salary where
  gmapT f (S x) = S (f x)
  gmapQ f (S x) = [f x]

-- Originally the paper wrote something like:
-- gmapT f (x:xs) = f x : f xs
-- gmapQ f (x:xs) = [f x, f xs]
-- The traversal can be guaranteed but some redundant information appears with strings.
-- Therefore we use "map f all" instead.
instance Term a => Term [a] where
  gmapT f []         = []
  gmapT f all@(x:xs) = map f all
  gmapQ f []         = []
  gmapQ f all@(x:xs) = map f all

instance Term Char where
  gmapT _   = id
  gmapQ _ _ = []

instance Term Bool where
  gmapT _   = id
  gmapQ _ _ = []

instance Term Float where
  gmapT _   = id
  gmapQ _ _ = []
