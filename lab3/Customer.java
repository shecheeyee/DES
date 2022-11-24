
class Customer {
    private final double arrivalTime;
    private final double serviceTime;

    Customer(double arrivalTime, double serviceTime) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }
    
    @Override
    public String toString() {   
        return " left";
    }
    
    double totalTime() {
        return this.arrivalTime + this.serviceTime;
    }

    double getArrival() {
        return this.arrivalTime; 
    }

}
