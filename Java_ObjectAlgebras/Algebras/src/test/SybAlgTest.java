package test;

import java.util.ArrayList;
import java.util.List;
import query.SybAlgQuery;
import transform.SybAlgExample;
import trees.SybAlg;

public class SybAlgTest {
	
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		ralf(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
			return alg.E(alg.P("Ralf", "Amsterdam"), alg.S(8000.0));
		}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		joost(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
			return alg.E(alg.P("Joost", "Amsterdam"), alg.S(1000.0));
		}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		marlow(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
			return alg.E(alg.P("Marlow", "Cambridge"), alg.S(2000.0));
		}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Employee 
		blair(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
			return alg.E(alg.P("Blair", "London"), alg.S(100000.0));
		}
	static <Company, Dept, SubUnit, Employee, Person, Salary> Company 
		genCom(SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> alg) {
			List<SubUnit> s = (List<SubUnit>)new ArrayList<SubUnit>();
			s.add(alg.PU(joost(alg)));
			s.add(alg.PU(marlow(alg)));
			List<Dept> d = (List<Dept>)new ArrayList<Dept>();
			d.add(alg.D("Research", ralf(alg), s));
			d.add(alg.D("Strategy", blair(alg), (List<SubUnit>)new ArrayList<SubUnit>()));
			return alg.C(d);
		}
	
	public static void main(String[] args) {
		SybAlgQuery query = new SybAlgQuery();
		SybAlgExample trans = new SybAlgExample(query);
		
		// Ralf Amsterdam  Joost Amsterdam  Marlow Cambridge   Blair London   
		System.out.println(genCom(query));
		
		// Ralf(Research) Amsterdam  Joost(Research) Amsterdam  Marlow(Research) Cambridge   Blair(Strategy) London   
		System.out.println(genCom(trans).subst("-"));
	}
}
