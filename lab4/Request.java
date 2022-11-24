
class Request {
    private final int dist;
    private final int numPassengers;
    private final int time;

    Request(int dist, int numPassengers, int time) {
        this.dist = dist;
        this.numPassengers = numPassengers;
        this.time = time;
    }

    public String toString() {
        return this.dist + "km for " + this.numPassengers + "pax @ " + this.time + "hrs";
    }

    public int computeFare(Service service) {
        return service.computeFare(this.dist, this.numPassengers, this.time);
    }


}
