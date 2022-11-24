class BookOne extends Booking {

    private final Service service;

    BookOne(Driver driver, Request req, Service service) {
        super(driver, req);
        this.service = service;
    }
    
    @Override
    public String toString() {
        return "$" + String.format("%.2f", super.fare(this.service) / 100) 
            + " using " + super.getCabName() + " (" + this.service.getName() + ")";
    }

    public Service getService() {
        return this.service;
    }

    @Override
    public double fare(Service service) {
        return this.getReq().computeFare(service);
    }

    @Override
    public int getWait() {
        return this.getCab().getWaiting();
    }
}
          
