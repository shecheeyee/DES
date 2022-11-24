/open Solid.java
/open Shape3D.java
/open Cuboid.java
/open Sphere.java
/open SolidCuboid.java
/open SolidSphere.java
/open SolidImpl.java




SolidCuboid solidcuboid = new SolidCuboid(2.0, 2.0, 2.0, 0.5)

solidcuboid.volume()

solidcuboid.mass()

Cuboid cuboid = solidcuboid

solidcuboid.volume()

solidcuboid.mass()

Shape3D shape = solidcuboid

shape.volume()

shape.mass()


Solid solid = solidcuboid

solid.volume()
solid.mass()

shape = cuboid

shape = solid
