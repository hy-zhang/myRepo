module Tree (
	Tree(..)
,	singleton
, 	treeInsert
, 	construct
) where

data Tree a = EmptyTree | Node a (Tree a) (Tree a) 
	deriving (Show)
	
singleton :: a -> Tree a
singleton x = Node x EmptyTree EmptyTree

treeInsert :: (Ord a) => a -> Tree a -> Tree a
treeInsert x EmptyTree = singleton x
treeInsert x (Node y left right)
	| x == y = Node x left right
	| x < y = Node y (treeInsert x left) right
	| otherwise = Node y left (treeInsert x right)
	
instance (Eq a) => Eq (Tree a) where 
	EmptyTree == EmptyTree = True
	EmptyTree == Node _ _ _ = False
	Node a left1 right1 == Node b left2 right2 = (a == b) && (left1 == left2) && (right1 == right2)
	
construct :: (Ord a) => [a] -> Tree a
construct = foldr treeInsert EmptyTree
	