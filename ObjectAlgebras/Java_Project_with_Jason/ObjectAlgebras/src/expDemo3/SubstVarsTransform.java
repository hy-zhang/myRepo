package expDemo3;

//BEGIN_SUBSTVARS_TRANSFORM
public class SubstVarsTransform implements ExpAlgTransform{
	private String s;
	private G_Exp gExp; 
	public SubstVarsTransform(String s, G_Exp gExp){
		this.s = s;
		this.gExp = gExp;
	}
	@Override
	public G_Exp Var(String ss) {
		return new G_Exp() {
			@Override
			public <Exp> Exp accept(ExpAlg<Exp> alg) {
				if (ss.equals(s)) return gExp.accept(alg);
				else return alg.Var(ss);
			}
		};
	}
	
}
//END_SUBSTVARS_TRANSFORM