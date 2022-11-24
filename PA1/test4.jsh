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
p1.snd(r1).rcv().ack()
p1.snd(r1).rcv().equals(p1)
p1.ack()
p1.snd(r1).rcv().ack().equals(r1)
p1.snd(r1).rcv().ack().equals(p1.snd(r1))

