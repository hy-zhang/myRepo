package combinator;

import java.util.ArrayList;
import java.util.List;
import library.Pair;
import trees.StmAlg;

public class CombineStmAlg<A0, A1, A2, B0, B1, B2>
	implements StmAlg<Pair<A0, B0>, Pair<A1, B1>, Pair<A2, B2>> {

	public StmAlg<A0, A1, A2> alg1;
	public StmAlg<B0, B1, B2> alg2;

	public CombineStmAlg(StmAlg<A0, A1, A2> _alg1, StmAlg<B0, B1, B2> _alg2) {
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

	public Pair<A1, B1> EAdd(Pair<A1, B1> p0, Pair<A1, B1> p1) {
		return new Pair<A1, B1>(alg1.EAdd(p0.a(), p1.a()), alg2.EAdd(p0.b(), p1.b()));
	}

	public Pair<A1, B1> EInt(int p0) {
		return new Pair<A1, B1>(alg1.EInt(p0), alg2.EInt(p0));
	}

	public Pair<A1, B1> EStm(Pair<A0, B0> p0) {
		return new Pair<A1, B1>(alg1.EStm(p0.a()), alg2.EStm(p0.b()));
	}

	public Pair<A1, B1> EVar(java.lang.String p0) {
		return new Pair<A1, B1>(alg1.EVar(p0), alg2.EVar(p0));
	}

	public Pair<A0, B0> SAss(java.lang.String p0, Pair<A1, B1> p1) {
		return new Pair<A0, B0>(alg1.SAss(p0, p1.a()), alg2.SAss(p0, p1.b()));
	}

	public Pair<A0, B0> SBlock(List<Pair<A0, B0>> p0) {
		return new Pair<A0, B0>(alg1.SBlock(getPairList(p0).a()), alg2.SBlock(getPairList(p0).b()));
	}

	public Pair<A0, B0> SDecl(Pair<A2, B2> p0, java.lang.String p1) {
		return new Pair<A0, B0>(alg1.SDecl(p0.a(), p1), alg2.SDecl(p0.b(), p1));
	}

	public Pair<A0, B0> SReturn(Pair<A1, B1> p0) {
		return new Pair<A0, B0>(alg1.SReturn(p0.a()), alg2.SReturn(p0.b()));
	}

	public Pair<A2, B2> TFloat() {
		return new Pair<A2, B2>(alg1.TFloat(), alg2.TFloat());
	}

	public Pair<A2, B2> TInt() {
		return new Pair<A2, B2>(alg1.TInt(), alg2.TInt());
	}

}