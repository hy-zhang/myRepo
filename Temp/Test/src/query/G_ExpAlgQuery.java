package query;

import library.Monoid;
import tree.ExpAlg;

public interface G_ExpAlgQuery<A0> extends ExpAlg<A0> {

	Monoid<A0> mExp();

	@Override
	default A0 Add(A0 p0, A0 p1) {
		A0 res = mExp().empty();
		res = mExp().join(res, p0);
		res = mExp().join(res, p1);
		return res;
	}

	@Override
	default A0 Lit(int p0) {
		A0 res = mExp().empty();
		return res;
	}

	@Override
	default A0 Var(java.lang.String p0) {
		A0 res = mExp().empty();
		return res;
	}

}