import java.util.function.Function;

class Aggregate<S,T> {
    protected final S s;
    protected final T t;


    Aggregate(S s) {
        this.s = s;
        this.t = null;
    }

    Aggregate(S s, T t) {
        this.s = s;
        this.t = t;
    }


    static <S,T> Aggregate<S,T> seed(S s) {
        return new Aggregate<S,T>(s);
    }

    Aggregate<S,T> map(Function<? super S, ? extends S> mapper, T t) {
        return new Aggregate<S,T>(mapper.apply(this.s), t);
    }


    @Override
    public String toString() {
        return "(" + this.s + ", " + this.t.toString() + ")";
    }


}
