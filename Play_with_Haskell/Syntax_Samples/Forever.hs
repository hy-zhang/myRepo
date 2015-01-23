import Control.Monad	

main = forever $ do 
	a <- getLine
	print . length $ a