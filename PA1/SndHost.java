class SndHost implements Host {
    private final String identifier;
    private final ImList<String> track;
    private final ImList<String> prot;

    SndHost(String identifier) {
        this.identifier = identifier;
        ImList<String> track = new ImList<String>().add(this.identifier);
        ImList<String> prot = new ImList<String>();
        this.track = track;
        this.prot = prot;
    }

    SndHost(String identifier, ImList<String> track, ImList<String> prot) {
        this.identifier = identifier;
        this.track = track;
        this.prot = prot;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.track.size(); i++) { 
            if (i % 2 != 0) {
                str += " >--";
                str += this.track.get(i);
                str += "--> ";
            }
            else {
                str += this.track.get(i);
            }
        }
        return str;
    }
    @Override 
    public boolean equals(Host host) {
        if (this.identifier.equals(host.getId())) {
            return true;
        }
        return false;
    }

    public String getId() {
        return this.identifier;
    }

    public RcvTerm rcv() {
        ImList<String> temp = this.track;
        temp = temp.add("rcv");
        temp = temp.add(this.track.get(0));
        return new RcvTerm(temp.get(0), temp, this.prot);
    }

    public void broadcast() {
        String str;
        int x = this.prot.size();
        for (int i = x - 1; i >= 1 ; i--) {
            str = "";
            str += this.prot.get(i);
            str += ":beep";
            System.out.println(str);
        }
    }
}
