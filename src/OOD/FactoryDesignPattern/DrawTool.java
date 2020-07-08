package OOD.FactoryDesignPattern;

public class DrawTool {
    public void draw(ShapeType type) {
        Shape shape = ShapeFactory.createShape(type);
        shape.draw();
    }

    public static void main(String[] args) {
        DrawTool tool = new DrawTool();
        tool.draw(ShapeType.CIRCLE);
        tool.draw(ShapeType.TRIANGLE);
    }
}
