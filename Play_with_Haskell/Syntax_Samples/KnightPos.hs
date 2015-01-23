import Control.Monad

type Axis = (Int, Int)

move1 :: Axis -> [Axis]
move1 (x, y) = do
	n1 <- [-2, -1, 1, 2]
	n2 <- [-2, -1, 1, 2]
	guard (abs n1 + abs n2 == 3)
	return (x + n1, y + n2)
	
[ 2332323] >>= (\n1 -> ....)
	
canReachIn3 :: Axis -> Axis -> Bool
canReachIn3 x y = y `elem` move3 x
	where move3 x0 = do
		n0 <- return x0
		n1 <- move1 n0
		n2 <- move1 n1
		n3 <- move1 n2
		return n3