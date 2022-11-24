/open Shape3D.java
/open Cuboid.java
/open Sphere.java
/open SolidCuboid.java
/open SolidSphere.java
/open SolidImpl.java

SolidImpl solid = new SolidImpl(new Sphere(1.0), 0.5)
solid.volume()
solid.mass()
Shape3D shape = solid
