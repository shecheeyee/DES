import java.util.stream.IntStream;

class IntExpr extends AbstractIntExpr {
    private static final Operator<Integer> sub = Operator.<Integer>of((x, y) -> x - y, 4);
    private static final Operator<Integer> div = Operator.<Integer>of((x, y) -> x / y, 3);
    private static final Operator<Integer> exp = 
        Operator.of((x,y) -> IntStream.range(0,y).reduce(1, (m, n) -> m * x), 2);

    //IntExpr(int x) {
    //    super(x);
    //}

    IntExpr(Expr<Integer> expr) {
        super(expr);
    }


    static IntExpr of(int y) {
        return new IntExpr(Expr.<Integer>of(y));
    }

    IntExpr add(int y) {
        return new IntExpr(super.op(addition, y));
    }

    IntExpr mul(int y) {
        return new IntExpr(super.op(multiplication, y));
    }

    IntExpr sub(int y) {
        return new IntExpr(super.op(sub, y));
    }

    IntExpr div(int y) {
        return new IntExpr(super.op(div, y));
    }

    IntExpr exp(int y) {
        return new IntExpr(super.op(exp, y));
    }




}


