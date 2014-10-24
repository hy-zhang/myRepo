package algebras;

public class Privacy extends ASubstAlg {
	
	Privacy(SybAlg<Printer, Printer, Printer, Printer, Printer, Printer> alg) { super(alg); }
	
	@Override
	public ASubst P(String name, String address) {
		return (e) -> {
			if (name.equals(e)) return alg.P(name, "@@@"); 
			else return alg.P(name, address);
		};
	}
	
}
