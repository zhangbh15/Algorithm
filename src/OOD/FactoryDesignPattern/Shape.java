package OOD.FactoryDesignPattern;

public abstract class Shape {
    protected String shapeName;
    public Shape(String name) {
        this.shapeName = name;
    }
    public abstract void draw();
}
