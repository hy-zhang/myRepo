package test;

import query.CombineQuerySybAlg;
import query.QuerySybAlg;

public class StringDoublePairQuerySybAlgebra2 extends CombineQuerySybAlg<String, Double> {

	public StringDoublePairQuerySybAlgebra2(QuerySybAlg<String> query1, QuerySybAlg<Double> query2) {
		super(query1, query2);
	}

}
