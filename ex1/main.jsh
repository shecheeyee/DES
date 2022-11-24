double epsilon = 1e-15;

double distanceBetween(Point p, Point q) {
    double dx = p.getX() - q.getX();
    double dy = p.getY() - q.getY();

    return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
}

boolean circleContainsPoint(Circle c, Point p) {
    return distanceBetween(c.getC(), p) < c.getR() + epsilon;
}

Circle createUnitCircle(Point p, Point q) {
	Point mid = p.midPoint(q);
	double angle = p.angleTo(q);
	double distPM = p.distTo(mid);
	double d = Math.sqrt(1 - distPM * distPM);
	Point newCentre = mid.moveTo(angle + Math.PI / 2, d);
	double radius = 1;
	return new Circle(newCentre, radius);

}


int findMaxDiscCoverage(List<Point> points) {
    int maxDiscCoverage = 0;
    int numOfPoints = points.size();

    for (int i = 0; i < numOfPoints - 1; i++) {
        for (int j = i + 1; j < numOfPoints; j++) {
            Point p = points.get(i);
            Point q = points.get(j);
            double distPQ = distanceBetween(p, q);
            if (distPQ < 2.0 + epsilon && distPQ > 0) {
                Point midpoint = new Point((p.getX() + q.getX()) / 2, (p.getY() + q.getY()) / 2);
                double d = Math.sqrt(1.0 - Math.pow(distanceBetween(midpoint, p), 2));
                double theta = Math.atan2(q.getY() - p.getY(), q.getX() - p.getX());
                Circle c = new Circle(
                        new Point(midpoint.getX() + d * Math.cos(theta + Math.PI / 2),
                            midpoint.getY() + d * Math.sin(theta + Math.PI / 2)), 
                        1.0);

                int coverage = 0;
                for (Point point : points) {
                    if (circleContainsPoint(c, point)) {
                        coverage = coverage + 1;
                    }
                }
                if (coverage > maxDiscCoverage) {
                    maxDiscCoverage = coverage;
                }
            }
        }
    }
    return maxDiscCoverage;
}
