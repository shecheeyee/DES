
class Server {
    private final String name;
    private final double finalTime;
    private final Customer customer;

    Server(String name, double finalTime, Customer customer) {
        this.name = name;
        this.finalTime = finalTime;
        this.customer = customer;
    }

    //Server updateTime() {
    //    this.finalTime = customer.totalTime();
    //    return new Server(this.name, this.finalTime, this.customer); 
    //}

    double getFinal() {
        return this.finalTime;
    }

    Server takeCus(Customer newCustomer) {
        return new Server(this.name, newCustomer.totalTime(), newCustomer);
    }
    
    @Override
    public String toString() {
        return this.name;
    }

}

