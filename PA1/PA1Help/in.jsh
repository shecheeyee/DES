/open Fruit.java
/open Sweet.java
/open Apple.java
/open Body.java

Sweet sweet = new Apple("apple");
Fruit fruit = new Apple("apple");

sweet.equals(fruit)
fruit.equals(sweet)

Body body = new Body();
body.eat(sweet);
body.healthyEat(fruit);
body.eat(fruit);
body.healthyEat(sweet);
