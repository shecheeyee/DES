/open AbstractNum.java
/open Num.java

Num one = Num.one()

Num zero = Num.zero()

Num invalid = Num.of(-1)

zero.add(zero)

zero.add(one)

zero.add(invalid)

one.add(zero)

one.add(one)

one.add(invalid)

invalid.add(zero)

Num result = zero.add(one).add(zero).add(one)

result.add(result)

zero.add(invalid).add(zero).add(one)
