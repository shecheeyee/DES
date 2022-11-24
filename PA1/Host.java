interface Host extends Protocol {

    public String toString();

    public boolean equals(Host host);

    public String getId();

    public void broadcast();
    //public ImList<String> getProt();
    //public Term ack();
}
