package trees;

import java.util.List;

public interface SybAlg<Company, Dept, SubUnit, Employee, Person, Salary> {	
	public Company C(List<Dept> depts);
	public Dept D(String name, Employee manager, List<SubUnit> subUnits);
	public SubUnit PU(Employee employee);
	public SubUnit DU(Dept dept);
	public Employee E(Person p, Salary s);
	public Person P(String name, String address);
	public Salary S(double salary);
}
