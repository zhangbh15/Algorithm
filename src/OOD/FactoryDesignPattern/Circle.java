package OOD.FactoryDesignPattern;

public class Circle extends Shape {
    public Circle() {
        super("Circle");
    }

    @Override
    public void draw() {
        // draw a circle
        System.out.println("Draw: " + shapeName);
    }
}
