
class PrivateCar implements Driver {
    private final String plate;
    private final int waitingTime;
    private final ImList<Service> serviceList
        = new ImList<Service>().add(new JustRide()).add(new ShareARide());

    PrivateCar(String plate, int waitingTime) {
        this.plate = plate;
        this.waitingTime = waitingTime;
    }

    @Override
    public String toString() {
        return this.plate + " (" + this.waitingTime + " mins away) " + getName();
    }

    public String getName() {
        return "PrivateCar";
    }

    public int getWaiting() {
        return this.waitingTime;
    }

    public ImList<Service> services() {
        return serviceList;
    }



}
