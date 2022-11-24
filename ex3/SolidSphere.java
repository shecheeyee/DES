class SolidSphere extends Sphere implements Solid {
    private final double density;

    SolidSphere(double radius, double density) {
        super(radius);
        this.density = density;
    }

    @Override
    public String toString() {
        return "solid-sphere" + " [" + String.format("%.2f", super.getR()) + "]"
            + " with a mass of " + String.format("%.2f", this.mass());
    }

    public double mass() {
        return new SolidImpl(this, this.density).mass(); 
    }


}
