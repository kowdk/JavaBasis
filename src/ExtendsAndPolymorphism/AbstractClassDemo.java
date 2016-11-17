package ExtendsAndPolymorphism;

import java.awt.Canvas;
import java.util.List;

abstract class Shape
{
	public abstract void draw(Canvas c);

	public void draw(AbstractClassDemo abstractClassDemo) {
		// TODO Auto-generated method stub
		
	}
}

class Circle extends Shape
{

	@Override
	public void draw(Canvas c) {
		// TODO Auto-generated method stub
		System.out.println("circle on " + c);
	}
	
}

class Rectangle extends Shape
{

	@Override
	public void draw(Canvas c) {
		// TODO Auto-generated method stub
		System.out.println("rectangle on " + c);
	}
}

public class AbstractClassDemo {
	public void drawAll(List<? extends Shape> list)
	{
		for(Shape s : list)
		{			
			s.draw(this);
		}
	}
}