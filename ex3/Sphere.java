class Sphere implements Shape3D {
    private final double radius;
    private static final int FOUR = 4;
    private static final int THREE = 3;

    Sphere(double radius) {
        this.radius = radius;
    }

    @Override 
    public String toString() {
        return "sphere" + " [" + String.format("%.2f", this.radius) + "]"; 
    }

    public double volume() {
        double c = (double)FOUR / (double)THREE;
        return c * Math.PI * Math.pow(this.radius, THREE);
    }

    public double getR() {
        return this.radius;
    }
}
