package com.zewei.annotation.processor;

import java.util.List;

import javax.lang.model.type.*;

public class SubstTransformTypeVisitor implements TypeVisitor<String, String[]> {
	
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
		String[] lListTypeArgs = new String[lTypeArgs.length];
		for (int i = 0; i < lTypeArgs.length; ++i){
			lListTypeArgs[i] = "java.util.List<" + lTypeArgs[i] + ">";
		}
		String argument = "";
		String returnValue = "";
		for (int i = 0; i < lp.size(); i++) {
			if (arrayContains(lListTypeArgs, lp.get(i).toString()) != -1) {
				argument += "List<Subst<A, B>>";
				returnValue += "substList(p" + i + ", acc)";
			} else if (arrayContains(lTypeArgs, lp.get(i).toString()) != -1) {
				argument += "Subst<A, B>";
				returnValue += "p" + i + ".subst(acc)";
			} else {
				argument += lp.get(i).toString();
				returnValue += "p" + i;
			}
			argument += " p" + i;
			if (i < lp.size() - 1) {
				argument += ", ";
				returnValue += ", ";
			}
		}
		String res = "\t@Override\n\tpublic Subst<A, B> " + p[0] + "(" + argument + ") {\n";
		res += "\t\treturn acc -> alg." + p[0] + "(" + returnValue + ");\n\t}\n\n";
		return res;
	}
	
	@Override
	public String visit(TypeMirror arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(TypeMirror arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitArray(ArrayType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitDeclared(DeclaredType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitError(ErrorType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitIntersection(IntersectionType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitNoType(NoType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitNull(NullType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitPrimitive(PrimitiveType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitTypeVariable(TypeVariable arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitUnion(UnionType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitUnknown(TypeMirror arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitWildcard(WildcardType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
