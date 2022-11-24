class Statistics {
    private final double wait;
    private final double served;
    private final int left;

    Statistics(double wait, double served, int left) {
        this.wait = wait;
        this.served = served;
        this.left = left;
    }

    @Override
    public String toString() {
        return "[" + String.format("%.3f", this.wait / this.served) + " " + 
            (int) this.served + " " + this.left + "]";
    }

    public Statistics update(double time, double serve, int leave) {
        return new Statistics(this.wait + time, this.served + serve, this.left + leave);
    }

}
