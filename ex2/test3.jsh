/open Cruise.java
/open Loader.java
/open Service.java

new Service(new Loader(3), new Cruise("A1234", 0, 2, 30), 0)
new Service(new Loader(3).nextLoader(), new Cruise("A1234", 0, 2, 30), 60)
