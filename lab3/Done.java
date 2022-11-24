class Done implements Event {
    private final Server server;
    private final Customer newCustomer;
    private final int cusNumber;
    private static final int PRIOR = 1;
  


    Done(Customer newCustomer, Server server, int cusNumber) {
        this.server = server;
        this.newCustomer = newCustomer;
        this.cusNumber = cusNumber;
    }

    public Event execute() {
        return this;
    }

    @Override
    public String toString() {
        return this.getTime() + " customer " + cusNumber + " done serving by " + server.toString();
    }

    public String getEvent() {
        return "Done";
    }

    public double getTime() {
        return newCustomer.totalTime();
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
