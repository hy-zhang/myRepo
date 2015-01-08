import java.util.*;
import java.util.function.Function;

public class Lambdas {
	
	void OnTest() {
		
		/* Simple lambda expressions */
		Function<Integer, Integer> doubleMe = x -> 2 * x;
		System.out.println(doubleMe.apply(5));
		System.out.println(((Function<Integer, Integer>) x -> 2 * x).apply(5));
		
		/* Foreach, {statements} */
		List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5);
		numList.forEach(x -> System.out.print(x + " "));
		System.out.println();
		numList.forEach(x -> {
			int y = doubleMe.apply(x);
			System.out.print(y + " ");
		});
		System.out.println();
		
		/* {... return stmts} */
		Function<Integer, Boolean> isPrime = x -> {
			boolean res = true;
			for (int i = 2; i < x; i++)
				if (x % i == 0) res = false;
			return res;
		};
		System.out.println(isPrime.apply(5));
		
		/* Higher-order and currying */
		Function<Function<Integer, Integer>, Function<Integer, Integer>> applyTwice = x -> (y -> x.apply(x.apply(y)));
		System.out.println(applyTwice.apply(doubleMe).apply(5));
		Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;
		System.out.println(add.apply(3).apply(5));
		
	}
	
	public static void main(String[] args) {
		Lambdas l = new Lambdas();
		l.OnTest();
	}

}

