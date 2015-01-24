import java.util.*;
import java.util.function.*;

class StringMonoid implements Monoid<String> {
	public String join(String x, String y) { return x + " " + y; }
	public String empty() { return ""; }
}

public class SybTest {
	
	static Employee ralf() {
		return new Employee(new Person("Ralf", "Amsterdam"), new Salary(8000.0f));
	}
	
	static Employee joost() {
		return new Employee(new Person("Joost", "Amsterdam"), new Salary(1000.0f));
	}
	
	static Employee marlow() {
		return new Employee(new Person("Marlow", "Cambridge"), new Salary(2000.0f));
	}
	
	static Employee blair() {
		return new Employee(new Person("Blair", "London"), new Salary(100000.0f));
	}
	
	static Company genCom() {
		SubUnit s1 = new SubUnit(joost());
		SubUnit s2 = new SubUnit(marlow());
		List<SubUnit> s12 = Arrays.asList(s1, s2);
		Dept d1 = new Dept("Research", ralf(), s12);
		Dept d2 = new Dept("Strategy", blair(), Collections.emptyList());
		List<Dept> d12 = Arrays.asList(d1, d2);
		return new Company(d12);
	}
	
	static Function<Super, Super> inc() {
		return x -> {
			if (x instanceof Salary) ((Salary)x).s *= 1.1;
			else x.map(inc());
			return x;
		};
	}
	
	static Function<Super, Super> hide() {
		return x -> {
			if (x instanceof Person) {
				if (((Person)x).a.equals("Amsterdam")) ((Person)x).n = "***";
			} else x.map(hide());
			return x;
		};
	}
	
	static StringMonoid mStr = new StringMonoid();

	static Function<Super, String> info() {
		return x -> {
			if (x instanceof Person) return ((Person)x).n; 
			else return x.mapQ(info(), mStr);
		};
	}
	
	static void print(Company c) {
		System.out.println(c.mapQ(info(), mStr));
		System.out.println("Ralf   = " + c.d.get(0).m.s.s);
		System.out.println("Joost  = " + c.d.get(0).s.get(0).e.s.s);
		System.out.println("Marlow = " + c.d.get(0).s.get(1).e.s.s);
		System.out.println("Blair  = " + c.d.get(1).m.s.s);
		System.out.println();
	}
	
	public static void main(String args[]) {
		Company c = genCom();
		print(c);
		c.map(inc());
		print(c);
		c.map(hide());
		print(c);
	}
	
}
