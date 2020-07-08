package OOD.FactoryDesignPattern;

public class Triangle extends Shape {
    public Triangle() {
        super("Triangle");
    }

    @Override
    public void draw() {
        // draw a triangle;
        System.out.println("Draw: " + shapeName);
    }
}
