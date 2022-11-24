class TermImpl implements Term {

    private final String identifier;
    private final ImList<String> track;
    private final ImList<String> prot;

    TermImpl(String identifier) {
        this.identifier = identifier;
        ImList<String> track = new ImList<String>().add(this.identifier);
        this.track = track;
        ImList<String> prot = new ImList<String>().add(this.identifier);
        this.prot = prot;
    }

    TermImpl(String identifier, ImList<String> track, ImList<String> prot) {
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
    public boolean equals(Term term) {
        if (this.identifier.equals(term.getId())) {
            return true;
        }
        return false;
    }

    public ImList<String> getTrack() {
        return this.track;
    }

    public ImList<String> getProt() {
        return this.prot;
    }

    public String getId() {
        return this.identifier;
    }
    public SndHost snd(ComHost host) {
        ImList<String> temp = this.track;
        temp = temp.add("snd");
        temp = temp.add(host.getId());
        ImList<String> tempTwo = this.prot;
        tempTwo = tempTwo.addAll(host.getProt());
        return new SndHost(host.getId(), temp, tempTwo);
    }

}
