package trees;

public interface TreeAlg<Tree> {
	Tree Leaf(int x);
	Tree Fork(int x, Tree l, Tree r);
}
