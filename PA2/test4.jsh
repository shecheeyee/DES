/open Operator.java
/open Expr.java
/open AbstractIntExpr.java
/open IntExpr.java

IntExpr.of(2).mul(3).add(4)
IntExpr.of(2).add(3).mul(4)
IntExpr.of(2).add(3).mul(4).mul(5)
IntExpr.of(2).add(3).mul(4).add(5)
IntExpr.of(2).mul(3).add(4).mul(5)
IntExpr.of(36).div(6).mul(3).add(2).exp(2).sub(8)
