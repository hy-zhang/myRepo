landL :: Int -> (Int, Int) -> Maybe (Int, Int)
landL m (x, y) 
	| abs (x + m - y) > 3 = Nothing
	| otherwise = Just (x + m, y)
	
landR :: Int -> (Int, Int) -> Maybe (Int, Int)
landR m (x, y) 
	| abs (y + m - x) > 3 = Nothing
	| otherwise = Just (x, y + m)

x -: f = f x

res1 = return (0, 0) >>= landL 1 >>= landR 3

res2 = do
	x1 <- return (0, 0)
	x2 <- landL 1 x1
	x3 <- landR 5 x2
	return x3
	