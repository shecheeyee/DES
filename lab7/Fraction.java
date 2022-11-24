import java.util.Optional;

class Fraction extends AbstractNum<Frac> {

    private Fraction(Optional<Frac> opt) {
        super(opt.filter(x -> x.first().isValid()).filter(x -> x.second().isValid() 
                    && !x.second().equals(Num.zero())));
    }

    static Fraction of(int x, int y) {
        if (Num.of(y).equals(Num.zero()) || !Num.of(x).isValid() || !Num.of(y).isValid()) {
            return new Fraction(Optional.empty());
        } else {
            return new Fraction(Optional.of(Frac.of(Num.of(x), Num.of(y))));
        }
    }

    Fraction mul(Fraction other) {
//        Optional<Num> a = this.opt.flatMap(x -> Optional.of(x.first()));
//        Optional<Num> b = this.opt.flatMap(x -> Optional.of(x.second()));
//        Optional<Num> c = other.opt.flatMap(x -> Optional.of(x.first()));
//        Optional<Num> d = other.opt.flatMap(x -> Optional.of(x.second()));
//        Num top = new Num(a.flatMap(x -> c.map(y -> x.mul(y)))
//            .flatMap(y -> y.opt.map(x -> x)));
//        Num btm = new Num(b.flatMap(x -> d.map(y -> x.mul(y)))
//                .flatMap(y -> y.opt.map(x -> x)));
//        return new Fraction(Optional.of(Frac.of(top, btm)));
        Optional<Frac> frac = this.opt.flatMap(x -> {
            return other.opt.flatMap(y -> {
                Num a = x.first().mul(y.first());
                Num b = x.second().mul(y.second());
                return Optional.ofNullable(Frac.of(a,b));
            });
        });
        return new Fraction(frac);
    }

    Fraction add(Fraction other) {
        //Optional<Num> a = this.opt.flatMap(x -> Optional.of(x.first()));
        //Optional<Num> b = this.opt.flatMap(x -> Optional.of(x.second()));
        //Optional<Num> c = other.opt.flatMap(x -> Optional.of(x.first()));
        //Optional<Num> d = other.opt.flatMap(x -> Optional.of(x.second()));
        //Num ad = new Num(a.flatMap(x -> d.map(y -> x.mul(y)))
        //    .flatMap(y -> y.opt.map(x -> x)));
        //Num bc = new Num(b.flatMap(x -> c.map(y -> x.mul(y)))
        //        .flatMap(y -> y.opt.map(x -> x)));
        //Num bd = new Num(b.flatMap(x -> d.map(y -> x.mul(y)))
        //        .flatMap(y -> y.opt.map(x -> x)));
        //return new Fraction(Optional.of(Frac.of(ad.add(bc), bd)));
        Optional<Frac> frac = this.opt.flatMap(x -> {
            return other.opt.flatMap(y -> {
                Num a = (x.first().mul(y.second())).add(x.second().mul(y.first()));
                Num b = x.second().mul(y.second());
                return Optional.ofNullable(Frac.of(a,b));
            });
        });
        return new Fraction(frac);
    }

    Fraction sub(Fraction other) {
        Optional<Num> a = this.opt.flatMap(x -> Optional.of(x.first()));
        Optional<Num> b = this.opt.flatMap(x -> Optional.of(x.second()));
        Optional<Num> c = other.opt.flatMap(x -> Optional.of(x.first()));
        Optional<Num> d = other.opt.flatMap(x -> Optional.of(x.second()));
        Num ad = new Num(a.flatMap(x -> d.map(y -> x.mul(y)))
            .flatMap(y -> y.opt.map(x -> x)));
        Num bc = new Num(b.flatMap(x -> c.map(y -> x.mul(y)))
                .flatMap(y -> y.opt.map(x -> x)));
        Num bd = new Num(b.flatMap(x -> d.map(y -> x.mul(y)))
                .flatMap(y -> y.opt.map(x -> x)));
        Num top = new Num(ad.sub(bc).opt.filter(x -> AbstractNum.valid.test(x)));
        return new Fraction(Optional.ofNullable(Frac.of(top, bd)));
    }
        
            


 
}




