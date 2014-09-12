res1 :: [(Int, Int)]
res1 = do
	n1 <- [1,2,3]
	n2 <- [4,5,6]
	return (n1, n2)
	
res2 = [(n1, n2) | n1 <- [1,2,3], n2 <- [4,5,6]]

res3 :: [Int]
res3 = do
	(n1, n2) <- [(1,2), (2,3), (3,4)]
	return (n1 + n2)
	
res4 = [n1 + n2 | (n1, n2) <- [(1,2), (2,3), (3,4)]]

main = do
	print $ res1 == res2
	print $ res3 == res4

