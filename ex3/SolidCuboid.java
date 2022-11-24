class SolidCuboid extends Cuboid implements Solid {
    private final double density;

    SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
        this.density = density;
    }

    @Override
    public String toString() {
        return "solid-cuboid" + " [" + String.format("%.2f", super.getH()) 
            + " x " + String.format("%.2f", super.getW())
            + " x " + String.format("%.2f", super.getL()) + "]"
            + " with a mass of " + String.format("%.2f", this.mass());
    }

    public double mass() {
        return new SolidImpl(this, this.density).mass(); 
    }

}

