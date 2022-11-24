import java.lang.Comparable;

class Booking implements Comparable<Booking> {
    private final Driver driver;
    private final Request req;

    Booking(Driver driver, Request req) {
        this.driver = driver;
        this.req = req;
    }

    @Override  
    public int compareTo(Booking other) {
        if (this.fare(this.cheaper()) < other.fare(other.cheaper())) {
            return -1;
        } else if (this.fare(this.cheaper()) > other.fare(other.cheaper())) {
            return 1;
        } else {
            if (this.getWait() < other.getWait()) {
                return -1;
            } else if (this.getWait() > other.getWait()) {
                return 1;
            } 
            return 0;
        }
    }
    
    public Driver getCab() {
        return this.driver;
    }

    public Request getReq() {
        return this.req;
    }

    public String getCabName() {
        return this.driver.toString();
    }

    public double fare(Service service) {
        return this.req.computeFare(service);
    }

    public Service cheaper() {
        Service cheapest = this.driver.services().get(0);
        for (int i = 1;i < this.driver.services().size(); i++) {
            if (this.fare(this.driver.services().get(i)) < this.fare(cheapest)) {
                cheapest = this.driver.services().get(i);
            }
        } 
        return cheapest;
    } 

    public int getWait() {
        return this.driver.getWaiting();
    }
    
    public String eachService(Service service) {
        return "$" + String.format("%.2f", this.fare(service) / 100) +
            " using " + this.driver.toString() + " (" + service.getName() + ")";
    }

    @Override
    public String toString() {
        return "$" + String.format("%.2f", this.fare(this.cheaper()) / 100) + 
            " using " + this.driver.toString() + " (" + this.cheaper().getName() + ")";
    }
        
}
