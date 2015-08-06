package mixin;

import lombok.Mixin;

@Mixin
public interface Point {
	int x();
	int y();
	void x(int x);
	void y(int y);
	Point withX(int x);
}
