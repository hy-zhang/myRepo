import java.util.function.*;

public class PartII {
	
	void OnTest() {
		
		/* Infer types */
		System.out.println(applyTwice((Integer x) -> x * 2).apply(5));
		System.out.println(applyTwice((Function<Integer, Integer>) x -> x * 2).apply(5));
		
		/* Haskell code */
		/* applyTwice f x = f $ f x */
		/* applyTwice (\x -> x * 2) 5 :: (Num t) => t */
	}
	
	<T> Function<T, T> applyTwice(Function<T, T> f) {
		return x -> f.apply(f.apply(x));
	}
	
	public static void main(String args[]) {
		PartII p = new PartII();
		p.OnTest();
	}
	
}
