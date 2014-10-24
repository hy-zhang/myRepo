package algebras;

import java.util.ArrayList;
import java.util.List;

public class ASubstAlg implements SybAlg<ASubst, ASubst, ASubst, ASubst, ASubst, ASubst> {

	SybAlg<Printer, Printer, Printer, Printer, Printer, Printer> alg;
	
	ASubstAlg(SybAlg<Printer, Printer, Printer, Printer, Printer, Printer> alg) { this.alg = alg; }
	
	List<Printer> getList(String e, List<ASubst> list) {
		List<Printer> res = new ArrayList<Printer>();
		for (ASubst element : list) res.add(element.subst(e));
		return res;
	}
	
	@Override
	public ASubst C(List<ASubst> depts) {
		return (e) -> alg.C(getList(e, depts));
	}

	@Override
	public ASubst D(String name, ASubst manager, List<ASubst> subUnits) {
		return (e) -> alg.D(name, manager.subst(e), getList(e, subUnits));
	}

	@Override
	public ASubst PU(ASubst employee) {
		return (e) -> alg.PU(employee.subst(e));
	}

	@Override
	public ASubst DU(ASubst dept) {
		return (e) -> alg.DU(dept.subst(e));
	}

	@Override
	public ASubst E(ASubst p, ASubst s) {
		return (e) -> alg.E(p.subst(e), s.subst(e));
	}

	@Override
	public ASubst P(String name, String address) {
		return (e) -> alg.P(name, address);
	}

	@Override
	public ASubst S(double salary) {
		return (e) -> alg.S(salary);
	}

}
