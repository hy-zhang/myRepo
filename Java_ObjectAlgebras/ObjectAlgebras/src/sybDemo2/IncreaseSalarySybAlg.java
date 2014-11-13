package sybDemo2;

import java.util.ArrayList;
import java.util.List;

import trees.SybAlg;

//BEGIN_INCREASE_SALARY
public class IncreaseSalarySybAlg implements SybAlg<G_Company, G_Dept, G_SubUnit, G_Employee, G_Person, G_Salary>{
	public  G_Company C(List<G_Dept> p0) {
		return new G_Company() {
			public <Company,Dept,SubUnit,Employee,Person,Salary> Company accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg) {
				List<Dept> gp0 = new ArrayList<Dept>();
				for (G_Dept s: p0) {
					gp0.add(s.accept(alg));
				}
				return alg.C(gp0);
			}
		};
	}
	public  G_Dept D(java.lang.String p0, G_Employee p1, List<G_SubUnit> p2) {
		return new G_Dept() {
			public <Company,Dept,SubUnit,Employee,Person,Salary> Dept accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg) {
				List<SubUnit> gp2 = new java.util.ArrayList<SubUnit>();
				for (G_SubUnit s: p2) {
					gp2.add(s.accept(alg));
				}
				return alg.D(p0, p1.accept(alg), gp2);
			}
		};
	}
	public  G_SubUnit DU(G_Dept p0) {
		return new G_SubUnit() {
			public <Company,Dept,SubUnit,Employee,Person,Salary> SubUnit accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg) {
				return alg.DU(p0.accept(alg));
			}
		};
	}
	public  G_Employee E(G_Person p0, G_Salary p1) {
		return new G_Employee() {
			public <Company,Dept,SubUnit,Employee,Person,Salary> Employee accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg) {
				return alg.E(p0.accept(alg), p1.accept(alg));
			}
		};
	}
	public  G_Person P(java.lang.String p0, java.lang.String p1) {
		return new G_Person() {
			public <Company,Dept,SubUnit,Employee,Person,Salary> Person accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg) {
				return alg.P(p0, p1);
			}
		};
	}
	public  G_SubUnit PU(G_Employee p0) {
		return new G_SubUnit() {
			public <Company,Dept,SubUnit,Employee,Person,Salary> SubUnit accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg) {
				return alg.PU(p0.accept(alg));
			}
		};
	}
	public  G_Salary S(float p0) {
		return new G_Salary() {
			public <Company,Dept,SubUnit,Employee,Person,Salary> Salary accept(SybAlg<Company,Dept,SubUnit,Employee,Person,Salary> alg) {
				return alg.S(1.1f*p0);
			}
		};
	}
}
//END_INCREASE_SALARY