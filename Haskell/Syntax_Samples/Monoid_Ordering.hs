import Data.Monoid

strCompare :: String -> String -> Ordering
strCompare x y = (length x `compare` length y) `mappend` (vowels x `compare` vowels y) `mappend` (x `compare` y)
	where
		vowels = length . filter (`elem` "aeiou")