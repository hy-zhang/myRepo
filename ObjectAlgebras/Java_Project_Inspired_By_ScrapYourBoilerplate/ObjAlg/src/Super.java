import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

interface Monoid<R> {
	R join(R x, R y);
	R empty();
	default R fold(List<R> lr) {
		R res = empty();
		for (R r : lr) res = join(res, r);
		return res;
	}
}

class Super { 
	void map(Function<Super, Super> f) {}
	<E> E mapQ(Function<Super, E> f, Monoid<E> m) { return m.empty(); }
}
	
class Company extends Super {
	List<Dept> d;
	Company(List<Dept> d) { this.d = d; }
	void map(Function<Super, Super> f) {
		d = d.stream().map(x ->(Dept)(f.apply((Super)x))).collect(Collectors.toList());
	}
	<E> E mapQ(Function<Super, E> f, Monoid<E> m) {
		E res = m.empty();
		res = m.join(res, m.fold(d.stream().map(x -> f.apply((Super)x)).collect(Collectors.toList())));
		return res;
	}
}
	
class Dept extends Super {
	String n; Employee m; List<SubUnit> s;
	Dept(String n, Employee m, List<SubUnit> s) { this.n = n; this.m = m; this.s = s; }
	void map(Function<Super, Super> f) {
		if (m != null) m = (Employee)f.apply((Super)m);
		s = s.stream().map(x ->(SubUnit)(f.apply((Super)x))).collect(Collectors.toList());
	}
	<E> E mapQ(Function<Super, E> f, Monoid<E> m0) {
		E res = m0.empty();
		if (m != null) res = m0.join(res, f.apply((Super)m));
		res = m0.join(res, m0.fold(s.stream().map(x -> f.apply((Super)x)).collect(Collectors.toList())));
		return res;
	}
}
	
class SubUnit extends Super {
	Employee e; Dept d;
	SubUnit(Employee e) { this.e = e; }
	SubUnit(Dept d) { this.d = d; }
	void map(Function<Super, Super> f) {
		if (e != null) e = (Employee)f.apply((Super)e);
		if (d != null) d = (Dept)f.apply((Super)d);
	}
	<E> E mapQ(Function<Super, E> f, Monoid<E> m) {
		E res = m.empty();
		if (e != null) res = m.join(res, f.apply((Super)e));
		if (d != null) res = m.join(res, f.apply((Super)d));
		return res;
	}
}
	
class Employee extends Super {
	Person p; Salary s;
	Employee(Person p, Salary s) { this.p = p; this.s = s; }
	void map(Function<Super, Super> f) {
		if (p != null) p = (Person)f.apply((Super)p);
		if (s != null) s = (Salary)f.apply((Super)s);
	}
	<E> E mapQ(Function<Super, E> f, Monoid<E> m) {
		E res = m.empty();
		if (p != null) res = m.join(res, f.apply((Super)p));
		if (s != null) res = m.join(res, f.apply((Super)s));
		return res;
	}
}
	
class Person extends Super {
	String n, a;
	Person(String n, String a) { this.n = n; this.a = a; }
}
	
class Salary extends Super {
	float s;
	Salary(float s) { this.s = s; }
}
