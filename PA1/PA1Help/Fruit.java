abstract class Fruit {
    private final String name;

    Fruit(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fruit) {
            return (((Fruit)obj).name == this.name);
        } else {
            return false;
        }
    }

    public String toString() {
        return this.name;
    }
}
