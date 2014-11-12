package transform;

import java.util.List;

import subst.Subst;
import trees.SybAlg;

public class SybAlgExample extends SybAlgTransform<String, String> {

	public SybAlgExample(SybAlg<String, String, String, String, String, String> alg) {
		super(alg);
	}

	@Override
	public Subst<String, String> D(String name, Subst<String, String> manager,
			List<Subst<String, String>> subUnits) {
		return acc -> alg.D(name, manager.subst(name), substList(subUnits, name));
	}

	@Override
	public Subst<String, String> P(String name, String address) {
		return acc -> alg.P(name + "(" + acc + ")", address);
	}

}
