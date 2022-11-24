/open Cruise.java
/open Loader.java
/open Service.java 
/open level4.jsh

List<Cruise> cruises = List.of(new Cruise("A1234", 0, 2, 30), new Cruise("A2345", 30, 2, 30), new Cruise("A3456", 130, 2, 30))
cruises

serveCruises(cruises, 3)

cruises = List.of(new Cruise("A1234", 0, 2, 30), new Cruise("A2345", 0, 2, 30), new Cruise("A3456", 0, 2, 30))
serveCruises(cruises, 3)

serveCruises(cruises, 1)
