class Transmitter extends ComHost {

    Transmitter(String identifier) {
        super(identifier);
        //ImList<String> track = new ImList<String>().add(super.getId());
        //ImList<Protocol> prot = new ImList<Protocol>();
        //this.track = track;
        //this.prot = prot;
    }

    Transmitter(String identifier, ImList<String> track, ImList<String> prot) {
        super(identifier, track, prot);
    }
    
    public String getId() {
        return super.getId();
    }

    public String toString() {
        return super.toString();
    }

    public boolean equals(Host host) {
        return super.equals(host);
    }

}

