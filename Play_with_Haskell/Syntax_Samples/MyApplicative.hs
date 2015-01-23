class (Functor f) => MyApplicative f where
	mypure :: a -> f a
	cont :: f (a -> b) -> f a -> f b

instance MyApplicative Maybe where
	mypure x = Just x
	Nothing `cont` _ = Nothing
	(Just f) `cont` y = fmap f y