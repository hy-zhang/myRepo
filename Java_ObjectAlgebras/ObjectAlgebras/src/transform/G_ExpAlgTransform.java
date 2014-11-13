package transform;

import library.Subst;
import java.util.List;
import java.util.ArrayList;
import trees.ExpAlg;

public class G_ExpAlgTransform<A, B> implements ExpAlg<Subst<A, B>> {

	public ExpAlg<A> alg;

	public G_ExpAlgTransform(ExpAlg<A> alg) { this.alg = alg; }

	public List<A> substList(List<Subst<A, B>> list, B acc) {
		List<A> res = new ArrayList<A>();
		for (Subst<A, B> i : list)
			res.add(i.subst(acc));
		return res;
	}

	@Override
	public Subst<A, B> Add(Subst<A, B> p0, Subst<A, B> p1) {
		return acc -> alg.Add(p0.subst(acc), p1.subst(acc));
	}

	@Override
	public Subst<A, B> Lit(int p0) {
		return acc -> alg.Lit(p0);
	}

	@Override
	public Subst<A, B> Var(java.lang.String p0) {
		return acc -> alg.Var(p0);
	}

}