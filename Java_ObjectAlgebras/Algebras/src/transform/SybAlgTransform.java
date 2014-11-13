package transform;

import subst.Subst;
import trees.SybAlg;
import java.util.ArrayList;
import java.util.List;

public class SybAlgTransform<A, B> implements SybAlg<Subst<A, B>, Subst<A, B>, Subst<A, B>, Subst<A, B>, Subst<A, B>, Subst<A, B>> {
	
	SybAlg<A, A, A, A, A, A> alg;
	
	public SybAlgTransform(SybAlg<A, A, A, A, A, A> alg) { this.alg = alg; }
	
	List<A> substList(List<Subst<A, B>> list, B acc) {
		List<A> res = new ArrayList<A>();
		for (Subst<A, B> i : list) 
			res.add(i.subst(acc));
		return res;
	}

	@Override
	public Subst<A, B> C(List<Subst<A, B>> depts) {
		return acc -> alg.C(substList(depts, acc));
	}

	@Override
	public Subst<A, B> D(String name, Subst<A, B> manager,
			List<Subst<A, B>> subUnits) {
		return acc -> alg.D(name, manager.subst(acc), substList(subUnits, acc));
	}

	@Override
	public Subst<A, B> PU(Subst<A, B> employee) {
		return acc -> alg.PU(employee.subst(acc));
	}

	@Override
	public Subst<A, B> DU(Subst<A, B> dept) {
		return acc -> alg.DU(dept.subst(acc));
	}

	@Override
	public Subst<A, B> E(Subst<A, B> p, Subst<A, B> s) {
		return acc -> alg.E(p.subst(acc), s.subst(acc));
	}

	@Override
	public Subst<A, B> P(String name, String address) {
		return acc -> alg.P(name, address);
	}

	@Override
	public Subst<A, B> S(double salary) {
		return acc -> alg.S(salary);
	}

}
