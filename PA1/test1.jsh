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


TermImpl test = new TermImpl("test")
Pager p1 = new Pager("pager1")
Term t1 = p1
new Term()
p1 instanceof Pager
p1 instanceof Term
t1.equals(p1)
p1.equals(t1)
p1.equals(new Pager("pager1"))
t1.equals(new Pager("pager1"))
p1.equals("pager1")


