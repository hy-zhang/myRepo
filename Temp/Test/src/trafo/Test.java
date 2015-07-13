package trafo;

import tree.ExpAlg;

public class Test {

	static <Exp> Exp build(ExpAlg<Exp> alg) {
		Desugar<Exp> d = new Desugar<Exp>() {
			public ExpAlg<Exp> expAlg() { return alg; }
		};
		return d.Add(d.Double(d.Var("a")), d.Lit(5));
	}
	
	public static void main(String args[]) {
		PrettyPrint printAlg = new PrettyPrint();
		System.out.println(build(printAlg));
	}
	
}
