/open Protocol.java
/open ImList.java
/open Term.java
/open Host.java
/open TermImpl.java
/open Pager.java
/open RcvTerm.java
/open ComHost.java
/open Transmitter.java
/open AckHost.java
/open SndHost.java

Pager p1 = new Pager("pager1")
Transmitter r1 = new Transmitter("transmit1")
p1.snd(r1)
p1.snd(r1).rcv().ack()
Pager p2 = new Pager("pager2")
Host h1 = p2.snd(p1.snd(r1).rcv().ack()).rcv().ack()
Host h2 = p2.snd(r1).rcv().ack()
h1
h2
h1.getClass()
h2.getClass()
System.out.println("h1h2broadcast")
h1.broadcast()
h2.broadcast()
System.out.println("next section")


p1.snd(r1).broadcast()
System.out.println("test1")
p1.snd(r1).rcv().ack().broadcast()
System.out.println("test2")
p2.snd(p1.snd(r1).rcv().ack()).broadcast()
System.out.println("before error")
p2.snd(p1.snd(r1)).rcv().ack()
