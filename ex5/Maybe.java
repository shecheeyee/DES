import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.lang.Runnable;

class Maybe<T> {
    private final T thing;

    protected Maybe(T thing) {
        this.thing = thing;
    }

    static <U> Maybe<U> of(U thing) {
        if (thing == null) {
            throw new NullPointerException();
        }
        return new Maybe<U>(thing);
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        if (this.thing == null) {
            return Maybe.<R>empty();
        } else {
            return Maybe.<R>of(mapper.apply(this.thing));
        }
    }

    @Override
    public String toString() {
        if (this.thing == null) {
            return "Maybe.empty";
        } else {
            return "Maybe[" + this.thing + "]";
        }
    }

    public static <T> Maybe<T> ofNullable(T thing) {
        if (thing == null) {
            return Maybe.<T>empty();
        } else {
            return new Maybe<T>(thing);
        }
    }


    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Maybe<?>) {
            Maybe<?> newObj = (Maybe<?>) obj;
            if ((this.thing == null) && (newObj.thing == null)) {
                return true;
            } else if ((this.thing != null) && (newObj.thing != null)) {
                return this.thing.equals(newObj.thing);
            } else {
                return false;
            }
        }
        return false;
    }

    public Maybe<T> filter(Predicate<? super T> predicate) {
        if (this.thing == null) {
            return Maybe.<T>empty();
        } else if (predicate.test(this.thing)) {
            return this;
        } else {
            return Maybe.<T>empty();
        }
    }
   
    public void ifPresent(Consumer<? super T> consumer) {
        if (this.thing != null) {
            consumer.accept(this.thing);
        } 
    }

    public void ifPresentOrElse(Consumer<? super T> consumer, Runnable action) {
        if (this.thing != null) {
            consumer.accept(this.thing);
        } else {
            action.run();
        }
    }

    public T orElse(T other) {
        if (this.thing == null) {
            return other;
        } else {
            return this.thing;
        }
    }

    public T orElseGet(Supplier<? extends T> other) {
        if (this.thing == null) {
            return other.get();
        } else {
            return this.thing;
        }
    }

    public Maybe<T> or(Supplier<? extends Maybe<? extends T>> supplier) {
        if (this.thing == null) {
            T res = supplier.get().orElseGet(null);
            return Maybe.of(res);
        } else { 
            return this;
        }
    }

    public <U> Maybe<U> flatMap(Function<? super T, ? extends Maybe<? extends U>> mapper) {
        if (this.thing == null) {
            return Maybe.<U>empty();
        } else {
            return Maybe.<U>of(mapper.apply(this.thing).orElse(null));
        }
    }



}

