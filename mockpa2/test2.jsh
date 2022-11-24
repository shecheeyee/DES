/open Circle.java
/open Count.java
/open Aggregate.java

Aggregate.<Integer,Circle>seed(0)

Aggregate.<Integer,Circle>seed(10)

Aggregate.<Integer,Circle>seed(1).map(x -> x * 2, new Circle(2.0))
 
Count<String> coun = Count.<String>of("one").map("two").map("three")
 
coun

Aggregate<Integer,String> agg = coun.map("four")
  
agg 

coun
  
Aggregate.<Integer,Circle>seed(0).map(x -> x + 1, new Circle(1.0)).map(x -> x + 2, new Circle(2.0))
   
Aggregate.<Double,Circle>seed(0.0).map(x -> x + 1.0, new Circle(1.0)).
