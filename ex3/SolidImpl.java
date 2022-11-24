class SolidImpl implements Shape3D, Solid {
    private final Shape3D shape;
    private final double density;

    SolidImpl(Shape3D shape, double density) {
        this.shape = shape;
        this.density = density;
    }

    @Override
    public String toString() {
        return "SolidImpl";
    }

    public double volume() {
        return shape.volume();
    }

    public double mass() {
        return this.volume() * this.density;
    }

 
}
