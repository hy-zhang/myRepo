package query;

import java.util.List;

import trees.SybAlg;

public class SybAlgQuery implements SybAlg<String, String, String, String, String, String> {

	@Override
	public String C(List<String> depts) {
		String res = "";
		for (String i : depts) res += i + " ";
		return res;
	}

	@Override
	public String D(String name, String manager, List<String> subUnits) {
		String res = "";
		res += manager + " ";
		for (String i : subUnits) res += i + " ";
		return res;
	}

	@Override
	public String PU(String employee) {
		return employee;
	}

	@Override
	public String DU(String dept) {
		return dept;
	}

	@Override
	public String E(String p, String s) {
		return p + " " + s;
	}

	@Override
	public String P(String name, String address) {
		return name + " " + address;
	}

	@Override
	public String S(double salary) {
		return "";
	}

}
