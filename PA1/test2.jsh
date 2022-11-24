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
p1.snd(r1).equals(r1)
Host h1 = r1
new Host()
p1.snd(r1).equals(h1)
r1.equals(h1)
h1.equals(p1)

