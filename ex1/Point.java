import java.lang.Math;

class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return  "point (" + String.format("%.3f",getX()) +
            ", " + String.format("%.3f",getY()) + ")";
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Point midPoint(Point p) {
        double dx = (this.getX() + p.getX()) / 2;
        double dy = (this.getY() + p.getY()) / 2;
        return new Point(dx, dy);
    }

    double angleTo(Point p) {
        double theta = Math.atan2(p.getY() - this.getY(), p.getX() - this.getX());
        return theta;
    }

    Point moveTo(double theta, double d) {
        return new Point(this.getX() + d * Math.cos(theta), this.getY() + d * Math.sin(theta));
    }

    double distTo(Point otherpoint) {
        double dispX = this.getX() - otherpoint.getX();
        double dispY = this.getY() - otherpoint.getY();
        return Math.sqrt(dispX * dispX + dispY * dispY);
    }
}

