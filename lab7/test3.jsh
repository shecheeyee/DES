/open AbstractNum.java
/open Num.java

Num one = Num.one()
one
Num zero = Num.zero()
zero
Num invalid = Num.of(-1)
invalid
zero.sub(zero)

zero.sub(one)

zero.sub(invalid)

one.sub(zero)

one.sub(one)

one.sub(invalid)

invalid.sub(zero)

zero.mul(zero)

zero.mul(one)

zero.mul(invalid)

one.mul(zero)

one.mul(one)

one.mul(invalid)

invalid.mul(zero)

Num.of(2).mul(Num.of(3))
