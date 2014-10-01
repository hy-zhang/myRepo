package query;

import trees.SybAlg;
import library.Pair;
import library.PairMonoid;

import java.util.ArrayList;
import java.util.List;


public class CombineQuerySybAlg<A, B> extends QuerySybAlg<Pair<A, B>> {
	private QuerySybAlg<A> q1;
	private QuerySybAlg<B> q2;
	
	public CombineQuerySybAlg(QuerySybAlg<A> query1, QuerySybAlg<B> query2) {
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
		return new Pair(l1, l2);
	}

	public Pair<A, B> C(List<Pair<A, B>> p0) {
		Pair<List<A>, List<B>> res0 = getPairList(p0);
		return new Pair(q1.C(res0.a()), q2.C(res0.b()));
	}

	public Pair<A, B> D(String p0, Pair<A, B> p1, List<Pair<A, B>> p2) {
		Pair<List<A>, List<B>> res0 = getPairList(p2);
		return new Pair(q1.D(p0, p1.a(), res0.a()), q2.D(p0, p1.b(), res0.b()));
	}

	public Pair<A, B> PU(Pair<A, B> p0) {
		return new Pair(q1.PU(p0.a()), q2.PU(p0.b()));
	}

	public Pair<A, B> DU(Pair<A, B> p0) {
		return new Pair(q1.DU(p0.a()), q2.DU(p0.b()));
	}

	public Pair<A, B> E(Pair<A, B> p0, Pair<A, B> p1) {
		return new Pair(q1.E(p0.a(), p1.a()), q2.E(p0.b(), p1.b()));
	}

	public Pair<A, B> P(String p0, String p1) {
		return new Pair(q1.P(p0, p1), q2.P(p0, p1));
	}

	public Pair<A, B> S(double p0) {
		return new Pair(q1.S(p0), q2.S(p0));
	}
}
