import java.util.*;
import java.util.function.*;

public class PartIII {
	
	void OnTest() {
		
		List<Integer> list1 = Arrays.asList(1, 2, 3);		
		List<Integer> list2 = new ArrayList<Integer>();
		List<Integer> list3 = new ArrayList<Integer>();
		
		/* Two steps */
		list1.forEach(x -> list2.add(x * 2));
		list2.forEach(x -> list3.add(x + 1));
		System.out.println(list3);
		
		list2.clear();
		list3.clear();

		/* Combine two steps to one */
		list1.forEach(x -> list3.add(x * 2 + 1));
		System.out.println(list3);
		
		list2.clear();
		list3.clear();
		
		/* What if the two consumers are unreachable? */
		Consumer<Integer> c1 = x -> list2.add(x * 2);
		list1.forEach(c1);
		Consumer<Integer> c2 = x -> list3.add(x + 1);
		list2.forEach(c2);
		System.out.println(list3);
		
		/* It doesn't work. Only c2 can be applied to list1, and types must be the same. */
		list2.clear();
		list3.clear();
		list1.forEach(c1.andThen(c2));
		System.out.println(list3);
		
		/* What if we are given two functions instead of two consumers? */
		/* Trivial. Just compose them. */
		
		/* In fact, we don't want forEach() to be a terminal operation. */
		/* We have map() instead. */
		
		/* Streams: terminal & intermediate operations */
		Arrays.asList(3, 1, 4, 5, 2)
			.stream()
			.sorted((e1, e2) -> e1.compareTo(e2))
			.map(x -> x * 2)
			.filter(x -> x > 5)
			.forEach(x -> System.out.print(x + " "));
		
		/* Infinite streams: lazy evaluation */
		System.out.println(from(0).head());
		/* If we use lists to implement infinite streams, exception during runtime. (StackOverFlow) */
		/* Also wrong if supplier is customized. */
		/* Parallel streams: not so magic as expected, see the reference. */
		
	}
	
	MyStream<Integer> from(int n) {
		return new Cons<Integer>(n, () -> from(n + 1));
	}
	
	public static void main(String args[]) {
		PartIII p = new PartIII();
		p.OnTest();
	}
	
}

interface MyStream<T> {
	public T head();
	public MyStream<T> tail();
	public boolean isEmpty();
}

class Empty<T> implements MyStream<T> {
	public T head() { throw new UnsupportedOperationException("Empty"); }
	public MyStream<T> tail() { throw new UnsupportedOperationException("Empty"); }
	public boolean isEmpty() { return true; }
}

class Cons<T> implements MyStream<T> {
	private T head;
	private Supplier<MyStream<T>> tail;
	public Cons(T head, Supplier<MyStream<T>> tail) { this.head = head; this.tail = tail; }
	public T head() { return head; }
	public MyStream<T> tail() { return tail.get(); }
	public boolean isEmpty() { return false; }
}
