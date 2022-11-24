import java.util.function.*;
import java.util.*;

class IFL<T> {
    Supplier<Optional<T>> head;
    Supplier<IFL<T>> tail;
    boolean isEmptyList;

    IFL(Supplier<Optional<T>> head, Supplier<IFL<T>> tail, boolean isEmptyList) {
        this.head = head;
        this.tail = tail;
        this.isEmptyList = isEmptyList;
    }

    //Stream.generatre(() -> 1).map(...)
    //Stream.iterate
    static <T> IFL<T> generate(Supplier<Optional<T>> supplier) {
        Supplier<Optional<T>> newHead = supplier;
        Supplier<IFL<T>> newTail = () -> IFL.generate(supplier);

        return new IFL<T>(newHead, newTail, false);
    }

    static <T> IFL<T> iterate(T seed, UnaryOperator<T> next) {
        Supplier<Optional<T>> newHead = () -> Optional.of(seed);
        Supplier<IFL<T>> newTail = () -> 
            IFL.iterate(next.apply(seed), next);
        return new IFL<T>(newHead, newTail, false);
    }

    //IFL.iterate(1, x -> x + 1).limit(5).reduce(0, (x,y) -> x + y)
    <U> U reduce(U seed, BiFunction<U, ? super T, U> bif) {
        U result = seed;
        IFL<T> curr = this;
        while (!curr.isEmptyList) {
            //bif.apply(result, curr.head()); //doing a reduction
            U u = result; //each time its a new u, so its effectively final
            result = curr.head().map(x -> bif.apply(u, x)).orElse(result); //if it is empty, then it will just be result, then loop ends too

            //result keeps changing, hence it is not final/ effectively final
            curr = curr.tail();
        }
        return result;
    }

    <R> IFL<R> map(Function<? super T, ? extends R> mapper) {
        Supplier<Optional<R>> newHead = () -> this.head().map(mapper); //this.head now an optional
        Supplier<IFL<R>> newTail = () -> this.tail().map(mapper);

        return new IFL<R>(newHead, newTail, this.isEmptyList);
    }

    IFL<T> filter(Predicate<? super T> predicate) {
        Supplier<Optional<T>> newHead = () -> this.head().filter(predicate);
        Supplier<IFL<T>> newTail = () -> this.tail().filter(predicate);
        return new IFL<T>(newHead, newTail, this.isEmptyList);
        }

    //IFL.generate(() -> 1).head //gets the head
    Optional<T> head() {
        return this.head.get(); //lazyily evaulated
    }

    IFL<T> tail() {
        return this.tail.get();
    }

    void forEach(Consumer<? super T> consumer) {

        IFL<T> curr = this;
        while(!curr.isEmptyList) {
            curr.head().ifPresent(consumer);
            curr = curr.tail();
        
        }
    }


    IFL<T> limit(int n) {
        Supplier<Optional<T>> newHead = () -> this.head();
        Supplier<IFL<T>> newTail = () -> this.tail().limit(n - 1);
        return new IFL<T>(newHead, newTail, n <= 0);
    }

}


