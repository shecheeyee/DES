/open Circle.java
/open Count.java

Count.<Circle>of(new Circle(1.0))

Count.<Circle>of(new Circle(1.0)).map(new Circle(2.0))

Count<String> one = Count.<String>of("one")
one

one.map("two").map("three")

one
