package transform;

import tree.DoubleAlg;

public interface DoubleAlgTransform<A0> extends DoubleAlg<A0> {

	DoubleAlg<A0> doubleAlg();

	@Override
	default A0 Double(A0 p0) {
		return doubleAlg().Double(p0);
	}

}