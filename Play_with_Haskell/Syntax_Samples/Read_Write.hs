short :: String -> String
short = unlines . filter ((< 10) . length) . lines

main = do
	cont <- readFile "data.txt"
	writeFile "data2.txt" $ short cont