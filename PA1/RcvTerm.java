class RcvTerm implements Term {

    private final String identifier;
    private final ImList<String> track;
    private final ImList<String> prot;

    RcvTerm(String identifier, ImList<String> track, ImList<String> prot) {
        this.identifier = identifier;
        this.track = track;
        this.prot = prot;
    }

    @Override
    public String toString(){
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
    public boolean equals(Term term) {
        if (this.getId().equals(term.getId())) {
            return true;
        }
        return false;
    }

    public String getId() {
        return this.identifier;
    }

    public AckHost ack() {
        ImList<String> temp = this.track;
        temp = temp.add("ack");
        temp = temp.add(temp.get(2));
        return new AckHost(temp.get(2), temp, this.prot);
    }
}

