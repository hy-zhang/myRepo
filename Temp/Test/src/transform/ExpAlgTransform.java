package transform;

import tree.ExpAlg;

public interface ExpAlgTransform<A0> extends ExpAlg<A0> {

	ExpAlg<A0> expAlg();

	@Override
	default A0 Add(A0 p0, A0 p1) {
		return expAlg().Add(p0, p1);
	}

	@Override
	default A0 Lit(int p0) {
		return expAlg().Lit(p0);
	}

	@Override
	default A0 Var(java.lang.String p0) {
		return expAlg().Var(p0);
	}

}