package monoid;

import java.lang.Double;

import library.Monoid;

public class DoubleMonoid implements Monoid<Double>{
	public Double join(Double x, Double y){
		return x + y;
	}
    public Double empty(){
		return 0.0;
	}
}
