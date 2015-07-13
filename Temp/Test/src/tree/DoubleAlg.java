package tree;

import com.zewei.annotation.processor.Algebra;

@Algebra
public interface DoubleAlg<Exp> {
	Exp Double(Exp e);
}
