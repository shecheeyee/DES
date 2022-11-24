/open Cruise.java
/open Loader.java
/open Service.java 
/open BigCruise.java
/open SmallCruise.java
/open level4.jsh

new SmallCruise("S0001", 0).getArrivalTime()


new SmallCruise("S0001", 0).getServiceTime()


new SmallCruise("S0001", 0).getNumOfLoadersRequired()


Cruise cruise = new SmallCruise("S0123", 1220)
cruise = new BigCruise("B0001", 0, 70, 3000)
cruise.getArrivalTime()
cruise.getServiceTime()
cruise.getNumOfLoadersRequired()
List<Cruise> cruises = List.of(new SmallCruise("S1111", 1300))
serveCruises(cruises, 1)
cruises = List.of(new BigCruise("B1111", 1300, 80, 3000), new SmallCruise("S1111", 1359), new SmallCruise("S1112", 1500), new SmallCruise("S1113", 1529))


serveCruises(cruises, 3)


