package com.zewei.annotation.processor;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.IntersectionType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.type.UnionType;
import javax.lang.model.type.WildcardType;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes(value={"com.zewei.annotation.processor.Transform"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class TransformProcessor extends AbstractProcessor{

	private Filer filer;
	
	@Override
	public void init(ProcessingEnvironment env){
		filer = env.getFiler();
	}
	
	String createGenericClass(String folder, String className, String typeArguments, Element element){
		return "package " + folder + ";\n"
				+ "import " + element.getEnclosingElement().getSimpleName() + "." + element.getSimpleName() + ";\n"
				+ "public interface G_" + className + "{\n" +
				"  " + typeArguments + " " + className + " accept(" + element.getSimpleName() + typeArguments + " alg);\n}";
	}
	
	String createTransformClass(String folder, Element element, String[] lTypeArgs, String typeArguments){
		List<? extends Element> le = element.getEnclosedElements();
		String className = element.getSimpleName() + "Transform";
		String classContent = "package " + folder + ";\n";
		classContent += "import " + element.getEnclosingElement().getSimpleName() + "." + element.getSimpleName() + ";\n";
		classContent += "import generic.*;\n";
		classContent += "public interface " + className + " extends " + element.getSimpleName() + "<";
		for (int i = 0; i < lTypeArgs.length; ++i){
			classContent += "G_" + lTypeArgs[i];
			if (i < lTypeArgs.length-1) classContent += ", ";
		}
		classContent += ">{\n";
				
		for (Element e: le){
			String[] args = {e.getSimpleName().toString(), typeArguments, element.getSimpleName().toString()};
			classContent += e.asType().accept(new ExecutableTypeVisitor(), args);
		}
		classContent += "}";
		return classContent;
	}
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment env) {
		String folder = null;
		String classContent = null;
		JavaFileObject jfo = null;
		for (Element element: env.getElementsAnnotatedWith(Transform.class)){
			if (element.getSimpleName().toString().startsWith("G_")){
				continue;
			}

			TypeMirror tm = element.asType();
			String message = tm.accept(new DeclaredTypeVisitor(), element);
			String typeArguments = "<" + message + ">";
			String[] lTypeArgs = toList(message);
			
			//create generic classes G_
			for (String s: lTypeArgs){
				folder = "generic";
				classContent = createGenericClass(folder, s, typeArguments, element);
				try{
					jfo = filer.createSourceFile(folder + "/" + "G_" + s, element);
					jfo.openWriter().append(classContent).close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
			
			//create transform classes _Transform
			folder = "transform";
			classContent = createTransformClass(folder, element, lTypeArgs, typeArguments);
			jfo = null;
			try{
				jfo = filer.createSourceFile(folder + "/" + element.getSimpleName()+"Transform", element);
				jfo.openWriter().append(classContent).close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
		return true;
	}
	
	private String[] toList(String message) {
		return message.split(",");
	}

	String toAnchorList(String message){
		int len = message.length();
		String res = "<" + message.substring(1, len-1)+">";
		return res;
	}
	
	@Override
	public SourceVersion getSupportedSourceVersion(){
		return SourceVersion.latestSupported();
	}


	public class DeclaredTypeVisitor implements TypeVisitor<String, Element>{

		@Override
		public String visitDeclared(DeclaredType t, Element p) {
			String res = t.getTypeArguments().toString().replace(" ", "");
			int len = res.length();
			res = res.substring(1, len-1);
			return res;
		}

		@Override
		public String visit(TypeMirror t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visit(TypeMirror t) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitPrimitive(PrimitiveType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitNull(NullType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitArray(ArrayType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitError(ErrorType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitTypeVariable(TypeVariable t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitWildcard(WildcardType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitNoType(NoType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitUnknown(TypeMirror t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitUnion(UnionType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitIntersection(IntersectionType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String visitExecutable(ExecutableType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


	}
	
	public class ExecutableTypeVisitor implements TypeVisitor<String, String[]>{
		@Override
		public String visitExecutable(ExecutableType t, String[] p) {
			List<? extends TypeMirror> lp = t.getParameterTypes();
			String[] lTypeArgs = p[1].substring(1, p[1].length()-1).split(",");
			String res = " @Override\n" 
						+ " default G_" + t.getReturnType() + " " + p[0] + "(";
			for (int i = 0; i < lp.size(); ++i){
				boolean flag = false;
				if (p[1].contains(lp.get(i).toString())) res += "G_";
				else {
					for (String s: lTypeArgs){
						if (lp.get(i).toString().contains(s)){
							res += lp.get(i).toString().replace(s, "G_"+s);
							flag = true;
							break;
						}
					}
				}
				if (flag == false) res += lp.get(i).toString();
				res += " p" + i;
				if (i < lp.size()-1) res += ", ";
			}
			res += "){\n";
			res += "  return new G_" + t.getReturnType() + "(){\n"
					+ "   @Override\n"
					+ "   public " + p[1] + " " + t.getReturnType() + " accept(" + p[2] + p[1] + " alg){\n";
			
			for (int i = 0; i < lp.size(); ++i){
				for (String s: lTypeArgs){
					if (lp.get(i).toString().contains(s) && lp.get(i).toString().contains("java.util.List")){
						//dirty trick for lists
						res += "    java.util.List<" + s + "> gp" + i + " = new java.util.ArrayList<" + s + ">();\n";
						res += "    for (G_" + s + " s: p" + i + "){\n"
								+ "     gp" + i + ".add(s.accept(alg));\n"
								+ "    }\n";
						break;
					}
				}
			}
			res += "    return alg." + p[0] + "(";
			for (int i = 0; i < lp.size(); ++i){
				boolean flag = false;
				for (String s: lTypeArgs){
					if (lp.get(i).toString().contains(s) && lp.get(i).toString().contains("java.util.List")){
						res += "gp" + i;
						flag = true;
						break;
					}
				}
				if (flag == false) {
					res += "p" + i;
					if (p[1].contains(lp.get(i).toString())) res +=".accept(alg)";
				}
				if (i < lp.size()-1) res += ", ";
			}
			res += ");\n" 
					+"   }\n";
			res += "  };\n }\n";
			return res;
		}

		@Override
		public String visit(TypeMirror t, String[] p) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String visit(TypeMirror t) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String visitPrimitive(PrimitiveType t, String[] p) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String visitNull(NullType t, String[] p) {
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
		public String visitTypeVariable(TypeVariable t, String[] p) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String visitWildcard(WildcardType t, String[] p) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String visitNoType(NoType t, String[] p) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String visitUnknown(TypeMirror t, String[] p) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String visitUnion(UnionType t, String[] p) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String visitIntersection(IntersectionType t, String[] p) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
