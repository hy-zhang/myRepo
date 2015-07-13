package transform;

import java.util.function.Function;
import java.util.List;
import java.util.ArrayList;
import tree.DoubleAlg;

public interface G_DoubleAlgTransform<A, B0> extends DoubleAlg<Function<A, B0>> {

	DoubleAlg<B0> doubleAlg();

	default <B> List<B> substListDoubleAlg(List<Function<A, B>> list, A acc) {
		List<B> res = new ArrayList<B>();
		for (Function<A, B> i : list)
			res.add(i.apply(acc));
		return res;
	}

	@Override
	default Function<A, B0> Double(Function<A, B0> p0) {
		return acc -> doubleAlg().Double(p0.apply(acc));
	}

}