import java.lang.annotation.*;
import java.time.*;
import java.util.*;
import java.util.function.*;

public class Lambdas {
	
	void OnTest() {
		
		/* Simple lambda expressions */
		Function<Integer, Integer> doubleMe = x -> 2 * x;
		System.out.println("doubleMe 5 = " + doubleMe.apply(5));
		System.out.println("doubleMe 5 = " + ((Function<Integer, Integer>) x -> 2 * x).apply(5));
		
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
		System.out.println("isPrime 5 = " + isPrime.apply(5));
		
		/* Higher-order and currying */
		Function<Function<Integer, Integer>, Function<Integer, Integer>> applyTwice = x -> y -> x.apply(x.apply(y));
		System.out.println("applyTwice doubleMe 5 = " + applyTwice.apply(doubleMe).apply(5));
		Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;
		System.out.println("add 3 5 = " + add.apply(3).apply(5));
		
		/* Custom interfaces */
		Util u = x -> x % 2 == 1;
		System.out.println("isOdd 5 = " + u.isOdd(5));

		/* Method references */
		System.out.println("applyTwice sqrt 16.0 = " + applyTwice(Math::sqrt).apply(16.0));
		numList.forEach(System.out::print);
		System.out.println();
		
		/* Annotations support */
		@SuppressWarnings("unused")
		@MyAnno int tempInt = 5;
		@SuppressWarnings("unused")
		@MyAnno Function<@MyAnno Integer, @MyAnno Double> tempFunc;
		
		/* Streams and Optional */
		Optional<Integer> res = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
			.stream()
			.map(x -> x * x)
			.filter(x -> x > 50)
			.findFirst();
		System.out.println("First square number > 50, -1 otherwise: " + res.orElse(-1));
		
		/* Date/Time */
		System.out.println(new Date().toString()); // Java 7
		System.out.println(Clock.systemUTC().instant()); // Java 8
		System.out.println(LocalDate.now() + " " + LocalTime.now()); // Java 8
		
		/* Default & Static methods */
		
	}

	interface Util {
		boolean isOdd(int x);
	}
	
	<T> Function<T, T> applyTwice(Function<T, T> f) {
		return x -> f.apply(f.apply(x));
	}
	
	@Target( { ElementType.TYPE_USE, ElementType.TYPE_PARAMETER } )
	@interface MyAnno {}
	
	public static void main(String[] args) {
		Lambdas l = new Lambdas();
		l.OnTest();
	}

}
