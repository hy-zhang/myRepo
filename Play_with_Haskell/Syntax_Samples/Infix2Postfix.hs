import qualified Data.List as L

type Stack = String

top :: Stack -> Char
top [] = ' '
top xs = head xs

push :: Char -> Stack -> Stack
push = (:)

infix2postfix :: [String] -> (String, Stack)
infix2postfix = foldl parse ("", "")

isSymbol :: String -> Bool
isSymbol [a] = if a >= '0' && a <= '9' then False else True
isSymbol _ = False

changeToNum :: Char -> Int
changeToNum x
	| x == '+' || x == '-' = 1
	| otherwise = 2

lessThan :: Char -> Char -> Bool
lessThan x y = changeToNum x < changeToNum y

parse :: (String, Stack) -> String -> (String, Stack)
parse (result, stack) cur
	| isSymbol cur = rotateCompare (result, stack) cur
	| otherwise = (result ++ cur ++ " ", stack)
	where
		rotateCompare (res, sta) cu 
			| top sta == ' ' || lessThan (top sta) (head cu) = (res, push (head cu) sta)
			| otherwise = rotateCompare (res ++ [top sta] ++ " ", tail sta) cu
			
getFinalResult :: (String, Stack) -> String
getFinalResult (xs, []) = xs
getFinalResult (xs, (y:ys)) = getFinalResult (xs ++ [y] ++ " ", ys)

result :: String -> String
result = init . getFinalResult . infix2postfix . L.words