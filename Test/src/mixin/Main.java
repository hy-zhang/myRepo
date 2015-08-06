package mixin;

public class Main {
	public static void main(String args[]) {
		Point p = Point.of(2, 3);
		System.out.println(p.x() + " " + p.y());
		p.x(6); p.y(7);
		System.out.println(p.x() + " " + p.y());
		Point q = p.withX(0);
		System.out.println(q.x() + " " + q.y());
	}
}
