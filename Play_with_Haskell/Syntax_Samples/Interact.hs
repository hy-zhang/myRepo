short :: String -> String
short = unlines . filter ((< 10) . length) . lines

main = interact short