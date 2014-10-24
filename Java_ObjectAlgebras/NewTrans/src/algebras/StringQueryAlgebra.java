package algebras;

import java.util.List;

public class StringQueryAlgebra implements SybAlg<Printer, Printer, Printer, Printer, Printer, Printer> {

	@Override
	public Printer C(List<Printer> depts) {
		return new Printer() {
			public String getString() {
				String res = "";
				for (Printer dept : depts) 
					res += dept.getString() + " ";
				return res;
			}
			public int getInt() {
				return 0;
			}
		};
	}

	@Override
	public Printer D(String name, Printer manager, List<Printer> subUnits) {
		return new Printer() {
			public String getString() {
				String res = "";
				res += name + " ";
				res += manager.getString() + " ";
				for (Printer subUnit : subUnits) 
					res += subUnit.getString() + " ";
				return res;
			}
			public int getInt() {
				return 0;
			}
		};
	}

	@Override
	public Printer PU(Printer employee) {
		return new Printer() {
			public String getString() {
				String res = "";
				res += employee.getString() + " ";
				return res;
			}
			public int getInt() {
				return 0;
			}
		};
	}

	@Override
	public Printer DU(Printer dept) {
		return new Printer() {
			public String getString() {
				String res = "";
				res += dept.getString() + " ";
				return res;
			}
			public int getInt() {
				return 0;
			}
		};
	}

	@Override
	public Printer E(Printer p, Printer s) {
		return new Printer() {
			public String getString() {
				String res = "";
				res += p.getString() + " ";
				res += s.getString() + " ";
				return res;
			}
			public int getInt() {
				return 0;
			}
		};
	}

	@Override
	public Printer P(String name, String address) {
		return new Printer() {
			public String getString() {
				String res = "";
				res += name + " ";
				res += address + " ";
				return res;
			}
			public int getInt() {
				return 0;
			}
		};
	}

	@Override
	public Printer S(double salary) {
		return new Printer() {
			public String getString() {
				String res = "";
				return res;
			}
			public int getInt() {
				return 0;
			}
		};
	}

}
