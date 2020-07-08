package OOD.FactoryDesignPattern;

public class ShapeFactory {
    public static Shape createShape(ShapeType type) {
        switch (type) {
            case TRIANGLE: return new Triangle();
            case CIRCLE: return new Circle();
            default: throw new IllegalArgumentException("Unsupported shape type.");
        }
    }
}
