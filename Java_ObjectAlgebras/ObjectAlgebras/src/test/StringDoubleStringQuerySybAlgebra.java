package test;

import library.Pair;
import query.CombineQuerySybAlg;
import query.QuerySybAlg;

public class StringDoubleStringQuerySybAlgebra extends CombineQuerySybAlg<Pair<String, Double>, String> {

	public StringDoubleStringQuerySybAlgebra(QuerySybAlg<Pair<String, Double>> query1, QuerySybAlg<String> query2) {
		super(query1, query2);
	}

}
