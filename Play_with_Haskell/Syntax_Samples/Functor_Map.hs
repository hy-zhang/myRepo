import qualified Data.Map as M
import qualified Data.List as L

class MyFunctor f where
	myfmap :: (a -> b) -> f a -> f b
	
instance (Ord a) => MyFunctor (M.Map a) where
	myfmap f xs = M.fromList $ L.map (\(fst, snd) -> (fst, f snd)) $ M.toList xs