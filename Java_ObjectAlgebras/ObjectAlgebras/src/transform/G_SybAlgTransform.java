package transform;

import library.Subst;
import java.util.List;
import java.util.ArrayList;
import trees.SybAlg;

public class G_SybAlgTransform<A, B> implements SybAlg<Subst<A, B>, Subst<A, B>, Subst<A, B>, Subst<A, B>, Subst<A, B>, Subst<A, B>> {

	public SybAlg<A, A, A, A, A, A> alg;

	public G_SybAlgTransform(SybAlg<A, A, A, A, A, A> alg) { this.alg = alg; }

	public List<A> substList(List<Subst<A, B>> list, B acc) {
		List<A> res = new ArrayList<A>();
		for (Subst<A, B> i : list)
			res.add(i.subst(acc));
		return res;
	}

	@Override
	public Subst<A, B> C(List<Subst<A, B>> p0) {
		return acc -> alg.C(substList(p0, acc));
	}

	@Override
	public Subst<A, B> D(java.lang.String p0, Subst<A, B> p1, List<Subst<A, B>> p2) {
		return acc -> alg.D(p0, p1.subst(acc), substList(p2, acc));
	}

	@Override
	public Subst<A, B> DU(Subst<A, B> p0) {
		return acc -> alg.DU(p0.subst(acc));
	}

	@Override
	public Subst<A, B> E(Subst<A, B> p0, Subst<A, B> p1) {
		return acc -> alg.E(p0.subst(acc), p1.subst(acc));
	}

	@Override
	public Subst<A, B> P(java.lang.String p0, java.lang.String p1) {
		return acc -> alg.P(p0, p1);
	}

	@Override
	public Subst<A, B> PU(Subst<A, B> p0) {
		return acc -> alg.PU(p0.subst(acc));
	}

	@Override
	public Subst<A, B> S(float p0) {
		return acc -> alg.S(p0);
	}

}