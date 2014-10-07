package query;

import java.util.ArrayList;
import java.util.List;
import library.Pair;
import library.PairMonoid;

public class CombineSybAlg<A, B> extends QuerySybAlg<Pair<A, B>> {

	private QuerySybAlg<A> q1;
	private QuerySybAlg<B> q2;

	public CombineSybAlg(QuerySybAlg<A> query1, QuerySybAlg<B> query2) {
		super(new PairMonoid<A, B>(query1.m(), query2.m()));
		q1 = query1;
		q2 = query2;
	}

	Pair<List<A>, List<B>> getPairList(List<Pair<A, B>> l) {
		List<A> l1 = (List<A>)new ArrayList<A>();
		List<B> l2 = (List<B>)new ArrayList<B>();
		for (Pair<A, B> element : l) {
			l1.add(element.a());
			l2.add(element.b());
		}
		return new Pair<List<A>, List<B>>(l1, l2);
	}

	public Pair<A, B> C(List<Pair<A, B>> p0) {
		return new Pair<A, B>(q1.C(getPairList(p0).a()), q2.C(getPairList(p0).b()));
	}

	public Pair<A, B> D(java.lang.String p0, Pair<A, B> p1, List<Pair<A, B>> p2) {
		return new Pair<A, B>(q1.D(p0, p1.a(), getPairList(p2).a()), q2.D(p0, p1.b(), getPairList(p2).b()));
	}

	public Pair<A, B> DU(Pair<A, B> p0) {
		return new Pair<A, B>(q1.DU(p0.a()), q2.DU(p0.b()));
	}

	public Pair<A, B> E(Pair<A, B> p0, Pair<A, B> p1) {
		return new Pair<A, B>(q1.E(p0.a(), p1.a()), q2.E(p0.b(), p1.b()));
	}

	public Pair<A, B> P(java.lang.String p0, java.lang.String p1) {
		return new Pair<A, B>(q1.P(p0, p1), q2.P(p0, p1));
	}

	public Pair<A, B> PU(Pair<A, B> p0) {
		return new Pair<A, B>(q1.PU(p0.a()), q2.PU(p0.b()));
	}

	public Pair<A, B> S(double p0) {
		return new Pair<A, B>(q1.S(p0), q2.S(p0));
	}

}