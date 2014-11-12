package query;

import trees.TreeAlg;

public class TreeAlgQuery implements TreeAlg<String> {

	@Override
	public String Leaf(int x) {
		return "" + x;
	}

	@Override
	public String Fork(int x, String l, String r) {
		return "" + x + " " + l + " " + r;
	}

}
