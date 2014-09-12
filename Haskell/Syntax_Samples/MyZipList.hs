class (Functor f) => MyApplicative f where
	mypure :: a -> f a
	cont :: f (a -> b) -> f a -> f b

newtype MyZipList a = MyZipList { getMyList :: [a] }
	
instance Functor MyZipList where
	fmap f (MyZipList xs) = MyZipList (fmap f xs)	
	
instance MyApplicative MyZipList where
	mypure x = MyZipList (repeat x)
	cont (MyZipList xs) (MyZipList ys) = MyZipList (zipWith (\f x -> f x) xs ys)
	
s = getMyList $ mypure (+) `cont` MyZipList [1,2,3] `cont` MyZipList [2,4,6]