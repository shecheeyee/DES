class Count<T> extends Aggregate{
   

    Count(int s, T t) {
        super(s, t);
    }

    //@Override
    //public String toString() {
    //    return "(" + this.count + ", " + this.t.toString() + ")";
    //}

    static <T> Count<T> of(T t) {

        return new Count<T>(1 , t);
    }

    Count<T> map(T t) {
        return new Count<T>(s, t);
    }

    
}




