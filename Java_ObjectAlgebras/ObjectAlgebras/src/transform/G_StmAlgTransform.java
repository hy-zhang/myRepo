package transform;

import library.Subst;
import java.util.List;
import java.util.ArrayList;
import trees.StmAlg;

public class G_StmAlgTransform<A, B> implements StmAlg<Subst<A, B>, Subst<A, B>, Subst<A, B>> {

	public StmAlg<A, A, A> alg;

	public G_StmAlgTransform(StmAlg<A, A, A> alg) { this.alg = alg; }

	public List<A> substList(List<Subst<A, B>> list, B acc) {
		List<A> res = new ArrayList<A>();
		for (Subst<A, B> i : list)
			res.add(i.subst(acc));
		return res;
	}

	@Override
	public Subst<A, B> EAdd(Subst<A, B> p0, Subst<A, B> p1) {
		return acc -> alg.EAdd(p0.subst(acc), p1.subst(acc));
	}

	@Override
	public Subst<A, B> EInt(int p0) {
		return acc -> alg.EInt(p0);
	}

	@Override
	public Subst<A, B> EStm(Subst<A, B> p0) {
		return acc -> alg.EStm(p0.subst(acc));
	}

	@Override
	public Subst<A, B> EVar(java.lang.String p0) {
		return acc -> alg.EVar(p0);
	}

	@Override
	public Subst<A, B> SAss(java.lang.String p0, Subst<A, B> p1) {
		return acc -> alg.SAss(p0, p1.subst(acc));
	}

	@Override
	public Subst<A, B> SBlock(List<Subst<A, B>> p0) {
		return acc -> alg.SBlock(substList(p0, acc));
	}

	@Override
	public Subst<A, B> SDecl(Subst<A, B> p0, java.lang.String p1) {
		return acc -> alg.SDecl(p0.subst(acc), p1);
	}

	@Override
	public Subst<A, B> SReturn(Subst<A, B> p0) {
		return acc -> alg.SReturn(p0.subst(acc));
	}

	@Override
	public Subst<A, B> TFloat() {
		return acc -> alg.TFloat();
	}

	@Override
	public Subst<A, B> TInt() {
		return acc -> alg.TInt();
	}

}