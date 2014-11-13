package transform;

import subst.Subst;
import trees.TreeAlg;

public class TreeAlgTransform<A, B> implements TreeAlg<Subst<A, B>> {
	
	TreeAlg<A> alg;
	
	public TreeAlgTransform(TreeAlg<A> alg) { this.alg = alg; }

	@Override
	public Subst<A, B> Leaf(int x) {
		return acc -> alg.Leaf(x);
	}

	@Override
	public Subst<A, B> Fork(int x, Subst<A, B> l, Subst<A, B> r) {
		return acc -> alg.Fork(x, l.subst(acc), r.subst(acc));
	}

}
