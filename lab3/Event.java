interface Event {
   
    public Event execute(); //call event

    public String toString();

    public String getEvent();

    public double getTime();

    public int getPriorityInt();

    public boolean hasNextEvent();

    public int getCusNumber();
}


