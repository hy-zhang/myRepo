package algebras;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	static <Any> Any ralf(SybAlg<Any, Any, Any, Any, Any, Any> alg) {
		return alg.E(alg.P("Ralf", "Amsterdam"), alg.S(8000.0));
	}
	
	static <Any> Any joost(SybAlg<Any, Any, Any, Any, Any, Any> alg) {
		return alg.E(alg.P("Joost", "Amsterdam"), alg.S(1000.0));
	}
	
	static <Any> Any marlow(SybAlg<Any, Any, Any, Any, Any, Any> alg) {
		return alg.E(alg.P("Marlow", "Cambridge"), alg.S(2000.0));
	}
	
	static <Any> Any blair(SybAlg<Any, Any, Any, Any, Any, Any> alg) {
		return alg.E(alg.P("Blair", "London"), alg.S(100000.0));
	}
	
	static <Any> Any Company(SybAlg<Any, Any, Any, Any, Any, Any> alg) {
		List<Any> s = (List<Any>)new ArrayList<Any>();
		s.add(alg.PU(joost(alg)));
		s.add(alg.PU(marlow(alg)));
		List<Any> d = (List<Any>)new ArrayList<Any>();
		d.add(alg.D("Research", ralf(alg), s));
		d.add(alg.D("Strategy", blair(alg), (List<Any>)new ArrayList<Any>()));
		return alg.C(d);
	}
	
	public static void main(String args[]) {
		Printer company1 = Company(new StringQueryAlgebra());
		Privacy privacy = new Privacy(new StringQueryAlgebra());
		Printer company2 = Company(privacy).subst("Marlow");
		System.out.println(company1.getString());
		System.out.println(company2.getString());
	}
}
