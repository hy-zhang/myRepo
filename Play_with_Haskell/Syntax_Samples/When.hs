import Control.Monad

main = do 
	input <- getLine
	when (not . null $ input) $ do
		putStrLn . unwords . map reverse . words $ input
		main