import java.util.function.Supplier;

class Customer {
    private final Pair<Double, Lazy<Double>> inputTimes;
    private final int id;

    Customer(Pair<Double, Lazy<Double>> inputTimes, int id) {
        this.inputTimes = inputTimes;
        this.id = id;
    }

    @Override
    public String toString() {
        return "" + this.id;
    }

    public double getArrival() {
        return this.inputTimes.first();
    }

    public double getService() {
        return (this.inputTimes.second()).get();
    }

    public int getId() {
        return this.id;
    }


}



