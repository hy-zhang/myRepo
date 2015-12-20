# Nested interfaces

## Sample code

```
interface A {
	interface S {
		interface Exp {}
	}
}

interface B extends A.S {
}

interface C {
	interface Exp {}
	interface W {}
	interface A {}
	interface MT extends A, B, W {
		interface WWS extends Exp {
			/* <->: Absolute path
			 * <?>: To be inferred
			 * 1. <->C.MT.Exp
			 * 	(1). Exp in body of <->C.MT
			 * 	(2). <->C.MT extends <?>A, <?>B => 
			 * 			C.A.Exp ? NO
			 * 			B.Exp   ? => <?>A.S.Exp => YES
			 * 			C.W.Exp ? NO
			 * 			... <- If > 1 Yes with different answers, ERROR ->
			 * 2. <->C.Exp
			 * 3. <->Exp
			*/
		}
	}
}
```