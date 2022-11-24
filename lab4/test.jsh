/open Service.java

/open CabType.java

/open JustRide.java

/open Request.java

/open TakeACab.java

/open NormalCab.java

/open ImList.java

/open Booking.java

new JustRide();

new JustRide().computeFare(20, 3, 1000);

new JustRide().computeFare(10, 1, 900);

new Request(20, 3, 1000);

new Request(20, 3, 1000).computeFare(new JustRide());

new Request(10, 1, 900);

new Request(10, 1, 900).computeFare(new JustRide());

new TakeACab();

new Request(20, 3, 1000).computeFare(new TakeACab());

new Request(10, 1, 900).computeFare(new TakeACab());

new NormalCab("SHA1234", 5);

new Booking(new NormalCab("SHA1234", 5), new Request(20, 3, 1000));


