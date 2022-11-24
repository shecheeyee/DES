class Leave implements Event {
    private final Customer newCustomer;
    private final Server server;
    private final int cusNumber; 
    private static final int PRIOR = 3; 

    Leave(Customer newCustomer, Server server, int cusNumber) {
        this.newCustomer = newCustomer;
        this.server = server;
        this.cusNumber = cusNumber;
    }

    public Event execute() {
        //System.out.println(this.toString());
        return this;
    }
   
    @Override
    public String toString() {
        return this.getTime() + " customer " + cusNumber + " leaves";
    }

    public String getEvent() {
        return "Leave";
    }

    public double getTime() {
        return newCustomer.getArrival();
    }

    public boolean hasNextEvent() {
        return false;
    }

    public int getCusNumber() {
        return cusNumber;
    }

    public int getPriorityInt() {
        return PRIOR;
    }

}
