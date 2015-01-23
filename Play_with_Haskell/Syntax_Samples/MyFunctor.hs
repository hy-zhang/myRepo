class MyFunctor f where
	myfmap :: (a -> b) -> f a -> f b

instance MyFunctor IO where
	myfmap f action = do
		result <- action
		return . f $ result
		
main = do 
	output <- myfmap (++ "!") $ getLine
	putStrLn output