package com.zewei.annotation.processor;

import java.util.List;

import javax.lang.model.type.*;

public class GeneralQueryTypeVisitor implements TypeVisitor<String, String[]> {
	
	int arrayContains(String[] ls, String s) {
		int i = 0;
		for (String ts: ls) {
			if (s.equals(ts)) return i;
			i++;
		}
		return -1;
	}
	
	@Override
	public String visitExecutable(ExecutableType t, String[] p) {
		List<? extends TypeMirror> lp = t.getParameterTypes();
		String[] lTypeArgs = p[1].split(",");
		int monoid = arrayContains(lTypeArgs, t.getReturnType().toString());
		String res = "\t@Override\n\tpublic A" + + arrayContains(lTypeArgs, t.getReturnType().toString()) + " ";
		res += p[0] + "(";
		for (int i = 0; i < lp.size(); i++) {
			String realType = lp.get(i).toString();
			for (int j = 0; j < lTypeArgs.length; j++) {
				if (lp.get(i).toString().contains("<" + lTypeArgs[j] + ">")) {
					realType = realType.replaceAll("<" + lTypeArgs[j] + ">", "<A" + j + ">");
					break;
				}
				if (lp.get(i).toString().equals(lTypeArgs[j])) {
					realType = "A" + j;
					break;
				}
			}
			res += realType;
			res += " p" + i;
			if (i < lp.size() - 1) res += ", ";
		}
		res += ") {\n";
		res += "\t\tA" + monoid + " res = m" + monoid + ".empty();\n";
		for (int i = 0; i < lp.size(); i++) {
			if (lp.get(i).toString().contains(lTypeArgs[monoid])) {
				res += "\t\tres = m" + monoid + ".join(res, ";
				if (lp.get(i).toString().contains("List")) res += "m" + monoid + ".fold(p" + i + ")";
				else res += "p" + i;
				res += ");\n";
			}
		}
		res += "\t\treturn res;\n";
		res += "\t}\n\n";
		return res;
	}

	@Override
	public String visit(TypeMirror t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(TypeMirror t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitArray(ArrayType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitDeclared(DeclaredType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitError(ErrorType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitIntersection(IntersectionType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitNoType(NoType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitNull(NullType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitPrimitive(PrimitiveType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitTypeVariable(TypeVariable t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitUnion(UnionType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitUnknown(TypeMirror t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitWildcard(WildcardType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

}
