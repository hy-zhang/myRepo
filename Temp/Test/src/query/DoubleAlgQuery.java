package query;

import library.Monoid;
import tree.DoubleAlg;

public interface DoubleAlgQuery<R> extends DoubleAlg<R> {

	Monoid<R> m();

	default R Double(R p0) {
		R res = m().empty();
		res = m().join(res, p0);
		return res;
	}

}