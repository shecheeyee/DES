class Robo extends Server {

    
    Robo(ImList<Customer> customers, int qmax, int qNum, boolean isFree, 
            double availTime, boolean rest, boolean selfCheck) {
        super(customers, 1, qNum, isFree, availTime, false, selfCheck); 
    }

    @Override
    public String toString() {
        return "i am robo";   
    }

}
