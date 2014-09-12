import Control.Monad	

main = do
	forM_ [1..5] (\num -> do
		putStr $ "The " ++ show num ++ "-th input: "
		input <- getLine
		let len = length input
		putStrLn $ "Length of <" ++ input ++ "> is " ++ show len
		)