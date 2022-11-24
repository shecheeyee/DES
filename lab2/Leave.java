class Leave implements Event {
    private final Customer customer;
    private final Server server;
    private final int cusNumber;

    Leave(Customer customer, Server server, int cusNumber) {
        this.customer = customer;
        this.server = server;
        this.cusNumber = cusNumber;
    }

    public Event execute() {
        //System.out.println(this.toString());
        return this;
    }
   
    @Override
    public String toString() {
        return "customer " + cusNumber + " leaves";
    }
}
