/open Service.java
/open Driver.java
/open JustRide.java
/open Request.java
/open TakeACab.java
/open ShareARide.java
/open NormalCab.java
/open PrivateCar.java
/open ImList.java
/open Booking.java
/open BookOne.java
/open BookComparator.java
/open level5.jsh

new BookOne(new NormalCab("SHA1234", 5), new Request(10, 1, 900), new JustRide())

BookOne b1 = new BookOne(new PrivateCar("SMA7890", 5), new Request(10, 1, 900), new JustRide())

BookOne b2 = new BookOne(new PrivateCar("SMA7890", 6), new Request(10, 1, 900), new JustRide())

findBestBooking(new Request(20, 3, 1000),List.of(new NormalCab("SHA1234", 5), new PrivateCar("SMA7890", 10)))

findBestBooking(new Request(10, 1, 900),List.of(new NormalCab("SHA1234", 5), new PrivateCar("SMA7890", 10)))
