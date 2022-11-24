import java.util.Optional;
import java.util.function.Supplier;

class Expr<T> {
    private final Supplier<T> t;
    private final Optional<Operator<T>> ope;
    private final Supplier<Optional<Expr<T>>> right;


    Expr(Supplier<T> t, Supplier<Optional<Expr<T>>> right, Optional<Operator<T>> ope) {
        this.t = t;
        this.right = right;
        this.ope = ope;
    }

    Expr(Expr<T> expr) {
        this(expr.t, expr.right, expr.ope);
    }


    T evaluate() {
        return this.ope.map(
                x -> x.apply(this.t.get(), this.right.get().map(y -> y.evaluate()).orElseThrow()))
            .orElseGet(this.t);
    }



    @Override
    public String toString() {
        return "(" + this.evaluate() + ")";
    }

   
    static <T> Expr<T> of(T i) {
        return new Expr<T>(() -> i, () -> Optional.empty(), Optional.empty());
    }


    Expr<T> op(Operator<T> oper, Expr<T> t) {
        return op(oper, () -> Optional.of(Expr.of(t.evaluate())));
    }

    Expr<T> op(Operator<T> oper, T t) {
        return op(oper,() -> Optional.of(Expr.of(t)));
    }

    Expr<T> op(Operator<T> oper, Supplier<Optional<Expr<T>>> i) {
        return this.ope.map(x -> x.compareTo(oper) <= 0 ?
                new Expr<T>(() -> this.evaluate(), i, Optional.of(oper)) :
                new Expr<T>(t, () -> right.get().map(y -> y.op(oper, i)), this.ope))
                .orElse(new Expr<T>(t, i, Optional.of(oper)));
    }

}


