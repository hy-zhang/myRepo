package combine;

import query.QuerySybAlg;
import library.Monoid;
import library.Pair;

public class Combine<A, B> extends QuerySybAlg<Pair<A, B>> {
	public Combine(Monoid<Pair<A, B>> m) {
		super(m);
	}	 
}
