package test;

import java.lang.Double;

import combine.Combine;

import library.Monoid;
import library.Pair;
import query.QuerySybAlg;

public class StringDoublePairQuerySybAlgebra extends Combine<String, Double> {

	StringDoublePairQuerySybAlgebra(Monoid<Pair<String, Double>> m) {
		super(m);
	}

	@Override
	public Pair<String, Double> P(String name, String address){
		return new Pair(name, 0.0);
	}
	
	@Override
	public Pair<String, Double> S(double p0){
		return new Pair("", p0);
	}
}
