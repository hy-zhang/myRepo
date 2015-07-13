package query;

import library.Monoid;
import tree.ExpAlg;

public interface ExpAlgQuery<R> extends ExpAlg<R> {

	Monoid<R> m();

	default R Add(R p0, R p1) {
		R res = m().empty();
		res = m().join(res, p0);
		res = m().join(res, p1);
		return res;
	}

	default R Lit(int p0) {
		R res = m().empty();
		return res;
	}

	default R Var(java.lang.String p0) {
		R res = m().empty();
		return res;
	}

}