interface Event {
    
    public String toString();

    public Pair<Event, Pair<ImList<Server>, SelfCheckOut>> execute(ImList<Server> serverList,
            SelfCheckOut autoCounter);

    public double getTime();

    public String getEvent();

    public int getPriorityInt();

    public Customer getCus();

    public boolean hasNext();

    public Statistics getStats(Statistics stats);

    public int getQ();

    public Event update(double time);

    public Event changeQ(int num);
}
