package monoid;

import library.Monoid;
import library.PairMonoid;

public class StringDoublePairMonoid extends PairMonoid<String, Double> {

	public StringDoublePairMonoid(Monoid<String> m1, Monoid<Double> m2) {
		super(m1, m2);
	}
	
}
