package query;

import library.Monoid;
import tree.DoubleAlg;

public interface G_DoubleAlgQuery<A0> extends DoubleAlg<A0> {

	Monoid<A0> mExp();

	@Override
	default A0 Double(A0 p0) {
		A0 res = mExp().empty();
		res = mExp().join(res, p0);
		return res;
	}

}