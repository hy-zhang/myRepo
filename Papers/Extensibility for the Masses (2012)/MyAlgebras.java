public class MyAlgebras {
	public static void main(String args[]) {
		Eval exp1 = new Test<Eval>().myExp(new EvalSubExpAlg());
		Printer exp2 = new Test<Printer>().myExp(new PrintExpAlg());
		System.out.println(exp1.eval());
		System.out.println(exp2.printer());
	}
}

class Test<E> {
	E myExp(SubExpAlg<E> e) {
		return e.sub(e.add(e.lit(5), e.lit(3)), e.lit(2));
	}
}

interface ExpAlg<E> {
	E lit(int x);
	E add(E x, E y);
}

interface Eval {
	int eval();
}


class EvalExpAlg implements ExpAlg<Eval> {
	public Eval lit(final int x) {
		return new Eval() {
			public int eval() {
				return x;
			}
		};
	}
	public Eval add(final Eval x, final Eval y) {
		return new Eval() {
			public int eval() {
				return x.eval() + y.eval();
			}
		};
	}
}

interface SubExpAlg<E> extends ExpAlg<E> {
	E sub(E x, E y);
}

class EvalSubExpAlg extends EvalExpAlg implements SubExpAlg<Eval> {
	public Eval sub(final Eval x, final Eval y) {
		return new Eval() {
			public int eval() {
				return x.eval() - y .eval();
			}
		};
	}
}

interface PrintExp<E> extends SubExpAlg<E> {
}

interface Printer {
	String printer();
}

class PrintExpAlg implements PrintExp<Printer> {
	public Printer lit(final int x) {
		return new Printer() {
			public String printer() {
				return ""+x;
			}
		};
	}
	public Printer add(final Printer x, final Printer y) {
		return new Printer() {
			public String printer() {
				return "(" + x.printer() + "+" + y.printer() + ")";
			}
		};
	}
	public Printer sub(final Printer x, final Printer y) {
		return new Printer() {
			public String printer() {
				return "(" + x.printer() + "-" + y.printer() + ")";
			}
		};
	}
}
