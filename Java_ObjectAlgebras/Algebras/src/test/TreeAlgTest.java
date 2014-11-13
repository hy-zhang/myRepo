package test;

import query.TreeAlgQuery;
import transform.TreeAlgExample;
import trees.TreeAlg;

public class TreeAlgTest {
	
	static <A> A genTree(TreeAlg<A> alg) {
		return alg.Fork(1, alg.Fork(2, alg.Leaf(4), alg.Leaf(5)), alg.Fork(3, alg.Leaf(6), alg.Leaf(7)));
	}
	
	public static void main(String args[]) {
		TreeAlgQuery query = new TreeAlgQuery();
		TreeAlgExample trans = new TreeAlgExample(query);
		
		// In order. 1 2 4 5 3 6 7
		System.out.println(genTree(query));
		
		// In order. 1 3 7 8 4 10 11
		System.out.println(genTree(trans).subst(0));
	}

}
