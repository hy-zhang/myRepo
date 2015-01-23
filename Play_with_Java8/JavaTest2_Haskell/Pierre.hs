landLeft :: Int -> (Int, Int) -> Maybe (Int, Int)
landLeft n (x, y) = if balance then Just (x + n, y) else Nothing
	where balance = x + n >= 0 && abs (x + n - y) < 4

landRight :: Int -> (Int, Int) -> Maybe (Int, Int)
landRight n (x, y) = if balance then Just (x, y + n) else Nothing
	where balance = y + n >= 0 && abs (y + n - x) < 4
	
test :: Maybe (Int, Int)
test = return (0, 0) >>= landLeft 1 >>= landRight 5 >>= landLeft 4
