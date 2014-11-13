package transform;

import subst.Subst;
import trees.TreeAlg;

public class TreeAlgExample extends TreeAlgTransform<String, Integer> {

	public TreeAlgExample(TreeAlg<String> alg) {
		super(alg);
	}

	@Override
	public Subst<String, Integer> Leaf(int x) {
		return acc -> alg.Leaf(x + acc);
	}

	@Override
	public Subst<String, Integer> Fork(int x, Subst<String, Integer> l,
			Subst<String, Integer> r) {
		return acc -> alg.Fork(x + acc, l.subst(x + acc), r.subst(x + acc));
	}

}
