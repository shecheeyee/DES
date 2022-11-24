import java.lang.Comparable;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.Optional;

class Operator<T> implements Comparable<Operator<T>> {
    private final int prior;
    private final BinaryOperator<T> op;
    private final Supplier<BinaryOperator<T>> supp;

    Operator(BinaryOperator<T> op, int prior) {
        this.prior = prior;
        this.op = op;
        this.supp = () -> op;
    }

    Operator(Supplier<BinaryOperator<T>> supp, int prior) {
        this.supp = supp;
        this.prior = prior;
        this.op = supp.get();
    }

    private int getP() {
        return this.prior;
    } 


    public static <T> Operator<T> of(BinaryOperator<T> op, int t) {
        return new Operator<T>(op, t);
    }


    @Override
    public int compareTo(Operator<T> other) {
        if (this.getP() < other.getP()) {
            return -1;
        } else if (this.getP() > other.getP()) {
            return 1;
        } else {
            return 0;
        }
        //return this.getP() - (other.getP());
    }


    @Override
    public  String toString() {
        return "Operator of precedence " + this.prior;
    }


    T apply(T t1, T t2) {
        return this.supp.get().apply(t1, t2);
    }

}





