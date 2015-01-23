import qualified Data.Foldable as F
import qualified Data.Monoid as M
import qualified Control.Applicative as A

data Tree a = EmptyTree | Node a (Tree a) (Tree a) deriving (Show)

newtype MyInt = MyInt Int deriving (Show, Ord, Eq)

treeInsert :: (Ord a) => a -> Tree a -> Tree a
treeInsert x EmptyTree = Node x EmptyTree EmptyTree
treeInsert x all@(Node y left right)
	| x == y = all
	| x < y = Node y (treeInsert x left) right
	| x > y = Node y left (treeInsert x right)
	
constructTree :: (Ord a) => [a] -> Tree a 
constructTree = foldr treeInsert EmptyTree	

instance F.Foldable Tree where
	foldMap f EmptyTree = M.mempty
	foldMap f (Node x left right) = (F.foldMap f left) `M.mappend` (f x) `M.mappend` (F.foldMap f right)
	
-- instance M.Monoid MyInt where? Needless!	
	
myPlus :: MyInt -> MyInt -> MyInt
(MyInt x) `myPlus` (MyInt y) = MyInt (x * y)
	
testTree = constructTree . map MyInt $ [1..6]

result = F.foldl myPlus (MyInt 1) testTree