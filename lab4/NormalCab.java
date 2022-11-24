
class NormalCab implements Driver {
    private final String plate;
    private final int waitingTime;
    private final ImList<Service> serviceList = 
        new ImList<Service>().add(new JustRide()).add(new TakeACab());

    NormalCab(String plate, int waitingTime) {
        this.plate = plate;
        this.waitingTime = waitingTime;
    }

    @Override
    public String toString() {
        return this.plate + " (" + this.waitingTime + " mins away) " + getName();
    }

    public String getName() {
        return "NormalCab";
    }

    public int getWaiting() {
        return this.waitingTime;
    }

    public ImList<Service> services() {
        return serviceList;
    }

}
