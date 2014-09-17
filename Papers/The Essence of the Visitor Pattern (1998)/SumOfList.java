public class SumOfList {
	
	public static void main(String args[]) {
		System.out.println(new TypeCast().sum);
		System.out.println(new Dedicated().sum);
		System.out.println(new Visitors().sum);
	}
	
}

class TypeCast {
	
	int sum;
	
	interface List {}

	class Nil implements List {}
	
	class Cons implements List {
		int head;
		List tail;
		Cons(int h, List t) {
			head = h; tail = t;
		}
	}
	
	int getSum(List l) {
		if (l instanceof Nil) return 0;
		Cons c = (Cons) l;
		return c.head + getSum(c.tail);
	}
	
	TypeCast() {
		List l = new Cons(3, new Cons(4, new Cons(5, new Nil())));
		sum = getSum(l);
	}
	
}

class Dedicated {
	
	int sum;
	
	interface List {
		int sum();
	}
	
	class Nil implements List {
		public int sum() {
			return 0;
		}
	}
	
	class Cons implements List {
		int head;
		List tail;
		Cons(int h, List t) {
			head = h; tail = t;
		}
		public int sum() {
			return head + tail.sum();
		}
	}
	
	Dedicated() {
		List l = new Cons(3, new Cons(4, new Cons(5, new Nil())));
		sum = l.sum();
	}
	
}

class Visitors {
	
	int sum;
	
	interface List {
		void accept(Visitor v);
	}
	
	interface Visitor {
		void visit(Nil x);
		void visit(Cons x);
	}
	
	class Nil implements List {
		public void accept(Visitor v) {
			v.visit(this);
		}
	}
	
	class Cons implements List {
		int head;
		List tail;
		Cons(int h, List t) {
			head = h; tail = t;
		}
		public void accept(Visitor v) {
			v.visit(this);
		}
	}
	
	class SumVisitor implements Visitor {
		int sum = 0;
		public void visit(Nil x) {}
		public void visit(Cons x) {
			sum += x.head;
			x.tail.accept(this);
		}
	}
	
	Visitors() {
		List l = new Cons(3, new Cons(4, new Cons(5, new Nil())));
		SumVisitor v = new SumVisitor();
		l.accept(v);
		sum = v.sum;
	}
	
}