class Serve implements Event {
    private final Customer newCustomer;
    private final Server server;
    private final int cusNumber;

    Serve(Customer newCustomer, Server server, int cusNumber) {
        this.newCustomer = newCustomer;
        this.server = server;
        this.cusNumber = cusNumber;
    }

    public Event execute() {
        //System.out.println(this.toString()); 
        return this;
    }
    
    //do not need execute because these are the terminating event
    @Override
    public String toString() { 
        return "customer " + cusNumber + server.toString();
    }

}
