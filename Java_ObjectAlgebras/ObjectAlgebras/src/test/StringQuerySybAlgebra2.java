package test;

import library.Monoid;
import query.QuerySybAlg;

public class StringQuerySybAlgebra2 extends QuerySybAlg<String> {

	public StringQuerySybAlgebra2(Monoid<String> m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String P(String name, String address){
		return address;
	}
	
}
