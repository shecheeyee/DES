import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

class Main {

    public static boolean isPrime(int n) {
        return n > 1 && IntStream
            .range(2,n)
            .noneMatch(x -> n % x == 0);
    }

    public static IntStream factors(int x) {
        return IntStream
            .rangeClosed(1, x)
            .filter(y -> isPrime(y));
    }

    public static IntStream twinPrimes(int n) {
        int[] intArray = factors(n).toArray();
        return IntStream.range(0, intArray.length)
            .filter(i -> (isPrime(intArray[i] - 2)) || (isPrime(intArray[i] + 2)))
            .map(i -> intArray[i])
            .distinct();
    }

    public static String reverse(String str) {
        return Stream.<String>of(str.split(""))
            .reduce("", (a, b) -> b + a, (y,z) -> z + y);
    }

    public static long countRepeats(List<Integer> list) {
        Stream stream = list.stream();
        int[] intArray = list.stream().mapToInt(x -> x).toArray();
        int length = intArray.length;
        long repeats = IntStream.range(1, intArray.length - 1)
            .filter(i -> (intArray[i - 1] == intArray[i]) && (intArray[i] != intArray[i + 1]))
            .count();
        if (intArray[length - 1] == intArray[length - 2]) {
            repeats++;
        }
        return repeats;
    }

    public static UnaryOperator<List<Integer>> generateRule() {
        return l -> IntStream
            .range(0, l.size())
            .map(i -> {
                if (l.get(i) == 0) {
                    if (i == 0) {
                        return l.get(i + 1);
                    } else {
                        if (i == l.size() - 1) {
                            return l.get(i - 1);
                        } else {
                            if (l.get(i - 1) == l.get(i + 1)) {
                                return 0;
                            } else {
                                return 1;
                            }
                        }
                    }
                } else {
                    return 0;
                }
            }
            )
            .boxed()
            .collect(Collectors.toList());
    }

    static Stream<String> gameOfLife(List<Integer> list, UnaryOperator<List<Integer>> rule, int n) {
        return Stream
            .iterate(list, rule)
            .limit(n)
            .map(x -> x.stream()
                    .map(y -> {
                        if (y == 0) {
                            return ".";
                        } else {
                            return "x";
                        }
                    })
                    .reduce("", (p, q) -> (p + q))
                );
    }
    
}
