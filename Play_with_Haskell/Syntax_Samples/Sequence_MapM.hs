main = do 
	lst <- sequence [getLine, getLine, getLine]
	putStrLn "--------------"
	sequence $ map print lst
	putStrLn "--------------"
	mapM print lst
	putStrLn "--------------"