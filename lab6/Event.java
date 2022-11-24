interface Event {
    
    public String toString();

    public Pair<Event, ImList<Server>> execute(ImList<Server> serverList);

    public double getTime();

    public String getEvent();

    public int getPriorityInt();

    public Customer getCus();

    public boolean hasNext();

    public Statistics getStats(Statistics stats);

    public int getQ();

    public Event update(double time);
}
