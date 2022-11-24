import java.util.List;
import java.util.stream.Stream;
import java.util.Optional;

class Num extends AbstractNum<Integer> {

    Num(Optional<Integer> opt) {
        super(opt);
    }

    private Num(AbstractNum<Integer> num) {
        this(num.opt);
    }

    static Num zero() {
        return new Num(AbstractNum.zero());
    }

    static Num of(int i) {
        if (AbstractNum.valid.test(i)) {
            return new Num(Optional.of(i));
        } else {
            return new Num(Optional.empty());
        }
  
    }

    Num succ() {
        if (isValid()) {
            return new Num(opt.map(x -> AbstractNum.s.apply(x)));
        } else {
            return new Num(Optional.empty());
        }
    }

    static Num one() {
        return Num.zero().succ();
    }

    Num add(Num other) {
        if (other.isValid() && isValid()) {
            Num counter = Num.zero();
            Num origin = this;
            while (!counter.equals(other)) {
                origin = origin.succ();
                counter = counter.succ();
            }
            return origin;
        } else {
            return new Num(Optional.empty());
        }
    }

    Num sub(Num other) {
        if (other.isValid() && isValid()) {
            //Num counter = Num.zero();
            Num origin = new Num(other.opt.map(x -> AbstractNum.n.apply(x)));
            return new Num(origin.add(this).opt.filter(x -> AbstractNum.valid.test(x)));
        } else {
            return new Num(Optional.empty());
        }
    }

    Num mul(Num other) {
        if (other.isValid() && isValid()) {
            Num res = Num.zero();
            Num origin = this;
            Num counter = Num.zero();
            while (!counter.equals(other)) {
                res = res.add(origin);
                counter = counter.succ();
            }
            return res;

        } else {
            return new Num(Optional.empty());
        }
    }

}


