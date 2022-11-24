class Serve implements Event {
    private final Customer newCustomer;
    private final Server server;
    private final int cusNumber;
    private static final int PRIOR = 4;
  

    Serve(Customer newCustomer, Server server, int cusNumber) {
        this.newCustomer = newCustomer;
        this.server = server;
        this.cusNumber = cusNumber;
    }

    public Event execute() {
        //System.out.println(this.toString());
        Done done = new Done(this.newCustomer, this.server, this.cusNumber);
        return done;
    }
    
    //do not need execute because these are the terminating event
    @Override
    public String toString() { 
        return this.getTime() + " customer " + cusNumber + " served by " + server.toString();
    }

    public String getEvent() {
        return "Serve";
    }

    public double getTime() {
        return newCustomer.getArrival();
    }

    public boolean hasNextEvent() {
        return true;
    }

    public int getCusNumber() {
        return cusNumber;
    }
    
    public int getPriorityInt() {
        return PRIOR;
    }

}
