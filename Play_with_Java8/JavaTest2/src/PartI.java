import java.util.function.*;

public class PartI {
	
	void OnTest() {

		/* Higher-order functions and currying */
		
		/* Haskell code */
		/* addThree x y z = x + y + z */
		/* addThree = \x -> \y -> \z -> x + y + z */
		/* addThree (x, y, z) = x + y + z */
		/* (Num a) => a -> a -> a -> a */
		/* (Num a) => (a, a, a) -> a */
		
		/* Java code */
		IntFunction<IntFunction<IntUnaryOperator>> addThree = x -> y -> z -> x + y + z;
		IntTriFunction addThree2 = (x, y, z) -> x + y + z;
		
		System.out.println("addThree 2 3 5 = " + addThree.apply(2).apply(3).applyAsInt(5));
		System.out.println("addThree2 (2, 3, 5) = " + addThree2.apply(2, 3, 5));
		
		/* Compilation error */
		/* System.out.println((x -> y -> z -> x + y + z).apply(2).apply(3).apply(5)); */
		
	}
	
	interface IntTriFunction {
		Integer apply(Integer a, Integer b, Integer c);
	}
	
	public static void main(String args[]) {
		PartI p = new PartI();
		p.OnTest();
	}
	
}
