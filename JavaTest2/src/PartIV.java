
import java.util.Optional;
import java.util.function.Function;


public class PartIV {
	
	/* Optional Monad compared to Maybe in Haskell */
	void OnTest() {
		System.out.println(
			Optional.of(new IntPair(0, 0))
				.flatMap(landLeft(1))
				.flatMap(landRight(5))
				.flatMap(landLeft(4))
				.orElse(new IntPair(-1, -1))
				.print());
	}
	
	Function<IntPair, Optional<IntPair>> landLeft(int n) {
		return p -> {
			if (p.a + n >= 0 && Math.abs(p.a + n - p.b) < 4) return Optional.of(new IntPair(p.a + n, p.b));
			return Optional.empty();
		};
	}
	
	Function<IntPair, Optional<IntPair>> landRight(int n) {
		return p -> {
			if (p.b + n >= 0 && Math.abs(p.b + n - p.a) < 4) return Optional.of(new IntPair(p.a, p.b + n));
			return Optional.empty();
		};
	}
	
	public static void main(String args[]) {
		PartIV p = new PartIV();
		p.OnTest();	
	}
	
}

class IntPair {
	Integer a; Integer b;
	IntPair(Integer a, Integer b) { this.a = a; this.b = b; }
	String print() { return "(" + a + ", " + b + ")"; }
}
