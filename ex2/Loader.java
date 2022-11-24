class Loader {
    private final int identifier;
    private final int numOfLoaders;

    Loader(int numOfLoaders) {
        this.numOfLoaders = numOfLoaders;
        this.identifier = 1;
    }

    Loader(int numOfLoaders, int identifier) {
        this.identifier = identifier;
        this.numOfLoaders = numOfLoaders;
    }

    public Loader nextLoader() {
        int num = this.identifier;
        if (num >= numOfLoaders) {
            return new Loader(this.numOfLoaders, 1);
        } 
        num++;
        return new Loader(this.numOfLoaders, num);
    }

    @Override
    public String toString() {
        return "Loader #" + this.identifier;
    }
}
