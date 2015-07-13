package combinator;

import java.util.ArrayList;
import java.util.List;
import library.Pair;
import tree.ExpAlg;

public class CombineExpAlg<A0, B0>
	implements ExpAlg<Pair<A0, B0>> {

	public ExpAlg<A0> alg1;
	public ExpAlg<B0> alg2;

	public CombineExpAlg(ExpAlg<A0> _alg1, ExpAlg<B0> _alg2) {
		alg1 = _alg1;
		alg2 = _alg2;
	}

	private <A, B> Pair<List<A>, List<B>> getPairList(List<Pair<A, B>> l) {
		List<A> l1 = (List<A>)new ArrayList<A>();
		List<B> l2 = (List<B>)new ArrayList<B>();
		for (Pair<A, B> element : l) {
			l1.add(element.a());
			l2.add(element.b());
		}
		return new Pair<List<A>, List<B>>(l1, l2);
	}

	public Pair<A0, B0> Add(Pair<A0, B0> p0, Pair<A0, B0> p1) {
		return new Pair<A0, B0>(alg1.Add(p0.a(), p1.a()), alg2.Add(p0.b(), p1.b()));
	}

	public Pair<A0, B0> Lit(int p0) {
		return new Pair<A0, B0>(alg1.Lit(p0), alg2.Lit(p0));
	}

	public Pair<A0, B0> Var(java.lang.String p0) {
		return new Pair<A0, B0>(alg1.Var(p0), alg2.Var(p0));
	}

}