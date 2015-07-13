package util;

import transform.DoubleAlgTransform;
import tree.DoubleAlg;

public class DoubleAlgTrans<A0> implements DoubleAlgTransform<A0> {

	private DoubleAlg<A0> alg;

	public DoubleAlgTrans(DoubleAlg<A0> alg) {this.alg = alg;}

	public DoubleAlg<A0> doubleAlg() {return alg;}

}