package transform;

import java.util.function.Function;
import java.util.List;
import java.util.ArrayList;
import tree.ExpAlg;

public interface G_ExpAlgTransform<A, B0> extends ExpAlg<Function<A, B0>> {

	ExpAlg<B0> expAlg();

	default <B> List<B> substListExpAlg(List<Function<A, B>> list, A acc) {
		List<B> res = new ArrayList<B>();
		for (Function<A, B> i : list)
			res.add(i.apply(acc));
		return res;
	}

	@Override
	default Function<A, B0> Add(Function<A, B0> p0, Function<A, B0> p1) {
		return acc -> expAlg().Add(p0.apply(acc), p1.apply(acc));
	}

	@Override
	default Function<A, B0> Lit(int p0) {
		return acc -> expAlg().Lit(p0);
	}

	@Override
	default Function<A, B0> Var(java.lang.String p0) {
		return acc -> expAlg().Var(p0);
	}

}