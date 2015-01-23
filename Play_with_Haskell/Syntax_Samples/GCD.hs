import Control.Monad.Writer

gcd' :: Int -> Int -> Writer [String] Int
gcd' x y
	| y == 0 = do
		tell ["Finish at " ++ show x]
		return x
	| otherwise = do
		tell [show x ++ " mod " ++ show y ++ " = " ++ show y ++ " mod " ++ show (x `mod` y)]
		gcd' y (x `mod` y)
	
result = mapM_ putStrLn . snd . runWriter $ gcd' 123 234