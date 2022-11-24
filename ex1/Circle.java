class Circle {
    private final Point centre;
    private final double radius;
    private static final double EPSILON = 1e-15;

    Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public String toString() {
        return "circle of radius " + getR() + " centred at " + centre.toString();
    }

    public double getR() {
        return this.radius;
    }
    
    public Point getC() {
        return this.centre;
    }

    boolean contains(Point p) {
        if (getC().distTo(p) < getR() + EPSILON) {
            return true;
        } else {
            return false;
        }
    }
}
